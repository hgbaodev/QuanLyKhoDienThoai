package DTO;

import java.util.Objects;
import java.util.logging.Logger;

public class KhoHang {
    private int makhohang;
    private String tenkhohang;
    private String diachi;
    private String mota;

    public KhoHang() {
    }

    public KhoHang(int makhohang, String tenkhohang, String diachi, String mota) {
        this.makhohang = makhohang;
        this.tenkhohang = tenkhohang;
        this.diachi = diachi;
        this.mota = mota;
    }

    public int getMakhohang() {
        return makhohang;
    }

    public void setMakhohang(int makhohang) {
        this.makhohang = makhohang;
    }

    public String getTenkhohang() {
        return tenkhohang;
    }

    public void setTenkhohang(String tenkhohang) {
        this.tenkhohang = tenkhohang;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    public String getMota() {
        return mota;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.makhohang;
        hash = 61 * hash + Objects.hashCode(this.tenkhohang);
        hash = 61 * hash + Objects.hashCode(this.diachi);
        hash = 61 * hash + Objects.hashCode(this.mota);
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
        final KhoHang other = (KhoHang) obj;
        return true;
    }

    @Override
    public String toString() {
        return "KhoHang{" + "makhohang=" + makhohang + ", tenkhohang=" + tenkhohang + ", diachi=" + diachi + ", mota=" + mota + '}';
    }
   
    
}
