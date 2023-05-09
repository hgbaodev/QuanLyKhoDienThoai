/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;


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
    private int soluongton;

    public PhienBanSanPhamDTO() {
    }

    public PhienBanSanPhamDTO(int maphienbansp, int masp, int ram, int rom, int mausac, int gianhap, int giaxuat, int soluongton) {
        this.maphienbansp = maphienbansp;
        this.masp = masp;
        this.ram = ram;
        this.rom = rom;
        this.mausac = mausac;
        this.gianhap = gianhap;
        this.giaxuat = giaxuat;
        this.soluongton = soluongton;
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

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.maphienbansp;
        hash = 53 * hash + this.masp;
        hash = 53 * hash + this.ram;
        hash = 53 * hash + this.rom;
        hash = 53 * hash + this.mausac;
        hash = 53 * hash + this.gianhap;
        hash = 53 * hash + this.giaxuat;
        hash = 53 * hash + this.soluongton;
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
        if (this.ram != other.ram) {
            return false;
        }
        if (this.rom != other.rom) {
            return false;
        }
        return this.mausac == other.mausac;
    }

    @Override
    public String toString() {
        return "PhienBanSanPhamDTO{" + "maphienbansp=" + maphienbansp + ", masp=" + masp + ", ram=" + ram + ", rom=" + rom + ", mausac=" + mausac + ", gianhap=" + gianhap + ", giaxuat=" + giaxuat + ", soluongton=" + soluongton + '}';
    }

    
}
