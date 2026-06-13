package view;

import dao.CheckinDAO;
import model.Ticket;
import net.sourceforge.tess4j.ITesseract;
import net.sourceforge.tess4j.Tesseract;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Checkin extends javax.swing.JFrame {

    // Khai báo thủ công toàn bộ các thành phần giao diện để quản lý tập trung
    private javax.swing.JButton btnBack;
    private javax.swing.JButton btnCheckin;
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

    public Checkin() {
        initComponentsCustom(); // Tự động dựng giao diện bằng code
        styleUI();              // Bơm màu sắc Flat UI
        setLocationRelativeTo(null);
    }

    // Hàm tự động tính toán pixel và dựng giao diện, GIỮ NGUYÊN BỐ CỤC TUYỆT ĐỐI CỦA BẠN
    private void initComponentsCustom() {
        jLabel1 = new javax.swing.JLabel("CHECK IN");
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
        btnCheckin = new javax.swing.JButton("Check-in");
        btnReset = new javax.swing.JButton("Reset");
        btnExit = new javax.swing.JButton("Exit");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("E-Parking Haui - Vehicle Check-in");
        getContentPane().setLayout(null);

        // Định vị Tiêu đề chính
        jLabel1.setBounds(297, 15, 200, 35);
        getContentPane().add(jLabel1);

        // Thiết lập vùng nhập liệu (panelForm) bằng Absolute Layout cố định
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
        txtCheckout.setEnabled(false);

        lblImage.setBounds(415, 10, 235, 255);
        panelForm.add(lblImage);

        getContentPane().add(panelForm);

        // Thiết lập vùng chứa các nút bấm (panelButtons)
        panelButtons.setLayout(null);
        panelButtons.setBounds(56, 350, 660, 50);

        btnBack.setBounds(0, 5, 130, 35); panelButtons.add(btnBack);
        btnCheckin.setBounds(160, 5, 130, 35); panelButtons.add(btnCheckin);
        btnReset.setBounds(320, 5, 130, 35); panelButtons.add(btnReset);
        btnExit.setBounds(480, 5, 130, 35); panelButtons.add(btnExit);

        getContentPane().add(panelButtons);

        // Hành động các nút bấm
        btnBack.addActionListener(e -> btnBackActionPerformed());
        btnCheckin.addActionListener(e -> btnCheckinActionPerformed());
        btnReset.addActionListener(e -> btnResetActionPerformed());
        btnExit.addActionListener(e -> btnExitActionPerformed());
        btnImage.addActionListener(e -> btnImageActionPerformed());

        setSize(780, 460);
    }

    private void displayImage(File imageFile) {
        if (imageFile != null && lblImage.getWidth() > 0 && lblImage.getHeight() > 0) {
            ImageIcon icon = new ImageIcon(imageFile.getAbsolutePath());
            Image img = icon.getImage().getScaledInstance(lblImage.getWidth(), lblImage.getHeight(), Image.SCALE_SMOOTH);
            lblImage.setIcon(new ImageIcon(img));
            lblImage.repaint(); // Đảm bảo hình ảnh được vẽ lại ngay lập tức
        }
    }

    private void generateTicketID() {
        try {
            String newTicketId = CheckinDAO.generateTicketId();
            txtTicketID.setText(newTicketId);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Không thể tạo mã vé: " + ex.getMessage(), "Lỗi Cơ Sở Dữ Liệu", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void fillInfo() {
        Date now = new Date();
        txtDate.setText(new SimpleDateFormat("yyyy-MM-dd").format(now));
        txtCheckin.setText(new SimpleDateFormat("HH:mm:ss").format(now));
    }

    private void btnBackActionPerformed() {
        this.dispose();
        new HomeStaff().setVisible(true);
    }

    private void btnCheckinActionPerformed() {
        String ticketID = txtTicketID.getText().trim();
        String licensePlate = txtLicensePlate.getText().trim();
        String vehicleType = (String) cboVehicleType.getSelectedItem();
        String date = txtDate.getText().trim();
        String checkin = txtCheckin.getText().trim();

        if (ticketID.isEmpty() || licensePlate.isEmpty() || licensePlate.equals("Đang chạy OCR nhận diện...")) {
            JOptionPane.showMessageDialog(this, "Vui lòng quét chọn ảnh xe và chờ nhận diện biển số trước!", "Thông báo", JOptionPane.WARNING_MESSAGE);
            return;
        }

        Ticket ticket = new Ticket(ticketID, licensePlate, vehicleType, date, checkin, null);

        try {
            if (CheckinDAO.isLicensePlateExists(licensePlate)) {
                JOptionPane.showMessageDialog(this, "Biển số xe này đã tồn tại trong bãi!", "Trùng dữ liệu", JOptionPane.ERROR_MESSAGE);
                return;
            }

            if (CheckinDAO.insertTicket(ticket)) {
                JOptionPane.showMessageDialog(this, "Check-in thành công! Xe đã vào bãi thành công.");
                btnResetActionPerformed();
            } else {
                JOptionPane.showMessageDialog(this, "Check-in thất bại, vui lòng kiểm tra lại kết nối.");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        int confirm = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn có muốn thoát không?", "Xác nhận thoát", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    // 🌟 ĐÃ SỬA LỖI ĐƯỜNG DẪN TESSDATA ĐỂ TRANH LỖI "INVALID MEMORY ACCESS"
    private void btnImageActionPerformed() {
        JFileChooser chooser = new JFileChooser("src/licensePlate");
        chooser.setFileFilter(new FileNameExtensionFilter("Hình ảnh xe", "png", "jpg", "jpeg"));
        int option = chooser.showOpenDialog(this);

        if (option == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // 1. Điền thông tin thời gian, mã vé và hiển thị hình ảnh lên khung trước
            generateTicketID();
            fillInfo();
            displayImage(selectedFile);

            // Tạm thời vô hiệu hóa nút xác nhận và hiển thị trạng thái chờ xử lý cho người dùng
            btnCheckin.setEnabled(false);
            txtLicensePlate.setText("Đang chạy OCR nhận diện...");

            // 2. Sử dụng SwingWorker chạy nền xử lý ảnh tránh gây treo luồng chính (EDT Thread)
            new javax.swing.SwingWorker<String, Void>() {
                @Override
                protected String doInBackground() throws Exception {
                    ITesseract tesseract = new Tesseract();

                    // 🌟 VÁ LỖI CỐT LÕI: Lấy đường dẫn tuyệt đối chính xác của thư mục tessdata trên máy tính
                    String absoluteTessdataPath = new java.io.File("tessdata").getAbsolutePath();
                    tesseract.setDatapath(absoluteTessdataPath);
                    tesseract.setLanguage("eng"); // Sử dụng bộ dữ liệu ngôn ngữ tiếng Anh

                    String rawOcr = tesseract.doOCR(selectedFile);
                    // Lọc sạch các ký tự lạ, xuống dòng, chỉ giữ lại chữ, số và dấu gạch ngang của biển số xe
                    return rawOcr.replaceAll("[^a-zA-Z0-9-]", "").trim();
                }

                @Override
                protected void done() {
                    try {
                        String finalPlate = get();
                        if (finalPlate.isEmpty()) {
                            txtLicensePlate.setText("");
                            JOptionPane.showMessageDialog(Checkin.this, "Không nhận diện được ký tự, vui lòng nhập tay!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                        } else {
                            txtLicensePlate.setText(finalPlate); // Đổ kết quả biển số sạch vào textfield
                        }
                    } catch (Exception ex) {
                        txtLicensePlate.setText("");
                        JOptionPane.showMessageDialog(Checkin.this, "Lỗi OCR nhận diện ảnh: " + ex.getMessage(), "Lỗi hệ thống", JOptionPane.ERROR_MESSAGE);
                    } finally {
                        btnCheckin.setEnabled(true); // Mở khóa lại nút bấm khi hoàn thành tác vụ

                        // Ép Form làm tươi lại giao diện để hiện chữ ngay lập tức
                        panelForm.revalidate();
                        panelForm.repaint();
                    }
                }
            }.execute();
        }
    }

    private void styleUI() {
        getContentPane().setBackground(new java.awt.Color(236, 240, 241));
        panelForm.setBackground(new java.awt.Color(236, 240, 241));
        panelButtons.setBackground(new java.awt.Color(236, 240, 241));

        jLabel1.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 28));
        jLabel1.setForeground(new java.awt.Color(44, 62, 80));

        for (Component c : panelForm.getComponents()) {
            if (c instanceof JLabel && c != lblImage) {
                c.setFont(new java.awt.Font("Segoe UI", java.awt.Font.BOLD, 14));
                ((JLabel) c).setForeground(new java.awt.Color(52, 73, 94));
            }
        }

        styleTextField(txtTicketID);
        styleTextField(txtLicensePlate);
        styleTextField(txtDate);
        styleTextField(txtCheckin);
        styleTextField(txtCheckout);

        txtTicketID.setBackground(new java.awt.Color(220, 225, 230));

        lblImage.setBackground(java.awt.Color.WHITE);
        lblImage.setOpaque(true);
        lblImage.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199), 2));

        styleButton(btnCheckin, new java.awt.Color(46, 204, 113));
        styleButton(btnImage, new java.awt.Color(52, 73, 94));
        styleButton(btnBack, new java.awt.Color(52, 152, 219));
        styleButton(btnReset, new java.awt.Color(241, 196, 15));
        styleButton(btnExit, new java.awt.Color(231, 76, 60));

        panelForm.setBorder(javax.swing.BorderFactory.createTitledBorder(
                javax.swing.BorderFactory.createLineBorder(new java.awt.Color(189, 195, 199)),
                "Hệ Thống Phân Phối Xe Vào", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION,
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
                javax.swing.BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
    }
}