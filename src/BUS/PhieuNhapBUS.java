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

    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();

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

    public HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> getChiTietSanPham(int maphieunhap) {
        ArrayList<ChiTietSanPhamDTO> chitietsp = chitietsanphamDAO.selectAllByMaPhieuNhap(maphieunhap);
        HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> result = new HashMap<>();
        for (ChiTietSanPhamDTO i : chitietsp) {
            if (result.get(i.getMaphienbansp()) == null) {
                result.put(i.getMaphienbansp(), new ArrayList<>());
            }
        }
        for (ChiTietSanPhamDTO i : chitietsp) {
            result.get(i.getMaphienbansp()).add(i);
        }
        return result;
    }

    public ArrayList<ChiTietPhieuNhapDTO> getChiTietPhieu(int maphieunhap) {
        return ctPhieuNhapDAO.selectAll(Integer.toString(maphieunhap));
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

    public ArrayList<PhieuNhapDTO> search(String text, int index) {
        text = text.toLowerCase();
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        switch (index) {
            case 0:
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (Integer.toString(i.getMaphieu()).contains(text) 
                            || nccBUS.getTenNhaCungCap(i.getManhacungcap()).toLowerCase().contains(text)
                            || nvBUS.getNameById(i.getManguoitao()).toLowerCase().contains(text)) {
                        result.add(i);
                    }     
                }
                break;
            
            case 1:
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (Integer.toString(i.getMaphieu()).contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case 2:
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (nccBUS.getTenNhaCungCap(i.getManhacungcap()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
            case 3:
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (nvBUS.getNameById(i.getManguoitao()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
                break;
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> filterByMoney(String head, String tail) {
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        if (!head.equals("") && !tail.equals("")) {
            Long min = Long.parseLong(head);
            Long max = Long.parseLong(tail);
            for (PhieuNhapDTO i : this.listPhieuNhap) {
                if (i.getTongTien() >= min && i.getTongTien() <= max) {
                    result.add(i);
                }
            }
        } else if (!head.equals("") && tail.equals("")) {
            Long min = Long.parseLong(head);
            for (PhieuNhapDTO i : this.listPhieuNhap) {
                if (i.getTongTien() >= min) {
                    result.add(i);
                }
            }
        } else if (head.equals("") && !tail.equals("")) {
            Long max = Long.parseLong(tail);
            for (PhieuNhapDTO i : this.listPhieuNhap) {
                if (i.getTongTien() <= max) {
                    result.add(i);
                }
            }
        } else {
            for (PhieuNhapDTO i : this.listPhieuNhap) {
                result.add(i);
            }
        }

        return result;

    }
}
