package DAO;

import config.JDBCUtil;
import DTO.Taikhoan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author robot
 */
public class TaikhoanDAO implements DAOinterface<Taikhoan>{
    
    public static TaikhoanDAO getInstance(){
        return new TaikhoanDAO();
    }

    @Override
    public int insert(Taikhoan t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `taikhoan`(`trangthai`,``) VALUES (1,)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaikhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(Taikhoan t) {
          int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `taikhoan` SET `email`='?',`hoten`='?',`matkhau`='?',`trangthai`='?',"
                    + "`makhohang`='?',`manhomquyen`='?' WHERE email=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t.getHoten());
            pst.setString(2, t.getMatkhau());
            pst.setInt(3, t.getTrangthai());
            pst.setString(4, t.getMakhohang());
            pst.setString(5, t.getManhomquyen());
            pst.setString(6, t.getEmail());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaikhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
         int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "DELETE FROM taikhoan WHERE email = '?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(TaikhoanDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<Taikhoan> selectAll() {
        ArrayList<Taikhoan> result = new ArrayList<Taikhoan>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String email = rs.getString("email");
                String hoten = rs.getString("hoten");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                String makhohang = rs.getString("makhohang");
                String manhomquyen = rs.getString("manhomquyen");
                
                Taikhoan us = new Taikhoan(email, hoten, matkhau, trangthai, makhohang, manhomquyen);
                result.add(us);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public Taikhoan selectById(String t) {
        Taikhoan result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM taikhoan WHERE email='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                String email = rs.getString("email");
                String hoten = rs.getString("hoten");
                String matkhau = rs.getString("matkhau");
                int trangthai = rs.getInt("trangthai");
                String makhohang = rs.getString("makhohang");
                String manhomquyen = rs.getString("manhomquyen");
                
                result = new Taikhoan(email, hoten, matkhau, trangthai, makhohang, manhomquyen);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'taikhoan'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst() ) {
                System.out.println("No data");
            } else {
                while ( rs2.next() ) {
                    result = rs2.getInt("AUTO_INCREMENT");
                    
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
