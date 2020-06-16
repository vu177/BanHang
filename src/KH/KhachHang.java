/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XuLy_KH;

public class KhachHang {
    public String maKH;
    public String hotenKH;
    public String diachiKH;
    public String sdtKH;

    public KhachHang(String maKH, String hotenKH, String diachiKH, String sdtKH) {
        this.maKH = maKH;
        this.hotenKH = hotenKH;
        this.diachiKH = diachiKH;
        this.sdtKH = sdtKH;
    }
    public KhachHang() {
        this.maKH = "";
        this.hotenKH = "";
        this.diachiKH = "";
        this.sdtKH = "";
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHotenKH() {
        return hotenKH;
    }

    public void setHotenKH(String hotenKH) {
        this.hotenKH = hotenKH;
    }

    public String getDiachiKH() {
        return diachiKH;
    }

    public void setDiachiKH(String diachiKH) {
        this.diachiKH = diachiKH;
    }

    public String getSdtKH() {
        return sdtKH;
    }

    public void setSdtKH(String sdtKH) {
        this.sdtKH = sdtKH;
    }
    
    
}
