/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_Kho;

public class MatHang {
    public String maHH;
    public String ten;
    public String loai;
    public String dvt;
    public int soluong;
    public int dongia;

    public MatHang(String maHH, String ten, String loai,String dvt, int soluong, int dongia) {
        this.maHH = maHH;
        this.ten = ten;
        this.loai = loai;
        this.soluong = soluong;
        this.dongia = dongia;
        this.dvt=dvt;
    }
    
    public MatHang() {
        this.maHH = "";
        this.ten = "";
        this.loai = "";
        this.dvt="";
        this.soluong = 0;
        this.dongia = 0;
    }
    public String getMaHH() {
        return maHH;
    }

    public void setMaHH(String maHH) {
        this.maHH = maHH;
    }

    public String getDvt() {
        return dvt;
    }

    public void setDvt(String dvt) {
        this.dvt = dvt;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
    
    
}
