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
import model.DanhMucChucNang;

public class DanhMucChucNangDAO implements DAOinterface<DanhMucChucNang>{
    public static DanhMucChucNangDAO getInstance(){
        return new DanhMucChucNangDAO();
        
    }

    @Override
    public int insert(DanhMucChucNang t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `danhmuchucnang`(`maquyen`,`tenquyen`,`hanhdong`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaquyen());
            pst.setString(2, t.getTenquyen());
            pst.setString(3, t.getHanhdong());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(DanhMucChucNang t) {
       int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `danhmuchucnang` SET `maquyen`='?',`tenquyen`='?',`hanhdong`='?' WHERE maquyen=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaquyen());
            pst.setString(2, t.getTenquyen());
            pst.setString(3, t.getHanhdong());
            pst.setInt(4, t.getMaquyen());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM danhmuchucnang WHERE maquyen = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DanhMucChucNangDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<DanhMucChucNang> selectAll() {
         ArrayList<DanhMucChucNang> result = new ArrayList<DanhMucChucNang>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM danhmuchucnang";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maquyen = rs.getInt("maquyen");
                String tenquyen = rs.getString("tenquyen");
                String hanhdong = rs.getString("hanhdong");
                DanhMucChucNang dmcn = new DanhMucChucNang(maquyen, tenquyen, hanhdong);
                result.add(dmcn);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public DanhMucChucNang selectById(String t) {
        DanhMucChucNang result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM danhmuchucnang WHERE maquyen='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maquyen = rs.getInt("maquyen");
                String tenquyen = rs.getString("tenquyen");
                String hanhdong = rs.getString("hanhdong");
                result = new DanhMucChucNang(maquyen, tenquyen, hanhdong);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
