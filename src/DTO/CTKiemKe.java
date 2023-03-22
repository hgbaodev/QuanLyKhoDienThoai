/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

public class CTKiemKe {
    private int makiemke;
    private int masanpham;
    private int soluong;
    private int chenhlech;
    private String ghichu;

    public CTKiemKe() {
    }

    public CTKiemKe(int makiemke, int masanpham, int soluong, int chenhlech, String ghichu) {
        this.makiemke = makiemke;
        this.masanpham = masanpham;
        this.soluong = soluong;
        this.chenhlech = chenhlech;
        this.ghichu = ghichu;
    }

    public int getMakiemke() {
        return makiemke;
    }

    public void setMakiemke(int makiemke) {
        this.makiemke = makiemke;
    }

    public int getMasanpham() {
        return masanpham;
    }

    public void setMasanpham(int masanpham) {
        this.masanpham = masanpham;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    public int getChenhlech() {
        return chenhlech;
    }

    public void setChenhlech(int chenhlech) {
        this.chenhlech = chenhlech;
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
        hash = 83 * hash + this.makiemke;
        hash = 83 * hash + this.masanpham;
        hash = 83 * hash + this.soluong;
        hash = 83 * hash + this.chenhlech;
        hash = 83 * hash + Objects.hashCode(this.ghichu);
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
        final CTKiemKe other = (CTKiemKe) obj;
        return true;
    }

    @Override
    public String toString() {
        return "ChiTietKiemKe{" + "makiemke=" + makiemke + ", masanpham=" + masanpham + ", soluong=" + soluong + ", chenhlech=" + chenhlech + ", ghichu=" + ghichu + '}';
    }
    
}
