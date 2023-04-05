/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author 84907
 */
public class Phieu {
    private int maphieu;
    private int manguoitao;
    private Timestamp thoigiantao;
    ArrayList<ChiTietPhieuDTO> CTPhieu;
    private double tongTien;

    public Phieu() {
    }

    public Phieu(int maphieu, int manguoitao, Timestamp thoigiantao, ArrayList<ChiTietPhieuDTO> CTPhieu, double tongTien) {
        this.maphieu = maphieu;
        this.manguoitao = manguoitao;
        this.thoigiantao = thoigiantao;
        this.CTPhieu = CTPhieu;
        this.tongTien = tongTien;
    }

    public int getMaphieu() {
        return maphieu;
    }

    public void setMaphieu(int maphieu) {
        this.maphieu = maphieu;
    }

    public int getManguoitao() {
        return manguoitao;
    }

    public void setManguoitao(int manguoitao) {
        this.manguoitao = manguoitao;
    }

    public Timestamp getThoigiantao() {
        return thoigiantao;
    }

    public void setThoigiantao(Timestamp thoigiantao) {
        this.thoigiantao = thoigiantao;
    }

    public ArrayList<ChiTietPhieuDTO> getCTPhieu() {
        return CTPhieu;
    }

    public void setCTPhieu(ArrayList<ChiTietPhieuDTO> CTPhieu) {
        this.CTPhieu = CTPhieu;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.maphieu;
        hash = 79 * hash + this.manguoitao;
        hash = 79 * hash + Objects.hashCode(this.thoigiantao);
        hash = 79 * hash + Objects.hashCode(this.CTPhieu);
        hash = 79 * hash + (int) (Double.doubleToLongBits(this.tongTien) ^ (Double.doubleToLongBits(this.tongTien) >>> 32));
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
        final Phieu other = (Phieu) obj;
        return true;
    }

    @Override
    public String toString() {
        return "Phieu{" + "maphieu=" + maphieu + ", manguoitao=" + manguoitao + ", thoigiantao=" + thoigiantao + ", CTPhieu=" + CTPhieu + ", tongTien=" + tongTien + '}';
    }
    
    
}
