package view;

import dao.AccountDAO;
import javax.swing.*;
import java.awt.*;

public class LoginStaff extends javax.swing.JFrame {

    // Khai báo thủ công toàn bộ các thành phần giao diện
    private javax.swing.JLabel lblTitle;
    private javax.swing.JLabel lblUsername;
    private javax.swing.JLabel lblPassword;
    private javax.swing.JTextField txtUsername;
    private javax.swing.JPasswordField txtPassword;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnExit;
    private javax.swing.JPanel panelLoginCard; // Khung hộp chứa form đăng nhập
    private javax.swing.JPanel panelButtons;   // Khung chứa các nút bấm

    public LoginStaff() {
        initComponentsCustom(); // Tự động dựng giao diện bằng code co giãn
        styleUI();              // Bơm màu sắc Flat UI (Tone Xanh Lá của Staff)
        setLocationRelativeTo(null); // Hiển thị ở tâm màn hình khi khởi động
    }

    // Hàm thiết lập bố cục tự động co giãn thông minh khi phóng to/thu nhỏ màn hình
    private void initComponentsCustom() {
        panelLoginCard = new javax.swing.JPanel();
        lblTitle = new javax.swing.JLabel("Đăng Nhập Staff", javax.swing.SwingConstants.CENTER);

        lblUsername = new javax.swing.JLabel("Tên đăng nhập:");
        lblPassword = new javax.swing.JLabel("Mật khẩu:");

        txtUsername = new javax.swing.JTextField();
        txtPassword = new javax.swing.JPasswordField();

        panelButtons = new javax.swing.JPanel();
        btnBack = new javax.swing.JButton("Quay lại");
        btnClear = new javax.swing.JButton("Xóa");
        btnLogin = new javax.swing.JButton("Đăng nhập");
        btnExit = new javax.swing.JButton("Thoát ứng dụng");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("E-Parking Haui - Staff Login");

        // 1. Dùng GridBagLayout cho cửa sổ chính để quản lý tỷ lệ co giãn của hộp trắng
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().setBackground(new java.awt.Color(245, 247, 250));

        // 2. Định hình khối hộp đăng nhập trắng (panelLoginCard)
        panelLoginCard.setLayout(new java.awt.GridBagLayout());
        panelLoginCard.setBackground(java.awt.Color.WHITE);
        panelLoginCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 230), 1),
                javax.swing.BorderFactory.createEmptyBorder(40, 60, 40, 60) // Tăng khoảng đệm bên trong hộp
        ));

        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.insets = new java.awt.Insets(15, 15, 15, 15); // Khoảng cách giữa các thành phần bên trong

        // --- HÀNG 0: Tiêu đề "Đăng Nhập Staff" ---
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL;
        panelLoginCard.add(lblTitle, gbc);

        // --- HÀNG 1: Ô nhập Tên đăng nhập ---
        gbc.gridwidth = 1;
        gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 1;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        panelLoginCard.add(lblUsername, gbc);

        gbc.weightx = 1.0; gbc.gridx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL; // Cho phép ô nhập liệu kéo dài ra
        txtUsername.setPreferredSize(new Dimension(280, 40));
        panelLoginCard.add(txtUsername, gbc);

        // --- HÀNG 2: Ô nhập Mật khẩu ---
        gbc.weightx = 0; gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = java.awt.GridBagConstraints.EAST;
        gbc.fill = java.awt.GridBagConstraints.NONE;
        panelLoginCard.add(lblPassword, gbc);

        gbc.weightx = 1.0; gbc.gridx = 1;
        gbc.fill = java.awt.GridBagConstraints.HORIZONTAL; // Cho phép ô mật khẩu kéo dài ra
        txtPassword.setPreferredSize(new Dimension(280, 40));
        panelLoginCard.add(txtPassword, gbc);

        // --- HÀNG 3: Cụm 4 nút bấm ---
        panelButtons.setLayout(new java.awt.GridLayout(2, 2, 20, 20)); // Tăng khoảng cách nút bấm cho thoáng
        panelButtons.setOpaque(false);
        panelButtons.add(btnBack);
        panelButtons.add(btnClear);
        panelButtons.add(btnLogin);
        panelButtons.add(btnExit);

        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Cho phép cụm nút co giãn theo chiều dọc
        gbc.fill = java.awt.GridBagConstraints.BOTH; // Kéo giãn cả chiều rộng và chiều cao nút khi phóng to
        panelButtons.setPreferredSize(new Dimension(400, 100));
        panelLoginCard.add(panelButtons, gbc);

        // 3. ĐĂNG KÝ CO GIÃN CHO HỘP TRẮNG ĐỐI VỚI CỬA SỔ CHÍNH
        java.awt.GridBagConstraints frameGbc = new java.awt.GridBagConstraints();
        frameGbc.gridx = 0; frameGbc.gridy = 0;

        // 🌟 ĐÃ SỬA: Bật fill = BOTH và cấp trọng số weight để cả cái hộp trắng tự phình to ra theo màn hình
        frameGbc.fill = java.awt.GridBagConstraints.BOTH;
        frameGbc.weightx = 1.0;
        frameGbc.weighty = 1.0;

        // Tạo khoảng lề (Margin) bao quanh hộp để khi phóng to full màn hình, hộp không bị đập sát vào viền màn hình máy tính
        frameGbc.insets = new java.awt.Insets(60, 100, 60, 100);

        getContentPane().add(panelLoginCard, frameGbc);

        // Gắn sự kiện hành động
        txtPassword.addActionListener(e -> btnLoginActionPerformed());
        btnBack.addActionListener(e -> btnBackActionPerformed());
        btnClear.addActionListener(e -> btnClearActionPerformed());
        btnLogin.addActionListener(e -> btnLoginActionPerformed());
        btnExit.addActionListener(e -> btnExitActionPerformed());

        setSize(850, 550);
    }

    private void btnBackActionPerformed() {
        this.dispose();
        new Welcome().setVisible(true); // Quay lại màn hình chào mừng chính
    }

    private void btnClearActionPerformed() {
        txtUsername.setText("");
        txtPassword.setText("");
        txtUsername.requestFocus(); // Đưa con trỏ chuột về lại ô tài khoản
    }

    private void btnLoginActionPerformed() {
        String username = txtUsername.getText().trim();
        String password = new String(txtPassword.getPassword()).trim();

        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ tài khoản và mật khẩu!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        try {
            // 1. Gọi hàm login static từ AccountDAO để lấy thực thể Account
            model.Account acc = dao.AccountDAO.login(username, password);

            // 2. Kiểm tra tài khoản có tồn tại và vai trò có phải là Staff hay không
            if (acc != null && "Staff".equalsIgnoreCase(acc.getRole())) {
                JOptionPane.showMessageDialog(this, "Log in successfully (STAFF)");
                this.dispose();
                new HomeStaff().setVisible(true); // Tiến vào màn hình làm việc của nhân viên
            } else {
                // Trường hợp trả về null hoặc role là Admin thì không cho vào phân hệ này
                JOptionPane.showMessageDialog(this, "Tài khoản, mật khẩu sai hoặc bạn không có quyền Staff!", "Đăng nhập thất bại", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi kết nối hệ thống: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnExitActionPerformed() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát ứng dụng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Bơm màu sắc Flat UI hiện đại, đồng bộ hóa mỹ thuật
    private void styleUI() {
        // Thiết lập nền trắng làm nổi bật khối hộp Đăng nhập (Login Card)
        panelLoginCard.setBackground(java.awt.Color.WHITE);
        panelButtons.setBackground(java.awt.Color.WHITE);

        // Bo viền nhẹ cho khung hộp đăng nhập để tăng chiều sâu đồ họa
        panelLoginCard.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 225, 230), 1),
                javax.swing.BorderFactory.createEmptyBorder(10, 10, 10, 10)
        ));

        // Thiết lập tiêu đề chữ in đậm kích thước lớn
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        lblTitle.setForeground(new java.awt.Color(44, 62, 80));

        // Thiết lập thuộc tính cho các nhãn chữ (Labels)
        lblUsername.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblUsername.setForeground(new java.awt.Color(52, 73, 94));
        lblPassword.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        lblPassword.setForeground(new java.awt.Color(52, 73, 94));

        // Style cho các ô nhập dữ liệu văn bản
        styleTextField(txtUsername);
        styleTextField(txtPassword);

        // Thiết lập hệ thống màu sắc nút bấm đồng bộ giao diện Flat UI
        styleButton(btnBack, new java.awt.Color(52, 152, 219));    // Màu xanh dương quay lại
        styleButton(btnClear, new java.awt.Color(241, 196, 15));   // Màu vàng reset xóa chữ
        styleButton(btnLogin, new java.awt.Color(46, 204, 113));   // Màu xanh lá cây thương hiệu Staff cực đẹp
        styleButton(btnExit, new java.awt.Color(231, 76, 60));     // Màu đỏ san hô thoát ứng dụng
    }

    private void styleButton(javax.swing.JButton btn, java.awt.Color color) {
        btn.setBackground(color);
        btn.setForeground(java.awt.Color.WHITE);
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private void styleTextField(javax.swing.JTextField txt) {
        txt.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        txt.setBackground(java.awt.Color.WHITE);
        txt.setBorder(javax.swing.BorderFactory.createCompoundBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199)),
                javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8)
        ));
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new LoginStaff().setVisible(true);
        });
    }
}