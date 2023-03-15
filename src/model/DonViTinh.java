/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DonViTinh {

    private int madonvi;
    private String tendonvi;

    public DonViTinh() {
    }

    public DonViTinh(int madonvi, String tendonvi) {
        this.madonvi = madonvi;
        this.tendonvi = tendonvi;
    }

    public int getMadonvi() {
        return madonvi;
    }

    public void setMadonvi(int madonvi) {
        this.madonvi = madonvi;
    }

    public String getTendonvi() {
        return tendonvi;
    }

    public void setTendonvi(String tendonvi) {
        this.tendonvi = tendonvi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.madonvi;
        hash = 71 * hash + Objects.hashCode(this.tendonvi);
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
        final DonViTinh other = (DonViTinh) obj;
        if (this.madonvi != other.madonvi) {
            return false;
        }
        return Objects.equals(this.tendonvi, other.tendonvi);
    }

    @Override
    public String toString() {
        return "DonViTinh{" + "madonvi=" + madonvi + ", tendonvi=" + tendonvi + '}';
    }

    
    
}
