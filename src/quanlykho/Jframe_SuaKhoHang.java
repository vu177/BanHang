package quanlykho;


import XuLy_KH.KetNoi_CSDL;
import XuLy_Kho.MatHang;
import XuLy_Kho.Show_Kho;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;


public class Jframe_SuaKhoHang extends javax.swing.JFrame {
    static KetNoi_CSDL kn = new KetNoi_CSDL();

    private ArrayList <MatHang> list =new ArrayList<>();
    private MatHang mh;
    public Jframe_SuaKhoHang() throws SQLException {
        initComponents();
        Connection cn = kn.getKetNoiDuLieu();
        PreparedStatement ps = cn.prepareStatement("SELECT * FROM LOAIHANG");
        ResultSet rs = ps.executeQuery();
        while(rs.next())
        {
            String s = rs.getString(2);
            cbLoaiHang.addItem(s);
        }
        DocDS();
    }
    public void up(){
        
    }
    public void DocDS(){
      Show_Kho doc=new Show_Kho();
        list =doc.getList();
        DefaultTableModel tb =(DefaultTableModel) tableHang.getModel();
        tb.setRowCount(0);
        for(MatHang s:list){
                Vector <Object> vec =new Vector<>();
                
                vec.add(s.getMaHH());
                vec.add(s.getTen());
                vec.add(s.getLoai());
                vec.add(s.getDvt());
                vec.add(s.getSoluong());
                vec.add(s.getDongia());
                tb.addRow(vec);
               
            }
       tableHang.setModel(tb);
       tableHang.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    
          @Override
          public void valueChanged(ListSelectionEvent e) {
            if( tableHang.getSelectedRow()>=0){
                tfMaHang.setText(tableHang.getValueAt(tableHang.getSelectedRow(),0)+"");
                tfTenHang.setText(tableHang.getValueAt(tableHang.getSelectedRow(),1)+"");
                cbLoaiHang.setSelectedItem(tableHang.getModel().getValueAt(tableHang.getSelectedRow(), 2)+"");
                tfDVT.setText(tableHang.getValueAt(tableHang.getSelectedRow(),3)+"");
                tfSoLuong.setText(tableHang.getValueAt(tableHang.getSelectedRow(),4)+"");
                tfDonGia.setText(tableHang.getValueAt(tableHang.getSelectedRow(),5)+"");
            }   
          }
       }
       );
    }
    public void resetText() {
        tfTenHang.setText("");
        tfDVT.setText("");
        tfDonGia.setText("");
        tfSoLuong.setText("");
        tfMaHang.setText("");      
        lbThongBao.setText("");
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        lbThongTin = new javax.swing.JLabel();
        lbMaHang = new javax.swing.JLabel();
        lbLoaiHang = new javax.swing.JLabel();
        lbSoLuong = new javax.swing.JLabel();
        lbTenHang = new javax.swing.JLabel();
        lbDVT = new javax.swing.JLabel();
        lbDonGia = new javax.swing.JLabel();
        tfMaHang = new javax.swing.JTextField();
        tfTenHang = new javax.swing.JTextField();
        tfDVT = new javax.swing.JTextField();
        tfSoLuong = new javax.swing.JTextField();
        tfDonGia = new javax.swing.JTextField();
        cbLoaiHang = new javax.swing.JComboBox<>();
        lbChinh = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableHang = new javax.swing.JTable();
        btnQuayLai = new javax.swing.JButton();
        btnLuu = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        lbDanhSach = new javax.swing.JLabel();
        lbThongBao = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setForeground(new java.awt.Color(0, 0, 255));

        lbThongTin.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbThongTin.setForeground(new java.awt.Color(0, 153, 0));
        lbThongTin.setText("Nhập thông tin:");

        lbMaHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbMaHang.setText("Mã hàng:");

        lbLoaiHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbLoaiHang.setText("Loại hàng:");

        lbSoLuong.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbSoLuong.setText("Số lượng:");

        lbTenHang.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbTenHang.setText("Tên hàng:");

        lbDVT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDVT.setText("Đơn vị tính:");

        lbDonGia.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lbDonGia.setText("Đơn giá:");

        tfMaHang.setEditable(false);

        tfTenHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTenHangActionPerformed(evt);
            }
        });

        cbLoaiHang.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        cbLoaiHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbLoaiHangActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbThongTin)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbMaHang)
                            .addComponent(lbLoaiHang)
                            .addComponent(lbSoLuong))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(tfMaHang)
                            .addComponent(tfSoLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 218, Short.MAX_VALUE)
                            .addComponent(cbLoaiHang, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbTenHang)
                            .addComponent(lbDVT)
                            .addComponent(lbDonGia))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfTenHang, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(tfDVT)
                            .addComponent(tfDonGia))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(lbThongTin)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbTenHang, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(lbMaHang)
                        .addComponent(tfMaHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(tfTenHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbLoaiHang)
                    .addComponent(lbDVT)
                    .addComponent(tfDVT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbLoaiHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbSoLuong)
                    .addComponent(lbDonGia)
                    .addComponent(tfDonGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 10, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        lbChinh.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbChinh.setForeground(new java.awt.Color(204, 0, 0));
        lbChinh.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbChinh.setText("Sửa thông tin kho hàng");

        tableHang.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tableHang);

        btnQuayLai.setBackground(new java.awt.Color(0, 153, 0));
        btnQuayLai.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnQuayLai.setForeground(new java.awt.Color(255, 255, 255));
        btnQuayLai.setText("Quay lại");
        btnQuayLai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQuayLaiActionPerformed(evt);
            }
        });

        btnLuu.setBackground(new java.awt.Color(0, 153, 0));
        btnLuu.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnLuu.setForeground(new java.awt.Color(255, 255, 255));
        btnLuu.setText("Lưu");
        btnLuu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 0));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        lbDanhSach.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        lbDanhSach.setForeground(new java.awt.Color(204, 0, 0));
        lbDanhSach.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbDanhSach.setText("Danh sách hàng hoá");

        lbThongBao.setBackground(new java.awt.Color(255, 153, 153));
        lbThongBao.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        lbThongBao.setForeground(new java.awt.Color(255, 51, 51));
        lbThongBao.setOpaque(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(lbChinh, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(117, 117, 117)
                .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(80, 80, 80)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(79, 79, 79)
                .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(lbDanhSach, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1)
                .addContainerGap())
            .addComponent(lbThongBao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(lbChinh, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLuu, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnQuayLai, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbDanhSach, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(lbThongBao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        try {
            // TODO add your handling code here:
            String maHang = tfMaHang.getText();
            Connection cn = kn.getKetNoiDuLieu();
            PreparedStatement ps = cn.prepareStatement("SELECT MAHH FROM KHOHANG");
            ResultSet rs = ps.executeQuery();
            int i=0; //Kiểm tra trùng
            while(rs.next())
            {
                String s = rs.getString(1);
                if(maHang.compareToIgnoreCase(s)==0)
                {
                    int luachon = JOptionPane.showConfirmDialog(rootPane, "Bạn có chắc chắn xóa?", "Thông báo!", JOptionPane.YES_NO_OPTION);
                    if(luachon==JOptionPane.YES_OPTION)
                    {
                        try {
                            // TODO add your handling code here:
                            ps = cn.prepareStatement("DELETE FROM KHOHANG WHERE MAHH = ?");
                            ps.setString(1, maHang);
                            int rowCount = ps.executeUpdate();
                            // In ra số dòng được trèn vào bởi câu lệnh trên.
                            // lbThongBao.setText("Row Count affected = " + rowCount);
                            DocDS();
                            resetText();
                        } catch (SQLException ex) {
                            Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    i=1;
                    break;
                }
            }
            if(i==0)
            {
                JOptionPane.showMessageDialog(rootPane, "Mặt hàng này chưa có!", "Thông báo!", WIDTH);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tfTenHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTenHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTenHangActionPerformed

    private void btnLuuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuActionPerformed
        // TODO add your handling code here:
        try
        {
            String maHang = tfMaHang.getText();
            
            String tenHang = tfTenHang.getText(); 
            tenHang = tenHang.trim(); //xóa khoảng trắng đầu và cuối
            if(tenHang.equals(""))
                throw new MissingValueException("Tên hàng là gì?");            
            String loaiHang = (String) cbLoaiHang.getSelectedItem();
            
            String DVT = tfDVT.getText(); 
            DVT = DVT.trim(); //xóa khoảng trắng đầu và cuối
            if(DVT.equals(""))
                throw new MissingValueException("Đơn vị tính là gì?");
            for(int i=0; i< DVT.length(); i++)
                if(!(DVT.charAt(i)<'0' || DVT.charAt(i)>'9'))
                    throw new MissingValueException("Đơn vị tính không hợp lệ!");
            
            String soLuong = tfSoLuong.getText();
            soLuong = soLuong.trim(); //xóa khoảng trắng đầu và cuối
            if(soLuong.equals(""))
                throw new MissingValueException("Số lượng bao nhiêu?");
            for(int i=0; i< soLuong.length(); i++)
                if(soLuong.charAt(i)<'0' || soLuong.charAt(i)>'9')
                    throw new MissingValueException("Số lượng không hợp lệ!");
            
            String donGia = tfDonGia.getText();
            donGia = donGia.trim(); //xóa khoảng trắng đầu và cuối
            if(donGia.equals(""))
                throw new MissingValueException("Đơn giá bao nhiêu?");
            for(int i=0; i< donGia.length(); i++)
                if(donGia.charAt(i)<'0' || donGia.charAt(i)>'9')
                    throw new MissingValueException("Đơn giá không hợp lệ!");
            if(Integer.parseInt(donGia)<1000)
                    throw new MissingValueException("Đơn giá phải lớn hơn 1000VND!");
            
            
            Connection cn = kn.getKetNoiDuLieu(); 
            PreparedStatement ps = cn.prepareStatement("SELECT MAHH FROM KHOHANG");
            ResultSet rs = ps.executeQuery();
            int i=0;
            while (rs.next()) {
                String s = rs.getString(1);
                if (maHang.compareToIgnoreCase(s) == 0) {
                    int luachon = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn cập nhật thông tin hàng hóa?", "Thông báo!", JOptionPane.YES_NO_OPTION);
                    if (luachon == 0) {
                        try {
                            ps = cn.prepareStatement("UPDATE KHOHANG SET TENHANG = ?, MALH = ?, DVT = ?, SOLUONG = ?, DONGIA = ? WHERE MAHH = ?");
                            ps.setString(1, tenHang);
                            //Lấy tên loại hàng từ database khác
                            PreparedStatement ps1 = cn.prepareStatement("SELECT MALH FROM LOAIHANG WHERE TENLH = ?");
                            ps1.setString(1, loaiHang);
                            ResultSet rs1 = ps1.executeQuery();
                            while (rs1.next()) {
                                loaiHang = rs1.getString(1);
                            }
                            ps.setString(2, loaiHang);
                            ps.setString(3, DVT);
                            ps.setString(4, soLuong);
                            ps.setString(5, donGia);
                            ps.setString(6, maHang);

                            int rowCount = ps.executeUpdate();
                            //lbThongBao.setText("Row Count affected= " + rowCount);
                            DocDS();
                            resetText();

                        } catch (SQLException ex) {
                            Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                    i = 1;
                    break;
                }
            }
            if (i == 0)
            {
                ps = cn.prepareStatement("INSERT INTO KHOHANG VALUES(?,?,?,?,?,?)");
                ps.setString(1, maHang);
                ps.setString(2, tenHang);
                
                //Lấy tên loại hàng từ database khác
                PreparedStatement ps1 = cn.prepareStatement("SELECT MALH FROM LOAIHANG WHERE TENLH = ?");
                ps1.setString(1, loaiHang);
                ResultSet rs1 = ps1.executeQuery();
                while(rs1.next())
                {
                    loaiHang = rs1.getString(1);
                }
                ps.setString(3, loaiHang);
                ps.setString(4, DVT);
                ps.setString(5, soLuong);
                ps.setString(6, donGia);
                int rowCount = ps.executeUpdate();
                //lbThongBao.setText("Row Count affacted = " + rowCount);
                JOptionPane.showMessageDialog(rootPane, "Thêm hàng thành công!", "Thông báo!", WIDTH);
                DocDS();
                resetText();
            }
            
          
        } catch (SQLException ex) {
            Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(Level.SEVERE, null, ex);
        } catch (MissingValueException ex) {
            lbThongBao.setText(ex.getMessage());
        }
    }//GEN-LAST:event_btnLuuActionPerformed

    private void cbLoaiHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbLoaiHangActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cbLoaiHangActionPerformed

    private void btnQuayLaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQuayLaiActionPerformed
        Jframe_QL j=new Jframe_QL();
        j.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnQuayLaiActionPerformed

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
            java.util.logging.Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Jframe_SuaKhoHang().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(Jframe_SuaKhoHang.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuu;
    private javax.swing.JButton btnQuayLai;
    private javax.swing.JButton btnXoa;
    private javax.swing.JComboBox<String> cbLoaiHang;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbChinh;
    private javax.swing.JLabel lbDVT;
    private javax.swing.JLabel lbDanhSach;
    private javax.swing.JLabel lbDonGia;
    private javax.swing.JLabel lbLoaiHang;
    private javax.swing.JLabel lbMaHang;
    private javax.swing.JLabel lbSoLuong;
    private javax.swing.JLabel lbTenHang;
    private javax.swing.JLabel lbThongBao;
    private javax.swing.JLabel lbThongTin;
    private javax.swing.JTable tableHang;
    private javax.swing.JTextField tfDVT;
    private javax.swing.JTextField tfDonGia;
    private javax.swing.JTextField tfMaHang;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfTenHang;
    // End of variables declaration//GEN-END:variables
}
