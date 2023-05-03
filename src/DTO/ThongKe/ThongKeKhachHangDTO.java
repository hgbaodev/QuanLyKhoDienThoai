/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO.ThongKe;
import DTO.PhieuXuatDTO;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author andin
 */
public class ThongKeKhachHangDTO {
    int makh;
    String tenkh;
    int soluongphieu;
    long tongtien;

    public ThongKeKhachHangDTO() {
    }

    public ThongKeKhachHangDTO(int makh, String tenkh, int soluongphieu, long tongtien) {
        this.makh = makh;
        this.tenkh = tenkh;
        this.soluongphieu = soluongphieu;
        this.tongtien = tongtien;
    }

    public int getMakh() {
        return makh;
    }

    public void setMakh(int makh) {
        this.makh = makh;
    }

    public String getTenkh() {
        return tenkh;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public int getSoluongphieu() {
        return soluongphieu;
    }

    public void setSoluongphieu(int soluongphieu) {
        this.soluongphieu = soluongphieu;
    }

    public long getTongtien() {
        return tongtien;
    }

    public void setTongtien(long tongtien) {
        this.tongtien = tongtien;
    }

    
}
