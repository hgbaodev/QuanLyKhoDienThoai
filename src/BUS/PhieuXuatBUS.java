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

    private ArrayList<PhieuXuatDTO> listPhieuXuat;

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
    
    public void insert(PhieuXuatDTO px){
        phieuXuatDAO.insert(px);
    }

    public ArrayList<PhieuXuatDTO> filterByMoney(String head, String tail) {
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        if (!head.equals("") && !tail.equals("")) {
            Long min = Long.parseLong(head);
            Long max = Long.parseLong(tail);
            for (PhieuXuatDTO i : this.listPhieuXuat) {
                if (i.getTongTien() >= min && i.getTongTien() <= max) {
                    result.add(i);
                }
            }
        } else if (!head.equals("") && tail.equals("")) {
            Long min = Long.parseLong(head);
            for (PhieuXuatDTO i : this.listPhieuXuat) {
                if (i.getTongTien() >= min) {
                    result.add(i);
                }
            }
        } else if (head.equals("") && !tail.equals("")) {
            Long max = Long.parseLong(tail);
            for (PhieuXuatDTO i : this.listPhieuXuat) {
                if (i.getTongTien() <= max) {
                    result.add(i);
                }
            }
        } else {
            for (PhieuXuatDTO i : this.listPhieuXuat) {
                result.add(i);
            }
        }
        return result;

    }
    
    public void insertCtp(ArrayList<ChiTietPhieuDTO> ct){
        chiTietPhieuXuatDAO.insert(ct);
    }

}
