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
import model.CTPhieuChuyen;


/**
 *
 * @author 84907
 */
public class CTPhieuChuyenDAO implements DAOinterface<CTPhieuChuyen>{
    public static CTPhieuChuyenDAO getInstance(){
        return new CTPhieuChuyenDAO();
    }

    @Override
    public int insert(CTPhieuChuyen t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctphieuchuyen`(`maphieuchuyen`,`masanpham`,`soluong`,`dongia`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieuchuyen());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4,t.getDongia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuChuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(CTPhieuChuyen t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctphieuchuyen` SET `maphieuchuyen`='?',`masanpham`='?',`soluong`='?',`dongia`='?' WHERE maphieuchuyen = ? AND masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieuchuyen());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4, t.getDongia());
            pst.setInt(5, t.getMaphieuchuyen());
            pst.setInt(6, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuChuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieuchuyen WHERE maphieuchuyen = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuChuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CTPhieuChuyen> selectAll() {
        ArrayList<CTPhieuChuyen> result = new ArrayList<CTPhieuChuyen>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieuchuyen";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieuchuyen = rs.getInt("maphieuchuyen");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia =rs.getDouble("dongia");
                
                CTPhieuChuyen ctpchuyen = new CTPhieuChuyen(maphieuchuyen, masanpham, soluong, dongia);
                result.add(ctpchuyen);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CTPhieuChuyen selectById(String t) {
       CTPhieuChuyen result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieuchuyen WHERE maphieuchuyen='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieuchuyen = rs.getInt("maphieuchuyen");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia = rs.getDouble("dongia");
                result = new CTPhieuChuyen(maphieuchuyen, masanpham, soluong, dongia);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
}
