/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.util.Objects;

/**
 *
 * @author 84907
 */
public class SanPhamDTO {

    private int madanhmuc;
    private String tendanhmuc;
    private String hinhanh;
    private String xuatxu;
    private String chipxuly;
    private int dungluongpin;
    private double kichthuocman;
    private String hedieuhanh;
    private int phienbanhdh;
    private String camerasau;
    private String cameratruoc;
    private int thoigianbaohanh;
    private int thuonghieu;
    private int khuvuckho;
    private int soluongton;

    public SanPhamDTO() {
    }

    public SanPhamDTO(int madanhmuc, String tendanhmuc, String hinhanh, String xuatxu, String chipxuly, int dungluongpin, double kichthuocman, String hedieuhanh, int phienbanhdh, String camerasau, String cameratruoc, int thoigianbaohanh, int thuonghieu, int khuvuckho, int soluongton) {
        this.madanhmuc = madanhmuc;
        this.tendanhmuc = tendanhmuc;
        this.hinhanh = hinhanh;
        this.xuatxu = xuatxu;
        this.chipxuly = chipxuly;
        this.dungluongpin = dungluongpin;
        this.kichthuocman = kichthuocman;
        this.hedieuhanh = hedieuhanh;
        this.phienbanhdh = phienbanhdh;
        this.camerasau = camerasau;
        this.cameratruoc = cameratruoc;
        this.thoigianbaohanh = thoigianbaohanh;
        this.thuonghieu = thuonghieu;
        this.khuvuckho = khuvuckho;
        this.soluongton = soluongton;
    }

    public int getMadanhmuc() {
        return madanhmuc;
    }

    public void setMadanhmuc(int madanhmuc) {
        this.madanhmuc = madanhmuc;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getXuatxu() {
        return xuatxu;
    }

    public void setXuatxu(String xuatxu) {
        this.xuatxu = xuatxu;
    }

    public String getChipxuly() {
        return chipxuly;
    }

    public void setChipxuly(String chipxuly) {
        this.chipxuly = chipxuly;
    }

    public int getDungluongpin() {
        return dungluongpin;
    }

    public void setDungluongpin(int dungluongpin) {
        this.dungluongpin = dungluongpin;
    }

    public double getKichthuocman() {
        return kichthuocman;
    }

    public void setKichthuocman(double kichthuocman) {
        this.kichthuocman = kichthuocman;
    }

    public String getHedieuhanh() {
        return hedieuhanh;
    }

    public void setHedieuhanh(String hedieuhanh) {
        this.hedieuhanh = hedieuhanh;
    }

    public int getPhienbanhdh() {
        return phienbanhdh;
    }

    public void setPhienbanhdh(int phienbanhdh) {
        this.phienbanhdh = phienbanhdh;
    }

    public String getCamerasau() {
        return camerasau;
    }

    public void setCamerasau(String camerasau) {
        this.camerasau = camerasau;
    }

    public String getCameratruoc() {
        return cameratruoc;
    }

    public void setCameratruoc(String cameratruoc) {
        this.cameratruoc = cameratruoc;
    }

    public int getThoigianbaohanh() {
        return thoigianbaohanh;
    }

    public void setThoigianbaohanh(int thoigianbaohanh) {
        this.thoigianbaohanh = thoigianbaohanh;
    }

    public int getThuonghieu() {
        return thuonghieu;
    }

    public void setThuonghieu(int thuonghieu) {
        this.thuonghieu = thuonghieu;
    }

    public int getKhuvuckho() {
        return khuvuckho;
    }

    public void setKhuvuckho(int khuvuckho) {
        this.khuvuckho = khuvuckho;
    }

    public int getSoluongton() {
        return soluongton;
    }

    public void setSoluongton(int soluongton) {
        this.soluongton = soluongton;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final SanPhamDTO other = (SanPhamDTO) obj;
        if (this.madanhmuc != other.madanhmuc) {
            return false;
        }
        if (this.tendanhmuc != other.tendanhmuc) {
            return false;
        }
        if (this.dungluongpin != other.dungluongpin) {
            return false;
        }
        if (Double.doubleToLongBits(this.kichthuocman) != Double.doubleToLongBits(other.kichthuocman)) {
            return false;
        }
        if (this.phienbanhdh != other.phienbanhdh) {
            return false;
        }
        if (this.thoigianbaohanh != other.thoigianbaohanh) {
            return false;
        }
        if (this.thuonghieu != other.thuonghieu) {
            return false;
        }
        if (this.khuvuckho != other.khuvuckho) {
            return false;
        }
        if (this.soluongton != other.soluongton) {
            return false;
        }
        if (!Objects.equals(this.hinhanh, other.hinhanh)) {
            return false;
        }
        if (!Objects.equals(this.xuatxu, other.xuatxu)) {
            return false;
        }
        if (!Objects.equals(this.chipxuly, other.chipxuly)) {
            return false;
        }
        if (!Objects.equals(this.hedieuhanh, other.hedieuhanh)) {
            return false;
        }
        if (!Objects.equals(this.camerasau, other.camerasau)) {
            return false;
        }
        return Objects.equals(this.cameratruoc, other.cameratruoc);
    }

    @Override
    public String toString() {
        return "SanPhamDTO{" + "madanhmuc=" + madanhmuc + ", tendanhmuc=" + tendanhmuc + ", hinhanh=" + hinhanh + ", xuatxu=" + xuatxu + ", chipxuly=" + chipxuly + ", dungluongpin=" + dungluongpin + ", kichthuocman=" + kichthuocman + ", hedieuhanh=" + hedieuhanh + ", phienbanhdh=" + phienbanhdh + ", camerasau=" + camerasau + ", cameratruoc=" + cameratruoc + ", thoigianbaohanh=" + thoigianbaohanh + ", thuonghieu=" + thuonghieu + ", khuvuckho=" + khuvuckho + ", soluongton=" + soluongton + '}';
    }

}
