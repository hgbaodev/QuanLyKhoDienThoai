package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.PhieuNhapDTO;
import java.util.ArrayList;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhieuNhapBUS {

    public final PhieuNhapDAO phieunhapDAO = new PhieuNhapDAO();
    public final ChiTietPhieuNhapDAO ctPhieuNhapDAO = new ChiTietPhieuNhapDAO();
    public ArrayList<PhieuNhapDTO> listPhieuNhap;

    public PhieuNhapBUS() {
    }

    public ArrayList<PhieuNhapDTO> getAll() {
        this.listPhieuNhap = phieunhapDAO.selectAll();
        return listPhieuNhap;
    }

    public boolean add(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        boolean check = phieunhapDAO.insert(phieu) != 0;
        if (check) {
            this.listPhieuNhap.add(phieu);
            this.addChiTietPhieu(ctPhieu);
        }
        return check;
    }

    public boolean update(PhieuNhapDTO phieu, ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        boolean check = phieunhapDAO.update(phieu) != 0;
        if (check) {
            this.removeChiTietPhieu(Integer.toString(phieu.getMaphieu()));
            this.addChiTietPhieu(ctPhieu);
        }
        return check;
    }

    public boolean addChiTietPhieu(ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        boolean check = ctPhieuNhapDAO.insert(ctPhieu) != 0;
        return check;
    }

    public boolean removeChiTietPhieu(String maphieu) {
        boolean check = ctPhieuNhapDAO.delete(maphieu) != 0;
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

    public double getTongTien(ArrayList<ChiTietPhieuNhapDTO> ctphieu) {
        double result = 0;
        for(ChiTietPhieuNhapDTO item : ctphieu) {
            result += item.getDongia();
        }
        return result;
    }
}
