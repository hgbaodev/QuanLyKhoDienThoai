/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CTPhieuChuyen {
    private int maphieuchuyen;
    private int masanpham;
    private int soluong;
    private double dongia;

    public CTPhieuChuyen() {
    }

    public CTPhieuChuyen(int maphieuchuyen, int masanpham, int soluong, double dongia) {
        this.maphieuchuyen = maphieuchuyen;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieuchuyen() {
        return maphieuchuyen;
    }

    public void setMaphieuchuyen(int maphieuchuyen) {
        this.maphieuchuyen = maphieuchuyen;
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
        hash = 53 * hash + this.maphieuchuyen;
        hash = 53 * hash + this.masanpham;
        hash = 53 * hash + this.soluong;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.dongia) ^ (Double.doubleToLongBits(this.dongia) >>> 32));
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
        final CTPhieuChuyen other = (CTPhieuChuyen) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ctphieuchuyen{" + "maphieuchuyen=" + maphieuchuyen + ", masanpham=" + masanpham + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }
    
}
