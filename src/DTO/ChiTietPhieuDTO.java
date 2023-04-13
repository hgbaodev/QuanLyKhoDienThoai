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
public class ChiTietPhieuDTO {
    private int maphieu;
    private String imei;
    private int dongia;

    public ChiTietPhieuDTO(int maphieu, String imei, int dongia) {
        this.maphieu = maphieu;
        this.imei = imei;
        this.dongia = dongia;
    }

    public ChiTietPhieuDTO() {
    }

    
    
    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public int getDongia() {
        return dongia;
    }

    public void setDongia(int dongia) {
        this.dongia = dongia;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.maphieu;
        hash = 61 * hash + Objects.hashCode(this.imei);
        hash = 61 * hash + this.dongia;
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
        final ChiTietPhieuDTO other = (ChiTietPhieuDTO) obj;
        if (this.maphieu != other.maphieu) {
            return false;
        }
        if (this.dongia != other.dongia) {
            return false;
        }
        return Objects.equals(this.imei, other.imei);
    }

    @Override
    public String toString() {
        return "ChiTietPhieuDTO{" + "maphieu=" + maphieu + ", imei=" + imei + ", dongia=" + dongia + '}';
    }

    
}
