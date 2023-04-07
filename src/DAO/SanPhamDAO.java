/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import config.JDBCUtil;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import DTO.SanPhamDTO;


public class SanPhamDAO implements DAOinterface<SanPhamDTO>{
    public static SanPhamDAO getInstance(){
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPhamDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `sanpham`(`masanpham`, `tensanpham`, `xuatxu`, `gianhap`, `giaxuat`,`hinhanh`,`madonvitinh`,`maloaihang`,`makhuvuc`,`soluong`,`trangthai`) VALUES (?,?,?,?,?,?,?,?,?,0,1)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMasp());
            pst.setString(2,t.getTensp());
            pst.setString(3, t.getXuatxu());
            pst.setDouble(4,t.getGianhap());
            pst.setDouble(5,t.getGiaxuat());
            pst.setString(6,t.getHinhanh());
            pst.setInt(7,t.getMaDVT());
            pst.setInt(8,t.getMaloaihang());
            pst.setInt(9,t.getMakhuvuc());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(SanPhamDTO t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `tensanpham`=?,`xuatxu`=?,`gianhap`=?,`giaxuat`=?,`hinhanh`=?,`madonvitinh`=?,`maloaihang`=?,`makhuvuc`=? WHERE `masanpham`=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1,t.getTensp());
            pst.setString(2, t.getXuatxu());
            pst.setDouble(3,t.getGianhap());
            pst.setDouble(4,t.getGiaxuat());
            pst.setString(5,t.getHinhanh());
            pst.setInt(6,t.getMaDVT());
            pst.setInt(7,t.getMaloaihang());
            pst.setInt(8,t.getMakhuvuc());
            pst.setInt(9, t.getMasp());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `trangthai`=0 WHERE masanpham = ?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<SanPhamDTO> selectAll() {
        ArrayList<SanPhamDTO> result = new ArrayList<SanPhamDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE `trangthai`= 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int masp = rs.getInt("masanpham");
                String tensp= rs.getString("tensanpham");
                String xuatxu = rs.getString("xuatxu");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                String hinhanh = rs.getString("hinhanh");
                int maDVT = rs.getInt("madonvitinh");
                int maloaihang = rs.getInt("maloaihang");
                int makhuvuc = rs.getInt("makhuvuc");
                int soluong = rs.getInt("soluong");
                SanPhamDTO sp = new SanPhamDTO(masp,tensp,xuatxu,gianhap,giaxuat,hinhanh,maDVT,maloaihang,makhuvuc,soluong);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPhamDTO selectById(String t) {
        SanPhamDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham WHERE masanpham='?'";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int masp = rs.getInt("masanpham");
                String tensp= rs.getString("tensanpham");
                String xuatxu = rs.getString("xuatxu");
                int gianhap = rs.getInt("gianhap");
                int giaxuat = rs.getInt("giaxuat");
                String hinhanh = rs.getString("hinhanh");
                int maDVT = rs.getInt("madonvitinh");
                int maloaihang = rs.getInt("maloaihang");
                int makhuvuc = rs.getInt("makhuvuc");
                int soluong = rs.getInt("soluong");
                result = new SanPhamDTO(masp,tensp,xuatxu,gianhap,giaxuat,hinhanh,maDVT,maloaihang,makhuvuc,soluong);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'sanpham'";
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
