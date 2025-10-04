public class TestWithdraw {
    public static void main(String[] args) {
        String cardNo = "1234567890";
        String pin = "1234";   // thay bằng pin thật trong DB

        if (WithdrawModule.verifyPin(cardNo, pin)) {
            System.out.println("✅ PIN correct!");
            WithdrawModule.withdraw(cardNo, 1000);
        } else {
            System.out.println("❌ Sai PIN hoặc thẻ không tồn tại");
        }
    }
}
