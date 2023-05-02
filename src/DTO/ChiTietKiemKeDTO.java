package DTO;

/**
 *
 * @author robot
 */
public class ChiTietKiemKeDTO {
    private int maphieukiemke;
    private int maphienba;
    private int soluong;
    private int chenhlech;
    private String ghichu;
    
    public ChiTietKiemKeDTO(){
        
    }

    public ChiTietKiemKeDTO(int maphieukiemke, int maphienba, int soluong, int chenhlech, String ghichu) {
        this.maphieukiemke = maphieukiemke;
        this.maphienba = maphienba;
        this.soluong = soluong;
        this.chenhlech = chenhlech;
        this.ghichu = ghichu;
    }

    public int getMaphieukiemke() {
        return maphieukiemke;
    }

    public void setMaphieukiemke(int maphieukiemke) {
        this.maphieukiemke = maphieukiemke;
    }

    public int getMaphienba() {
        return maphienba;
    }

    public void setMaphienba(int maphienba) {
        this.maphienba = maphienba;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getChenhlech() {
        return chenhlech;
    }

    public void setChenhlech(int chenhlech) {
        this.chenhlech = chenhlech;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    @Override
    public String toString() {
        return "ChiTietKiemKeDTO{" + "maphieukiemke=" + maphieukiemke + ", maphienba=" + maphienba + ", soluong=" + soluong + ", chenhlech=" + chenhlech + ", ghichu=" + ghichu + '}';
    }
}
