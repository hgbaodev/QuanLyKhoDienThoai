package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuNhapBUS {

    public final PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();
    public final ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    public final ChiTietSanPhamDAO chitietsanphamDAO = new ChiTietSanPhamDAO();
    ArrayList<PhieuNhapDTO> listPhieuNhap;
    
    public PhieuNhapBUS() {
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        this.listPhieuNhap = phieunhapDAO.selectAll();
        return this.listPhieuNhap;
    }
    
    public ArrayList<PhieuNhapDTO> getAllList() {
        return this.listPhieuNhap;
    }

    public ArrayList<ChiTietSanPhamDTO> convertHashMapToArray(HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        for (ArrayList<ChiTietSanPhamDTO> ctsp : chitietsanpham.values()) {
            result.addAll(ctsp);
        }
        return result;
    }

    public boolean add(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> ctPhieu, HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham) {
        boolean check = phieunhapDAO.insert(phieu) != 0;
        if (check) {
            check = ctPhieuNhapDAO.insert(ctPhieu) != 0;
            check = chitietsanphamDAO.insert_mutiple(convertHashMapToArray(chitietsanpham)) != 0;
        }
        return check;
    }

    public ChiTietPhieuNhapDTO findCT(ArrayList<ChiTietPhieuNhapDTO> ctphieu, int mapb) {
        ChiTietPhieuNhapDTO p = null;
        int i = 0;
        while (i < ctphieu.size() && p == null) {
            if (ctphieu.get(i).getMaphienbansp() == mapb) {
                p = ctphieu.get(i);
            } else {
                i++;
            }
        }
        return p;
    }

    public long getTongTien(ArrayList<ChiTietPhieuNhapDTO> ctphieu) {
        long result = 0;
        for (ChiTietPhieuNhapDTO item : ctphieu) {
            result += item.getDongia() * item.getSoluong();
        }
        return result;
    }
}
