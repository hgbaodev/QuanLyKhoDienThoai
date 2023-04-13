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
    private int maphienbansp;
    private int soluong;
    private int dongia;

    public ChiTietPhieuDTO() {
    }

    public ChiTietPhieuDTO(int maphieu, int maphienbansp, int soluong, int dongia) {
        this.maphieu = maphieu;
        this.maphienbansp = maphienbansp;
        this.soluong = soluong;
        this.dongia = dongia;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
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
        hash = 19 * hash + this.maphieu;
        hash = 19 * hash + this.maphienbansp;
        hash = 19 * hash + this.soluong;
        hash = 19 * hash + this.dongia;
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
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.soluong != other.soluong) {
            return false;
        }
        return this.dongia == other.dongia;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuDTO{" + "maphieu=" + maphieu + ", maphienbansp=" + maphienbansp + ", soluong=" + soluong + ", dongia=" + dongia + '}';
    }

    
}
