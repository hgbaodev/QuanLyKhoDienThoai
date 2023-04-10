/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DungLuongRamDAO;
import DTO.ThuocTinhSanPham.DungLuongRamDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRamBUS {
    private DungLuongRamDAO dungluongramDAO = new DungLuongRamDAO();
    private ArrayList<DungLuongRamDTO> listRam = new ArrayList<>();

    public DungLuongRamBUS() {
        this.listRam = dungluongramDAO.selectAll();
    }
    
    public ArrayList<DungLuongRamDTO> getAll() {
        return this.listRam;
    }
    
    public String[] getArrKichThuoc() {
        String[] result = new String[listRam.size()];
        for(int i = 0; i < listRam.size(); i++) {
            result[i] = Integer.toString(listRam.get(i).getDungluongram())+"GB";
        }
        return result;
    }
}
