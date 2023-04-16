/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuXuatDAO;
import DTO.PhieuXuatDTO;
import java.util.ArrayList;

/**
 *
 * @author robot
 */
public class PhieuXuatBUS {
    
    private final PhieuXuatDAO phieuXuatDAO = PhieuXuatDAO.getInstance();
    private final ChiTietPhieuNhapDAO chiTietPhieuNhapDAO = ChiTietPhieuNhapDAO.getInstance();
    private final ChiTietSanPhamDAO chiTietSanPhamDAO = ChiTietSanPhamDAO.getInstance();
    private ArrayList<PhieuXuatDTO> listPhieuXuat;
    
    public PhieuXuatBUS(){
        listPhieuXuat = phieuXuatDAO.selectAll();
    }
    
    
    
}
