package BUS;

import DAO.ChiTietSanPhamDAO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import java.util.ArrayList;
import java.util.HashMap;

public class ChiTietSanPhamBUS {

    private final ChiTietSanPhamDAO ctspDAO = new ChiTietSanPhamDAO();
    public PhienBanSanPhamBUS pbspbus = new PhienBanSanPhamBUS();
    public ArrayList<PhienBanSanPhamDTO> listpbsp;
    public ArrayList<ChiTietSanPhamDTO> listctsp = new ArrayList<>();

    public ChiTietSanPhamBUS() {
        
    }

    public ArrayList<ChiTietSanPhamDTO> getAllByMaPBSP(int pbsp) {
        listctsp = ctspDAO.selectAllbyPb(pbsp);
        return listctsp;
    }

    public ArrayList<ChiTietSanPhamDTO> getAll() {
        return this.listctsp;
    }

    public ChiTietSanPhamDTO getByIndex(int index) {
        return this.listctsp.get(index);
    }

    public ArrayList<ChiTietSanPhamDTO> getCTSPbyMasp(int masp) {
        listpbsp = pbspbus.getAll(masp);
        for (PhienBanSanPhamDTO i : listpbsp) {
            ArrayList<ChiTietSanPhamDTO> ctsptemp = this.getAllByMaPBSP(i.getMaphienbansp());
            listctsp.addAll(ctsptemp);
            //Show(listctsp);
        }
        return listctsp;
    }

    public HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> getChiTietSanPhamFromMaPN(int maphieunhap) {
        ArrayList<ChiTietSanPhamDTO> chitietsp = ctspDAO.selectAllByMaPhieuNhap(maphieunhap);
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
    
    public HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> getChiTietSanPhamFromMaPX(int maphieuxuat) {
        ArrayList<ChiTietSanPhamDTO> chitietsp = ctspDAO.selectAllByMaPhieuXuat(maphieuxuat);
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

    public void Show(ArrayList<ChiTietSanPhamDTO> x) {
        for (ChiTietSanPhamDTO a : x) {
            System.out.println(a.getImei());
        }
    }

    public void updateXuat(ArrayList<ChiTietSanPhamDTO> ct) {
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : ct) {
            ctspDAO.updateXuat(chiTietSanPhamDTO);
        }
    }

    public ArrayList<ChiTietSanPhamDTO> selectAllByMaPhieuXuat(int maphieu) {
        return ctspDAO.selectAllByMaPhieuXuat(maphieu);
    }
}
