/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class DonViTinhDTO {
    private int maDVT;
    private String tenDVT;

    public DonViTinhDTO() {
    }

    public DonViTinhDTO(int maDVT, String tenDVT) {
        this.maDVT = maDVT;
        this.tenDVT = tenDVT;
    }

    public int getMaDVT() {
        return maDVT;
    }

    public void setMaDVT(int maDVT) {
        this.maDVT = maDVT;
    }

    public String getTenDVT() {
        return tenDVT;
    }

    public void setTenDVT(String tenDVT) {
        this.tenDVT = tenDVT;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 47 * hash + Objects.hashCode(this.maDVT);
        hash = 47 * hash + Objects.hashCode(this.tenDVT);
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
        final DonViTinhDTO other = (DonViTinhDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "DonViTinh{" + "maDVT=" + maDVT + ", tenDVT=" + tenDVT + '}';
    }
    
}
