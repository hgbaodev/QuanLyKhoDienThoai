/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import java.sql.Timestamp;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeDAO {
    public static ThongKeDAO getInstance(){
        return new ThongKeDAO();
    }

    public ArrayList<ThongKeKhachHangDTO> thongkeKH() {
        ArrayList<KhachHangDTO> listKh = KhachHangDAO.getInstance().selectAll();
        ArrayList<ThongKeKhachHangDTO> tkkh = new ArrayList<>();
        for (KhachHangDTO kh : listKh) {
            ArrayList<PhieuXuatDTO> px = PhieuXuatDAO.getInstance().selectAllofKH(kh.getMaKH());
            int count = 0, total = 0;
            for (PhieuXuatDTO i : px) {
                total += i.getTongTien();
                count++;
            }
            tkkh.add(new ThongKeKhachHangDTO(kh.getMaKH(),kh.getHoten(),count,total));
        }
        return tkkh;
    }

}
