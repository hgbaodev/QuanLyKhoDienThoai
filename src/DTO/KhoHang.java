package DTO;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class KhoHang {
    private int makho;
    private String tenkhohang;
    private String diachi;
    private String mota;

    public KhoHang() {
    }

    public KhoHang(int makho, String tenkhohang, String diachi, String mota) {
        this.makho = makho;
        this.tenkhohang = tenkhohang;
        this.diachi = diachi;
        this.mota = mota;
    }

    public int getMakho() {
        return makho;
    }

    public void setMakho(int makho) {
        this.makho = makho;
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
        int hash = 3;
        hash = 37 * hash + this.makho;
        hash = 37 * hash + Objects.hashCode(this.tenkhohang);
        hash = 37 * hash + Objects.hashCode(this.diachi);
        hash = 37 * hash + Objects.hashCode(this.mota);
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
        if (this.makho != other.makho) {
            return false;
        }
        if (!Objects.equals(this.tenkhohang, other.tenkhohang)) {
            return false;
        }
        if (!Objects.equals(this.diachi, other.diachi)) {
            return false;
        }
        return Objects.equals(this.mota, other.mota);
    }

    @Override
    public String toString() {
        return "KhoHang{" + "makho=" + makho + ", tenkhohang=" + tenkhohang + ", diachi=" + diachi + ", mota=" + mota + '}';
    }
    
    
}
