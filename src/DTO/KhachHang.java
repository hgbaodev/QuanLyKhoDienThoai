/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class KhachHang {
    private String maKH;
    private String hoten;
    private String sdt;
    private String diachi;

    public KhachHang() {
    }

    public KhachHang(String maKH, String hoten, String sdt, String diachi) {
        this.maKH = maKH;
        this.hoten = hoten;
        this.sdt = sdt;
        this.diachi = diachi;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiachi() {
        return diachi;
    }

    public void setDiachi(String diachi) {
        this.diachi = diachi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.maKH);
        hash = 79 * hash + Objects.hashCode(this.hoten);
        hash = 79 * hash + Objects.hashCode(this.sdt);
        hash = 79 * hash + Objects.hashCode(this.diachi);
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
        final KhachHang other = (KhachHang) obj;
        return true;
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", hoten=" + hoten + ", sdt=" + sdt + ", diachi=" + diachi + '}';
    }
    
}
