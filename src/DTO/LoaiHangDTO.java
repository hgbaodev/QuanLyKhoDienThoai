/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

public class LoaiHangDTO {
    private int maloaihang;
    private String tenloaihang;

    public LoaiHangDTO() {
    }

    public LoaiHangDTO(int maloaihang, String tenloaihang) {
        this.maloaihang = maloaihang;
        this.tenloaihang = tenloaihang;
    }

    public int getMaloaihang() {
        return maloaihang;
    }

    public void setMaloaihang(int maloaihang) {
        this.maloaihang = maloaihang;
    }

    public String getTenloaihang() {
        return tenloaihang;
    }

    public void setTenloaihang(String tenloaihang) {
        this.tenloaihang = tenloaihang;
    }

    @Override
    public String toString() {
        return "LoaiHangDTO{" + "maloaihang=" + maloaihang + ", tenloaihang=" + tenloaihang + '}';
    }
    
    
}
