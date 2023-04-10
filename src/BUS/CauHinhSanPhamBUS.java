/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.CauHinhSanPhamDAO;
import DTO.CauHinhSanPhamDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class CauHinhSanPhamBUS {
    private CauHinhSanPhamDAO cauhinhDAO = new CauHinhSanPhamDAO();
    private ArrayList<CauHinhSanPhamDTO> listCauHinh = new ArrayList<>();
    
    public CauHinhSanPhamBUS(int masp) {
        this.listCauHinh = cauhinhDAO.selectAll(Integer.toString(masp));
    }
    
    public ArrayList<CauHinhSanPhamDTO> getAll() {
        return this.listCauHinh;
    }
}
