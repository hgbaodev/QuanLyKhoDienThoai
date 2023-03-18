package model;

import java.util.Date;
import java.util.Objects;

public class NhanVien {
    private int manhanvien;
    private String hoten;
    private Boolean gioitinh;
    private Date ngaysinh;
    private String sodienthoai;
    private String cccd;
    private String email;

    public NhanVien() {
    }

    public NhanVien(int manhanvien, String hoten, Boolean gioitinh, Date ngaysinh, String sodienthoai, String cccd, String email) {
        this.manhanvien = manhanvien;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sodienthoai = sodienthoai;
        this.cccd = cccd;
        this.email = email;
    }

    public int getManhanvien() {
        return manhanvien;
    }

    public void setManhanvien(int manhanvien) {
        this.manhanvien = manhanvien;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public Boolean getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(Boolean gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSodienthoai() {
        return sodienthoai;
    }

    public void setSodienthoai(String sodienthoai) {
        this.sodienthoai = sodienthoai;
    }

    public String getCccd() {
        return cccd;
    }

    public void setCccd(String cccd) {
        this.cccd = cccd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + this.manhanvien;
        hash = 83 * hash + Objects.hashCode(this.hoten);
        hash = 83 * hash + Objects.hashCode(this.gioitinh);
        hash = 83 * hash + Objects.hashCode(this.ngaysinh);
        hash = 83 * hash + Objects.hashCode(this.sodienthoai);
        hash = 83 * hash + Objects.hashCode(this.cccd);
        hash = 83 * hash + Objects.hashCode(this.email);
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
        final NhanVien other = (NhanVien) obj;
        if (this.manhanvien != other.manhanvien) {
            return false;
        }
        if (!Objects.equals(this.hoten, other.hoten)) {
            return false;
        }
        if (!Objects.equals(this.sodienthoai, other.sodienthoai)) {
            return false;
        }
        if (!Objects.equals(this.cccd, other.cccd)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.gioitinh, other.gioitinh)) {
            return false;
        }
        return Objects.equals(this.ngaysinh, other.ngaysinh);
    }

    @Override
    public String toString() {
        return "NhanVien{" + "manhanvien=" + manhanvien + ", hoten=" + hoten + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + ", sodienthoai=" + sodienthoai + ", cccd=" + cccd + ", email=" + email + '}';
    }
}
