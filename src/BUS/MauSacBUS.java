/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.MauSacDAO;
import DTO.ThuocTinhSanPham.MauSacDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class MauSacBUS {
    private MauSacDAO mausacDAO = new MauSacDAO();
    private ArrayList<MauSacDTO> listMauSac = new ArrayList<>();

    public MauSacBUS() {
        this.listMauSac = mausacDAO.selectAll();
    }
    
    public ArrayList<MauSacDTO> getAll() {
        return this.listMauSac;
    }
    
    public String[] getArrTenMauSac() {
        String[] result = new String[listMauSac.size()];
        for(int i = 0; i < listMauSac.size(); i++) {
            result[i] = listMauSac.get(i).getTenmau();
        }
        return result;
    }
}
