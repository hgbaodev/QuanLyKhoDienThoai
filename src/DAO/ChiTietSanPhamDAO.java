package DAO;

import DTO.ChiTietSanPhamDTO;
import DTO.KhuVucKhoDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietSanPhamDAO implements DAOinterface<ChiTietSanPhamDTO> {
    
    public static ChiTietSanPhamDAO getInstance(){
        return new ChiTietSanPhamDAO();
    }

    @Override
    public int insert(ChiTietSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctsanpham`(`maimei`, `maphienbansp`, `maphieunhap`, `tinhtrang`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getImei());
            pst.setInt(2, t.getMaphienbansp());
            pst.setInt(3, t.getMaphieunhap());
            pst.setInt(4, t.getTinhtrang());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int insert_mutiple(ArrayList<ChiTietSanPhamDTO> list) {
        int result = 0;
        for(ChiTietSanPhamDTO sp : list) {
            result += this.insert(sp);
        }
        return result;
    }

    @Override
    public int update(ChiTietSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctsanpham` SET `maphienbansp`=?,`maphieunhap`=?,`maphieuxuat`=?,`tinhtrang`=? WHERE `maimei`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphienbansp());
            pst.setInt(2, t.getMaphieunhap());
            pst.setInt(3, t.getMaphieuxuat());
            pst.setInt(4, t.getTinhtrang());
            pst.setString(5, t.getImei());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int updateXuat(ChiTietSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctsanpham` SET `maphieuxuat`=?,`tinhtrang`=? WHERE `maimei`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieuxuat());
            pst.setInt(2, t.getTinhtrang());
            pst.setString(3, t.getImei());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int reset(ChiTietSanPhamDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctsanpham` SET `maphieuxuat`= NULL ,`tinhtrang`='1' WHERE `maimei`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getImei());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctsanpham` SET `tinhtrang` = 0 WHERE  maimei = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(KhuVucKhoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    public int deletePn(int t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctsanpham WHERE maphieunhap = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setInt(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(ChiTietPhieuXuatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<ChiTietSanPhamDTO> selectAll() {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int macauhinh = rs.getInt("phienbansp");
                int maphieunhap = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, macauhinh, maphieunhap, maphieuxuat, tinhtrang);
                result.add(ct);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    
    public ArrayList<ChiTietSanPhamDTO> selectAllbyPb(int mapbsp) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham where maphienbansp = ? and tinhtrang = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, mapbsp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int maphienban = rs.getInt("maphienbansp");
                int maphieunhap = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, maphieunhap, maphieuxuat, tinhtrang);
                result.add(ct);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    public ArrayList<ChiTietSanPhamDTO> selectbyPb(int mapbsp) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham where maphienbansp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, mapbsp);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int maphienban = rs.getInt("maphienbansp");
                int maphieunhap = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, maphieunhap, maphieuxuat, tinhtrang);
                result.add(ct);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    public ArrayList<ChiTietSanPhamDTO> selectPBvaTT(int mapbsp,int tt) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham where maphienbansp = ? and tinhtrang = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, mapbsp);
            pst.setInt(2,tt);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int maphienban = rs.getInt("maphienbansp");
                int maphieunhap = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, maphieunhap, maphieuxuat, tinhtrang);
                result.add(ct);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }

    @Override
    public ChiTietSanPhamDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public ArrayList<Integer> getMaPhienBanSPOfPhieu(int maphieu) {
        ArrayList<Integer> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham where maphieunhap = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieu);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int maphienban = rs.getInt("maphienbansp");
                result.add(maphienban);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<ChiTietSanPhamDTO> selectAllByMaPhieuNhap(int maphieunhap) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham where maphieunhap = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieunhap);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int maphienban = rs.getInt("maphienbansp");
                int mapn = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, mapn, maphieuxuat, tinhtrang);
                result.add(ct);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<ChiTietSanPhamDTO> selectAllByMaPhieuXuat(int maphieunhap) {
        ArrayList<ChiTietSanPhamDTO> result = new ArrayList<>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctsanpham where maphieuxuat = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, maphieunhap);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                String imei = rs.getString("maimei");
                int maphienban = rs.getInt("maphienbansp");
                int mapn = rs.getInt("maphieunhap");
                int maphieuxuat = rs.getInt("maphieuxuat");
                int tinhtrang = rs.getInt("tinhtrang");
                ChiTietSanPhamDTO ct = new ChiTietSanPhamDTO(imei, maphienban, mapn, maphieuxuat, tinhtrang);
                result.add(ct);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return result;
    }
    
}
