package view;

import dao.CheckoutDAO;
import database.DBConnect;
import model.Ticket;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Checkout extends javax.swing.JFrame {

    // Khai báo thủ công toàn bộ các thành phần giao diện
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheckout;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnImage;
    private javax.swing.JButton btnReset;
    private javax.swing.JComboBox<String> cboVehicleType;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel panelForm;
    private javax.swing.JPanel panelButtons;
    private javax.swing.JLabel lblImage;
    private javax.swing.JTextField txtCheckin;
    private javax.swing.JTextField txtCheckout;
    private javax.swing.JTextField txtDate;
    private javax.swing.JTextField txtLicensePlate;
    private javax.swing.JTextField txtTicketID;

    public Checkout() {
        initComponentsCustom(); // Tự động dựng giao diện bằng code
        styleUI();              // Bơm màu sắc Flat UI
        setLocationRelativeTo(null);
    }

    // Hàm thiết lập pixel và bố cục giao diện cố định bằng Code
    private void initComponentsCustom() {
        jLabel1 = new javax.swing.JLabel("CHECK OUT");
        panelForm = new javax.swing.JPanel();
        panelButtons = new javax.swing.JPanel();

        jLabel2 = new javax.swing.JLabel("Ticket ID");
        jLabel3 = new javax.swing.JLabel("License Plate");
        jLabel7 = new javax.swing.JLabel("Vehicle Type");
        jLabel4 = new javax.swing.JLabel("Date");
        jLabel5 = new javax.swing.JLabel("Check-in");
        jLabel6 = new javax.swing.JLabel("Check-out");

        txtTicketID = new javax.swing.JTextField();
        txtLicensePlate = new javax.swing.JTextField();
        cboVehicleType = new javax.swing.JComboBox<>(new String[] { "Car", "Motorbike" });
        txtDate = new javax.swing.JTextField();
        txtCheckin = new javax.swing.JTextField();
        txtCheckout = new javax.swing.JTextField();

        btnImage = new javax.swing.JButton("Image");
        lblImage = new javax.swing.JLabel();

        btnBack = new javax.swing.JButton("Back");
        btnCheckout = new javax.swing.JButton("Check-out");
        btnReset = new javax.swing.JButton("Reset");
        btnExit = new javax.swing.JButton("Exit");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("E-Parking Haui - Vehicle Check-out");
        getContentPane().setLayout(null);

        // Định vị Tiêu đề chính
        jLabel1.setBounds(297, 15, 200, 35);
        getContentPane().add(jLabel1);

        // Thiết lập vùng nhập liệu (panelForm)
        panelForm.setLayout(null);
        panelForm.setBounds(56, 65, 660, 270);

        jLabel2.setBounds(10, 10, 100, 30); panelForm.add(jLabel2);
        txtTicketID.setBounds(130, 10, 177, 30); panelForm.add(txtTicketID);

        jLabel3.setBounds(10, 55, 100, 30); panelForm.add(jLabel3);
        txtLicensePlate.setBounds(130, 55, 177, 30); panelForm.add(txtLicensePlate);
        btnImage.setBounds(320, 55, 80, 30); panelForm.add(btnImage);

        jLabel7.setBounds(10, 100, 100, 30); panelForm.add(jLabel7);
        cboVehicleType.setBounds(130, 100, 177, 30); panelForm.add(cboVehicleType);

        jLabel4.setBounds(10, 145, 100, 30); panelForm.add(jLabel4);
        txtDate.setBounds(130, 145, 177, 30); panelForm.add(txtDate);

        jLabel5.setBounds(10, 190, 100, 30); panelForm.add(jLabel5);
        txtCheckin.setBounds(130, 190, 177, 30); panelForm.add(txtCheckin);

        jLabel6.setBounds(10, 235, 100, 30); panelForm.add(jLabel6);
        txtCheckout.setBounds(130, 235, 177, 30); panelForm.add(txtCheckout);

        lblImage.setBounds(415, 10, 235, 255);
        panelForm.add(lblImage);

        getContentPane().add(panelForm);

        // Thiết lập vùng chứa các nút bấm (panelButtons)
        panelButtons.setLayout(null);
        panelButtons.setBounds(56, 350, 660, 50);

        btnBack.setBounds(0, 5, 130, 35); panelButtons.add(btnBack);
        btnCheckout.setBounds(160, 5, 130, 35); panelButtons.add(btnCheckout);
        btnReset.setBounds(320, 5, 130, 35); panelButtons.add(btnReset);
        btnExit.setBounds(480, 5, 130, 35); panelButtons.add(btnExit);

        getContentPane().add(panelButtons);

        // Gắn sự kiện lắng nghe khi gõ biển số xe trực tiếp bằng tay và ấn Enter
        txtLicensePlate.addActionListener(e -> {
            String plateInput = txtLicensePlate.getText().trim();
            if (!plateInput.isEmpty()) {
                fillInfo(plateInput);
            }
        });

        // Gắn hành động sự kiện cho các nút bấm
        btnBack.addActionListener(e -> btnBackActionPerformed());
        btnCheckout.addActionListener(e -> btnCheckoutActionPerformed());
        btnReset.addActionListener(e -> btnResetActionPerformed());
        btnExit.addActionListener(e -> btnExitActionPerformed());
        btnImage.addActionListener(e -> btnImageActionPerformed());

        setSize(780, 460);
    }

    private void displayImage(File file) {
        try {
            BufferedImage img = ImageIO.read(file);
            ImageIcon icon = new ImageIcon(img.getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH));
            lblImage.setIcon(icon);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void fillInfo(String plate) {
        Ticket ticket = CheckoutDAO.findByLicensePlate(plate);
        if (ticket != null) {
            txtLicensePlate.setText(ticket.getLicensePlate());
            txtTicketID.setText(ticket.getTicketId());
            cboVehicleType.setSelectedItem(ticket.getVehicleType());
            txtDate.setText(ticket.getDate());
            txtCheckin.setText(ticket.getCheckin());
            txtCheckout.setText(java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")));

            int totalFare = CheckoutDAO.getFareAmount(ticket.getVehicleType());

            JOptionPane.showMessageDialog(this,
                    "======= THÔNG TIN XE RA =======\n" +
                            "Loại xe: " + ticket.getVehicleType() + "\n" +
                            "Biển số: " + ticket.getLicensePlate() + "\n" +
                            "==============================\n" +
                            "💰 GIÁ VÉ DỰ KIẾN: " + totalFare + " VND",
                    "Hệ thống tính phí", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Không tìm thấy lượt xe nào của biển số [" + plate + "] đang ở trong bãi!", "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void btnBackActionPerformed() {
        this.dispose();
        new HomeStaff().setVisible(true);
    }

    private void btnCheckoutActionPerformed() {
        String ticketId = txtTicketID.getText().trim();
        String checkoutTime = txtCheckout.getText().trim();
        String vehicleType = (String) cboVehicleType.getSelectedItem();
        String licensePlate = txtLicensePlate.getText().trim();
        String checkinTime = txtCheckin.getText().trim();
        String date = txtDate.getText().trim();

        if (ticketId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Chưa nhận diện được dữ liệu xe để thanh toán!", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean updated = CheckoutDAO.updateCheckoutTime(ticketId, checkoutTime);
            if (updated) {
                int baseFare = CheckoutDAO.getFareAmount(vehicleType);
                int finalFare = baseFare;

                try {
                    // Tính toán số tiền nếu xe đỗ qua đêm
                    LocalDate dateIn = LocalDate.parse(txtDate.getText().trim());
                    LocalDate dateOut = LocalDate.now();
                    long daysBetween = ChronoUnit.DAYS.between(dateIn, dateOut);

                    if (daysBetween > 0) {
                        finalFare = baseFare * (int) (daysBetween + 1);
                    }
                } catch (Exception ex) {
                    System.out.println("Lỗi tính phí qua đêm, áp dụng giá mặc định 1 lượt.");
                }

                String receiptText =
                        "===================================\n" +
                                "        E-PARKING HAUI RECEIPT     \n" +
                                "===================================\n" +
                                " Ticket ID    : " + ticketId + "\n" +
                                " License Plate: " + licensePlate + "\n" +
                                " Vehicle Type : " + vehicleType + "\n" +
                                " Parking Date : " + date + "\n" +
                                " Time In      : " + checkinTime + "\n" +
                                " Time Out     : " + checkoutTime + "\n" +
                                "-----------------------------------\n" +
                                " TOTAL CASH   : " + finalFare + " VND\n" +
                                "===================================\n" +
                                "      THANK YOU! SEE YOU AGAIN!    \n";

                JOptionPane.showMessageDialog(this, receiptText, "Thanh toán thành công", JOptionPane.INFORMATION_MESSAGE);

                // TỰ ĐỘNG KHỞI TẠO VÀ LƯU HÓA ĐƠN VÀO THƯ MỤC "fordel"
                File dir = new File("fordel");
                if (!dir.exists()) {
                    dir.mkdirs();
                }

                File receiptFile = new File(dir, "Receipt_" + ticketId + ".txt");
                try (FileWriter writer = new FileWriter(receiptFile)) {
                    writer.write(receiptText);
                    System.out.println("Đã lưu file hóa đơn tại: " + receiptFile.getAbsolutePath());
                } catch (IOException ioEx) {
                    System.out.println("Lỗi ghi file hóa đơn vật lý.");
                    ioEx.printStackTrace();
                }

                btnResetActionPerformed();
            } else {
                JOptionPane.showMessageDialog(this, "Không thể cập nhật trạng thái vé: " + ticketId, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void btnResetActionPerformed() {
        txtTicketID.setText("");
        txtLicensePlate.setText("");
        txtDate.setText("");
        txtCheckin.setText("");
        txtCheckout.setText("");
        cboVehicleType.setSelectedIndex(0);
        lblImage.setIcon(null);
    }

    private void btnExitActionPerformed() {
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn thoát ứng dụng không?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void btnImageActionPerformed() {
        JFileChooser chooser = new JFileChooser(new File("src/licensePlate"));
        chooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh xe ra", "png", "jpg", "jpeg"));
        int result = chooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            displayImage(selectedFile);
            btnCheckout.setEnabled(true);
            txtLicensePlate.setText("Đang chạy phân tích OCR...");

            // Sử dụng SwingWorker chạy nền tác vụ OCR tránh treo đơ UI
            new javax.swing.SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    ITesseract instance = new Tesseract();
                    instance.setDatapath("./tessdata");
                    instance.setLanguage("eng");
                    String ocrRaw = instance.doOCR(selectedFile);
                    // Làm sạch chuỗi: Chỉ giữ lại chữ cái, số và dấu gạch ngang, lọc bỏ ký tự lạ
                    return ocrRaw.replaceAll("[^a-zA-Z0-9-]", "").trim();
                }

                @Override
                protected void done() {
                    try {
                        String plate = get();
                        fillInfo(plate);
                    } catch (Exception e) {
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(Checkout.this, "Hệ thống không nhận diện được chữ từ hình ảnh!");
                        txtLicensePlate.setText("");
                    } finally {
                        btnCheckout.setEnabled(true);
                    }
                }
            }.execute();
        }
    }

    private void styleUI() {
        // ===== BACKGROUND TOÀN BỘ CỬA SỔ =====
        getContentPane().setBackground(new java.awt.Color(236, 240, 241));
        panelForm.setBackground(new java.awt.Color(236, 240, 241));
        panelButtons.setBackground(new java.awt.Color(236, 240, 241));

        // ===== TIÊU ĐỀ CHÍNH (TITLE) =====
        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        jLabel1.setForeground(new java.awt.Color(44, 62, 80));

        // ===== ĐỊNH DẠNG CÁC NHÃN CHỮ (LABELS) =====
        for (Component c : panelForm.getComponents()) {
            if (c instanceof JLabel && c != lblImage) {
                c.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                ((JLabel) c).setForeground(new java.awt.Color(52, 73, 94));
            }
        }

        // ===== ĐỊNH DẠNG CÁC Ô NHẬP LIỆU (TEXTFIELDS) =====
        styleTextField(txtTicketID);
        styleTextField(txtLicensePlate);
        styleTextField(txtDate);
        styleTextField(txtCheckin);
        styleTextField(txtCheckout);

        // Khóa luồng nhập thô dữ liệu thời gian để chống gian lận chỉnh sửa giờ hệ thống
        txtTicketID.setEditable(false); txtTicketID.setBackground(new java.awt.Color(220, 225, 230));
        txtDate.setEditable(false); txtDate.setBackground(new java.awt.Color(220, 225, 230));
        txtCheckin.setEditable(false); txtCheckin.setBackground(new java.awt.Color(220, 225, 230));
        txtCheckout.setEditable(false); txtCheckout.setBackground(new java.awt.Color(220, 225, 230));

        // ===== ĐỊNH DẠNG COMBOBOX =====
        cboVehicleType.setFont(new java.awt.Font("Segoe UI", java.awt.Font.PLAIN, 14));
        cboVehicleType.setBackground(java.awt.Color.WHITE);
        cboVehicleType.setEnabled(false); // Khóa loại xe, bắt buộc khớp theo dữ liệu gốc lúc vào bãi

        // ===== ĐỊNH DẠNG KHUNG HIỂN THỊ ẢNH BIỂN SỐ =====
        lblImage.setBackground(java.awt.Color.WHITE);
        lblImage.setOpaque(true);
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 2));

        // ===== ĐỊNH DẠNG CÁC NÚT BẤM (BUTTONS) =====
        styleButton(btnCheckout, new java.awt.Color(52, 152, 219)); // Màu xanh dương thanh toán thương mại
        styleButton(btnImage, new java.awt.Color(52, 73, 94));     // Màu xanh thẫm chọn ảnh
        styleButton(btnBack, new java.awt.Color(155, 89, 182));    // Màu tím quay lại
        styleButton(btnReset, new java.awt.Color(241, 196, 15));   // Màu vàng reset
        styleButton(btnExit, new java.awt.Color(231, 76, 60));     // Màu đỏ thoát

        // ===== VIỀN PHÂN KHU (PANEL BORDERS) =====
        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199)),
                "Thông tin điều phối lượt xe ra bãi & Thanh toán", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
                javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 12)));
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
}