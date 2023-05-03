package DAO;

import DTO.ChiTietKiemKeDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robot
 */
public class ChiTietKiemKeDAO implements ChiTietInterface<ChiTietKiemKeDTO>{
    
    public static ChiTietKiemKeDAO getInstance(){
        return new ChiTietKiemKeDAO();
    }

    @Override
    public int insert(ArrayList<ChiTietKiemKeDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `ctkiemke`(`maphieukiemke`, `maphienban`, `soluong`, `chenhlech`, `ghichu`) VALUES (?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1,t.get(i).getMaphieukiemke());
                pst.setInt(2, t.get(i).getMaphienban());
                pst.setInt(3, t.get(i).getSoluong());
                pst.setInt(4, t.get(i).getChenhlech());
                pst.setString(5, t.get(i).getGhichu());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(ChiTietPhieuNhapDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int update(ArrayList<ChiTietKiemKeDTO> t, String pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietKiemKeDTO> selectAll(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
