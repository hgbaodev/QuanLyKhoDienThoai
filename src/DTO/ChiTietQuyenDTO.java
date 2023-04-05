/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietQuyenDTO {
    private int manhomquyen;
    private String machucnang;
    private String hanhdong;

    public ChiTietQuyenDTO() {
    }

    public ChiTietQuyenDTO(int manhomquyen, String machucnang, String hanhdong) {
        this.manhomquyen = manhomquyen;
        this.machucnang = machucnang;
        this.hanhdong = hanhdong;
    }

    public int getManhomquyen() {
        return manhomquyen;
    }

    public void setManhomquyen(int manhomquyen) {
        this.manhomquyen = manhomquyen;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

    public String getHanhdong() {
        return hanhdong;
    }

    public void setHanhdong(String hanhdong) {
        this.hanhdong = hanhdong;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 83 * hash + Objects.hashCode(this.manhomquyen);
        hash = 83 * hash + Objects.hashCode(this.machucnang);
        hash = 83 * hash + Objects.hashCode(this.hanhdong);
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
        final ChiTietQuyenDTO other = (ChiTietQuyenDTO) obj;
        if (!Objects.equals(this.manhomquyen, other.manhomquyen)) {
            return false;
        }
        if (!Objects.equals(this.machucnang, other.machucnang)) {
            return false;
        }
        return Objects.equals(this.hanhdong, other.hanhdong);
    }

    @Override
    public String toString() {
        return "ChiTietQuyen{" + "manhomquyen=" + manhomquyen + ", machucnang=" + machucnang + ", hanhdong=" + hanhdong + '}';
    }

    
    
}
