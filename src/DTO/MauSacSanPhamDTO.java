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
public class MauSacSanPhamDTO {
    private int madmsp;
    private int mamau;
    private String tenmau;

    public MauSacSanPhamDTO() {
    }

    public MauSacSanPhamDTO(int madmsp, int mamau, String tenmau) {
        this.madmsp = madmsp;
        this.mamau = mamau;
        this.tenmau = tenmau;
    }

    public int getMadmsp() {
        return madmsp;
    }

    public void setMadmsp(int madmsp) {
        this.madmsp = madmsp;
    }

    public int getMamau() {
        return mamau;
    }

    public void setMamau(int mamau) {
        this.mamau = mamau;
    }

    public String getTenmau() {
        return tenmau;
    }

    public void setTenmau(String tenmau) {
        this.tenmau = tenmau;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.madmsp;
        hash = 89 * hash + this.mamau;
        hash = 89 * hash + Objects.hashCode(this.tenmau);
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
        final MauSacSanPhamDTO other = (MauSacSanPhamDTO) obj;
        if (this.madmsp != other.madmsp) {
            return false;
        }
        if (this.mamau != other.mamau) {
            return false;
        }
        return Objects.equals(this.tenmau, other.tenmau);
    }

    @Override
    public String toString() {
        return "MauSacSanPhamDTO{" + "madmsp=" + madmsp + ", mamau=" + mamau + ", tenmau=" + tenmau + '}';
    }
    
    
}
