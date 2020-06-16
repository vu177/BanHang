/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykho;

import XuLy_HoaDon.CTHD;
import XuLy_HoaDon.HoaDon;
import XuLy_HoaDon.Show_HD;
import XuLy_KH.KetNoi_CSDL;
import static java.awt.image.ImageObserver.WIDTH;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static quanlykho.Main.taikhoan;

/**
 *
 * @author admin
 */
public class Jframe_HD extends javax.swing.JFrame {

    public static String string1;
    public static String string2;
    public static String string3;
    public static String mahang;
    public static String tenhang;
    public static String loaihang;
    public static int SL_hang;
    public static String donvitinh;
    public static String dongia;
    public static long thanhtien;
    public static HoaDon HD;
    public static CTHD cthd;
    public Jframe_HD hoadon;
    public Jframe_MuaHang muahang = new Jframe_MuaHang();
    public Jframe_BH BH = new Jframe_BH();
    DefaultTableModel model = new DefaultTableModel();
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps;
    static KetNoi_CSDL kn = new KetNoi_CSDL();
    Locale localeVN = new Locale("vi", "VN");
    //Định dạng số
    NumberFormat vn = NumberFormat.getInstance(localeVN);

    public Jframe_HD() {
        hoadon = this;
        initComponents();
        AddCol1();
    }

    public void AddCol1() {
        model.addColumn("Mã Hàng");
        model.addColumn("Tên Hàng");
        model.addColumn("Loại Hàng");
        model.addColumn("Đơn Vị Tính");
        model.addColumn("Đơn Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Thành Tiền");

    }

    public void GetStr() {
        txtNV.setText(taikhoan);
        txtKH.setText(string1);
        txtNgay.setText(string2);
        String tongtien = string3; //Để hiện tiền theo định dạng
        txtTT.setText(vn.format(Integer.parseInt(tongtien)));
    }

    public void AddRow1() {
        Object[] gtri = new Object[7];
        gtri[0] = mahang;
        gtri[1] = tenhang;
        gtri[2] = loaihang;
        gtri[3] = donvitinh;
        gtri[4] = dongia;
        gtri[5] = SL_hang;
        gtri[6] = thanhtien;
        model.addRow(gtri);
        jTable1.setModel(model);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNV = new javax.swing.JTextField();
        txtKH = new javax.swing.JTextField();
        txtNgay = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        btnHD = new javax.swing.JButton();
        btnHuy = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setMinimumSize(new java.awt.Dimension(38, 100));
        jInternalFrame1.setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("HÓA ĐƠN MUA HÀNG");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/quanly.png"))); // NOI18N
        jLabel2.setText("Nhân Viên :");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 153, 0));
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/khachhang.png"))); // NOI18N
        jLabel3.setText("Khách Hàng :");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(204, 0, 0));
        jLabel4.setText("Ngày In Hóa Đơn:");

        txtNV.setEditable(false);
        txtNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtKH.setEditable(false);
        txtKH.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtNgay.setEditable(false);
        txtNgay.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Hàng", "Tên Hàng", "Loại Hàng", "Đơn Vị Tính", "Đơn Giá", "Số Lượng", "Thành tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jTable1KeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(204, 0, 0));
        jLabel5.setText("Tổng Tiền (Bao Gồm VAT) :");

        txtTT.setEditable(false);
        txtTT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("VND");

        btnHD.setBackground(new java.awt.Color(0, 153, 0));
        btnHD.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHD.setForeground(new java.awt.Color(255, 255, 255));
        btnHD.setText("In Hóa Đơn");
        btnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDActionPerformed(evt);
            }
        });

        btnHuy.setBackground(new java.awt.Color(0, 153, 0));
        btnHuy.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnHuy.setForeground(new java.awt.Color(255, 255, 255));
        btnHuy.setText("Không In Hóa Đơn");
        btnHuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHuyActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(btnHuy, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnHD, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))
                                .addGap(37, 37, 37)
                                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtNgay, javax.swing.GroupLayout.DEFAULT_SIZE, 239, Short.MAX_VALUE)
                                    .addComponent(txtTT))
                                .addGap(18, 18, 18)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(59, 59, 59)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(33, Short.MAX_VALUE))))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(211, 211, 211)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 38, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnHD, javax.swing.GroupLayout.DEFAULT_SIZE, 44, Short.MAX_VALUE)
                    .addComponent(btnHuy, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(40, 40, 40))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyPressed

    }//GEN-LAST:event_jTable1KeyPressed

    private void btnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDActionPerformed
//        try {
//            conn = kn.getKetNoiDuLieu();
//            String ma = "";
//            String nv = txtNV.getText().trim();
//            String kh = txtKH.getText().trim();
//            String ngay = txtNgay.getText().trim();
//            String tongtien = txtTT.getText().trim();
//
//            //ghi vào một đối tượng hóa đơn
//            HD = new HoaDon();
//            HD.setMahoadon(ma);
//            HD.setManv(nv);
//            HD.setMakh(kh);
//            HD.setNgaymua(ngay);
//            HD.setTongtien(Integer.parseInt(string3));
//            //ghi vào CSDL bảng hóa đơn
//            Show_HD show = new Show_HD();
//            int check = show.ThemHoaDon(HD);
//            if (check == -1) {
//                //JOptionPane.showMessageDialog(rootPane, "In hóa đơn không thành công", "Thông báo", WIDTH);
//            } else {
                JOptionPane.showMessageDialog(rootPane, "In hóa đơn thành công", "Thông báo", WIDTH);
//            }
//
//            // ghi vào CSDL bảng chi tiết hóa đơn
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                String mahang = (String) jTable1.getValueAt(i, 0);
//                String mahd = "";
//                int soluong = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
//                int dongia = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
//                String tt = jTable1.getValueAt(i, 6).toString();
//                double t_t = Double.parseDouble(tt);
//
//                //Lấy một đối tượng để lưu vào
//                cthd = new CTHD();
//                cthd.setMaHD(mahd);
//                cthd.setMaHH(mahang);
//                cthd.setSoLuong(soluong);
//                cthd.setDonGia(dongia);
//                cthd.setThanhTien((int) t_t);
//                Show_HD them = new Show_HD();
//                int kt = them.ThemCTHD(cthd);
//                if (kt == -1) {
//                    //JOptionPane.showMessageDialog(rootPane, "Ghi vào CTHD không thành công", "Thông báo", WIDTH);
//                } else {
//                    //JOptionPane.showMessageDialog(rootPane, "Ghi vào CTHD thành công", "Thông báo", WIDTH);
//
//                }
//            }
            //gọi chức năng bán hàng
              model.setRowCount(0);
              jTable1.setModel(model);
              BH=new Jframe_BH();
              BH.setVisible(true);
              dispose();
//            //đóng kết nối
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Jframe_HD.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_btnHDActionPerformed

    private void btnHuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHuyActionPerformed
//        try {
//            conn = kn.getKetNoiDuLieu();
//            String ma = "";
//            String nv = txtNV.getText().trim();
//            String kh = txtKH.getText().trim();
//            String ngay = txtNgay.getText().trim();
//            String tongtien = txtTT.getText().trim();
//
//            double tien = Double.parseDouble(tongtien);
//
//            //ghi vào một đối tượng hóa đơn
//            HD = new HoaDon();
//            HD.setMahoadon(ma);
//            HD.setManv(nv);
//            HD.setMakh(kh);
//            HD.setNgaymua(ngay);
//            HD.setTongtien((int) tien);
//            //ghi vào CSDL
//            Show_HD show = new Show_HD();
//            int check = show.ThemHoaDon(HD);
//            BH.setVisible(true);
//            dispose();
//
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Jframe_HD.class.getName()).log(Level.SEVERE, null, ex);
//        }try {
//            conn = kn.getKetNoiDuLieu();
//            String ma = "";
//            String nv = txtNV.getText().trim();
//            String kh = txtKH.getText().trim();
//            String ngay = txtNgay.getText().trim();
//            String tongtien = txtTT.getText().trim();
//
//            //ghi vào một đối tượng hóa đơn
//            HD = new HoaDon();
//            HD.setMahoadon(ma);
//            HD.setManv(nv);
//            HD.setMakh(kh);
//            HD.setNgaymua(ngay);
//            HD.setTongtien(Integer.parseInt(string3));
//            //ghi vào CSDL bảng hóa đơn
//            Show_HD show = new Show_HD();
//            int check = show.ThemHoaDon(HD);
//            if (check == -1) {
//                JOptionPane.showMessageDialog(rootPane, "In hóa đơn không thành công", "Thông báo", WIDTH);
//            } else {
//                JOptionPane.showMessageDialog(rootPane, "In hóa đơn thành công", "Thông báo", WIDTH);
//            }
//
//            // ghi vào CSDL bảng chi tiết hóa đơn
//            for (int i = 0; i < jTable1.getRowCount(); i++) {
//                String mahang = (String) jTable1.getValueAt(i, 0);
//                String mahd = "";
//                int soluong = Integer.parseInt(jTable1.getValueAt(i, 5).toString());
//                int dongia = Integer.parseInt(jTable1.getValueAt(i, 4).toString());
//                String tt = jTable1.getValueAt(i, 6).toString();
//                double t_t = Double.parseDouble(tt);
//
//                //Lấy một đối tượng để lưu vào
//                cthd = new CTHD();
//                cthd.setMaHD(mahd);
//                cthd.setMaHH(mahang);
//                cthd.setSoLuong(soluong);
//                cthd.setDonGia(dongia);
//                cthd.setThanhTien((int) t_t);
//                Show_HD them = new Show_HD();
//                int kt = them.ThemCTHD(cthd);
//                if (kt == -1) {
//                    //JOptionPane.showMessageDialog(rootPane, "Ghi vào CTHD không thành công", "Thông báo", WIDTH);
//                } else {
//                    //JOptionPane.showMessageDialog(rootPane, "Ghi vào CTHD thành công", "Thông báo", WIDTH);
//
//                }
//            }
            //gọi chức năng bán hàng
            model.setRowCount(0);
            jTable1.setModel(model);
            BH=new Jframe_BH();
            BH.setVisible(true);
            dispose();
//            //đóng kết nối
//            if (ps != null) {
//                ps.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//            if (st != null) {
//                st.close();
//            }
//        } catch (SQLException ex) {
//            Logger.getLogger(Jframe_HD.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }//GEN-LAST:event_btnHuyActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Jframe_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jframe_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jframe_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jframe_HD.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jframe_HD().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHD;
    private javax.swing.JButton btnHuy;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField txtKH;
    private javax.swing.JTextField txtNV;
    private javax.swing.JTextField txtNgay;
    private javax.swing.JTextField txtTT;
    // End of variables declaration//GEN-END:variables
}
