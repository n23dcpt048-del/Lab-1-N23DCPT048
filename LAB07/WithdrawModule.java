import java.sql.*;
import java.security.MessageDigest;

public class WithdrawModule {
    // Load driver 1 lần khi class được gọi
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("✅ MySQL JDBC Driver loaded");
        } catch (ClassNotFoundException e) {
            System.out.println("❌ Driver not found: " + e.getMessage());
        }
    }

    private static final String URL = "jdbc:mysql://localhost:3306/atm_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "huonggiang12345678";

    // Hàm băm SHA-256
    private static String sha256(String base) throws Exception {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(base.getBytes("UTF-8"));
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if(hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }
        return hexString.toString();
    }

    // Kiểm tra PIN
    public static boolean verifyPin(String cardNo, String pin) {
        String sql = "SELECT pin_hash FROM cards WHERE card_no=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, cardNo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                String pinHash = rs.getString("pin_hash");
                return pinHash.equals(sha256(pin));
            }
        } catch (Exception e) {
            e.printStackTrace(); // debug chi tiết
        }
        return false;
    }

    // Rút tiền
    public static void withdraw(String cardNo, double amount) {
        String selectSQL = "SELECT a.account_id, a.balance " +
                           "FROM accounts a JOIN cards c ON a.account_id=c.account_id " +
                           "WHERE c.card_no=? FOR UPDATE";
        String updateSQL = "UPDATE accounts SET balance=balance-? WHERE account_id=?";
        String insertSQL = "INSERT INTO transactions(account_id, card_no, atm_id, tx_type, amount, balance_after) " +
                           "VALUES(?, ?, 1, 'WITHDRAW', ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASS)) {
            conn.setAutoCommit(false);

            try (PreparedStatement ps1 = conn.prepareStatement(selectSQL);
                 PreparedStatement ps2 = conn.prepareStatement(updateSQL);
                 PreparedStatement ps3 = conn.prepareStatement(insertSQL)) {

                ps1.setString(1, cardNo);
                ResultSet rs = ps1.executeQuery();

                if (!rs.next()) {
                    throw new Exception("Card not found");
                }

                int accountId = rs.getInt("account_id");
                double balance = rs.getDouble("balance");

                if (balance < amount) {
                    throw new Exception("Insufficient funds");
                }

                // Update balance
                ps2.setDouble(1, amount);
                ps2.setInt(2, accountId);
                ps2.executeUpdate();

                // Insert transaction log
                ps3.setInt(1, accountId);
                ps3.setString(2, cardNo);
                ps3.setDouble(3, amount);
                ps3.setDouble(4, balance - amount);
                ps3.executeUpdate();

                conn.commit();
                System.out.println("✅ Withdraw success. New balance: " + (balance - amount));

            } catch (Exception e) {
                conn.rollback();
                System.out.println("❌ Error in transaction:");
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("❌ DB Connection Error:");
            e.printStackTrace();
        }
    }
}
