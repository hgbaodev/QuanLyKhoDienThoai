/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

/**
 *
 * @author Tran Nhat Sinh
 */
public class CauHinhSanPhamDTO {
    private int madmsp;
    private int macauhinh;
    private int rom;
    private int ram;

    public CauHinhSanPhamDTO(int madmsp, int macauhinh, int rom, int ram) {
        this.madmsp = madmsp;
        this.macauhinh = macauhinh;
        this.rom = rom;
        this.ram = ram;
    }

    public CauHinhSanPhamDTO() {
    }

    public int getMadmsp() {
        return madmsp;
    }

    public void setMadmsp(int madmsp) {
        this.madmsp = madmsp;
    }

    public int getMacauhinh() {
        return macauhinh;
    }

    public void setMacauhinh(int macauhinh) {
        this.macauhinh = macauhinh;
    }

    public int getRom() {
        return rom;
    }

    public void setRom(int rom) {
        this.rom = rom;
    }

    public int getRam() {
        return ram;
    }

    public void setRam(int ram) {
        this.ram = ram;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 83 * hash + this.madmsp;
        hash = 83 * hash + this.macauhinh;
        hash = 83 * hash + this.rom;
        hash = 83 * hash + this.ram;
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
        final CauHinhSanPhamDTO other = (CauHinhSanPhamDTO) obj;
        if (this.madmsp != other.madmsp) {
            return false;
        }
        if (this.macauhinh != other.macauhinh) {
            return false;
        }
        if (this.rom != other.rom) {
            return false;
        }
        return this.ram == other.ram;
    }

    @Override
    public String toString() {
        return "CauHinhSanPhamDTO{" + "madmsp=" + madmsp + ", macauhinh=" + macauhinh + ", rom=" + rom + ", ram=" + ram + '}';
    }
    
    
}
