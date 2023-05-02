package BUS;

import DAO.PhieuKiemKeDAO;
import DTO.PhieuKiemKeDTO;

/**
 *
 * @author robot
 */
public class PhieuKiemKeBUS {
    
    private PhieuKiemKeDAO phieuKiemKeDAO = PhieuKiemKeDAO.getInstance();
    
    public int insert(PhieuKiemKeDTO phieuKiemKeDTO){
        return phieuKiemKeDAO.insert(phieuKiemKeDTO);
    }
    
    public int getAutoIncrement(){
        return phieuKiemKeDAO.getAutoIncrement();
    }
}
