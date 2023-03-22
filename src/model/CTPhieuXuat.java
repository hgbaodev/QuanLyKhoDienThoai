/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CTPhieuXuat {

    private int maphieuxuat;
    private int masanpham;
    private int soluong;
    private double dongia;

    public CTPhieuXuat() {
    }

    public CTPhieuXuat(int maphieuxuat, int masanpham, int soluong, double dongia) {
        this.maphieuxuat = maphieuxuat;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public double getDongia() {
        return dongia;
    }

    public void setDongia(double dongia) {
        this.dongia = dongia;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.maphieuxuat;
        hash = 17 * hash + this.masanpham;
        hash = 17 * hash + this.soluong;
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.dongia) ^ (Double.doubleToLongBits(this.dongia) >>> 32));
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
        final CTPhieuXuat other = (CTPhieuXuat) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CTPhieuXuat{" + "maphieuxuat=" + maphieuxuat + ", masanpham=" + masanpham + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }
    
}
