/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietPhieuXuatDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuXuatDAO;
import DTO.ChiTietPhieuDTO;
import DTO.PhieuXuatDTO;
import java.util.ArrayList;

/**
 *
 * @author robot
 */
public class PhieuXuatBUS {

    private final PhieuXuatDAO phieuXuatDAO = PhieuXuatDAO.getInstance();

    private final ChiTietPhieuXuatDAO chiTietPhieuXuatDAO = ChiTietPhieuXuatDAO.getInstance();
    private final ChiTietSanPhamDAO chiTietSanPhamDAO = ChiTietSanPhamDAO.getInstance();

    private final ArrayList<PhieuXuatDTO> listPhieuXuat;

    public PhieuXuatBUS() {
        listPhieuXuat = phieuXuatDAO.selectAll();
    }

    public ArrayList<PhieuXuatDTO> getAll() {
        return this.listPhieuXuat;
    }

    public PhieuXuatDTO getSelect(int index) {
        return listPhieuXuat.get(index);
    }

    public void cancel(int px) {
        phieuXuatDAO.cancel(px);
    }
    
    public void remove(int px){
        listPhieuXuat.remove(px);
    }
    
    public void insert(PhieuXuatDTO px, ArrayList<ChiTietPhieuDTO> ct){
        phieuXuatDAO.insert(px);
        chiTietPhieuXuatDAO.insert(ct);
    }
    
    public ArrayList<ChiTietPhieuDTO> selectCTP(int maphieu){
        return chiTietPhieuXuatDAO.selectAll(Integer.toString(maphieu));
    }

}
