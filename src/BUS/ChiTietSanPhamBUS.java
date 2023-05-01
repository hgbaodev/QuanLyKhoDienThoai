/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.ChiTietSanPhamDAO;
import DAO.PhienBanSanPhamDAO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import java.util.ArrayList;

public class ChiTietSanPhamBUS {

    private final ChiTietSanPhamDAO ctspDAO = new ChiTietSanPhamDAO();
    public PhienBanSanPhamBUS pbspbus = new PhienBanSanPhamBUS();
    public ArrayList<PhienBanSanPhamDTO> listpbsp;
    public ArrayList<ChiTietSanPhamDTO> listctsp = new ArrayList<>();

    public ChiTietSanPhamBUS() {
        listctsp = ctspDAO.selectAll();
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
        ArrayList<ChiTietSanPhamDTO> list2 = new ArrayList<>();
        ArrayList<PhienBanSanPhamDTO> list = pbspbus.getAll(masp);
        for (PhienBanSanPhamDTO i : list) {
            ArrayList<ChiTietSanPhamDTO> ctsptemp = this.getAllByMaPBSP(i.getMaphienbansp());
            list2.addAll(ctsptemp);
        }
        return list2;
    }

    void Show(ArrayList<ChiTietSanPhamDTO> x) {
        for (ChiTietSanPhamDTO a : x) {
            System.out.println(a.getImei());
        }
    }

    public void updateXuat(ArrayList<ChiTietSanPhamDTO> ct) {
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : ct) {
            ctspDAO.updateXuat(chiTietSanPhamDTO);
        }
    }
    
    public ArrayList<ChiTietSanPhamDTO> selectAllByMaPhieuXuat(int maphieu){
        return ctspDAO.selectAllByMaPhieuXuat(maphieu);
    }
}
