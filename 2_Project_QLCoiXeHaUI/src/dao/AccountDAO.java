package dao;

import database.DBConnect;
import model.Account;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {

    // Thuộc tính phục vụ cho hàm khởi tạo cũ (nếu các file cũ của bạn có gọi tới)
    private Connection conn;

    // Giữ lại hàm khởi tạo cũ để đảm bảo tính tương thích ngược, không làm lỗi file cũ
    public AccountDAO(Connection conn) {
        this.conn = conn;
    }

    /**
     * 1. Kiểm tra tài khoản đăng nhập hợp lệ (Admin / Staff)
     * Đã chuyển sang dạng static và sửa chuẩn cú pháp try-with-resources song song hai tài nguyên
     */
    public static Account login(String username, String password) {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";

        // Cấu trúc khai báo tài nguyên chuẩn: Các biến ngăn cách bằng dấu chấm phẩy (;), đóng ngoặc rồi mới mở {
        try (Connection connection = DBConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, username);
            stmt.setString(2, password);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String role = rs.getString("role");
                    return new Account(username, password, role);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 2. Thêm tài khoản nhân viên mới vào hệ thống bãi xe (Quyền Admin thực hiện)
     */
    public static boolean insertAccount(Account acc) {
        String sql = "INSERT INTO account (username, password, role) VALUES (?, ?, ?)";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, acc.getUsername());
            stmt.setString(2, acc.getPassword());
            stmt.setString(3, acc.getRole());

            return stmt.executeUpdate() > 0;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 3. Đổi mật khẩu tài khoản trực ca (Quyền Admin cấp lại hoặc người dùng tự đổi)
     */
    public static boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE account SET password = ? WHERE username = ?";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newPassword);
            stmt.setString(2, username);

            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    /**
     * 4. Lấy danh sách tất cả Username hiện có trong hệ thống
     * Hàm này dùng để nạp danh sách người dùng vào JComboBox hiển thị trên giao diện đổi mật khẩu
     */
    public static ArrayList<String> getAllUsernames() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT username FROM account ORDER BY role, username";

        try (Connection connection = DBConnect.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(rs.getString("username"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}