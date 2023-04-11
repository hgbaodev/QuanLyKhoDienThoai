/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.CauHinhSanPhamDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Tran Nhat Sinh
 */
public class CauHinhSanPhamDAO implements ChiTietInterface<CauHinhSanPhamDTO> {
    public static CauHinhSanPhamDAO getInstance(){
        return new CauHinhSanPhamDAO();
    }
    
    @Override
    public int insert(ArrayList<CauHinhSanPhamDTO> t) {
        int result = 0;
        for (int i = 0; i < t.size(); i++) {
            try {
                Connection con = (Connection) JDBCUtil.getConnection();
                String sql = "INSERT INTO `cauhinh`(`macauhinh`, `masp`, `rom`, `ram`, `mausac`, `gianhap`, `giaxuat`) VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
                pst.setInt(1, t.get(i).getMacauhinh());
                pst.setInt(2, t.get(i).getMasp());
                pst.setInt(3, t.get(i).getRom());
                pst.setInt(4, t.get(i).getRam());
                pst.setInt(5, t.get(i).getMausac());
                pst.setInt(6, t.get(i).getGianhap());
                pst.setInt(7, t.get(i).getGiaxuat());
                result = pst.executeUpdate();
                JDBCUtil.closeConnection(con);
            } catch (SQLException ex) {
                Logger.getLogger(CauHinhSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `cauhinh` SET `trangthai`= 0 WHERE `macauhinh`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(CauHinhSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(ArrayList<CauHinhSanPhamDTO> t, String pk) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ArrayList<CauHinhSanPhamDTO> selectAll(String t) {
        ArrayList<CauHinhSanPhamDTO> result = new ArrayList<CauHinhSanPhamDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM cauhinh WHERE masp = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int macauhinh = rs.getInt("macauhinh");
                int masp = rs.getInt("masp");
                int ram = rs.getInt("ram");
                int mausac = rs.getInt("mausac");
                int rom = rs.getInt("rom");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                CauHinhSanPhamDTO ch = new CauHinhSanPhamDTO(macauhinh, masp, ram, rom, mausac, gianhap, giaxuat);
                result.add(ch);
            }
            JDBCUtil.closeConnection(con);
        } catch (SQLException e) {
        }
        return result;
    }

    public int getAutoIncrement() {
        int result = -1;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND TABLE_NAME = 'cauhinh'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery(sql);
            if (!rs2.isBeforeFirst()) {
                System.out.println("No data");
            } else {
                while (rs2.next()) {
                    result = rs2.getInt("AUTO_INCREMENT");

                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CauHinhSanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
