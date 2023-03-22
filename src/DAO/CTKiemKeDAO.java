/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.CTKiemKe;

public class CTKiemKeDAO implements DAOinterface<CTKiemKe>{

     public static CTKiemKeDAO getInstance(){
        return new CTKiemKeDAO();
    }
    @Override
    public int insert(CTKiemKe t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `ctkiemke`(`makiemke`,`masanpham`,`soluong`,`chenhlech`,`ghichu`) VALUES (?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMakiemke());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setInt(4, t.getChenhlech());
            pst.setString(5, t.getGhichu());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(CTKiemKe t) {
         int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `ctkiemke` SET `makiemke`='?',`masanpham`='?',`soluong`='?',`chenhlech`='?',`ghichu`='?' WHERE makiemke = ? AND masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMakiemke());
            pst.setInt(2, t.getMasanpham());
            pst.setInt(3, t.getSoluong());
            pst.setInt(4, t.getChenhlech());
            pst.setString(5, t.getGhichu());
            pst.setInt(6, t.getMakiemke());
            pst.setInt(7, t.getMasanpham());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM ctkiemke WHERE makiemke = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CTKiemKeDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<CTKiemKe> selectAll() {
        ArrayList<CTKiemKe> result = new ArrayList<CTKiemKe>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctkiemke";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makiemke = rs.getInt("makiemke");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                int chenhlech = rs.getInt("chenhlech");
                String ghichu = rs.getString("ghichu");
                
                CTKiemKe ctkiemke = new CTKiemKe(makiemke,masanpham,soluong,chenhlech,ghichu);
                result.add(ctkiemke);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public CTKiemKe selectById(String t) {
        CTKiemKe result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM ctkiemke WHERE makiemke='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int makiemke = rs.getInt("makiemke");
                int masanpham = rs.getInt("masanpham");
                int soluong = rs.getInt("soluong");
                int chenhlech = rs.getInt("chenhlech");
                String ghichu = rs.getString("ghichu");
                result = new CTKiemKe(makiemke, masanpham, soluong, chenhlech, ghichu);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
}
