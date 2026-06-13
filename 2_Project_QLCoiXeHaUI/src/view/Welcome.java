package view;

import javax.swing.*;
import java.awt.*;

public class Welcome extends javax.swing.JFrame {

    // Khai báo thủ công các thành phần giao diện để quản lý tập trung
    private javax.swing.JLabel lblTitle;
    private javax.swing.JButton btnAdmin;
    private javax.swing.JButton btnStaff;
    private javax.swing.JButton btnExit;
    private javax.swing.JPanel panelMainContainer; // Khối panel bọc toàn bộ nội dung

    public Welcome() {
        initComponentsCustom(); // Tự động dựng giao diện bằng code co giãn
        styleUI();              // Bơm màu sắc Flat UI hiện đại
        setLocationRelativeTo(null); // Hiển thị ở chính giữa màn hình khi khởi động
    }

    // Hàm thiết lập bố cục tự động co giãn thông minh khi phóng to/thu nhỏ màn hình
    private void initComponentsCustom() {
        lblTitle = new javax.swing.JLabel("HỆ THỐNG QUẢN LÝ BÃI XE KTPM04", javax.swing.SwingConstants.CENTER);
        btnAdmin = new javax.swing.JButton("ĐĂNG NHẬP ADMIN");
        btnStaff = new javax.swing.JButton("ĐĂNG NHẬP STAFF");
        btnExit = new javax.swing.JButton("THOÁT ỨNG DỤNG");
        panelMainContainer = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("E-Parking Haui - Welcome");

        // 1. Cửa sổ chính (JFrame) dùng GridBagLayout để ép khối nội dung luôn vào TÂM màn hình khi phóng to
        getContentPane().setLayout(new java.awt.GridBagLayout());
        getContentPane().setBackground(new java.awt.Color(245, 247, 250)); // Màu nền xám sáng dịu mắt

        // 2. Thiết lập khối panel trung tâm chứa các nút bấm (Sử dụng BoxLayout theo chiều dọc)
        panelMainContainer.setLayout(new javax.swing.BoxLayout(panelMainContainer, javax.swing.BoxLayout.Y_AXIS));
        panelMainContainer.setBackground(new java.awt.Color(245, 247, 250));

        // Căn giữa các thành phần bên trong Panel Container
        lblTitle.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnAdmin.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnStaff.setAlignmentX(Component.CENTER_ALIGNMENT);
        btnExit.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Thiết lập kích thước cố định cho các nút bấm thanh dài sang trọng (Kích thước chuẩn: 450x55)
        Dimension buttonSize = new Dimension(450, 55);
        btnAdmin.setMaximumSize(buttonSize); btnAdmin.setPreferredSize(buttonSize);
        btnStaff.setMaximumSize(buttonSize); btnStaff.setPreferredSize(buttonSize);
        btnExit.setMaximumSize(buttonSize);   btnExit.setPreferredSize(buttonSize);

        // Đổ các thành phần vào Panel và tạo khoảng cách đệm (Spacer) thoáng đãng giữa các nút
        panelMainContainer.add(lblTitle);
        panelMainContainer.add(Box.createRigidArea(new Dimension(0, 40)));  // Khoảng cách dưới tiêu đề
        panelMainContainer.add(btnAdmin);
        panelMainContainer.add(Box.createRigidArea(new Dimension(0, 20)));  // Khoảng cách giữa các nút
        panelMainContainer.add(btnStaff);
        panelMainContainer.add(Box.createRigidArea(new Dimension(0, 20)));
        panelMainContainer.add(btnExit);

        // 3. Ràng buộc GridBagConstraints để ép panelMainContainer luôn cố định ở trung tâm tuyệt đối
        java.awt.GridBagConstraints gbc = new java.awt.GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.fill = java.awt.GridBagConstraints.NONE; // Không kéo giãn méo mó khối Panel
        gbc.anchor = java.awt.GridBagConstraints.CENTER; // Giữ ở tâm khi bấm phóng to full màn hình

        getContentPane().add(panelMainContainer, gbc);

        // Gắn hành động chuyển cảnh cho các nút bấm
        btnAdmin.addActionListener(e -> btnAdminActionPerformed());
        btnStaff.addActionListener(e -> btnStaffActionPerformed());
        btnExit.addActionListener(e -> btnExitActionPerformed());

        setSize(800, 500); // Kích thước cửa sổ mặc định ban đầu cực kỳ cân đối
    }

    // Logic điều hướng chuyển cảnh sang các phân hệ đăng nhập tương ứng
    private void btnAdminActionPerformed() {
        this.dispose();
        new LoginAdmin().setVisible(true);
    }

    private void btnStaffActionPerformed() {
        this.dispose();
        new LoginStaff().setVisible(true);
    }

    private void btnExitActionPerformed() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Bạn có chắc chắn muốn thoát ứng dụng bãi xe không?",
                "Xác nhận thoát", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // Bơm màu sắc Flat UI đồng bộ chuẩn mỹ thuật công nghiệp
    private void styleUI() {
        // Định dạng tiêu đề chính
        lblTitle.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
        lblTitle.setForeground(new java.awt.Color(44, 62, 80)); // Màu xanh đen đậm đà sang trọng

        // Định dạng nút bấm Đăng nhập Admin (Tone màu xanh dương uy quyền)
        styleButton(btnAdmin, new java.awt.Color(52, 152, 219));

        // Định dạng nút bấm Đăng nhập Staff (Tone màu xanh lá cây an toàn)
        styleButton(btnStaff, new java.awt.Color(46, 204, 113));

        // Định dạng nút bấm Thoát ứng dụng (Tone màu đỏ san hô cảnh báo)
        styleButton(btnExit, new java.awt.Color(231, 76, 60));
    }

    // Hàm bổ trợ thiết lập thuộc tính đồ họa cho nút bấm Flat UI
    private void styleButton(javax.swing.JButton btn, java.awt.Color color) {
        btn.setBackground(color);
        btn.setForeground(java.awt.Color.WHITE); // Chữ trắng nổi bật trên nền màu
        btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 16)); // Font chữ nét đậm, kích cỡ 16 to rõ ràng
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR)); // Hiển thị hình bàn tay khi di chuột qua
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new Welcome().setVisible(true);
        });
    }
}