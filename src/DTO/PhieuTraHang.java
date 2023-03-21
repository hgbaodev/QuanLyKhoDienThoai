/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.time.LocalDateTime;
import java.util.Objects;

public class PhieuTraHang {
    private String nguoitao;
    private String masp;
    private LocalDateTime thoigian;
    private int soluong;

    public PhieuTraHang() {
    }

    public PhieuTraHang(String nguoitao, String masp, LocalDateTime thoigian, int soluong) {
        this.nguoitao = nguoitao;
        this.masp = masp;
        this.thoigian = thoigian;
        this.soluong = soluong;
    }

    public String getNguoitao() {
        return nguoitao;
    }

    public void setNguoitao(String nguoitao) {
        this.nguoitao = nguoitao;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
    }

    public LocalDateTime getThoigian() {
        return thoigian;
    }

    public void setThoigian(LocalDateTime thoigian) {
        this.thoigian = thoigian;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.nguoitao);
        hash = 41 * hash + Objects.hashCode(this.masp);
        hash = 41 * hash + Objects.hashCode(this.thoigian);
        hash = 41 * hash + this.soluong;
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
        final PhieuTraHang other = (PhieuTraHang) obj;
        return true;
    }

    @Override
    public String toString() {
        return "PhieuTraHang{" + "nguoitao=" + nguoitao + ", masp=" + masp + ", thoigian=" + thoigian + ", soluong=" + soluong + '}';
    }
    
}
