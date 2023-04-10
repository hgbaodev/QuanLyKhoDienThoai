/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class MauSacDTO {
    private int mamau;
    private String tenmau;

    public MauSacDTO(int mamau, String tenmau) {
        this.mamau = mamau;
        this.tenmau = tenmau;
    }

    public MauSacDTO() {
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
        int hash = 3;
        hash = 67 * hash + this.mamau;
        hash = 67 * hash + Objects.hashCode(this.tenmau);
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
        final MauSacDTO other = (MauSacDTO) obj;
        if (this.mamau != other.mamau) {
            return false;
        }
        return Objects.equals(this.tenmau, other.tenmau);
    }

    @Override
    public String toString() {
        return "MauSacDTO{" + "mamau=" + mamau + ", tenmau=" + tenmau + '}';
    }
    
    
}
