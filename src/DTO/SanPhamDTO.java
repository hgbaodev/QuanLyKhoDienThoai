/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author 84907
 */
public class SanPhamDTO {
    private int masp;
    private String tensp;
    private String xuatxu;
    private double gianhap;
    private double giaxuat;
    private String hinhanh;
    private int maDVT;
    private int maloaihang;
    private int makhuvuc;
    private int soluong;
    

    public SanPhamDTO() {
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

    public double getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(double giaxuat) {
        this.giaxuat = giaxuat;
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

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public SanPhamDTO(int masp, String tensp, String xuatxu, double gianhap, double giaxuat, String hinhanh, int maDVT, int maloaihang, int makhuvuc,int soluong) {
        this.masp = masp;
        this.tensp = tensp;
        this.xuatxu = xuatxu;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.hinhanh = hinhanh;
        this.maDVT = maDVT;
        this.maloaihang = maloaihang;
        this.makhuvuc = makhuvuc;
        this.soluong = soluong;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + this.masp;
        hash = 17 * hash + Objects.hashCode(this.tensp);
        hash = 17 * hash + Objects.hashCode(this.xuatxu);
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.gianhap) ^ (Double.doubleToLongBits(this.gianhap) >>> 32));
        hash = 17 * hash + (int) (Double.doubleToLongBits(this.giaxuat) ^ (Double.doubleToLongBits(this.giaxuat) >>> 32));
        hash = 17 * hash + Objects.hashCode(this.hinhanh);
        hash = 17 * hash + this.maDVT;
        hash = 17 * hash + this.maloaihang;
        hash = 17 * hash + this.makhuvuc;
        hash = 17 * hash + this.soluong;
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
        final SanPhamDTO other = (SanPhamDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "masp=" + masp + ", tensp=" + tensp + ", xuatxu=" + xuatxu + ", gianhap=" + gianhap + ", giaxuat=" + giaxuat + ", hinhanh=" + hinhanh + ", maDVT=" + maDVT + ", maloaihang=" + maloaihang + ", makhuvuc=" + makhuvuc + ", soluong=" + soluong + '}';
    }

    
}

    