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
import DTO.PhieuNhapDTO;
import DTO.PhieuXuatDTO;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author robot
 */
public class PhieuXuatBUS {

    private final PhieuXuatDAO phieuXuatDAO = PhieuXuatDAO.getInstance();

    private final ChiTietPhieuXuatDAO chiTietPhieuXuatDAO = ChiTietPhieuXuatDAO.getInstance();
    private final ChiTietSanPhamDAO chiTietSanPhamDAO = ChiTietSanPhamDAO.getInstance();
    private final ArrayList<PhieuXuatDTO> listPhieuXuat;
    
    NhanVienBUS nvBUS = new NhanVienBUS();
    KhachHangBUS khBUS = new KhachHangBUS();

    public PhieuXuatBUS() {
        this.listPhieuXuat = phieuXuatDAO.selectAll();
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

    public void remove(int px) {
        listPhieuXuat.remove(px);
    }

    public void insert(PhieuXuatDTO px, ArrayList<ChiTietPhieuDTO> ct) {
        phieuXuatDAO.insert(px);
        chiTietPhieuXuatDAO.insert(ct);
    }

    public ArrayList<ChiTietPhieuDTO> selectCTP(int maphieu) {
        return chiTietPhieuXuatDAO.selectAll(Integer.toString(maphieu));
    }

    public ArrayList<PhieuXuatDTO> fillerPhieuXuat(int type, String input, int makh, int manv, Date time_s, Date time_e, String price_minnn, String price_maxxx) {
        Long price_min = !price_minnn.equals("") ? Long.valueOf(price_minnn) : 0L;
        Long price_max = !price_maxxx.equals("") ? Long.valueOf(price_maxxx) : Long.MAX_VALUE;
        Timestamp time_start = new Timestamp(time_s.getTime());
        Timestamp time_end = new Timestamp(time_e.getTime());
        ArrayList<PhieuXuatDTO> result = new ArrayList<>();
        for (PhieuXuatDTO phieuXuat : getAll()) {
            boolean match = false;
            switch (type) {
                case 0 -> {
                    if (Integer.toString(phieuXuat.getMaphieu()).contains(input)
                            || khBUS.getTenKhachHang(phieuXuat.getMakh()).toLowerCase().contains(input)
                            || nvBUS.getNameById(phieuXuat.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 1 -> {
                    if (Integer.toString(phieuXuat.getMaphieu()).contains(input)) {
                        match = true;
                    }
                }
                case 2 -> {
                    if (khBUS.getTenKhachHang(phieuXuat.getMakh()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
                case 3 -> {
                    if (nvBUS.getNameById(phieuXuat.getManguoitao()).toLowerCase().contains(input)) {
                        match = true;
                    }
                }
            }

            if (match
                    && (manv == 0 || phieuXuat.getManguoitao() == manv) && (makh == 0 || phieuXuat.getMakh()== makh)
                    && (phieuXuat.getThoigiantao().compareTo(time_start) >= 0)
                    && (phieuXuat.getThoigiantao().compareTo(time_end) <= 0)
                    && phieuXuat.getTongTien() >= price_min
                    && phieuXuat.getTongTien() <= price_max) {
                result.add(phieuXuat);
            }
        }

        return result;
    }

}
