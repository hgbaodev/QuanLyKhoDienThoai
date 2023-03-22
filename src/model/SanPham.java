/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author 84907
 */
public class SanPham {
    private int masp;
    private String tensp;
    private String xuatxu;
    private double gianhap;
    private double giaban;
    private String hinhanh;
    private int maDVT;
    private int maloaihang;
    private int makhuvuc;
    

    public SanPham() {
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public double getGianhap() {
        return gianhap;
    }

    public void setGianhap(double gianhap) {
        this.gianhap = gianhap;
    }

    public double getGiaban() {
        return giaban;
    }

    public void setGiaban(double giaban) {
        this.giaban = giaban;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public int getMaDVT() {
        return maDVT;
    }

    public void setMaDVT(int maDVT) {
        this.maDVT = maDVT;
    }

    public int getMaloaihang() {
        return maloaihang;
    }

    public void setMaloaihang(int maloaihang) {
        this.maloaihang = maloaihang;
    }

    public int getMakhuvuc() {
        return makhuvuc;
    }

    public void setMakhuvuc(int makhuvuc) {
        this.makhuvuc = makhuvuc;
    }

    public SanPham(int masp, String tensp, String xuatxu, double gianhap, double giaban, String hinhanh, int maDVT, int maloaihang, int makhuvuc) {
        this.masp = masp;
        this.tensp = tensp;
        this.xuatxu = xuatxu;
        this.gianhap = gianhap;
        this.giaban = giaban;
        this.hinhanh = hinhanh;
        this.maDVT = maDVT;
        this.maloaihang = maloaihang;
        this.makhuvuc = makhuvuc;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + this.masp;
        hash = 47 * hash + Objects.hashCode(this.tensp);
        hash = 47 * hash + Objects.hashCode(this.xuatxu);
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.gianhap) ^ (Double.doubleToLongBits(this.gianhap) >>> 32));
        hash = 47 * hash + (int) (Double.doubleToLongBits(this.giaban) ^ (Double.doubleToLongBits(this.giaban) >>> 32));
        hash = 47 * hash + Objects.hashCode(this.hinhanh);
        hash = 47 * hash + this.maDVT;
        hash = 47 * hash + this.maloaihang;
        hash = 47 * hash + this.makhuvuc;
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
        final SanPham other = (SanPham) obj;
        return true;
    }

    @Override
    public String toString() {
        return "SanPham{" + "masp=" + masp + ", tensp=" + tensp + ", xuatxu=" + xuatxu + ", gianhap=" + gianhap + ", giaban=" + giaban + ", hinhanh=" + hinhanh + ", maDVT=" + maDVT + ", maloaihang=" + maloaihang + ", makhuvuc=" + makhuvuc + '}';
    }

}

    