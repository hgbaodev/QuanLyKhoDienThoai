/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.util.Objects;

public class KhuVucKho {
    private int makhuvuckho;
    private String tenkhuvuc;
    private String makhohang;

    public KhuVucKho() {
    }

    public KhuVucKho(int makhuvuckho, String tenkhuvuc, String makhohang) {
        this.makhuvuckho = makhuvuckho;
        this.tenkhuvuc = tenkhuvuc;
        this.makhohang = makhohang;
    }

    public int getMakhuvuckho() {
        return makhuvuckho;
    }

    public void setMakhuvuckho(int makhuvuckho) {
        this.makhuvuckho = makhuvuckho;
    }

    public String getTenkhuvuc() {
        return tenkhuvuc;
    }

    public void setTenkhuvuc(String tenkhuvuc) {
        this.tenkhuvuc = tenkhuvuc;
    }

    public String getMakhohang() {
        return makhohang;
    }

    public void setMakhohang(String makhohang) {
        this.makhohang = makhohang;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.makhuvuckho;
        hash = 17 * hash + Objects.hashCode(this.tenkhuvuc);
        hash = 17 * hash + Objects.hashCode(this.makhohang);
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
        final KhuVucKho other = (KhuVucKho) obj;
        return true;
    }

    @Override
    public String toString() {
        return "KhuVucKho{" + "makhuvuckho=" + makhuvuckho + ", tenkhuvuc=" + tenkhuvuc + ", makhohang=" + makhohang + '}';
    }
    
}
