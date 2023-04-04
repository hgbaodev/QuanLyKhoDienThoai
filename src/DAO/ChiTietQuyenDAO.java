/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChiTietQuyenDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietQuyenDAO implements DAOinterface<ChiTietQuyenDTO>{

    @Override
    public int insert(ChiTietQuyenDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `chitietquyen`(`manhomquyen`,`machucnang`,`hanhdong`) VALUES (?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getManhomquyen());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(DonViTinhDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ChiTietQuyenDTO t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<ChiTietQuyenDTO> selectAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ChiTietQuyenDTO selectById(String t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'warehousemanagement' AND   TABLE_NAME   = 'chitietquyen'";
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
