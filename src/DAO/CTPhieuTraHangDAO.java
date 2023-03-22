/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CTPhieuTraHang;

public class CTPhieuTraHangDAO implements DAOinterface<CTPhieuTraHang>{
    public static CTPhieuTraHangDAO getInstance(){
        return new CTPhieuTraHangDAO();
    }

    @Override
    public int insert(CTPhieuTraHang t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctphieutrahang`(`maphieutrahang`,`masanpham`,`soluong`,`dongia`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieutrahang());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4,t.getDongia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuTraHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(CTPhieuTraHang t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctphieutrahang` SET `maphieutrahang`='?',`masanpham`='?',`soluong`='?',`dongia`='?' "
                    + "WHERE maphieutrahang = ?  AND masanpham= ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieutrahang());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4, t.getDongia());
            pst.setInt(5, t.getMaphieutrahang());
            pst.setInt(6, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuTraHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieutrahang WHERE maphieutrahang = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuTraHangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CTPhieuTraHang> selectAll() {
        ArrayList<CTPhieuTraHang> result = new ArrayList<CTPhieuTraHang>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieutrahang";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieutrahang = rs.getInt("maphieutrahang");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia =rs.getDouble("dongia");
                
                CTPhieuTraHang ctptrahang = new CTPhieuTraHang(maphieutrahang, masanpham, soluong, dongia);
                result.add(ctptrahang);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CTPhieuTraHang selectById(String t) {
       CTPhieuTraHang result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieutrahang WHERE maphieutrahang='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieutrahang = rs.getInt("maphieutrahang");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia = rs.getDouble("dongia");
                result = new CTPhieuTraHang(maphieutrahang, masanpham, soluong, dongia);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
