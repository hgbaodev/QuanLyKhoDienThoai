/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeBUS {

    ThongKeDAO thongkeDAO = new ThongKeDAO();
    ArrayList<ThongKeKhachHangDTO> tkkh;
    HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> listTonKho;

    public ThongKeBUS() {
        listTonKho = ThongKeDAO.getThongKeTonKho(new Date(0), new Date(System.currentTimeMillis()));
    }
    
    public ArrayList<ThongKeKhachHangDTO> getAllKhachHang() {
//        this.tkkh = thongkeDAO.thongkeKH();
        return this.tkkh;
    }

    public HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getTonKho() {
        return this.listTonKho;
    }

    public HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> filterTonKho(Date time_start, Date time_end) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = ThongKeDAO.getThongKeTonKho(time_start, time_end);
        return result;
    }

    public int[] getSoluong(ArrayList<ThongKeTonKhoDTO> list) {
        int[] result = {0, 0, 0, 0};
        for (int i = 0; i < list.size(); i++) {
            result[0] += list.get(i).getTondauky();
            result[1] += list.get(i).getNhaptrongky();
            result[2] += list.get(i).getXuattrongky();
            result[3] += list.get(i).getToncuoiky();
        }
        return result;
    }
}
