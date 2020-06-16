/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_NV;

import XuLy_KH.KetNoi_CSDL;
import java.sql.SQLException;
import java.util.ArrayList;

public class Show_NV extends KetNoi_CSDL{
    
    public ArrayList<NhanVien> getListNV(){
        ArrayList <NhanVien> list =new ArrayList<>();
        String sql ="select *from dbo.NHANVIEN ";
        try {
            Open();
            sm =cn.createStatement();
            rs=sm.executeQuery(sql);
            while(rs.next()){
                
                NhanVien nv =new NhanVien();
                nv.setManv(rs.getString(1));
                nv.setHoten(rs.getString(2));
                nv.setDiachi(rs.getString(3));
                nv.setSdt(rs.getString(4));
                nv.setMatkhau(rs.getString(5));
                list.add(nv);
                System.out.println("Lay danh sach thanh cong.");
             
            }
               Close();
        } catch (SQLException e) {
        }
        return list;
    }
}
