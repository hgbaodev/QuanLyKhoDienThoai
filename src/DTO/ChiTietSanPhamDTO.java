/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietSanPhamDTO {
    private String imei;
    private int maphienbansp;
    private int maphieunhap;
    private int maphieuxuat;
    private int tinhtrang;

    public ChiTietSanPhamDTO() {
    }

    public ChiTietSanPhamDTO(String imei, int maphienbansp, int maphieunhap, int maphieuxuat, int tinhtrang) {
        this.imei = imei;
        this.maphienbansp = maphienbansp;
        this.maphieunhap = maphieunhap;
        this.maphieuxuat = maphieuxuat;
        this.tinhtrang = tinhtrang;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getMaphieunhap() {
        return maphieunhap;
    }

    public void setMaphieunhap(int maphieunhap) {
        this.maphieunhap = maphieunhap;
    }

    public int getMaphieuxuat() {
        return maphieuxuat;
    }

    public void setMaphieuxuat(int maphieuxuat) {
        this.maphieuxuat = maphieuxuat;
    }

    public int getTinhtrang() {
        return tinhtrang;
    }

    public void setTinhtrang(int tinhtrang) {
        this.tinhtrang = tinhtrang;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.imei);
        hash = 37 * hash + this.maphienbansp;
        hash = 37 * hash + this.maphieunhap;
        hash = 37 * hash + this.maphieuxuat;
        hash = 37 * hash + this.tinhtrang;
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
        final ChiTietSanPhamDTO other = (ChiTietSanPhamDTO) obj;
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.maphieunhap != other.maphieunhap) {
            return false;
        }
        if (this.maphieuxuat != other.maphieuxuat) {
            return false;
        }
        if (this.tinhtrang != other.tinhtrang) {
            return false;
        }
        return Objects.equals(this.imei, other.imei);
    }

    @Override
    public String toString() {
        return "ChiTietSanPhamDTO{" + "imei=" + imei + ", maphienbansp=" + maphienbansp + ", maphieunhap=" + maphieunhap + ", maphieuxuat=" + maphieuxuat + ", tinhtrang=" + tinhtrang + '}';
    }
    
    
}
