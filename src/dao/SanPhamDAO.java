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
import model.SanPham;


public class SanPhamDAO implements DAOinterface<SanPham>{
    public static SanPhamDAO getInstance(){
        return new SanPhamDAO();
    }

    @Override
    public int insert(SanPham t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `sanpham`(`masanpham`, `tensanpham`, `xuatxu`, `gianhap`, `giaban`,`hinhanh`,`madonvitinh`,`maloaihang`,`makhuvuc`) VALUES (?,?,?,?,?,?,?,?,?)";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMasp());
            pst.setString(2,t.getTensp());
            pst.setString(3, t.getXuatxu());
            pst.setDouble(4,t.getGianhap());
            pst.setDouble(5,t.getGiaban());
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
    public int update(SanPham t) {
        int result = 0 ;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `sanpham` SET `masanpham`='?',`tensanpham`='?',`xuatxu`='?',`gianhap`='?',`giaban`='?',"
                    + "`hinhanh`='?',`madonvitinh`='?',`maloaihang`='?',`makhuvuc`='?' WHERE masanpham=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setInt(1, t.getMasp());
            pst.setString(2,t.getTensp());
            pst.setString(3, t.getXuatxu());
            pst.setDouble(4,t.getGianhap());
            pst.setDouble(5,t.getGiaban());
            pst.setString(6,t.getHinhanh());
            pst.setInt(7,t.getMaDVT());
            pst.setInt(8,t.getMaloaihang());
            pst.setInt(9,t.getMakhuvuc());
            pst.setInt(10, t.getMasp());
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
            String sql = "DELETE FROM sanpham WHERE masanpham = '?'";
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
    public ArrayList<SanPham> selectAll() {
        ArrayList<SanPham> result = new ArrayList<SanPham>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM sanpham";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while(rs.next()){
                int masp = rs.getInt("masanpham");
                String tensp= rs.getString("tensanpham");
                String xuatxu = rs.getString("xuatxu");
                int gianhap = rs.getInt("gianhap");
                int giaban = rs.getInt("giaban");
                String hinhanh = rs.getString("hinhanh");
                int maDVT = rs.getInt("madonvitinh");
                int maloaihang = rs.getInt("maloaihang");
                int makhuvuc = rs.getInt("makhuvuc");
                
                SanPham sp = new SanPham(masp,tensp,xuatxu,gianhap,giaban,hinhanh,maDVT,maloaihang,makhuvuc);
                result.add(sp);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public SanPham selectById(String t) {
        SanPham result = null;
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
                int giaban = rs.getInt("giaban");
                String hinhanh = rs.getString("hinhanh");
                int maDVT = rs.getInt("madonvitinh");
                int maloaihang = rs.getInt("maloaihang");
                int makhuvuc = rs.getInt("makhuvuc");
                
                result = new SanPham(masp,tensp,xuatxu,gianhap,giaban,hinhanh,maDVT,maloaihang,makhuvuc);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }
    
}
