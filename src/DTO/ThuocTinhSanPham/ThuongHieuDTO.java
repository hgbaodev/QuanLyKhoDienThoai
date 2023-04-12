/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThuocTinhSanPham;

public class ThuongHieuDTO {
    private int mathuonghieu;
    private String tenthuonghieu;

    public ThuongHieuDTO() {
    }

    public ThuongHieuDTO(int mathuonghieu, String tenthuonghieu) {
        this.mathuonghieu = mathuonghieu;
        this.tenthuonghieu = tenthuonghieu;
    }

    public int getMathuonghieu() {
        return mathuonghieu;
    }

    public void setMathuonghieu(int mathuonghieu) {
        this.mathuonghieu = mathuonghieu;
    }

    public String getTenthuonghieu() {
        return tenthuonghieu;
    }

    public void setTenthuonghieu(String tenthuonghieu) {
        this.tenthuonghieu = tenthuonghieu;
    }

    @Override
    public String toString() {
        return "LoaiHangDTO{" + "mathuonghieu=" + mathuonghieu + ", tenthuonghieu=" + tenthuonghieu + '}';
    }
    
    
}
