# 🧪 Lab08 – Kiểm thử phần mềm ATM

## 🎯 Mục tiêu
Thực hành viết Unit Test và Integration Test cho hệ thống ATM, bao gồm kiểm tra logic rút tiền và xác thực đăng nhập qua form HTML.

---

## 🛠️ Công nghệ sử dụng

- **Java 8**
- **JUnit 4.13.2** – kiểm thử đơn vị
- **Selenium 4.35.0** – kiểm thử tích hợp (form login)
- **Apache Maven** – quản lý dự án và chạy test

---

## 📁 Cấu trúc thư mục

```
lab08-testing/
├── pom.xml
├── index.html
├── src/
│   ├── main/java/com/huonggiang/lab08/
│   │   ├── ATM.java
│   │   ├── Account.java
│   │   ├── App.java
│   │   └── WithdrawModule.java
│   └── test/java/com/huonggiang/lab08/
│       ├── AppTest.java
│       ├── TestWithdraw.java
│       └── SeleniumTestLogin.java
```

---

## ✅ Unit Test – `TestWithdraw.java`

Kiểm thử các chức năng của lớp `Account` và `ATM`:

| Test Case               | Mô tả                                      |
|------------------------|---------------------------------------------|
| ✅ Rút tiền thành công   | Rút < số dư → trả về `true`, giảm số dư     |
| ❌ Rút tiền thất bại     | Rút > số dư → trả về `false`, không đổi     |
| ✅ Nạp tiền              | Gọi `deposit()` → tăng số dư                |
| ✅ Kiểm tra số dư        | Gọi `getBalance()` → đúng số dư             |
| ✅ Xác thực mã PIN       | Đúng PIN → `true`, sai PIN → `false`        |

---

## 🌐 Integration Test – `SeleniumTestLogin.java`

Kiểm thử form đăng nhập trong `index.html` bằng Selenium:

| Test Case                      | Mô tả                                      |
|-------------------------------|---------------------------------------------|
| ✅ Đăng nhập đúng              | `admin/admin123` → hiện "Welcome"           |
| ❌ Sai mật khẩu                | `admin/sai` → hiện "Invalid credentials"    |
| ❌ Bỏ trống username/password | → hiện "Please enter username and password" |

---

## ▶️ Hướng dẫn chạy test

```bash
# Di chuyển vào thư mục project
cd lab08-testing

# Chạy toàn bộ test
mvn clean test
```

> 💡 Đảm bảo đã cài ChromeDriver và chỉnh đúng đường dẫn file `index.html` trong `SeleniumTestLogin.java`

---

## 📸 Kết quả kiểm thử

- `unit_test_results.png` – ảnh chụp kết quả Unit Test
- `integration_test_results.png` – ảnh chụp kết quả Integration Test

---

## 🧑‍💻 Thông tin sinh viên

- **Họ tên:** Vương Thị Hương Giang  
- **MSSV:** N23DCPT017  
- **Lớp:** Nhập môn Công nghệ phần mềm 