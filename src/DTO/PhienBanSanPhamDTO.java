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
public class PhienBanSanPhamDTO {
    private int maphienbansp;
    private int masp;
    private int ram;
    private int rom;
    private int mausac;
    private int gianhap;
    private int giaxuat;

    public PhienBanSanPhamDTO() {
    }

    public PhienBanSanPhamDTO(int maphienbansp, int masp, int ram, int rom, int mausac, int gianhap, int giaxuat) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.ram = ram;
        this.rom = rom;
        this.mausac = mausac;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
    }

    public int getMaphienbansp() {
        return maphienbansp;
    }

    public void setMaphienbansp(int maphienbansp) {
        this.maphienbansp = maphienbansp;
    }

    public int getMasp() {
        return masp;
    }

    public void setMasp(int masp) {
        this.masp = masp;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public int getMausac() {
        return mausac;
    }

    public void setMausac(int mausac) {
        this.mausac = mausac;
    }

    public int getGianhap() {
        return gianhap;
    }

    public void setGianhap(int gianhap) {
        this.gianhap = gianhap;
    }

    public int getGiaxuat() {
        return giaxuat;
    }

    public void setGiaxuat(int giaxuat) {
        this.giaxuat = giaxuat;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.maphienbansp;
        hash = 59 * hash + this.masp;
        hash = 59 * hash + this.ram;
        hash = 59 * hash + this.rom;
        hash = 59 * hash + this.mausac;
        hash = 59 * hash + this.gianhap;
        hash = 59 * hash + this.giaxuat;
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
        final PhienBanSanPhamDTO other = (PhienBanSanPhamDTO) obj;
        if (this.maphienbansp != other.maphienbansp) {
            return false;
        }
        if (this.masp != other.masp) {
            return false;
        }
        if (this.ram != other.ram) {
            return false;
        }
        if (this.rom != other.rom) {
            return false;
        }
        if (this.mausac != other.mausac) {
            return false;
        }
        if (this.gianhap != other.gianhap) {
            return false;
        }
        return this.giaxuat == other.giaxuat;
    }

    @Override
    public String toString() {
        return "PhienBanSanPhamDTO{" + "maphienbansp=" + maphienbansp + ", masp=" + masp + ", ram=" + ram + ", rom=" + rom + ", mausac=" + mausac + ", gianhap=" + gianhap + ", giaxuat=" + giaxuat + '}';
    }
}
