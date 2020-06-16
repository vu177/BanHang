/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykho;

import XuLy_HoaDon.HoaDon;
import XuLy_HoaDon.Show_DoanhThu;
import XuLy_KH.KetNoi_CSDL;
import XuLy_NV.NhanVien;
import XuLy_NV.Show_NV;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class Jframe_DoanhThu extends javax.swing.JFrame {

    DefaultTableModel tbm=new DefaultTableModel();
    public PreparedStatement ps;
    public ResultSet rs = null;
    public Statement st;
    static KetNoi_CSDL a=new KetNoi_CSDL();
    static Connection cn =a.KetNoi();
    DefaultTableModel tb = null;
    private ArrayList<HoaDon> list= new ArrayList<>();
    private HoaDon hd;
    //Định dạng tiền tệ
    Locale localeVN = new Locale("vi", "VN");
    NumberFormat currencyVN = NumberFormat.getCurrencyInstance(localeVN);
    //Định dạng số
    NumberFormat vn = NumberFormat.getInstance(localeVN);
    
    public Jframe_DoanhThu() {
        initComponents();
    }
    
    public void setTable()
    {
        
        tb.getDataVector().removeAllElements();

        String sql = "select MAHD,NGAYMUA,TONGTIEN from dbo.HOADON";
        try {
            PreparedStatement pt = cn.prepareStatement(sql);
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                tb.addRow(new Object[] {rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4)});
            }
        } catch (Exception e) {
        }
    
    }

    public void tongDT(){ 
        try {
            int doanhthu = 0;
            DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
            DateFormat df1=new SimpleDateFormat("dd/MM/yyyy");
            String sql = "Select TONGTIEN from dbo.HOADON where NGAYMUA between ? and ?";
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setString(1, df.format(jDateBatDau.getDate()));
            pt.setString(2, df.format(jDateKetThuc.getDate()));
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                doanhthu+=Integer.parseUnsignedInt(rs.getString(1));
            }
            txtTongDT.setText(vn.format(doanhthu));
            long tiengoc = doanhthu * 10 / 13;
            txtTienGoc.setText(vn.format(tiengoc));
            long tienlai = doanhthu - tiengoc;
            txtTienLai.setText(vn.format(tienlai));
        } catch (SQLException ex) {
            Logger.getLogger(Jframe_DoanhThu.class.getName()).log(Level.SEVERE, null, ex);
        }
  
    }
    
    public void tiengoc(){
        
    }
    
    
    public void xulyDT(){
        if( jTableDoanhThu.getSelectedRow()>=0){
            txtTongDT.setText((String) jTableDoanhThu.getValueAt(jTableDoanhThu.getSelectedRow(), 2));
        }       
    }
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jDateBatDau = new com.toedter.calendar.JDateChooser();
        jDateKetThuc = new com.toedter.calendar.JDateChooser();
        jButtonXem = new javax.swing.JButton();
        jLabelHienThiNgay = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDoanhThu = new javax.swing.JTable();
        TongDoanhThu = new javax.swing.JLabel();
        jButtonThoat = new javax.swing.JButton();
        txtTongDT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtTienGoc = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        TongDoanhThu1 = new javax.swing.JLabel();
        TongDoanhThu2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtTienLai = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("TÍNH DOANH THU");

        jLabel2.setText("Từ ngày");

        jLabel3.setText("đến ngày");

        jDateBatDau.setDateFormatString("dd/MM/yyyy");

        jDateKetThuc.setDateFormatString("dd/MM/yyyy");

        jButtonXem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonXem.setForeground(new java.awt.Color(204, 0, 0));
        jButtonXem.setText("Xem");
        jButtonXem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonXemActionPerformed(evt);
            }
        });

        jLabelHienThiNgay.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        jTableDoanhThu.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hoá đơn", "Ngày xuất ", "Tổng tiền"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableDoanhThu);

        TongDoanhThu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TongDoanhThu.setForeground(new java.awt.Color(204, 0, 0));
        TongDoanhThu.setText("Tổng doanh thu:");

        jButtonThoat.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButtonThoat.setForeground(new java.awt.Color(204, 0, 0));
        jButtonThoat.setText("Thoát");
        jButtonThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonThoatActionPerformed(evt);
            }
        });

        jLabel4.setText("VND");

        jLabel5.setText("VND");

        TongDoanhThu1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TongDoanhThu1.setForeground(new java.awt.Color(204, 0, 0));
        TongDoanhThu1.setText("Tổng tiền gốc:");

        TongDoanhThu2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        TongDoanhThu2.setForeground(new java.awt.Color(204, 0, 0));
        TongDoanhThu2.setText("Tổng tiền lãi:");

        jLabel6.setText("VND");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(jLabelHienThiNgay, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 3, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(13, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(TongDoanhThu, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(TongDoanhThu1, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTienGoc, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(TongDoanhThu2, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(txtTienLai, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(22, 22, 22))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(189, 189, 189)
                        .addComponent(jButtonThoat))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jButtonXem))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 32, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jDateBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateKetThuc, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonXem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelHienThiNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TongDoanhThu)
                    .addComponent(txtTongDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TongDoanhThu1)
                    .addComponent(txtTienGoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(TongDoanhThu2)
                    .addComponent(txtTienLai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButtonThoat, javax.swing.GroupLayout.DEFAULT_SIZE, 28, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonXemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonXemActionPerformed
        // TODO add your handling code here:
        DateFormat df=new SimpleDateFormat("MM/dd/yyyy");
        DateFormat df1=new SimpleDateFormat("dd/MM/yyyy");
        jLabelHienThiNgay.setText("Bảng hoá đơn từ "+df1.format(jDateBatDau.getDate())+" đến "+df1.format(jDateKetThuc.getDate())+":");
        
        tb = (DefaultTableModel) jTableDoanhThu.getModel();
        tb.getDataVector().removeAllElements();

        String sql = "Select MAHD,NGAYMUA,TONGTIEN from dbo.HOADON where NGAYMUA between ? and ?";
        try {
            PreparedStatement pt = cn.prepareStatement(sql);
            pt.setString(1, df.format(jDateBatDau.getDate()));
            pt.setString(2, df.format(jDateKetThuc.getDate()));
            ResultSet rs = pt.executeQuery();
            while(rs.next())
            {
                tb.addRow(new Object[] {rs.getString(1),rs.getString(2),currencyVN.format(Integer.parseUnsignedInt(rs.getString(3)))});
            }
        } catch (Exception e) {
        }
        tongDT();
    }//GEN-LAST:event_jButtonXemActionPerformed

    private void jButtonThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonThoatActionPerformed
        // TODO add your handling code here:
        Jframe_QL j=new Jframe_QL();
        j.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButtonThoatActionPerformed

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
            java.util.logging.Logger.getLogger(Jframe_DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jframe_DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jframe_DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jframe_DoanhThu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jframe_DoanhThu().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel TongDoanhThu;
    private javax.swing.JLabel TongDoanhThu1;
    private javax.swing.JLabel TongDoanhThu2;
    private javax.swing.JButton jButtonThoat;
    private javax.swing.JButton jButtonXem;
    private com.toedter.calendar.JDateChooser jDateBatDau;
    private com.toedter.calendar.JDateChooser jDateKetThuc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelHienThiNgay;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDoanhThu;
    private javax.swing.JTextField txtTienGoc;
    private javax.swing.JTextField txtTienLai;
    private javax.swing.JTextField txtTongDT;
    // End of variables declaration//GEN-END:variables
}

