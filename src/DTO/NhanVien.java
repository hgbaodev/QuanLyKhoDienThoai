/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Date;
import java.util.Objects;

public class NhanVien {
    private int manv;
    private String hoten;
    private String gioitinh;
    private String sdt;
    private Date ngaysinh;

    public NhanVien() {
    }

    public NhanVien(int manv, String hoten, String gioitinh, Date ngaysinh ,String sdt) {
        this.manv = manv;
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
    }
    
    public NhanVien(String hoten, String gioitinh, Date ngaysinh ,String sdt) {
        this.hoten = hoten;
        this.gioitinh = gioitinh;
        this.ngaysinh = ngaysinh;
        this.sdt = sdt;
    }

    public int getManv() {
        return manv;
    }

    public void setManv(int manv) {
        this.manv = manv;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getGioitinh() {
        return gioitinh;
    }

    public void setGioitinh(String gioitinh) {
        this.gioitinh = gioitinh;
    }

    public Date getNgaysinh() {
        return ngaysinh;
    }

    public void setNgaysinh(Date ngaysinh) {
        this.ngaysinh = ngaysinh;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.manv;
        hash = 17 * hash + Objects.hashCode(this.hoten);
        hash = 17 * hash + Objects.hashCode(this.gioitinh);
        hash = 17 * hash + Objects.hashCode(this.ngaysinh);
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
        return true;
    }

    @Override
    public String toString() {
        return "NhanVien{" + "manv=" + manv + ", hoten=" + hoten + ", gioitinh=" + gioitinh + ", ngaysinh=" + ngaysinh + '}';
    }
    
}
