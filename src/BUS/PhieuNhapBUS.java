/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuDTO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuNhapBUS {

    private final PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();
    private final ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    public ArrayList<PhieuNhapDTO> listPhieuNhap;

    public PhieuNhapBUS() {
        this.listPhieuNhap = phieunhapDAO.selectAll();
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        return listPhieuNhap;
    }

    public boolean add(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuDTO> ctPhieu) {
        boolean check = phieunhapDAO.insert(phieu) != 0;
        if (check) {
            this.listPhieuNhap.add(phieu);
            this.addChiTietPhieu(ctPhieu);
        }
        return check;
    }
    
    public boolean update(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuDTO> ctPhieu) {
        boolean check = phieunhapDAO.update(phieu) != 0;
        if(check) {
            this.removeChiTietPhieu(Integer.toString(phieu.getMaphieu()));
            this.addChiTietPhieu(ctPhieu);
        }
        return check;
    }

    public boolean addChiTietPhieu(ArrayList<ChiTietPhieuDTO> ctPhieu) {
        boolean check = ctPhieuNhapDAO.insert(ctPhieu) != 0;
        return check;
    }

    public boolean removeChiTietPhieu(String maphieu) {
        boolean check = ctPhieuNhapDAO.delete(maphieu) != 0;
        return check;
    }
}
