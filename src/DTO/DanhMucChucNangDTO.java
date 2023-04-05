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
public class DanhMucChucNangDTO {
    private String machucnang;
    private String tenchucnang;

    public DanhMucChucNangDTO() {
    }

    public DanhMucChucNangDTO(String machucnang, String tenchucnang) {
        this.machucnang = machucnang;
        this.tenchucnang = tenchucnang;
    }

    public String getMachucnang() {
        return machucnang;
    }

    public void setMachucnang(String machucnang) {
        this.machucnang = machucnang;
    }

    public String getTenchucnang() {
        return tenchucnang;
    }

    public void setTenchucnang(String tenchucnang) {
        this.tenchucnang = tenchucnang;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.machucnang);
        hash = 37 * hash + Objects.hashCode(this.tenchucnang);
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
        final DanhMucChucNangDTO other = (DanhMucChucNangDTO) obj;
        if (!Objects.equals(this.machucnang, other.machucnang)) {
            return false;
        }
        return Objects.equals(this.tenchucnang, other.tenchucnang);
    }

    @Override
    public String toString() {
        return "DanhMucChucNang{" + "machucnang=" + machucnang + ", tenchucnang=" + tenchucnang + '}';
    }
}
