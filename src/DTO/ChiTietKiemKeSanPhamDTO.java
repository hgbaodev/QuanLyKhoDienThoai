package DTO;

import java.util.Objects;

/**
 *
 * @author robot
 */
public class ChiTietKiemKeSanPhamDTO {
    private int maphieukiemke;
    private int maphienban;
    private String maimei;
    private int trangthai;

    public ChiTietKiemKeSanPhamDTO() {
    }

    public ChiTietKiemKeSanPhamDTO(int maphieukiemke, int maphienban, String maimei, int trangthai) {
        this.maphieukiemke = maphieukiemke;
        this.maphienban = maphienban;
        this.maimei = maimei;
        this.trangthai = trangthai;
    }

    public int getMaphieukiemke() {
        return maphieukiemke;
    }

    public void setMaphieukiemke(int maphieukiemke) {
        this.maphieukiemke = maphieukiemke;
    }

    public int getMaphienban() {
        return maphienban;
    }

    public void setMaphienban(int maphienban) {
        this.maphienban = maphienban;
    }

    public String getMaimei() {
        return maimei;
    }

    public void setMaimei(String maimei) {
        this.maimei = maimei;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + this.maphieukiemke;
        hash = 37 * hash + this.maphienban;
        hash = 37 * hash + Objects.hashCode(this.maimei);
        hash = 37 * hash + this.trangthai;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ChiTietKiemKeSanPhamDTO other = (ChiTietKiemKeSanPhamDTO) obj;
        if (this.maphieukiemke != other.maphieukiemke) {
            return false;
        }
        if (this.maphienban != other.maphienban) {
            return false;
        }
        if (this.trangthai != other.trangthai) {
            return false;
        }
        return Objects.equals(this.maimei, other.maimei);
    }

    
    
    @Override
    public String toString() {
        return "ChiTietKiemKeSanPhamDTO{" + "maphieukiemke=" + maphieukiemke + ", maphienban=" + maphienban + ", maimei=" + maimei + ", trangthai=" + trangthai + '}';
    }
    
    
    
}
