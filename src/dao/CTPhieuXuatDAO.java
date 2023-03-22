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
import model.CTPhieuXuat;

public class CTPhieuXuatDAO implements DAOinterface<CTPhieuXuat>{
    public static CTPhieuXuatDAO getInstance(){
        return new CTPhieuXuatDAO();
    }

    @Override
    public int insert(CTPhieuXuat t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctphieuxuat`(`maphieuxuat`,`masanpham`,`soluong`,`dongia`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieuxuat());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4, t.getDongia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuXuat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(CTPhieuXuat t) {
         int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctphieuxuat` SET `maphieuxuat`='?',`masanpham`='?',`soluong`='?',`dongia`='?' "
                    + "WHERE maphieuxuat = ? AND masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieuxuat());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4, t.getDongia());
            pst.setInt(5, t.getMaphieuxuat());
            pst.setInt(6, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuXuat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
       int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieuxuat WHERE maphieuxuat = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuXuat.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CTPhieuXuat> selectAll() {
        ArrayList<CTPhieuXuat> result = new ArrayList<CTPhieuXuat>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieuxuat";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieuxuat = rs.getInt("maphieuxuat");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia =rs.getDouble("dongia");
                
                CTPhieuXuat ctpxuat = new CTPhieuXuat(maphieuxuat, masanpham, soluong, dongia);
                result.add(ctpxuat);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CTPhieuXuat selectById(String t) {
        CTPhieuXuat result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieuxuat WHERE maphieuxuat='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieuxuat = rs.getInt("maphieuxuat");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia = rs.getDouble("dongia");
                result = new CTPhieuXuat(maphieuxuat, masanpham, soluong, dongia);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
        
}
