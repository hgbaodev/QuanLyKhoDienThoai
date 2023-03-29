/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DTO;

import java.security.Timestamp;
import java.util.ArrayList;

public class PhieuXuat extends Phieu{
    private int makhoxuat;
    private int makh;
    private int trangthai;

    public PhieuXuat() {
    }

    public PhieuXuat(int makhoxuat, int makh, int trangthai) {
        this.makhoxuat = makhoxuat;
        this.makh = makh;
        this.trangthai = trangthai;
    }

    public PhieuXuat(int maphieu, int manguoitao, Timestamp thoigiantao, ArrayList<ChiTietPhieu> CTPhieu, double tongTien,int makhoxuat, int makh, int trangthai) {
        super(maphieu, manguoitao, thoigiantao, CTPhieu, tongTien);
        this.makhoxuat = makhoxuat;
        this.makh = makh;
        this.trangthai = trangthai;
    }
    
    
}
