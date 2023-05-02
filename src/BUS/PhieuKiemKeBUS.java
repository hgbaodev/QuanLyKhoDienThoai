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
    
    public void insert(PhieuKiemKeDTO phieuKiemKeDTO, ArrayList<ChiTietKiemKeDTO> dsPhieu, ArrayList<ChiTietKiemKeSanPhamDTO> ctPhieu){
        phieuKiemKeDAO.insert(phieuKiemKeDTO);
        chiTietKiemKeDAO.insert(dsPhieu);
        chiTietKiemKeSanPhamDAO.insert(ctPhieu);
    }
    
    public int getAutoIncrement(){
        return phieuKiemKeDAO.getAutoIncrement();
    }
}
