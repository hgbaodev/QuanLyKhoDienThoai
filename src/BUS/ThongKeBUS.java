/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ThongKeDAO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeBUS {
    ThongKeDAO thongkeDAO = new ThongKeDAO();
    ArrayList<ThongKeKhachHangDTO> tkkh;
    public ArrayList<ThongKeKhachHangDTO> getAllKhachHang(){
       this.tkkh=thongkeDAO.thongkeKH();
       return this.tkkh;
    }
}
