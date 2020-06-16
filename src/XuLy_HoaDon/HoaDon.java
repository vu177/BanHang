/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_HoaDon;

/**
 *
 * @author BiaGhi ^^
 */
public class HoaDon {
    public String mahoadon;
    public String manv;
    public String makh;
    public String ngaymua;
    public int tongtien;

    public HoaDon(String mahoadon, String manv, String madoanhthu, String makh, String ngaymua, int tongtien) {
        this.mahoadon = mahoadon;
        this.manv = manv;
        this.makh = makh;
        this.ngaymua = ngaymua;
        this.tongtien = tongtien;
    }

    public HoaDon() {
    }

    public String getMahoadon() {
        return mahoadon;
    }

    public void setMahoadon(String mahoadon) {
        this.mahoadon = mahoadon;
    }

    public String getManv() {
        return manv;
    }

    public void setManv(String manv) {
        this.manv = manv;
    }

    public String getMakh() {
        return makh;
    }

    public void setMakh(String makh) {
        this.makh = makh;
    }

    public String getNgaymua() {
        return ngaymua;
    }

    public void setNgaymua(String ngaymua) {
        this.ngaymua = ngaymua;
    }

    public int getTongtien() {
        return tongtien;
    }

    public void setTongtien(int tongtien) {
        this.tongtien = tongtien;
    }

    public void AddRow1() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
