/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_NV;

public class NhanVien {
    public String manv;
    public String hoten;
    public String diachi;
    public String sdt;
    public String matkhau;

    public NhanVien(String manv, String hoten, String diachi, String sdt, String matkhau) {
        this.manv = manv;
        this.hoten = hoten;
        this.diachi = diachi;
        this.sdt = sdt;
        this.matkhau = matkhau;
    }
     public NhanVien() {
        this.manv = "";
        this.hoten = "";
        this.diachi = "";
        this.sdt = "";
        this.matkhau ="";
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getMatkhau() {
        return matkhau;
    }

    public void setMatkhau(String matkhau) {
        this.matkhau = matkhau;
    }
     
     
    
}
