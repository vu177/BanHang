/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_HoaDon;

import XuLy_KH.KetNoi_CSDL;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BiaGhi ^^
 */
public class Show_DoanhThu extends KetNoi_CSDL{
    public ArrayList<HoaDon> getListBangDT(){
        ArrayList <HoaDon> list =new ArrayList<>();
        String sql ="select MAHD,NGAYMUA,TONGTIEN from dbo.HOADON ";
        try {
            Open();
            sm =cn.createStatement();
            rs=sm.executeQuery(sql);
            while(rs.next()){
                
                HoaDon bangdoanhthu =new HoaDon();
                bangdoanhthu.setMahoadon(rs.getString(1));
                bangdoanhthu.setNgaymua(rs.getString(2));
                bangdoanhthu.setTongtien(rs.getInt(3));
                list.add(bangdoanhthu);
                System.out.println("Lay danh sach thanh cong.");
             
            }
               Close();
        } catch (SQLException e) {
        }
        return list;
    }
}
