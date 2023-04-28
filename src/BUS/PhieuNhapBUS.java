package BUS;

import DAO.ChiTietPhieuNhapDAO;
import DAO.ChiTietSanPhamDAO;
import DAO.PhieuNhapDAO;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhieuNhapDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
            case 0 -> {
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (Integer.toString(i.getMaphieu()).contains(text)
                            || nccBUS.getTenNhaCungCap(i.getManhacungcap()).toLowerCase().contains(text)
                            || nvBUS.getNameById(i.getManguoitao()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }

            case 1 -> {
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (Integer.toString(i.getMaphieu()).contains(text)) {
                        result.add(i);
                    }
                }
            }
            case 2 -> {
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (nccBUS.getTenNhaCungCap(i.getManhacungcap()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
            case 3 -> {
                for (PhieuNhapDTO i : this.listPhieuNhap) {
                    if (nvBUS.getNameById(i.getManguoitao()).toLowerCase().contains(text)) {
                        result.add(i);
                    }
                }
            }
        }
        return result;
    }

    public ArrayList<PhieuNhapDTO> fillerPhieuNhap(int type, String input, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());
        Timestamp time_end = new Timestamp(time_e.getTime());
        ArrayList<PhieuNhapDTO> result = new ArrayList<>();
        for (PhieuNhapDTO phieuNhap : getAllList()) {
            boolean match = false;
            switch (type) {
                case 0 ->
                    match = true;
                case 1 -> {
                    if (!input.isEmpty() && String.valueOf(phieuNhap.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (!input.isEmpty() && String.valueOf(phieuNhap.getManhacungcap()).equals(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (phieuNhap.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuNhap.getThoigiantao().compareTo(time_end) <= 0)
                    && phieuNhap.getTongTien() >= price_min
                    && phieuNhap.getTongTien() <= price_max) {
                result.add(phieuNhap);
            }
        }

        return result;
    }

}
