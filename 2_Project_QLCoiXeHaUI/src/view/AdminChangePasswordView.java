package view;

import dao.AccountDAO;
import database.DBConnect;
import java.sql.Connection;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class AdminChangePasswordView extends javax.swing.JFrame {

    public AdminChangePasswordView() {
        initComponents();
        styleUI();
        setLocationRelativeTo(null);
        loadUsernames();
    }

    // Nạp tất cả tên tài khoản vào ComboBox
    private void loadUsernames() {
        cboUsers.removeAllItems();
        try (Connection conn = DBConnect.getConnection()) {
            AccountDAO dao = new AccountDAO(conn);
            ArrayList<String> usernames = dao.getAllUsernames();
            for (String user : usernames) {
                cboUsers.addItem(user);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cboUsers = new javax.swing.JComboBox<>();
        txtNewPass = new javax.swing.JPasswordField();
        txtConfirmPass = new javax.swing.JPasswordField();
        btnSave = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Admin - Change Account Password");
        getContentPane().setLayout(null);

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 24));
        jLabel1.setText("ĐỔI MẬT KHẨU TÀI KHOẢN");
        jLabel1.setBounds(70, 20, 320, 30);
        getContentPane().add(jLabel1);

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        jLabel2.setText("Chọn tài khoản:");
        jLabel2.setBounds(40, 80, 120, 30);
        getContentPane().add(jLabel2);

        jLabel3.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        jLabel3.setText("Mật khẩu mới:");
        jLabel3.setBounds(40, 130, 120, 30);
        getContentPane().add(jLabel3);

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        jLabel4.setText("Xác nhận lại:");
        jLabel4.setBounds(40, 180, 120, 30);
        getContentPane().add(jLabel4);

        cboUsers.setBounds(170, 80, 220, 30);
        getContentPane().add(cboUsers);

        txtNewPass.setBounds(170, 130, 220, 30);
        getContentPane().add(txtNewPass);

        txtConfirmPass.setBounds(170, 180, 220, 30);
        getContentPane().add(txtConfirmPass);

        btnSave.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        btnSave.setText("Cập nhật");
        btnSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaveActionPerformed(evt);
            }
        });
        btnSave.setBounds(60, 240, 140, 35);
        getContentPane().add(btnSave);

        btnBack.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        btnBack.setText("Quay lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });
        btnBack.setBounds(230, 240, 140, 35);
        getContentPane().add(btnBack);

        setSize(450, 340);
    }

    private void btnSaveActionPerformed(java.awt.event.ActionEvent evt) {
        String targetUser = (String) cboUsers.getSelectedItem();
        String newPass = new String(txtNewPass.getPassword());
        String confirmPass = new String(txtConfirmPass.getPassword());

        if (targetUser == null || newPass.isEmpty() || confirmPass.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin!", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        if (!newPass.equals(confirmPass)) {
            JOptionPane.showMessageDialog(this, "Mật khẩu xác nhận không trùng khớp!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn đổi mật khẩu của tài khoản [" + targetUser + "] không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try (Connection conn = DBConnect.getConnection()) {
            AccountDAO dao = new AccountDAO(conn);

            // Gọi hàm updatePassword đã viết ở lượt trước để ghi đè mật khẩu mới luôn
            if (dao.updatePassword(targetUser, newPass)) {
                JOptionPane.showMessageDialog(this, "Đã đổi mật khẩu tài khoản [" + targetUser + "] thành công!");
                txtNewPass.setText("");
                txtConfirmPass.setText("");
            } else {
                JOptionPane.showMessageDialog(this, "Đổi mật khẩu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Lỗi DB: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        new HomeAdmin().setVisible(true); // Luôn luôn quay về HomeAdmin
    }

    private void styleUI() {
        getContentPane().setBackground(new java.awt.Color(236, 240, 241));
        jLabel1.setForeground(new java.awt.Color(44, 62, 80));
        cboUsers.setBackground(java.awt.Color.WHITE);

        btnSave.setBackground(new java.awt.Color(46, 204, 113)); // Xanh lá
        btnSave.setForeground(java.awt.Color.WHITE);
        btnSave.setFocusPainted(false);
        btnSave.setBorderPainted(false);
        btnSave.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        btnBack.setBackground(new java.awt.Color(231, 76, 60)); // Đỏ
        btnBack.setForeground(java.awt.Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnSave;
    private javax.swing.JComboBox<String> cboUsers;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPasswordField txtConfirmPass;
    private javax.swing.JPasswordField txtNewPass;
}