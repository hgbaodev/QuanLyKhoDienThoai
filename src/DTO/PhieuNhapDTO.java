/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.sql.Timestamp;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuNhapDTO extends PhieuDTO{
    private int manhacungcap;

    public PhieuNhapDTO(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    public PhieuNhapDTO(int manhacungcap, int maphieu, int manguoitao, Timestamp thoigiantao, long tongTien, int trangthai) {
        super(maphieu, manguoitao, thoigiantao, tongTien, trangthai);
        this.manhacungcap = manhacungcap;
    }

    public int getManhacungcap() {
        return manhacungcap;
    }

    public void setManhacungcap(int manhacungcap) {
        this.manhacungcap = manhacungcap;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + this.manhacungcap;
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
        final PhieuNhapDTO other = (PhieuNhapDTO) obj;
        return this.manhacungcap == other.manhacungcap;
    }

    @Override
    public String toString() {
        return "PhieuNhapDTO{" + "manhacungcap=" + manhacungcap +'}'+super.toString();
    }
}
