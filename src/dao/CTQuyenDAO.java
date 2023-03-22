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
import model.CTQuyen;


public class CTQuyenDAO implements DAOinterface<CTQuyen>{
    public static CTQuyenDAO getInstance(){
        return new CTQuyenDAO();
    }

    @Override
    public int insert(CTQuyen t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctquyen`(`maquyen`,`manhomquyen`,`check`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaquyen());
            pst.setInt(2, t.getManhomquyen());
            pst.setInt(3, t.getCheck());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(CTQuyen t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctquyen` SET `maquyen`='?',`manhomquyen`='?',`check`='?' WHERE maquyen=? AND manhomquyen=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMaquyen());
            pst.setInt(2, t.getManhomquyen());
            pst.setInt(3, t.getCheck());
            pst.setInt(4, t.getMaquyen());
            pst.setInt(5, t.getManhomquyen());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctquyen WHERE maquyen = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CTQuyen> selectAll() {
        ArrayList<CTQuyen> result = new ArrayList<CTQuyen>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctquyen";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maquyen = rs.getInt("maquyen");
                int manhomquyen = rs.getInt("manhomquyen");
                int check = rs.getInt("check");
                
                CTQuyen ctq  = new CTQuyen(maquyen, manhomquyen, check);
                result.add(ctq);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CTQuyen selectById(String t) {
        CTQuyen result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctquyen WHERE maquyen='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int maquyen = rs.getInt("maquyen");
                int manhomquyen = rs.getInt("manhomquyen");
                int check = rs.getInt("check");
                result = new CTQuyen(maquyen, manhomquyen, check);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
}
