package BUS;

import DAO.ChiTietKiemKeDAO;
import DAO.ChiTietKiemKeSanPhamDAO;
import DAO.PhieuKiemKeDAO;
import DTO.ChiTietKiemKeDTO;
import DTO.ChiTietKiemKeSanPhamDTO;
import DTO.PhieuKiemKeDTO;
import java.util.ArrayList;

/**
 *
 * @author robot
 */
public class PhieuKiemKeBUS {
    
    private PhieuKiemKeDAO phieuKiemKeDAO = PhieuKiemKeDAO.getInstance();
    private ChiTietKiemKeDAO chiTietKiemKeDAO = ChiTietKiemKeDAO.getInstance();
    private ChiTietKiemKeSanPhamDAO chiTietKiemKeSanPhamDAO = ChiTietKiemKeSanPhamDAO.getInstance();
    private ArrayList<PhieuKiemKeDTO> danhSachPhieu = phieuKiemKeDAO.selectAll();
    
    public void insert(PhieuKiemKeDTO phieuKiemKeDTO, ArrayList<ChiTietKiemKeDTO> dsPhieu, ArrayList<ChiTietKiemKeSanPhamDTO> ctPhieu){
        phieuKiemKeDAO.insert(phieuKiemKeDTO);
        chiTietKiemKeDAO.insert(dsPhieu);
        chiTietKiemKeSanPhamDAO.insert(ctPhieu);
    }

    public ArrayList<PhieuKiemKeDTO> getDanhSachPhieu() {
        return danhSachPhieu;
    }

    public void setDanhSachPhieu(ArrayList<PhieuKiemKeDTO> danhSachPhieu) {
        this.danhSachPhieu = danhSachPhieu;
    }
    
    
    
    public int getAutoIncrement(){
        return phieuKiemKeDAO.getAutoIncrement();
    }
    
    public ArrayList<PhieuKiemKeDTO> selectAll(){
        return phieuKiemKeDAO.selectAll();
    }
}
