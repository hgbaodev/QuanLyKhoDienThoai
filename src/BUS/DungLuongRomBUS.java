/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.DungLuongRomDAO;
import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DungLuongRomBUS {
    private DungLuongRomDAO dungluongramDAO = new DungLuongRomDAO();
    private ArrayList<DungLuongRomDTO> listRom = new ArrayList<>();

    public DungLuongRomBUS() {
        this.listRom = dungluongramDAO.selectAll();
    }
    
    public ArrayList<DungLuongRomDTO> getAll() {
        return this.listRom;
    }
    
    public String[] getArrKichThuoc() {
        String[] result = new String[listRom.size()];
        for(int i = 0; i < listRom.size(); i++) {
            result[i] = Integer.toString(listRom.get(i).getDungluongrom())+"GB";
        }
        return result;
    }
}
