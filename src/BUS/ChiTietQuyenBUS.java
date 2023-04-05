/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietQuyenBUS {
    private ChiTietQuyenDAO chitietquyenDAO = new ChiTietQuyenDAO();
    public ArrayList<ChiTietQuyenDTO> listChiTietQuyen;
    
    public ChiTietQuyenBUS() {
        
    }
    
    public ArrayList<ChiTietQuyenDTO> getAll(String manhomquyen){
        return this.listChiTietQuyen;
    }
    
    public boolean add(ArrayList<ChiTietQuyenDTO> listctquyen) {
        boolean check = chitietquyenDAO.insert(listctquyen) != 0;
        return check;
    }
}
  