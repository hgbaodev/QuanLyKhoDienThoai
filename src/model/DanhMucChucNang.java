/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

public class DanhMucChucNang {
    private int maquyen;
    private String tenquyen;
    private String hanhdong;

    public DanhMucChucNang() {
    }

    public DanhMucChucNang(int maquyen, String tenquyen, String hanhdong) {
        this.maquyen = maquyen;
        this.tenquyen = tenquyen;
        this.hanhdong = hanhdong;
    }

    public int getMaquyen() {
        return maquyen;
    }

    public void setMaquyen(int maquyen) {
        this.maquyen = maquyen;
    }

    public String getTenquyen() {
        return tenquyen;
    }

    public void setTenquyen(String tenquyen) {
        this.tenquyen = tenquyen;
    }

    public String getHanhdong() {
        return hanhdong;
    }

    public void setHanhdong(String hanhdong) {
        this.hanhdong = hanhdong;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.maquyen;
        hash = 79 * hash + Objects.hashCode(this.tenquyen);
        hash = 79 * hash + Objects.hashCode(this.hanhdong);
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
        final DanhMucChucNang other = (DanhMucChucNang) obj;
        return true;
    }

    @Override
    public String toString() {
        return "DanhMucChucNang{" + "maquyen=" + maquyen + ", tenquyen=" + tenquyen + ", hanhdong=" + hanhdong + '}';
    }
    
}
