/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRamDTO {
    private int madlram;
    private int dungluongram;

    public DungLuongRamDTO(int madlram, int dungluongram) {
        this.madlram = madlram;
        this.dungluongram = dungluongram;
    }

    public DungLuongRamDTO() {
    }

    public int getMadlram() {
        return madlram;
    }

    public void setMadlram(int madlram) {
        this.madlram = madlram;
    }

    public int getDungluongram() {
        return dungluongram;
    }

    public void setDungluongram(int dungluongram) {
        this.dungluongram = dungluongram;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 59 * hash + this.madlram;
        hash = 59 * hash + this.dungluongram;
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
        final DungLuongRamDTO other = (DungLuongRamDTO) obj;
        if (this.madlram != other.madlram) {
            return false;
        }
        return this.dungluongram == other.dungluongram;
    }

    @Override
    public String toString() {
        return "DungLuongRAM{" + "madlram=" + madlram + ", dungluongram=" + dungluongram + '}';
    }
    
    
    
}
