/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlykho;

import XuLy_KH.KetNoi_CSDL;
import XuLy_NV.NhanVien;
import XuLy_NV.Show_NV;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
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

/**
 *
 * @author admin
 */
public class Jframe_SuaNV extends javax.swing.JFrame {

    DefaultTableModel tbm=new DefaultTableModel();
    public PreparedStatement ps;
    public ResultSet rs = null;
    public Statement st;
    static KetNoi_CSDL a=new KetNoi_CSDL();
    static Connection cn =a.KetNoi();
    DefaultTableModel tb = null;
    private ArrayList<NhanVien> list= new ArrayList<>();
    private NhanVien nv;
   
    public Jframe_SuaNV() {
        initComponents();
        DocDS();
    }

    public void xoa(){
        
    }
    public void DocDS(){
        Show_NV doc=new Show_NV();
        list=doc.getListNV();
        DefaultTableModel tb =(DefaultTableModel) table.getModel();
        tb.setRowCount(0);
        
        for(NhanVien s:list){
            Vector <Object> vec =new Vector<>();
            vec.add(s.getManv());
            vec.add(s.getHoten());
            vec.add(s.getDiachi());
            vec.add(s.getSdt());
            vec.add(s.getMatkhau());
            tb.addRow(vec);
        }
        table.setModel(tb);
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
    
          
          public void valueChanged(ListSelectionEvent e) {
            if( table.getSelectedRow()>=0){
                txtMaNV.setText(table.getValueAt(table.getSelectedRow(),0)+"");
                txtHoTen.setText(table.getValueAt(table.getSelectedRow(),1)+"");
                //cbLoaiHang.setSelectedItem(table.getModel().getValueAt(table.getSelectedRow(), 2)+"");
                txtDC.setText(table.getValueAt(table.getSelectedRow(),2)+"");
                txtSDT.setText(table.getValueAt(table.getSelectedRow(),3)+"");
                txtMK.setText(table.getValueAt(table.getSelectedRow(),4)+"");
            }   
          }
          
       }
       );
    }
    
    
    public void resetText() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtDC.setText("");
        txtSDT.setText("");
        txtMK.setText("");      
    }
    public void resetTextlabel() {
        jLabel9.setText("");
        jLabel10.setText("");
        jLabel11.setText("");
        jLabel12.setText("");
        jLabel13.setText("");       
    }
            
    private boolean KiemTraTrungMaNV(String manv) {
        //true = k trùng, false là trùng
        boolean kiemtra = true;
//        KetNoi_CSDL ketnoidulieu = new KetNoi_CSDL();
//        Connection cn= ketnoidulieu.KetNoi();

        String sql = "SELECT MANV FROM NHANVIEN";
        try {
            
            Statement sm = cn.createStatement();           
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String maNVKiemTra = rs.getString(1);
                if (maNVKiemTra.equalsIgnoreCase(manv)) {
                    kiemtra = false;
                }
            }
        } catch (SQLException e) {
            System.err.println("Loi : " + e);
        }
        return kiemtra;
    }
    private boolean chuanHoaMaNV()
    {
        String dk = "[A-Z]{2,2}[0-9]{2,2}";// 2 kí tự đầu là chữ. 2 kí tự sau là số
        if(txtMaNV.getText().matches(dk))
        {
            System.out.println("Mã NV hợp lệ!");
            return true;
        }
        else
        {
            System.out.println("Mã NV không hợp lệ!");
        }
        return false;
    }
    private String chuanHoaSDT(String dk)
    {
        dk = "[0-9]{10,10}";// 2 kí tự đầu là chữ. 2 kí tự sau là số
        if(txtSDT.getText().matches(dk))
        {
            System.out.println("SĐT hợp lệ!");
        }
        else
        {
            System.out.println("SĐT không hợp lệ!");
        }
        return dk;
    }
    public String chuanHoa(String str) {
        str = str.trim();
        str = str.replaceAll("\\s+", " ");
        return str;
    }
    public String chuanHoaDanhTuRieng(String str) {
        str = chuanHoa(str);
        String temp[] = str.split(" ");
        str = "";
        for (int i = 0; i < temp.length; i++) {
            str += String.valueOf(temp[i].charAt(0)).toUpperCase() + temp[i].substring(1);
            if (i < temp.length - 1) {
                str += " ";
            }
        }
        return str;
    }
    private boolean testMaNV(String maNV) {
        //true = k trùng, false là trùng
        boolean kiemtra = true;
    
        String sql = "SELECT MANV FROM NHANVIEN";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                String maNVKT = rs.getString(1);
                if (maNVKT.equalsIgnoreCase(maNV)) {
                    kiemtra = false;
                }
            }
        } catch (Exception e) {
            System.err.println("Loi : " + e);
        }
        return kiemtra;
    }
    public static String encrypt(String str) throws NoSuchAlgorithmException, UnsupportedEncodingException{
      String enrStr;
      MessageDigest md = MessageDigest.getInstance("MD5");
      byte[] strByte =str.getBytes("UTF-8");
      byte[] enrStrByte =md.digest(strByte);
      
      BigInteger bigInt= new BigInteger(1,enrStrByte);
      enrStr=bigInt.toString(16);
      
      return enrStr;
    }  
    
   
    public void Update(){
        
        try{         
            PreparedStatement comm=cn.prepareStatement("update dbo.NHANVIEN set HOTEN=?,DIACHI=?,SDT=?,MATKHAU=? where MANV = ?");
            
            comm.setString(1, chuanHoaDanhTuRieng(txtHoTen.getText()));
            comm.setString(2, chuanHoaDanhTuRieng(txtDC.getText()));
            comm.setString(3, txtSDT.getText());
            try {
                comm.setString(4, encrypt(txtMK.getText()));
            } catch (NoSuchAlgorithmException ex) {
                Logger.getLogger(Jframe_SuaNV.class.getName()).log(Level.SEVERE, null, ex);
            } catch (UnsupportedEncodingException ex) {
                Logger.getLogger(Jframe_SuaNV.class.getName()).log(Level.SEVERE, null, ex);
            }
            comm.setString(5, txtMaNV.getText());
            comm.executeUpdate();
            tbm.setRowCount(0);
            DocDS();        
        }catch(SQLException ex){
            System.out.println(ex.toString());
        }       
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtHoTen = new javax.swing.JTextField();
        txtDC = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtMK = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        btnThem = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        btnThoat = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setBackground(new java.awt.Color(204, 255, 255));
        jInternalFrame1.setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 153, 0));
        jLabel1.setText("Thông tin nhân viên");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setText("Sửa thông tin nhân viên");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel3.setText("Mã nhân viên");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel4.setText("Họ tên");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel5.setText("Địa chỉ");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel6.setText("Sđt");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel7.setText("Mật khẩu");

        txtMaNV.setEditable(false);
        txtMaNV.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtHoTen.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtDC.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        txtMK.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 0, 0));
        jLabel8.setText("Danh Sách Nhân Viên");

        table.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "MÃ NV", "Họ Tên", "Địa Chỉ", "SĐT", "Mật Khẩu"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(table);

        btnThem.setBackground(new java.awt.Color(0, 153, 0));
        btnThem.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThem.setForeground(new java.awt.Color(255, 255, 255));
        btnThem.setText("Thêm");
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(0, 153, 0));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(255, 255, 255));
        btnXoa.setText("Xóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        btnSua.setBackground(new java.awt.Color(0, 153, 0));
        btnSua.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnSua.setForeground(new java.awt.Color(255, 255, 255));
        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnThoat.setBackground(new java.awt.Color(0, 153, 0));
        btnThoat.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnThoat.setForeground(new java.awt.Color(255, 255, 255));
        btnThoat.setText("Thoát");
        btnThoat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThoatActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 613, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNV)
                    .addComponent(txtHoTen)
                    .addComponent(txtDC)
                    .addComponent(txtSDT)
                    .addComponent(txtMK, javax.swing.GroupLayout.DEFAULT_SIZE, 353, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addComponent(btnThem)
                        .addGap(60, 60, 60)
                        .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(64, 64, 64)
                        .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(66, 66, 66)
                        .addComponent(btnThoat))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(209, 209, 209)
                        .addComponent(jLabel8))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(197, 197, 197)
                        .addComponent(jLabel2)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(14, 14, 14)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtMK, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThoat, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jInternalFrame1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed

try{
            PreparedStatement comm=cn.prepareStatement("Delete dbo.NHANVIEN where MANV=?");
            comm.setString(1,table.getValueAt(table.getSelectedRow(),0).toString());
            int lc = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xoá nhân viên này?","Xoá?",
                    JOptionPane.YES_NO_OPTION);
         switch(lc)
         {
             case -1: return;
             case 0: 
             {
                int chk = comm.executeUpdate();
                comm.executeUpdate();
                tbm.setRowCount(0);
                resetTextlabel();
                resetText();
                JOptionPane.showMessageDialog(this, "Xoá Nhân Viên thành công!","Thông báo!", WIDTH);
                DocDS();
                return;
             }
             case 1: return;
         }
            
            
     }catch(Exception ex){
         System.out.println(ex.toString());
     }
     
        if(testMaNV(txtMaNV.getText()) == true)
        {
            JOptionPane.showMessageDialog(rootPane, "Nhân viên này không có","Thông báo!", WIDTH);
            return;
        }
        try {
            PreparedStatement pt = cn.prepareStatement("Delete dbo.NHANVIEN where MANV=?");
            pt.setString(1, txtMaNV.getText());
            int k = JOptionPane.showConfirmDialog(this, "Bạn chắc chắn xoá nhân viên này?","Delete",JOptionPane.YES_NO_OPTION);
            if(k == 0) pt.executeUpdate();
            resetTextlabel();
            resetText();
            JOptionPane.showMessageDialog(this, "Xoá Nhân Viên thành công!", "Thông báo!", WIDTH);
            DocDS();
            
        } catch (Exception e) {
        }    
    }//GEN-LAST:event_btnXoaActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
   
        if(txtHoTen.getText().equals(""))
        {
            jLabel10.setText("Vui lòng nhập Họ Tên!");
            return;
        }
        if(txtDC.getText().equals(""))
        {
            jLabel11.setText("Vui lòng nhập Địa Chỉ!");
            return;
        }
        if(txtSDT.getText().equals(""))
        {
            jLabel12.setText("Vui lòng nhập Số ĐT!");
            return;
        }
        if(txtMK.getText().equals(""))
        {
            jLabel13.setText("Vui lòng nhập Mật Khẩu!");
            return;
        }

        String sdt=txtSDT.getText().trim();
        if(KiemTraTrungMaNV(txtMaNV.getText()) == true){
            if(txtSDT.getText().length()!=10||sdt.charAt(0)!='0'){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng SĐT 10 số!", "Thông báo!", WIDTH);
            }else{
            try{
                PreparedStatement ps=cn.prepareStatement("INSERT INTO dbo.NHANVIEN VALUES(?,?,?,?,?)");
                ps.setString(1, txtMaNV.getText());
                ps.setString(2, chuanHoaDanhTuRieng(txtHoTen.getText()));
                ps.setString(3, chuanHoaDanhTuRieng(txtDC.getText()));
                ps.setString(4, txtSDT.getText());
                ps.setString(5, encrypt(txtMK.getText()));
                int chk=ps.executeUpdate();
                if(chk>0){
                    JOptionPane.showMessageDialog(this, "Thêm Nhân Viên thành công!", "Thông báo!", WIDTH);
                    tbm.setRowCount(0);
                    DocDS();
                    resetTextlabel();
                    resetText();
                }
            }catch(Exception ex){
                System.out.println(ex.toString());
            }
        }
        }
        else{ 
            int luachon = JOptionPane.showConfirmDialog(this, "Mã nhân viên đã có, bạn muốn cập nhật lại thông tin? " ,"Cập nhật?"
                    , JOptionPane.YES_NO_OPTION);
                if (luachon == 0) {     
                if(txtSDT.getText().length()!=10||sdt.charAt(0)!='0'){
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng SĐT 10 số!", "Thông báo!", WIDTH);
            }else{
                        Update();                      
                        resetTextlabel();
                        resetText();
                        JOptionPane.showMessageDialog(this, "Sửa Nhân Viên thành công!", "Thông báo!", WIDTH);
                 } 
                if (luachon == 1) {
                    DocDS();
                    resetTextlabel();
                    //resetText();
                }
                }
        }
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:

        if(txtHoTen.getText().equals(""))
        {
            jLabel10.setText("Vui lòng nhập Họ Tên!");
            return;
        }
        if(txtDC.getText().equals(""))
        {
            jLabel11.setText("Vui lòng nhập Địa Chỉ!");
            return;
        }
        if(txtSDT.getText().equals(""))
        {
            jLabel12.setText("Vui lòng nhập Số ĐT!");
            return;
        }
        if(txtMK.getText().equals(""))
        {
            jLabel13.setText("Vui lòng nhập Mật Khẩu!");
            return;
        }
         for (int i = 0; i < txtSDT.getText().length(); i++) {
            if (txtSDT.getText().charAt(i) < '0' || txtSDT.getText().charAt(i) > '9') {
                JOptionPane.showMessageDialog(rootPane, "Số lượng chỉ nhập số", "Thông báo", WIDTH);
                txtSDT.setText("");
                return ;
            }
          }
        String sdt=txtSDT.getText().trim();
        if(txtSDT.getText().length()!=10||sdt.charAt(0)!='0'){
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đúng định dạng SĐT 10 số!", "Thông báo!", WIDTH);
        }else{
        Update();
        DocDS();
        resetTextlabel();
        resetText();
        JOptionPane.showMessageDialog(this, "Sửa Nhân Viên thành công!","Thông báo!", WIDTH);
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnThoatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThoatActionPerformed
        Jframe_QL j=new Jframe_QL();
        j.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnThoatActionPerformed

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
            java.util.logging.Logger.getLogger(Jframe_SuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jframe_SuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jframe_SuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jframe_SuaNV.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jframe_SuaNV().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThoat;
    private javax.swing.JButton btnXoa;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtMK;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtSDT;
    // End of variables declaration//GEN-END:variables
}
