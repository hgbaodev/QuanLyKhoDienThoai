/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

public class HanhDong {
    private int mahanhdong;
    private String tenhanhdong;

    public HanhDong() {
    }

    public HanhDong(int mahanhdong, String tenhanhdong) {
        this.mahanhdong = mahanhdong;
        this.tenhanhdong = tenhanhdong;
    }

    public int getMahanhdong() {
        return mahanhdong;
    }

    public void setMahanhdong(int mahanhdong) {
        this.mahanhdong = mahanhdong;
    }

    public String getTenhanhdong() {
        return tenhanhdong;
    }

    public void setTenhanhdong(String tenhanhdong) {
        this.tenhanhdong = tenhanhdong;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + this.mahanhdong;
        hash = 43 * hash + Objects.hashCode(this.tenhanhdong);
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
        final HanhDong other = (HanhDong) obj;
        return true;
    }

    @Override
    public String toString() {
        return "HanhDong{" + "mahanhdong=" + mahanhdong + ", tenhanhdong=" + tenhanhdong + '}';
    }
    
}
