/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.security.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author 84907
 */
public class PhieuNhap extends Phieu{
    private int mancc;
    private int trangthai;

    public PhieuNhap() {
    }

    public PhieuNhap(int mancc, int trangthai) {
        this.mancc = mancc;
        this.trangthai = trangthai;
    }

    public PhieuNhap(int maphieu, int manguoitao, Timestamp thoigiantao, ArrayList<ChiTietPhieu> CTPhieu, double tongTien, int mancc, int trangthai) {
        super(maphieu, manguoitao, thoigiantao, CTPhieu, tongTien);
        this.mancc = mancc;
        this.trangthai = trangthai;
    }

    public int getMancc() {
        return mancc;
    }

    public void setMancc(int mancc) {
        this.mancc = mancc;
    }

    public int getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(int trangthai) {
        this.trangthai = trangthai;
    }

    public ArrayList<ChiTietPhieu> getCTPhieu() {
        return CTPhieu;
    }

    public void setCTPhieu(ArrayList<ChiTietPhieu> CTPhieu) {
        this.CTPhieu = CTPhieu;
    }
    
}
