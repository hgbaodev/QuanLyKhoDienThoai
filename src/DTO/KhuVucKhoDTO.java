/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class KhuVucKhoDTO {

    private int makhuvuc;
    private String tenkhuvuc;
    private String ghichu;

    public KhuVucKhoDTO() {
    }

    public KhuVucKhoDTO(int makhuvuc, String tenkhuvuc, String ghichu) {
        this.makhuvuc = makhuvuc;
        this.tenkhuvuc = tenkhuvuc;
        this.ghichu = ghichu;
    }

    public int getMakhuvuc() {
        return makhuvuc;
    }

    public void setMakhuvuc(int makhuvuc) {
        this.makhuvuc = makhuvuc;
    }

    public String getTenkhuvuc() {
        return tenkhuvuc;
    }

    public void setTenkhuvuc(String tenkhuvuc) {
        this.tenkhuvuc = tenkhuvuc;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.makhuvuc;
        hash = 97 * hash + Objects.hashCode(this.tenkhuvuc);
        hash = 97 * hash + Objects.hashCode(this.ghichu);
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
        final KhuVucKhoDTO other = (KhuVucKhoDTO) obj;
        return true;
    }

    @Override
    public String toString() {
        return "KhuVucKho{" + "makhuvuc=" + makhuvuc + ", tenkhuvuc=" + tenkhuvuc + ", ghichu=" + ghichu + '}';
    }
    
}
