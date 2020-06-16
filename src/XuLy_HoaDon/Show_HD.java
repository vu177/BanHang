/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_HoaDon;

import XuLy_KH.KetNoi_CSDL;
import XuLy_NV.NhanVien;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BiaGhi ^^
 */
public class Show_HD extends KetNoi_CSDL {

    public ArrayList<HoaDon> getListHD() {
        ArrayList<HoaDon> list = new ArrayList<>();
        String sql = "select *from dbo.HOADON ";
        try {
            Open();
            sm = cn.createStatement();
            rs = sm.executeQuery(sql);
            while (rs.next()) {

                HoaDon hd = new HoaDon();
                hd.setMahoadon(rs.getString(1));
                hd.setManv(rs.getString(2));
                hd.setMakh(rs.getString(3));
                hd.setNgaymua(rs.getString(4));
                hd.setTongtien(rs.getInt(5));
                list.add(hd);
                System.out.println("Lay danh sach thanh cong.");

            }
            Close();
        } catch (SQLException e) {
        }
        return list;
    }

    public int ThemHoaDon(HoaDon hd) {
        int count = 0;
        String sql = "insert into HOADON values(?,?,?,?,?)";
        try {
            Open();
            ps = cn.prepareStatement(sql);
            ps.setString(1, hd.getMahoadon());
            ps.setString(2, hd.getManv());
            ps.setString(3, hd.getMakh());
            ps.setString(4, hd.getNgaymua());
            ps.setInt(5, hd.getTongtien());
            count = ps.executeUpdate();
            Close();
            return count;

        } catch (Exception e) {

        }
        return -1;
    }

    public int ThemCTHD(CTHD cthd) {
        try {
            int count = 0;
            String sql = "insert into CTHD values(?,?,?,?,?)";
            Open();
            ps = cn.prepareStatement(sql);
            PreparedStatement ps1 = cn.prepareStatement("SELECT MAX(MAHD) FROM HOADON");
            ResultSet rs1 = ps1.executeQuery();
            while (rs1.next()) {
                cthd.maHD = rs1.getString(1);
            }
            ps.setString(1, cthd.getMaHD());
            ps.setString(2, cthd.getMaHH());
            ps.setInt(3, cthd.soLuong);
            ps.setInt(4, cthd.donGia);
            ps.setInt(5, cthd.thanhTien);
            count = ps.executeUpdate();
            Close();
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(Show_HD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }
}
