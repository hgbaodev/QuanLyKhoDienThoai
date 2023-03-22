/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CTPhieuNhap {
    private int maphieunhap;
    private int masanpham;
    private int soluong;
    private double dongia;

    public CTPhieuNhap() {
    }

    public CTPhieuNhap(int maphieunhap, int masanpham, int soluong, double dongia) {
        this.maphieunhap = maphieunhap;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
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
        int hash = 3;
        hash = 31 * hash + this.maphieunhap;
        hash = 31 * hash + this.masanpham;
        hash = 31 * hash + this.soluong;
        hash = 31 * hash + (int) (Double.doubleToLongBits(this.dongia) ^ (Double.doubleToLongBits(this.dongia) >>> 32));
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
        final CTPhieuNhap other = (CTPhieuNhap) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CTPhieuNhap{" + "maphieunhap=" + maphieunhap + ", masanpham=" + masanpham + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }
    
}
