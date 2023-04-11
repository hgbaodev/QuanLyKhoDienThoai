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
    
    public CauHinhSanPhamBUS() {
    }
    
    public ArrayList<CauHinhSanPhamDTO> getAll(int masp) {
        return cauhinhDAO.selectAll(Integer.toString(masp));
    }
    
    public static boolean checkDuplicate( ArrayList<CauHinhSanPhamDTO> listch, CauHinhSanPhamDTO ch) {
        boolean check = false;
        int i = 0;
        while(i < listch.size() && check == false) {
            if(listch.get(i).equals(ch)) check = true;
            else i++;
        }
        return check;
    }
    
    public Boolean add(ArrayList<CauHinhSanPhamDTO> listch) {
        boolean check = cauhinhDAO.insert(listch) != 0;
        return check;
    }
}

