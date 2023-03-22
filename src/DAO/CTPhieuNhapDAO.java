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
import model.CTPhieuNhap;

public class CTPhieuNhapDAO implements DAOinterface<CTPhieuNhap>{
    public static CTPhieuNhapDAO getInstance(){
        return new CTPhieuNhapDAO();
    }

    @Override
    public int insert(CTPhieuNhap t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctphieunhap`(`maphieunhap`,`masanpham`,`soluong`,`dongia`) VALUES (?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieunhap());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4, t.getDongia());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(CTPhieuNhap t) {
         int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctphieunhap` SET `maphieunhap`='?',`masanpham`='?',`soluong`='?',`dongia`='?' WHERE maphieunhap= ? AND masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaphieunhap());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setDouble(4, t.getDongia());
            pst.setInt(5, t.getMaphieunhap());
            pst.setInt(6, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
       int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctphieunhap WHERE maphieunhap = '?' ";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTPhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CTPhieuNhap> selectAll() {
        ArrayList<CTPhieuNhap> result = new ArrayList<CTPhieuNhap>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieunhap";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieunhap = rs.getInt("maphieunhap");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia =rs.getDouble("dongia");
                
                CTPhieuNhap ctpnhap = new CTPhieuNhap(maphieunhap, masanpham, soluong, dongia);
                result.add(ctpnhap);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CTPhieuNhap selectById(String t) {
        CTPhieuNhap result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctphieunhap WHERE maphieunhap='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maphieunhap = rs.getInt("maphieunhap");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                double dongia = rs.getDouble("dongia");
                result = new CTPhieuNhap(maphieunhap, masanpham, soluong, dongia);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
        
}
