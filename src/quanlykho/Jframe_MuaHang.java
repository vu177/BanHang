package quanlykho;

import XuLy_HoaDon.CTHD;
import XuLy_HoaDon.HoaDon;
import XuLy_HoaDon.Show_HD;
import XuLy_KH.KetNoi_CSDL;
import XuLy_KH.KhachHang;
import XuLy_KH.ShowKH;
import XuLy_Kho.MatHang;
import XuLy_Kho.Show_Kho;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import static quanlykho.Jframe_BH.SL;
import static quanlykho.Jframe_BH.SL1;
import static quanlykho.Jframe_HD.kn;
import static quanlykho.Jframe_SuaKhoHang.kn;
import static quanlykho.Main.taikhoan;

public class Jframe_MuaHang extends javax.swing.JFrame {

    static KetNoi_CSDL kn = new KetNoi_CSDL();
    public static Jframe_MuaHang muahang;
    public static String str1;
    public static String str2;
    public static String str3;
    public static String str4;
    public static String str5;
    public static String str6;
    public static int str7;
    public static int index;
    public static Jframe_HD HD = new Jframe_HD();
    public static Jframe_BH BH = new Jframe_BH();
    DefaultTableModel model = new DefaultTableModel();
    private Connection conn = null;
    private Statement st = null;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private KhachHang kh;
    private MatHang mathang = new MatHang();
    private ArrayList<MatHang> list = new ArrayList<>();
    Locale localeVN = new Locale("vi", "VN");
    //Định dạng số
    NumberFormat vn = NumberFormat.getInstance(localeVN);
    //Hóa đơn
    public static HoaDon hoaDon;
    //Chi tiết hóa đơn
    public static CTHD cthd;

    public Jframe_MuaHang() {
        muahang = this;
        initComponents();
        txtNV.setText(taikhoan);
        this.table.setModel(model);
        addCol();

        //xóa dòng trên jtable
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (table.getSelectedRow() >= 0) {
                    index = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 0).toString());
                }
//                //giá trị bị thay đổi
//                if (e.getValueIsAdjusting()) {
//                    System.out.println("Chọn dòng");
//                }

            }
        });

    }

    public void CongHang() throws SQLException {
        for (int i = 0; i < table.getRowCount() - 1; i++) {
            String mahh = table.getValueAt(i, 0).toString();
            for (int j = i + 1; j < table.getRowCount(); j++) {
                if (table.getValueAt(j, 0).toString().compareTo(mahh) == 0) {
                    table.setValueAt(Integer.parseInt(table.getValueAt(i, 5).toString()) + Integer.parseInt(table.getValueAt(j, 5).toString()), i, 5);
                    Connection cn = kn.getKetNoiDuLieu();
                    PreparedStatement ps = cn.prepareStatement("SELECT DONGIA FROM KHOHANG WHERE MAHH = ?");
                    ps.setString(1, mahh);
                    ResultSet rs = ps.executeQuery();
                    while (rs.next()) {
                        String donGia = rs.getString(1);
                        table.setValueAt(Integer.parseInt(table.getValueAt(i, 5).toString()) * Integer.parseInt(donGia), i, 6);
                    }
                    model.removeRow(j);
                    table.setModel(model);
                }
            }
        }
    }

    public void addCol() {
        model.addColumn("Mã Hàng");
        model.addColumn("Tên Hàng");
        model.addColumn("Loại Hàng");
        model.addColumn("Đơn Vị Tính");
        model.addColumn("Đơn Giá");
        model.addColumn("Số Lượng");
        model.addColumn("Thành Tiền");

    }

    public void AddRow() {
        Object[] gtri = new Object[7]; //1 dòng của table
        gtri[0] = str1;
        gtri[1] = str2;
        gtri[2] = str3;
        gtri[3] = str4;
        gtri[4] = str5;
        gtri[5] = str6;
        gtri[6] = str7;

        model.addRow(gtri);
        table.setModel(model);
    }

    public void Result() {
        txtMa.setText("");
        txtTen.setText("");
        txtDC.setText("");
        txtSDT.setText("");
    }

    //chuẩn hóa danh tên
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

    //tính tổng tiền khách cần phải trả
    public void TongTien() {
        double sum = 0;

        for (int i = 0; i < table.getRowCount(); i++) {
            int sumRow = 0;
            sumRow = (Integer.parseInt(table.getValueAt(i, 4).toString())) * (Integer.parseInt(table.getValueAt(i, 5).toString()));
            sum += sumRow;
        }
        sum = (sum + (sum * 0.1));
        HD.string3 = Integer.toString((int) sum);
        txtTT.setText(vn.format(sum));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFrame1 = new javax.swing.JFrame();
        sđ = new javax.swing.JInternalFrame();
        jLabel9 = new javax.swing.JLabel();
        btnX = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        txtSL = new javax.swing.JTextField();
        btnC = new javax.swing.JButton();
        btnK = new javax.swing.JButton();
        jInternalFrame1 = new javax.swing.JInternalFrame();
        jLabel1 = new javax.swing.JLabel();
        txtNV = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        table = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtTT = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btnMua = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtMa = new javax.swing.JTextField();
        txtTen = new javax.swing.JTextField();
        txtDC = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        btnQL = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();

        jFrame1.setBounds(new java.awt.Rectangle(0, 0, 400, 250));

        sđ.setVisible(true);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Bạn muốn xóa mặt hàng này ?\n");

        btnX.setText("Xóa");
        btnX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("Bạn muốn thay số lượng mặt hàng .Xin nhập số lượng :");

        btnC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnC.setText("CÓ");
        btnC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCActionPerformed(evt);
            }
        });

        btnK.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnK.setText("KHÔNG");
        btnK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnKActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sđLayout = new javax.swing.GroupLayout(sđ.getContentPane());
        sđ.getContentPane().setLayout(sđLayout);
        sđLayout.setHorizontalGroup(
            sđLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, 404, Short.MAX_VALUE)
            .addGroup(sđLayout.createSequentialGroup()
                .addGroup(sđLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(sđLayout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnX))
                    .addGroup(sđLayout.createSequentialGroup()
                        .addGap(117, 117, 117)
                        .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(sđLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(btnC, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnK)
                .addGap(36, 36, 36))
        );
        sđLayout.setVerticalGroup(
            sđLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sđLayout.createSequentialGroup()
                .addGroup(sđLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnX))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtSL, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(sđLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnC)
                    .addComponent(btnK))
                .addGap(0, 55, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jFrame1Layout = new javax.swing.GroupLayout(jFrame1.getContentPane());
        jFrame1.getContentPane().setLayout(jFrame1Layout);
        jFrame1Layout.setHorizontalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sđ)
        );
        jFrame1Layout.setVerticalGroup(
            jFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sđ, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jInternalFrame1.setResizable(true);
        jInternalFrame1.setTitle("GIỏ hàng");
        jInternalFrame1.setVisible(true);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 0, 0));
        jLabel1.setText("Nhân Viên:");

        txtNV.setEditable(false);
        txtNV.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        jScrollPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jScrollPane1MouseEntered(evt);
            }
        });

        table.setModel(new javax.swing.table.DefaultTableModel(
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
        table.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                tableMouseEntered(evt);
            }
        });
        table.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tableKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(table);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(204, 0, 0));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Danh sách mặt hàng chọn mua");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 0, 0));
        jLabel3.setText("Tổng Tiền (Bao gồm VAT):");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("VND");

        btnMua.setBackground(new java.awt.Color(0, 153, 0));
        btnMua.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        btnMua.setForeground(new java.awt.Color(255, 255, 255));
        btnMua.setActionCommand("Thanh Toán");
        btnMua.setLabel("Thanh Toán");
        btnMua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMuaActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 153, 0));
        jLabel5.setText("Mã KH :");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 153, 0));
        jLabel6.setText("Họ Tên :");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 153, 0));
        jLabel7.setText("Địa Chỉ :");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 153, 0));
        jLabel8.setText("SĐT :");

        txtMa.setEditable(false);
        txtMa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtTen.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtDC.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txtSDT.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtSDT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSDTKeyPressed(evt);
            }
        });

        btnQL.setBackground(new java.awt.Color(255, 255, 255));
        btnQL.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnQL.setForeground(new java.awt.Color(0, 153, 0));
        btnQL.setText("Quay Lại");
        btnQL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQLActionPerformed(evt);
            }
        });

        btnXoa.setBackground(new java.awt.Color(255, 255, 255));
        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        btnXoa.setForeground(new java.awt.Color(0, 153, 0));
        btnXoa.setText("Xóa Hàng Hóa");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jInternalFrame1Layout = new javax.swing.GroupLayout(jInternalFrame1.getContentPane());
        jInternalFrame1.getContentPane().setLayout(jInternalFrame1Layout);
        jInternalFrame1Layout.setHorizontalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 650, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtMa))
                            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(26, 26, 26)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtSDT)
                            .addComponent(txtDC)))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(btnQL, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(62, 62, 62)
                        .addComponent(btnXoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnMua, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)))
                .addContainerGap())
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jInternalFrame1Layout.setVerticalGroup(
            jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jInternalFrame1Layout.createSequentialGroup()
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(txtMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8)
                    .addComponent(txtTen, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(btnMua, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jInternalFrame1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(jInternalFrame1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnQL, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jInternalFrame1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnMuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMuaActionPerformed

        String sdt = txtSDT.getText().trim();
        String ma = txtMa.getText().trim();
        String ten = txtTen.getText().trim();
        String dc = txtDC.getText().trim();
        conn = kn.getKetNoiDuLieu();

        //ghi thông tin khách hàng vào cở sở dữ liệu
        if (txtTen.getText().length() == 0 && txtDC.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập thông tin khách hàng", "Thông báo", WIDTH);
        } else if (txtTen.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập tên khách hàng", "Thông báo", WIDTH);
        } else if (txtDC.getText().length() == 0) {
            JOptionPane.showMessageDialog(rootPane, "Bạn chưa nhập địa chỉ khách hàng", "Thông báo", WIDTH);
        } else {
            //cập nhật lại số lượng sau khi chọn mua thành công       
            if (table.getRowCount() <= 0) {
                JOptionPane.showMessageDialog(rootPane, "Bạn chưa có mặt hàng nào.", "Thông báo!", WIDTH);
            } else {
                int SLCL;
                try {
                    int dem = 0;
                    for (int i = 0; i < table.getRowCount(); i++) {
                        SL = Integer.parseInt(table.getValueAt(i, 5).toString());
                        String sqlSL = "select SOLUONG from KHOHANG  where MAHH=?";
                        ps = conn.prepareStatement(sqlSL);
                        ps.setString(1, table.getValueAt(i, 0) + "");
                        rs = ps.executeQuery();
                        while (rs.next()) {
                            SL1 = Integer.parseInt(rs.getString(1));
                        }
                        if (SL1 - SL < 0) {
                            JOptionPane.showMessageDialog(rootPane, "Không đủ số lượng. Trong kho còn " + SL1, "Thông báo", WIDTH);
                        } else {
                            SLCL = SL1 - SL;
                            String sql = "update KHOHANG set SOLUONG = " + SLCL + " where MAHH=?";
                            ps = conn.prepareStatement(sql);
                            ps.setString(1, table.getValueAt(i, 0) + "");
                            ps.executeUpdate();
                            //reset để nhận giá trị mới.
                            SLCL = 0;
                            SL = 0;
                            dem++;//so luong tung mat hang phu hơp bien se tang
                        }
                    }
                    //nếu tất cả số lượng cần mua đều phù hợp thì bắt đầu ghi khách hàng vào CSDL

                    if (dem == table.getRowCount()) {
                        //lấy dữ liệu từ textfiled lưu vào một đối tượng kh
                        kh = new KhachHang();
                        kh.setMaKH(ma);
                        kh.setHotenKH(chuanHoaDanhTuRieng(ten));
                        kh.setDiachiKH(chuanHoaDanhTuRieng(dc));
                        kh.setSdtKH(sdt);

                        ShowKH show = new ShowKH();
                        int check = show.Them_KH(kh);

                        if (check == -1) {
                            //khách hàng đã có trong danh sách thì k thêm
                            Result();
                        } else {
                            JOptionPane.showMessageDialog(rootPane, "Bạn đã thêm thành công", "Thông báo", WIDTH);
                            Result();
                        }
                        //load lại đơn giá và đơn vị tính của từng mặt hàng khi thanh toán
                        for (int i = 0; i < table.getRowCount(); i++) {
                            String sqlMH = "select DONGIA from KHOHANG where MAHH = ?";

                            try {
                                ps = conn.prepareStatement(sqlMH);
                                ps.setString(1, table.getValueAt(i, 0) + "");
                                rs = ps.executeQuery();
                                HD.mahang = table.getValueAt(i, 0) + "";
                                HD.tenhang = table.getValueAt(i, 1) + "";
                                HD.loaihang = table.getValueAt(i, 2) + "";
                                HD.SL_hang = Integer.parseInt(table.getValueAt(i, 5).toString());
                                HD.donvitinh = table.getValueAt(i, 3) + "";
                                if (rs.next()) {
                                    HD.dongia = rs.getString(1);
                                    
                                    HD.thanhtien = Long.parseLong(table.getValueAt(i, 5).toString()) * Integer.parseInt(rs.getString(1));
                                }
                                HD.hoadon.AddRow1();

                            } catch (SQLException ex) {
                                Logger.getLogger(Jframe_MuaHang.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }

                        //  lấy dữ liệu qua Jframe Hóa Đơn 
                        String sqlKH = "select MAKH from KHACHHANG where SDT=" + sdt;
                        ps = conn.prepareStatement(sqlKH);
                        rs = ps.executeQuery();
                        if (rs.next()) {
                            HD.string1 = (rs.getString(1));
                        }
                        Date today = new Date(System.currentTimeMillis());
                        SimpleDateFormat timeFormat = new SimpleDateFormat(" yyyy.MM.dd  hh:mm:ss a");
                        HD.string2 = timeFormat.format(today.getTime());
                        // gọi jframe hóa đơn
                        HD.hoadon.GetStr();
                        HD.setVisible(true);
                        dispose();

                        //Ghi Hóa đơn, CTHD
                        try {
                            conn = kn.getKetNoiDuLieu();
                            //ghi vào một đối tượng hóa đơn
                            hoaDon = new HoaDon();
                            hoaDon.setMahoadon("");
                            hoaDon.setManv(txtNV.getText());
                            hoaDon.setMakh(ma);

                            //Lấy ngày mua
                            String ngay;
                            ngay = timeFormat.format(today.getTime());
                            hoaDon.setNgaymua(ngay);
                            hoaDon.setTongtien(Integer.parseInt(HD.string3));
                            //ghi vào CSDL bảng hóa đơn
                            Show_HD showHD = new Show_HD();

                            int checkHD = showHD.ThemHoaDon(hoaDon);
                            if (checkHD == -1) {
                                //JOptionPane.showMessageDialog(rootPane, "In hóa đơn không thành công", "Thông báo", WIDTH);
                            } else {
                                //JOptionPane.showMessageDialog(rootPane, "In hóa đơn thành công", "Thông báo", WIDTH);
                            }

                            // ghi vào CSDL bảng chi tiết hóa đơn
                            for (int i = 0; i < table.getRowCount(); i++) {
                                String mahang = (String) table.getValueAt(i, 0);
                                String mahd = "";
                                int soluong = Integer.parseInt(table.getValueAt(i, 5).toString());
                                int dongia = Integer.parseInt(table.getValueAt(i, 4).toString());
                                String tt = table.getValueAt(i, 6).toString();
                                double t_t = Double.parseDouble(tt);

                                //Lấy một đối tượng để lưu vào
                                cthd = new CTHD();
                                cthd.setMaHD(mahd);
                                cthd.setMaHH(mahang);
                                cthd.setSoLuong(soluong);
                                cthd.setDonGia(dongia);
                                cthd.setThanhTien((int) t_t);
                                Show_HD them = new Show_HD();
                                int kt = them.ThemCTHD(cthd);
                                if (kt == -1) {
                                    //JOptionPane.showMessageDialog(rootPane, "Ghi vào CTHD không thành công", "Thông báo", WIDTH);
                                } else {
                                    //JOptionPane.showMessageDialog(rootPane, "Ghi vào CTHD thành công", "Thông báo", WIDTH);

                                }
                            }
             // sau khi in hoa don thanh cong reset lai gio hang
               model.setRowCount(0);
               table.setModel(model);
                            if (ps != null) {
                                ps.close();
                            }
                            if (rs != null) {
                                rs.close();
                            }
                            if (st != null) {
                                st.close();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Jframe_HD.class.getName()).log(Level.SEVERE, null, ex);
                        }

                        //đóng kết nối
                        try {
                            if (ps != null) {
                                ps.close();
                            }
                            if (rs != null) {
                                rs.close();
                            }
                            if (st != null) {
                                st.close();
                            }
                        } catch (SQLException ex) {
                            Logger.getLogger(Jframe_DN.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Jframe_BH.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }


    }//GEN-LAST:event_btnMuaActionPerformed

    private void btnQLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQLActionPerformed

        BH.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnQLActionPerformed

    private void txtSDTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSDTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {

            conn = kn.getKetNoiDuLieu();
            //xử lý xem khách hàng đã có trog cơ sở dữ liệu chưa
            String sdt = txtSDT.getText().trim();
            String ma = txtMa.getText().trim();
            String ten = txtTen.getText().trim();
            String dc = txtDC.getText().trim();
            String sql = "select * from KHACHHANG where SDT=? ";
            if (txtSDT.getText().length() != 10 || sdt.charAt(0) != '0') {
                JOptionPane.showMessageDialog(this, "Nhập sai số điện thoại. SDT bắt đầu bằng chữ số 0 và gồm 10 chữ số.", "Thông báo", WIDTH);
            } else {
                try {

                    ps = conn.prepareStatement(sql);
                    ps.setString(1, sdt);
                    rs = ps.executeQuery();
                    if (rs.next()) {
                        //lấy dữ liệu in ra textfield
                        txtMa.setText(rs.getString("MAKH"));
                        txtTen.setText(rs.getString("HOTEN"));
                        txtDC.setText(rs.getString("DIACHI"));
                        
                    } else {
                        JOptionPane.showMessageDialog(rootPane, "Khách hàng mới.");
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Jframe_MuaHang.class.getName()).log(Level.SEVERE, null, ex);
                }
                //đóng kết nối
                try {
                    if (ps != null) {
                        ps.close();
                    }
                    if (rs != null) {
                        rs.close();
                    }
                    if (st != null) {
                        st.close();
                    }
                } catch (SQLException ex) {
                    Logger.getLogger(Jframe_DN.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_txtSDTKeyPressed

    private void tableKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tableKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            int sl = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 5).toString());
            int DG = Integer.parseInt(table.getValueAt(table.getSelectedRow(), 4).toString());
            int tongtien = sl * DG;
            table.setValueAt(tongtien, table.getSelectedRow(), 6);
            TongTien();
        }
    }//GEN-LAST:event_tableKeyPressed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        int option = JOptionPane.showConfirmDialog(rootPane, "Bạn có muốn xóa hàng hóa không ?", "Xóa", JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            model.removeRow(index);
            table.setModel(model);
            TongTien();
        }

    }//GEN-LAST:event_btnXoaActionPerformed

    private void jScrollPane1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseEntered

    }//GEN-LAST:event_jScrollPane1MouseEntered

    private void tableMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseEntered

    }//GEN-LAST:event_tableMouseEntered

    private void jScrollPane1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jScrollPane1MouseClicked

    }//GEN-LAST:event_jScrollPane1MouseClicked

    private void tableMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableMouseClicked
//       jFrame1.setVisible(true);

    }//GEN-LAST:event_tableMouseClicked

    private void btnXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXActionPerformed
        model.removeRow(index);
        table.setModel(model);
        TongTien();
        jFrame1.dispose();
    }//GEN-LAST:event_btnXActionPerformed

    private void btnKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKActionPerformed
        dispose();
    }//GEN-LAST:event_btnKActionPerformed

    private void btnCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCActionPerformed
        //int sl_TD =Integer.parseInt(txtSL.getText().toString());
        if (table.getSelectedRow() > 0) {
            int sl = Integer.parseInt((txtSL.getText().toString()));
            int DG = Integer.parseInt(table.getValueAt(table.getRowCount(), 4).toString());
            int tongtien = sl * DG;
            table.setValueAt(tongtien, table.getRowCount(), 6);
            table.setValueAt(sl, table.getRowCount(), 5);
        }
        jFrame1.dispose();
    }//GEN-LAST:event_btnCActionPerformed

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
            java.util.logging.Logger.getLogger(Jframe_MuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Jframe_MuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Jframe_MuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Jframe_MuaHang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Jframe_MuaHang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnC;
    private javax.swing.JButton btnK;
    private javax.swing.JButton btnMua;
    private javax.swing.JButton btnQL;
    private javax.swing.JButton btnX;
    private javax.swing.JButton btnXoa;
    private javax.swing.JFrame jFrame1;
    private javax.swing.JInternalFrame jInternalFrame1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JInternalFrame sđ;
    private javax.swing.JTable table;
    private javax.swing.JTextField txtDC;
    private javax.swing.JTextField txtMa;
    private javax.swing.JTextField txtNV;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtSL;
    private javax.swing.JTextField txtTT;
    private javax.swing.JTextField txtTen;
    // End of variables declaration//GEN-END:variables

//    
    //Bắt sự kiện bảng bị thay đổi (implements TableModelListener; table.getModel().addTableModelListener(this);)
//    @Override
//    public void tableChanged(TableModelEvent e) {
//        System.out.println("quanlykho.Jframe_MuaHang.tableChanged()");
//    }
}
