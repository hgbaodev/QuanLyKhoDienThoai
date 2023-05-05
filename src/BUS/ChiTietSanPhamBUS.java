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
        listctsp = ctspDAO.selectbyPb(pbsp);
        return listctsp;
    }

    public ArrayList<ChiTietSanPhamDTO> getAll() {
        return this.listctsp;
    }

    public ChiTietSanPhamDTO getByIndex(int index) {
        return this.listctsp.get(index);
    }

    public ArrayList<ChiTietSanPhamDTO> getAllCTSPbyMasp(int masp) {
        ArrayList<ChiTietSanPhamDTO> list2 = new ArrayList<>();
        ArrayList<PhienBanSanPhamDTO> list = pbspbus.getAll(masp);
        for (PhienBanSanPhamDTO i : list) {
            ArrayList<ChiTietSanPhamDTO> ctsptemp = this.getAllByMaPBSP(i.getMaphienbansp());
            list2.addAll(ctsptemp);
        }
        return list2;
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

    public ArrayList<ChiTietSanPhamDTO> FilterPBvaTT(String text,int masp, int phienban, int tinhtrang) {
        ArrayList<ChiTietSanPhamDTO> list = this.getAllCTSPbyMasp(masp);
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        for (ChiTietSanPhamDTO i : list) {
            if(i.getMaphienbansp() == phienban && i.getTinhtrang() == tinhtrang && i.getImei().contains(text))
                result.add(i);
        }
        return result;
    }
    public ArrayList<ChiTietSanPhamDTO> FilterPBvaAll(String text,int masp, int phienban) {
        ArrayList<ChiTietSanPhamDTO> list = this.getAllCTSPbyMasp(masp);
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        for (ChiTietSanPhamDTO i : list) {
            if(i.getMaphienbansp() == phienban && i.getImei().contains(text))
                result.add(i);
        }
        return result;
    }
}
