/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_KH;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KetNoi_CSDL {
    public Connection cn;
    public PreparedStatement ps;
    public ResultSet rs ;
    public Statement sm;
    
    public void Open(){
      
        try {
            String url="jdbc:sqlserver://;databaseName=QUANLYBANHANG";
            String name="sa";
            String password="123456";
            cn=DriverManager.getConnection(url, name, password);
            System.out.println("Ket noi thanh cong");
        } catch (Exception e) {
            System.out.println("Khong the ket noi");
        }
    }
    public void Close() throws SQLException{
        if(ps!= null)
            ps.close();
        if(rs !=null)
            rs.close();
        if(sm != null)
            sm.close();
    }

    public Connection KetNoi() {
        try {
            String uRL = "jdbc:sqlserver://localhost:1433;databaseName=QUANLYBANHANG";
            String user = "sa";
            String pass = "123456";
            
            cn = DriverManager.getConnection(uRL, user, pass);
            System.err.println("Kết nối thành công!");
        } catch (SQLException e) {
            System.err.println("Kết nối thất bại!");
        }
        return cn;
    }
    
    //Quang
    private Connection cnn;
    public Connection getKetNoiDuLieu()
    {
        try
        {
            String uRL = "jdbc:sqlserver://;databaseName=QUANLYBANHANG";
            String user = "sa";
            String pass = "123456";
            cnn = DriverManager.getConnection(uRL, user, pass);
            System.err.println("Kết nối thành công!");
        }
        catch(Exception e)
        {
            System.err.println("Kết nối thất bại.");
        }
        return cnn;
    }
    
}
    
