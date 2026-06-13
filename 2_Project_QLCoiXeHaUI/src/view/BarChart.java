/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;
import database.DBConnect;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.labels.StandardCategoryItemLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
/**
 *
 * @author Admin
 */
public class BarChart extends javax.swing.JFrame {
    private int selectedYear;
   public BarChart() {
        initComponents();
        this.selectedYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
        hienThiBieuDoLenPanel();
    }

    // Khởi tạo có tham số (giữ lại để không bị lỗi gọi từ form Revenue)
    public BarChart(int year) {
        initComponents();
        this.selectedYear = year;
        hienThiBieuDoLenPanel();
    }
    private DefaultCategoryDataset layDoanhThu12Thang() {
       DefaultCategoryDataset dataset = new DefaultCategoryDataset();
    
    // Tạo sẵn khung từ Tháng 1 đến Tháng 12 với giá trị mặc định là 0
    for (int i = 1; i <= 12; i++) {
        dataset.setValue(0, "Doanh thu", "T." + i);
    }

    // Lấy năm hiện tại của hệ thống máy tính (Ví dụ: 2026)
    int namHienTai = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);

    // CẬP NHẬT SQL: Thêm điều kiện WHERE để chỉ lấy dữ liệu của năm hiện tại
    String sql = "SELECT MONTH(t.parking_date) AS Thang, SUM(f.fare_amount) AS DoanhThu "
               + "FROM ticket_details t "
               + "JOIN parking_fare f ON t.vehicle_type = f.vehicle_type "
               + "WHERE YEAR(t.parking_date) = ? "  // 👈 Lọc chính xác theo năm
               + "GROUP BY MONTH(t.parking_date)";

    try (Connection conn = DBConnect.getConnection(); 
         PreparedStatement ps = conn.prepareStatement(sql)) {

        // Truyền năm hiện tại vào dấu hỏi chấm (?)
        ps.setInt(1, this.selectedYear);
        try (ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int thang = rs.getInt("Thang");
                double doanhThu = rs.getDouble("DoanhThu");
                
                // Cập nhật số tiền thực tế vào biểu đồ
                dataset.setValue(doanhThu, "Doanh thu", "T." + thang);
            }
        }

    } catch (Exception e) {
        System.out.println("Lỗi truy vấn dữ liệu biểu đồ: " + e.getMessage());
        e.printStackTrace();
    }

    return dataset;
    }
  private void hienThiBieuDoLenPanel() {
       setPreferredSize(new Dimension(950, 550)); 
        this.getContentPane().setLayout(new BorderLayout());

        // Lấy dữ liệu 12 tháng trực tiếp từ Database
        DefaultCategoryDataset dataset = layDoanhThu12Thang();

        JFreeChart barChart = ChartFactory.createBarChart(
                "BIỂU ĐỒ DOANH THU THEO CÁC THÁNG TRONG NĂM", 
                "Thời gian (Tháng)",                       
                "Doanh thu (VNĐ)",                  
                dataset, 
                PlotOrientation.VERTICAL, 
                false, true, false // Tắt Legend (Chú thích) vì chỉ có 1 danh mục Doanh thu, nhìn cho rộng
        );

        // ===== TOÀN BỘ GIAO DIỆN PHẲNG 2D CLEAN & BLUE =====
        barChart.setBackgroundPaint(Color.WHITE);
        
        Font titleFont = new Font("Segoe UI", Font.BOLD, 20);
        Font labelFont = new Font("Segoe UI", Font.PLAIN, 13);
        Font valueFont = new Font("Segoe UI", Font.PLAIN, 11);

        barChart.getTitle().setFont(titleFont);
        barChart.getTitle().setPaint(new Color(44, 62, 80));

        CategoryPlot plot = barChart.getCategoryPlot();
        plot.setBackgroundPaint(new Color(248, 249, 250)); // Nền xám nhạt tinh tế
        plot.setRangeGridlinePaint(new Color(222, 226, 230)); // Đường lưới kẻ ngang mảnh
        plot.setOutlineVisible(false); // Bỏ khung viền vuông bao quanh

        // Cấu hình trục X phẳng
        CategoryAxis xAxis = plot.getDomainAxis();
        xAxis.setLabelFont(labelFont);
        xAxis.setTickLabelFont(labelFont);
        xAxis.setTickMarksVisible(false);
        xAxis.setAxisLineVisible(false);

        // Cấu hình trục Y phẳng
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setLabelFont(labelFont);
        yAxis.setTickLabelFont(valueFont);
        yAxis.setAxisLineVisible(false);

        // ===== CẤU HÌNH MÀU XANH BLUE TỐI GIẢN (FLAT 2D) =====
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setDrawBarOutline(false);
        renderer.setShadowVisible(false); // Tắt hoàn toàn bóng đổ đổ bóng 3D rườm rà

        // Sử dụng mã màu Xanh Blue Modern trơn phẳng (Không dùng GradientPaint nữa)
        renderer.setSeriesPaint(0, new Color(41, 128, 185)); // Màu Classic Blue cực sang và dịu mắt
        
        renderer.setMaximumBarWidth(0.06); // Cho cột mảnh dẻ dứt khoát

        // Hiện số tiền tinh tế trên đầu mỗi cột
        renderer.setDefaultItemLabelsVisible(true);
        renderer.setDefaultItemLabelGenerator(new StandardCategoryItemLabelGenerator());
        renderer.setDefaultItemLabelFont(new Font("Segoe UI", Font.BOLD, 10));
        renderer.setDefaultItemLabelPaint(new Color(52, 73, 94));

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setBackground(Color.WHITE);
        chartPanel.setDisplayToolTips(true); 
        
        this.getContentPane().add(chartPanel, BorderLayout.CENTER);
        
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        pack(); 
        this.setLocationRelativeTo(null);
    }
  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
  
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>                        
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
 // </editor-fold>//GEN-END:initComponents
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new BarChart().setVisible(true);
        });
    }
   private javax.swing.JPanel panelChart;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
