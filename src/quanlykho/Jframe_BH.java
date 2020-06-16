/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykho;

import XuLy_KH.KetNoi_CSDL;
import XuLy_KH.KhachHang;
import XuLy_KH.ShowKH;
import XuLy_Kho.MatHang;
import XuLy_Kho.Show_Kho;
import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import static quanlykho.Jframe_MuaHang.str1;
import static quanlykho.Main.taikhoan;

/**
 *
 * @author admin
 */
public class Jframe_BH extends javax.swing.JFrame {

    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private ArrayList<MatHang> list = new ArrayList<>();
    private MatHang mh;
    private DefaultTableModel tb;
    private PreparedStatement ps = null;
    public static Jframe_MuaHang j = new Jframe_MuaHang();
    public static int SL = 0, SL1 = 0;

    public Jframe_BH() {
        initComponents();
        DocDS();
        txtTenNV.setText(taikhoan);

    }

    public void DocDS() {
        Show_Kho doc = new Show_Kho();
        list = doc.getList();
        tb = (DefaultTableModel) table1.getModel();
        tb.setRowCount(0);
        for (MatHang s : list) {
            Vector<Object> vec = new Vector<>();

            vec.add(s.getMaHH());
            vec.add(s.getTen());
            vec.add(s.getLoai());
            vec.add(s.getDvt());
            vec.add(s.getSoluong());
            vec.add(s.getDongia());
            tb.addRow(vec);

        }
        table1.setModel(tb);

        //lấy dữ liệu khi click vào
        table1.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table1.getSelectedRow() >= 0) {
                    j.str1 = table1.getValueAt(table1.getSelectedRow(), 0) + "";
                    j.str2 = table1.getValueAt(table1.getSelectedRow(), 1) + "";
                    j.str3 = table1.getValueAt(table1.getSelectedRow(), 2) + "";
                    j.str4 = table1.getValueAt(table1.getSelectedRow(), 3) + "";
                    j.str5 = table1.getValueAt(table1.getSelectedRow(), 5) + "";
                    //lấy số lượng hàng hóa để so sánh
                    SL1 = Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 4) + "");
                }

            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        txtTenNV = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfTimKiem = new javax.swing.JTextField();
        jscroll = new javax.swing.JScrollPane();
        table1 = new javax.swing.JTable();
        txtSL = new javax.swing.JTextField();
        btnChon = new javax.swing.JButton();
        btnGH = new javax.swing.JButton();
        btnQL = new javax.swing.JButton();
        btnTK = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        btnTK1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setTitle("Bán hàng");
        jInternalFrame1.setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Nhân Viên:");

        txtTenNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtTenNV.setEnabled(false);
        txtTenNV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTenNVActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 153, 0));
        jLabel2.setText("Tìm kiếm:");

        tfTimKiem.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        tfTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiemActionPerformed(evt);
            }
        });
        tfTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tfTimKiemKeyPressed(evt);
            }
        });

        table1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã hàng", "Tên hàng", "Loại hàng", "Đơn vị tính", "Số lượng", "Đơn giá"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jscroll.setViewportView(table1);

        txtSL.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSLKeyPressed(evt);
            }
        });

        btnChon.setBackground(new java.awt.Color(255, 255, 255));
        btnChon.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnChon.setForeground(new java.awt.Color(204, 0, 0));
        btnChon.setText("Chọn mua");
        btnChon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChonActionPerformed(evt);
            }
        });

        btnGH.setBackground(new java.awt.Color(0, 153, 0));
        btnGH.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnGH.setForeground(new java.awt.Color(255, 255, 255));
        btnGH.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/Untitled.png"))); // NOI18N
        btnGH.setText("Giỏ Hàng");
        btnGH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGHActionPerformed(evt);
            }
        });

        btnQL.setBackground(new java.awt.Color(255, 255, 255));
        btnQL.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnQL.setForeground(new java.awt.Color(0, 153, 0));
        btnQL.setText("Quay Lại");
        btnQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLActionPerformed(evt);
            }
        });

        btnTK.setBackground(new java.awt.Color(255, 255, 255));
        btnTK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTK.setForeground(new java.awt.Color(0, 153, 0));
        btnTK.setText("Tìm");
        btnTK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTKActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Số lượng");

        btnTK1.setBackground(new java.awt.Color(255, 255, 255));
        btnTK1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnTK1.setForeground(new java.awt.Color(0, 153, 0));
        btnTK1.setText("Xóa");
        btnTK1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTK1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addComponent(btnQL, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnGH))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfTimKiem, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(txtTenNV))
                        .addGap(18, 18, 18)
                        .addComponent(btnTK)
                        .addGap(18, 18, 18)
                        .addComponent(btnTK1))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 518, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnTK)
                    .addComponent(btnTK1))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnChon, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jInternalFrame1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jscroll, javax.swing.GroupLayout.PREFERRED_SIZE, 281, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 19, Short.MAX_VALUE)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGH)
                    .addComponent(btnQL, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGHActionPerformed
        j.setVisible(true);
        j.TongTien();
        dispose();
    }//GEN-LAST:event_btnGHActionPerformed

    private void btnQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLActionPerformed
        Jframe_ChucNang_NV j = new Jframe_ChucNang_NV();
        j.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnQLActionPerformed

    private void btnTKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTKActionPerformed
        String header[] = {"Mã hàng", "Tên hàng", "Loại hàng", "Đơn vị tính", "Số lượng", "Đơn giá"};
        tb = new DefaultTableModel(header, 0);
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://;databaseName=QUANLYBANHANG", "sa", "123456");
            // Câu lệnh xem dữ liệu
            String sql = "select * from KHOHANG ";
            if (tfTimKiem.getText().length() > 0) {
                sql = sql + " where TENHANG like N'%" + tfTimKiem.getText() + "%'";
            }
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            tb.setRowCount(0);
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Không có vật tư!");
                return;
            }
            // Trong khi chưa hết dữ liệu
            while (rs.next()) {
                Vector<Object> data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                //Lấy tên loại hàng từ database khác
                String loaiHang = rs.getString(3);
                PreparedStatement ps1 = conn.prepareStatement("SELECT TENLH FROM LOAIHANG WHERE MALH = ?");
                ps1.setString(1, loaiHang);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    data.add(rs1.getString(1));
                }
                data.add(rs.getString(4));
                data.add(rs.getInt(5));
                data.add(rs.getInt(6));
                // Thêm một dòng vào table model
                tb.addRow(data);
            }
            table1.setModel(tb); // Thêm dữ liệu vào table
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_btnTKActionPerformed

    private void btnChonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChonActionPerformed
        if(table1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn vật tư để nhập số lượng .", "Thông báo", WIDTH);
            txtSL.setText("");
        }else{
        if (txtSL.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập số lượng", "Thông báo", WIDTH);
        } else {
            //kiểm tra số lượng chọn mua
            for (int i = 0; i < txtSL.getText().length(); i++) {
                if (txtSL.getText().charAt(i) < '0' || txtSL.getText().charAt(i) > '9') {
                    JOptionPane.showMessageDialog(rootPane, "Số lượng chỉ nhập số", "Thông báo", WIDTH);
                    txtSL.setText("");
                } else {
                    SL = Integer.parseInt(txtSL.getText());
                    if ((SL1 - SL) < 0) {//số lượng chọn mua nhiều hơn số lượng trong kho
                        JOptionPane.showMessageDialog(rootPane, "Số lượng trong kho không đủ. Kho còn " + SL1 + ". Nhập lại số lượng.", "Thông báo", WIDTH);
                        txtSL.setText("");
                    } else if (SL <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn số lượng hàng mua lớn hơn 0.", "Thông báo", WIDTH);
                        txtSL.setText("");
                    } else {
                        try {
                            if ((SL1 - SL) == 0) {//số lượng chọn mua bằng số lượng trong kho
                                JOptionPane.showMessageDialog(rootPane, "Chọn mua thành công. Trong kho đã hết hàng.", "Thông báo", WIDTH);
                                j.str6 = txtSL.getText().trim();
                            }
                            //sau khi đk về số lượng t/m thì cho ghi vào jframe_MuaHang
                            j.str6 = txtSL.getText().trim();
                            j.str7 = Integer.parseInt(txtSL.getText().trim()) * Integer.parseInt(table1.getValueAt(table1.getSelectedRow(), 5).toString());
                            j.muahang.AddRow();
                            j.muahang.CongHang();
                            JOptionPane.showMessageDialog(rootPane, "Thêm thành công vào giỏ hàng", "Thông báo", WIDTH);
                            txtSL.setText("");
                        } catch (SQLException ex) {
                            Logger.getLogger(Jframe_BH.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                }
            }
        }
        }
    }//GEN-LAST:event_btnChonActionPerformed

    private void txtSLKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSLKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            //sau khi đã chọn mua thì thay đổi số lượng hàng hóa ngay trong kho hàng
            if(table1.getSelectedRow()<0){
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa chọn vật tư để nhập số lượng .", "Thông báo", WIDTH);
            txtSL.setText("");
         }else{
            for (int i = 0; i < txtSL.getText().length(); i++) {
                if (txtSL.getText().charAt(i) < '0' || txtSL.getText().charAt(i) > '9') {
                    JOptionPane.showMessageDialog(rootPane, "Số lượng chỉ nhập số", "Thông báo", WIDTH);
                    txtSL.setText("");
                } else {
                    SL = Integer.parseInt(txtSL.getText());
                    if ((SL1 - SL) == 0) {//số lượng chọn mua bằng số lượng trong kho
                        JOptionPane.showMessageDialog(rootPane, "Chọn mua thành công. Trong kho đã hết hàng.", "Thông báo", WIDTH);
                    } else if ((SL1 - SL) < 0) {//số lượng chọn mua nhiều hơn số lượng trong kho
                        JOptionPane.showMessageDialog(rootPane, "Số lượng trong kho không đủ. Kho còn " + SL1 + ". Nhập lại số lượng.", "Thông báo", WIDTH);
                        txtSL.setText("");
                    } else if (SL <= 0) {
                        JOptionPane.showMessageDialog(rootPane, "Vui lòng chọn số lượng hàng mua lớn hơn 0.", "Thông báo", WIDTH);
                        txtSL.setText("");

                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Chọn số lượng phù hợp", "Thông báo", WIDTH);

                    }
                }
            }
         }
        }

    }//GEN-LAST:event_txtSLKeyPressed

    private void txtTenNVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTenNVActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTenNVActionPerformed

    private void btnTK1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTK1ActionPerformed
        // TODO add your handling code here:
        tfTimKiem.setText(null);
        DocDS();
    }//GEN-LAST:event_btnTK1ActionPerformed

    private void tfTimKiemKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            String header[] = {"Mã hàng", "Tên hàng", "Loại hàng", "Đơn vị tính", "Số lượng", "Đơn giá"};
        tb = new DefaultTableModel(header, 0);
        try {
            conn = DriverManager.getConnection("jdbc:sqlserver://;databaseName=QUANLYBANHANG", "sa", "123456");
            // Câu lệnh xem dữ liệu
            String sql = "select * from KHOHANG ";
            if (tfTimKiem.getText().length() > 0) {
                sql = sql + " where TENHANG like N'%" + tfTimKiem.getText() + "%'";
            }
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            tb.setRowCount(0);
            if (rs.isBeforeFirst() == false) {
                JOptionPane.showMessageDialog(this, "Không có vật tư!");
                return;
            }
            // Trong khi chưa hết dữ liệu
            while (rs.next()) {
                Vector<Object> data = new Vector();
                data.add(rs.getString(1));
                data.add(rs.getString(2));
                //Lấy tên loại hàng từ database khác
                String loaiHang = rs.getString(3);
                PreparedStatement ps1 = conn.prepareStatement("SELECT TENLH FROM LOAIHANG WHERE MALH = ?");
                ps1.setString(1, loaiHang);
                ResultSet rs1 = ps1.executeQuery();
                while (rs1.next()) {
                    data.add(rs1.getString(1));
                }
                data.add(rs.getString(4));
                data.add(rs.getInt(5));
                data.add(rs.getInt(6));
                // Thêm một dòng vào table model
                tb.addRow(data);
            }
            table1.setModel(tb); // Thêm dữ liệu vào table
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
                if (st != null) {
                    st.close();
                }
                if (rs != null) {
                    rs.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        }
    }//GEN-LAST:event_tfTimKiemKeyPressed

    private void tfTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimKiemActionPerformed

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
            java.util.logging.Logger.getLogger(Jframe_BH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jframe_BH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jframe_BH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jframe_BH.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jframe_BH().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChon;
    private javax.swing.JButton btnGH;
    private javax.swing.JButton btnQL;
    private javax.swing.JButton btnTK;
    private javax.swing.JButton btnTK1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jscroll;
    private javax.swing.JTable table1;
    private javax.swing.JTextField tfTimKiem;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTenNV;
    // End of variables declaration//GEN-END:variables
}
