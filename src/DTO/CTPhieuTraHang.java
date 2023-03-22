/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

public class CTPhieuTraHang {
    private int maphieutrahang;
    private int masanpham;
    private int soluong;
    private double dongia;

    public CTPhieuTraHang() {
    }

    public CTPhieuTraHang(int maphieutrahang, int masanpham, int soluong, double dongia) {
        this.maphieutrahang = maphieutrahang;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieutrahang() {
        return maphieutrahang;
    }

    public void setMaphieutrahang(int maphieutrahang) {
        this.maphieutrahang = maphieutrahang;
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
        int hash = 7;
        hash = 31 * hash + this.maphieutrahang;
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
        final CTPhieuTraHang other = (CTPhieuTraHang) obj;
        return true;
    }

    @Override
    public String toString() {
        return "CTPhieuTraHang{" + "maphieutrahang=" + maphieutrahang + ", masanpham=" + masanpham + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }
    
}
