/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietGiaDMSanPhamDTO {
    private int mactgia;
    private int macauhinh;
    private int mamau;
    private int gianhap;
    private int giaban;

    public ChiTietGiaDMSanPhamDTO() {
    }

    public ChiTietGiaDMSanPhamDTO(int mactgia, int macauhinh, int mamau, int gianhap, int giaban) {
        this.mactgia = mactgia;
        this.macauhinh = macauhinh;
        this.mamau = mamau;
        this.gianhap = gianhap;
        this.giaban = giaban;
    }

    public int getMactgia() {
        return mactgia;
    }

    public void setMactgia(int mactgia) {
        this.mactgia = mactgia;
    }

    public int getMacauhinh() {
        return macauhinh;
    }

    public void setMacauhinh(int macauhinh) {
        this.macauhinh = macauhinh;
    }

    public int getMamau() {
        return mamau;
    }

    public void setMamau(int mamau) {
        this.mamau = mamau;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaban() {
        return giaban;
    }

    public void setGiaban(int giaban) {
        this.giaban = giaban;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.mactgia;
        hash = 71 * hash + this.macauhinh;
        hash = 71 * hash + this.mamau;
        hash = 71 * hash + this.gianhap;
        hash = 71 * hash + this.giaban;
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
        final ChiTietGiaDMSanPhamDTO other = (ChiTietGiaDMSanPhamDTO) obj;
        if (this.mactgia != other.mactgia) {
            return false;
        }
        if (this.macauhinh != other.macauhinh) {
            return false;
        }
        if (this.mamau != other.mamau) {
            return false;
        }
        if (this.gianhap != other.gianhap) {
            return false;
        }
        return this.giaban == other.giaban;
    }

    @Override
    public String toString() {
        return "ChiTietGiaDMSanPhamDTO{" + "mactgia=" + mactgia + ", macauhinh=" + macauhinh + ", mamau=" + mamau + ", gianhap=" + gianhap + ", giaban=" + giaban + '}';
    }
    
    
}
