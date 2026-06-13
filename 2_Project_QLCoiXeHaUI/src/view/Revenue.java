
package view;

import dao.RevenueDAO;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Revenue extends javax.swing.JFrame {

    public Revenue() {
        initComponents();
        javax.swing.table.JTableHeader header = tblRevenue.getTableHeader();
    javax.swing.table.DefaultTableCellRenderer headerRenderer =
        (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    tblRevenue.setModel(new javax.swing.table.DefaultTableModel(
        new Object [][] {},
        new String [] {"Car numbers", "Motorbike numbers", "Car fare", "Motorbike fare", "Total revenue"}
    ));

    styleUI(); // 👈 THÊM DÒNG NÀY

    setLocationRelativeTo(null);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblRevenue = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        txtInput = new javax.swing.JTextField();
        cboType = new javax.swing.JComboBox<>();
        cboVehicles = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        btnCheck = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        btnBack = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btnChitiet = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tblRevenue.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Số lượng ô tô", "Số lượng xe máy", "Số tiền giảm giá ô tô", "Số tiền giảm giá xe máy", "Tổng doanh thu"
            }
        ));
        tblRevenue.setPreferredSize(new java.awt.Dimension(450, 80));
        jScrollPane1.setViewportView(tblRevenue);

        txtInput.setPreferredSize(new java.awt.Dimension(200, 35));

        cboType.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        cboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Date", "Month", "Year" }));
        cboType.setPreferredSize(new java.awt.Dimension(120, 35));

        cboVehicles.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        cboVehicles.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Car", "Motorbike" }));
        cboVehicles.setPreferredSize(new java.awt.Dimension(120, 35));
        cboVehicles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboVehiclesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cboType, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cboVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboVehicles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnCheck.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnCheck.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/check.png"))); // NOI18N
        btnCheck.setText("Kiểm tra");
        btnCheck.setPreferredSize(new java.awt.Dimension(130, 35));
        btnCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCheckActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/reset.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setPreferredSize(new java.awt.Dimension(130, 35));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnExit.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/exit.png"))); // NOI18N
        btnExit.setText("Thoát ứng dụng");
        btnExit.setPreferredSize(new java.awt.Dimension(130, 35));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });

        btnBack.setFont(new java.awt.Font("Helvetica Neue", 1, 14)); // NOI18N
        btnBack.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/back.png"))); // NOI18N
        btnBack.setText("Quay lại");
        btnBack.setPreferredSize(new java.awt.Dimension(130, 35));
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnExit, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCheck, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnExit, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 1, 26)); // NOI18N
        jLabel1.setText("Thống kê thu nhập");

        btnChitiet.setText("Bar Chart");
        btnChitiet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChitietActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 657, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(209, 209, 209)
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnChitiet))
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(btnChitiet, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCheckActionPerformed
        String timeType = cboType.getSelectedItem().toString();
        String input = txtInput.getText().trim();
        String vehicleFilter = cboVehicles.getSelectedItem().toString();

        try {
            ArrayList<Object[]> result = RevenueDAO.getRevenue(timeType, input, vehicleFilter);
            DefaultTableModel model = (DefaultTableModel) tblRevenue.getModel();
            model.setRowCount(0); 

            for (Object[] row : result) {
                model.addRow(row);
            }
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Unexpected Error: " + ex.getMessage(), "Message", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnCheckActionPerformed

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        this.dispose();
        new HomeAdmin().setVisible(true);
    }//GEN-LAST:event_btnBackActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        txtInput.setText(""); 

        DefaultTableModel model = (DefaultTableModel) tblRevenue.getModel();
        model.setRowCount(0);

        cboType.setSelectedIndex(0);
        cboVehicles.setSelectedIndex(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to exit the application?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0); 
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void cboVehiclesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboVehiclesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboVehiclesActionPerformed

    private void btnChitietActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChitietActionPerformed
        String input = txtInput.getText().trim();
    // Nếu ô nhập bị bỏ trống, nhắc nhở điền năm
    if (input.isEmpty()) {
        javax.swing.JOptionPane.showMessageDialog(this, "Vui lòng nhập năm (hoặc mm-yyyy) vào ô điều kiện!", "Thông báo", javax.swing.JOptionPane.WARNING_MESSAGE);
        return;
    }

    int namHopLe = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR); // Năm dự phòng mặc định
    try {
        if (input.contains("-")) {
            // Nếu nhập dạng "06-2026", cắt lấy chuỗi sau dấu gạch ngang làm năm
            String[] parts = input.split("-");
            namHopLe = Integer.parseInt(parts[parts.length - 1].trim());
        } else {
            // Nếu chỉ gõ thuần số năm như "2026"
            namHopLe = Integer.parseInt(input);
        }
    } catch (Exception e) {
        javax.swing.JOptionPane.showMessageDialog(this, "Định dạng năm nhập vào không đúng! Ví dụ đúng: 2026 hoặc 06-2026", "Lỗi định dạng", javax.swing.JOptionPane.ERROR_MESSAGE);
        return;
    }

    // Khởi tạo BarChart truyền chuẩn số năm đã bốc tách sang
    BarChart chartFrame = new BarChart(namHopLe);
    chartFrame.setVisible(true);
    }//GEN-LAST:event_btnChitietActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Revenue().setVisible(true);
            }
        });
    }
    private void styleUI() {
// ===== 1. CẤU HÌNH BACKGROUND & LAYOUT (CHỐNG BỊ CHE KHUẤT) =====
    java.awt.Color bgLight = new java.awt.Color(245, 247, 250); // Màu nền xám trắng cực sang của Modern UI
    getContentPane().setBackground(bgLight);
    jPanel1.setBackground(bgLight);
    jPanel2.setBackground(bgLight);

    // Ép chiều cao tối thiểu cho Khung cuộn chứa bảng (JScrollPane) để không bị GroupLayout bóp nghẹt
    jScrollPane1.setPreferredSize(new java.awt.Dimension(657, 280)); 
    jScrollPane1.setMinimumSize(new java.awt.Dimension(657, 200));
    jScrollPane1.setBackground(java.awt.Color.WHITE);
    jScrollPane1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(218, 223, 230), 1));

    // Bỏ thuộc tính kích thước cứng cũ của table để nó tự co giãn theo JScrollPane
    tblRevenue.setPreferredSize(null); 
    tblRevenue.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_ALL_COLUMNS);

    // ===== 2. TIÊU ĐỀ CHỮ (TITLE) =====
    jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 26));
    jLabel1.setForeground(new java.awt.Color(44, 62, 80)); // Màu Charcoal tối giản

    // ===== 3. Ô NHẬP LIỆU (INPUT FIELD) =====
    txtInput.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
    // Tạo Border có khoảng đệm (Padding) phía trong để gõ chữ không bị dính sát viền
    txtInput.setBorder(javax.swing.BorderFactory.createCompoundBorder(
        javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 1),
        javax.swing.BorderFactory.createEmptyBorder(0, 8, 0, 8)
    ));

    // ===== 4. COMBOBOX THỜI GIAN & PHƯƠNG TIỆN =====
    styleComboBox(cboType);
    styleComboBox(cboVehicles);

    // ===== 5. ĐỊNH DẠNG BẢNG DOANH THU (TABLE REVENUE) =====
    tblRevenue.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    tblRevenue.setRowHeight(32); // 👈 Tăng từ 25 lên 32px để dữ liệu thoáng, không bao giờ lo bị che chữ
    tblRevenue.setGridColor(new java.awt.Color(230, 233, 237)); // Đường lưới mảnh dịu mắt
    tblRevenue.setSelectionBackground(new java.awt.Color(41, 128, 185)); // Màu Classic Blue khớp với BarChart
    tblRevenue.setSelectionForeground(java.awt.Color.WHITE);
    tblRevenue.setShowGrid(true);

    // ĐỊNH DẠNG HEADER CỦA BẢNG
    javax.swing.table.JTableHeader header = tblRevenue.getTableHeader();
    header.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    header.setBackground(new java.awt.Color(52, 73, 94)); // Màu nền tiêu đề nam tính thanh lịch
    header.setForeground(java.awt.Color.WHITE);
    header.setPreferredSize(new java.awt.Dimension(header.getPreferredSize().width, 35)); // Tăng độ dày Header

    // Căn giữa chữ trên thanh Header
    javax.swing.table.DefaultTableCellRenderer headerRenderer =
        (javax.swing.table.DefaultTableCellRenderer) header.getDefaultRenderer();
    headerRenderer.setHorizontalAlignment(javax.swing.JLabel.CENTER);

    // ===== 6. THAY ĐỔI MÀU SẮC ĐỒNG BỘ CHO NÚT BẤM (BUTTONS) =====
    styleButton(btnCheck, new java.awt.Color(46, 204, 113));   // Xanh lá (Hoàn thành)
    styleButton(btnReset, new java.awt.Color(230, 126, 34));   // Cam Modern (Thay cho màu vàng chói mắt cũ)
    styleButton(btnBack, new java.awt.Color(41, 128, 185));    // Classic Blue (Đồng bộ với BarChart)
    styleButton(btnExit, new java.awt.Color(231, 76, 60));     // Đỏ Pastel (Cảnh báo)
    styleButton(btnChitiet, new java.awt.Color(142, 68, 173)); // Tím thời thượng cho nút Bar Chart

    // ===== 7. ĐƯỜNG VIỀN PANEL (TINH TẾ & FLAT) =====
    // Thay đổi border thô mặc định thành border phẳng màu xám nhạt kèm font Segoe UI mượt mà
    java.awt.Font borderFont = new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12);
    java.awt.Color borderLineColor = new java.awt.Color(218, 223, 230);

    jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(
        javax.swing.BorderFactory.createLineBorder(borderLineColor, 1), " Bộ lọc thống kê ", 
        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, borderFont, new java.awt.Color(127, 140, 141)
    ));
    
    jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(
        javax.swing.BorderFactory.createLineBorder(borderLineColor, 1), " Thao tác chức năng ", 
        javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, borderFont, new java.awt.Color(127, 140, 141)
    ));

    // ===== 8. TÍNH TOÁN LẠI TOÀN BỘ KHUNG GIAO DIỆN =====
    this.pack(); // Buộc Java Swing cập nhật lại đúng kích thước chuẩn sau khi style
    this.setLocationRelativeTo(null); // Giữa màn hình thẳng tiến
}
    private void styleButton(javax.swing.JButton btn, java.awt.Color color) {

    btn.setBackground(color);
    btn.setForeground(java.awt.Color.WHITE);
    btn.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
    btn.setFocusPainted(false);
    btn.setBorderPainted(false);
    btn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
}
    private void styleComboBox(javax.swing.JComboBox cb) {

    cb.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 13));
    cb.setBackground(java.awt.Color.WHITE);
}
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheck;
    private javax.swing.JButton btnChitiet;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cboType;
    private javax.swing.JComboBox<String> cboVehicles;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblRevenue;
    private javax.swing.JTextField txtInput;
    // End of variables declaration//GEN-END:variables
}
