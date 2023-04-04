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
    private ArrayList<ChiTietQuyenDTO> listChiTietQuyen;
    
    public ChiTietQuyenBUS() {
        this.listChiTietQuyen = new ArrayList<>();
    }
    
    public ArrayList<ChiTietQuyenDTO> getAll(){
        return this.listChiTietQuyen;
    }
}
