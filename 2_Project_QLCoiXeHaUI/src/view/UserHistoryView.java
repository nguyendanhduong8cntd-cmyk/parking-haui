package view;

import dao.CheckoutDAO;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import model.Ticket;

public class UserHistoryView extends javax.swing.JFrame {

    private String userRole; // Lưu lại vai trò người dùng để quay lại đúng trang chủ

    public UserHistoryView(String role) {
        this.userRole = role;
        initComponents();
        styleUI();
        setLocationRelativeTo(null);
        loadHistoryData();
    }

    private void loadHistoryData() {
        DefaultTableModel model = (DefaultTableModel) tblHistory.getModel();
        model.setRowCount(0); // Làm sạch bảng trước khi đổ dữ liệu

        ArrayList<Ticket> historyList = CheckoutDAO.getAllHistory();
        for (Ticket t : historyList) {
            model.addRow(new Object[]{
                    t.getTicketId(),
                    t.getLicensePlate(),
                    t.getVehicleType(),
                    t.getDate(),
                    t.getCheckin(),
                    t.getCheckout() == null ? "In Parking" : t.getCheckout() // Nếu chưa ra thì hiện đang đỗ
            });
        }
    }

    private void initComponents() {
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHistory = new javax.swing.JTable();
        btnBack = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("User History Log");

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 26));
        jLabel1.setText("LỊCH SỬ NGƯỜI DÙNG BÃI XE");

        tblHistory.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {},
                new String [] { "Ticket ID", "License Plate", "Vehicle Type", "Date", "Check-in Time", "Check-out Time" }
        ));
        jScrollPane1.setViewportView(tblHistory);

        btnBack.setFont(new java.awt.Font("Helvetica Neue", 1, 14));
        btnBack.setText("Quay lại");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        // Định vị bố cục layout cơ bản
        getContentPane().setLayout(null);

        jLabel1.setBounds(190, 20, 420, 35);
        getContentPane().add(jLabel1);

        jScrollPane1.setBounds(40, 80, 720, 320);
        getContentPane().add(jScrollPane1);

        btnBack.setBounds(40, 420, 130, 35);
        getContentPane().add(btnBack);

        setSize(810, 520);
    }

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {
        this.dispose();
        // Kiểm tra vai trò để điều hướng quay về chính xác
        if ("Admin".equalsIgnoreCase(userRole)) {
            new HomeAdmin().setVisible(true);
        } else {
            new HomeStaff().setVisible(true);
        }
    }

    private void styleUI() {
        getContentPane().setBackground(new java.awt.Color(236, 240, 241));
        jLabel1.setForeground(new java.awt.Color(44, 62, 80));

        // Style Bảng lịch sử giống file Search/Revenue của bạn
        tblHistory.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
        tblHistory.setRowHeight(25);
        tblHistory.setGridColor(new java.awt.Color(189, 195, 199));
        tblHistory.setSelectionBackground(new java.awt.Color(52, 152, 219));
        tblHistory.setSelectionForeground(java.awt.Color.WHITE);

        javax.swing.table.JTableHeader header = tblHistory.getTableHeader();
        header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
        header.setBackground(new java.awt.Color(52, 73, 94));
        header.setForeground(java.awt.Color.WHITE);

        // Căn giữa chữ trên tiêu đề bảng
        javax.swing.table.DefaultTableCellRenderer renderer = (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
        renderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

        // Style nút Back màu xanh dương đồng bộ hệ thống
        btnBack.setBackground(new java.awt.Color(52, 152, 219));
        btnBack.setForeground(java.awt.Color.WHITE);
        btnBack.setFocusPainted(false);
        btnBack.setBorderPainted(false);
        btnBack.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
    }

    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblHistory;
}