package dao;

import model.Account;
import java.sql.*;
import java.util.ArrayList;

public class AccountDAO {
    private Connection conn;

    public AccountDAO(Connection conn) {
        this.conn = conn;
    }

    // 1. Kiểm tra tài khoản hợp lệ (Hàm cũ của bạn)
    public Account login(String username, String password) {
        String sql = "SELECT * FROM account WHERE username = ? AND password = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String role = rs.getString("role");
                return new Account(username, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 2. Thêm tài khoản mới (Hàm cũ của bạn)
    public boolean insertAccount(Account acc) {
        String sql = "INSERT INTO account (username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, acc.getUsername());
            stmt.setString(2, acc.getPassword());
            stmt.setString(3, acc.getRole());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 3. ĐỔI MẬT KHẨU TÀI KHOẢN
    public boolean updatePassword(String username, String newPassword) {
        String sql = "UPDATE account SET password = ? WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, username);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // 4. LẤY DANH SÁCH TẤT CẢ USERNAME (Hàm mới thêm để nạp dữ liệu vào ComboBox)
    public ArrayList<String> getAllUsernames() {
        ArrayList<String> list = new ArrayList<>();
        String sql = "SELECT username FROM account ORDER BY role, username";
        try (PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                list.add(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}