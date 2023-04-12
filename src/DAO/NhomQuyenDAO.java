package DAO;

import DTO.NhomQuyenDTO;
import java.sql.Connection;

import config.JDBCUtil;
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
public class NhomQuyenDAO implements DAOinterface<NhomQuyenDTO> {

    public static NhomQuyenDAO getInstance() {
        return new NhomQuyenDAO();
    }

    @Override
    public int insert(NhomQuyenDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "INSERT INTO `nhomquyen`(`tennhomquyen`,`trangthai`) VALUES (?,1)";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int update(NhomQuyenDTO t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhomquyen` SET `tennhomquyen`=? WHERE `manhomquyen`=?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t.getTennhomquyen());
            pst.setInt(2, t.getManhomquyen());
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public int delete(String t) {
        int result = 0;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "UPDATE `nhomquyen` SET `trangthai` = 0 WHERE manhomquyen = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, t);
            result = pst.executeUpdate();
            JDBCUtil.closeConnection(con);
        } catch (SQLException ex) {
            Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    @Override
    public ArrayList<NhomQuyenDTO> selectAll() {
        ArrayList<NhomQuyenDTO> result = new ArrayList<NhomQuyenDTO>();
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhomquyen WHERE trangthai = 1";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                NhomQuyenDTO dvt = new NhomQuyenDTO(manhomquyen, tennhomquyen);
                result.add(dvt);
            }
            JDBCUtil.closeConnection(con);
        } catch (Exception e) {
        }
        return result;
    }

    @Override
    public NhomQuyenDTO selectById(String t) {
        NhomQuyenDTO result = null;
        try {
            Connection con = (Connection) JDBCUtil.getConnection();
            String sql = "SELECT * FROM nhomquyen WHERE manhomquyen=?";
            PreparedStatement pst = (PreparedStatement) con.prepareStatement(sql);
            pst.setString(1, t);
            ResultSet rs = (ResultSet) pst.executeQuery();
            while (rs.next()) {
                int manhomquyen = rs.getInt("manhomquyen");
                String tennhomquyen = rs.getString("tennhomquyen");
                result = new NhomQuyenDTO(manhomquyen, tennhomquyen);
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
            String sql = "SELECT `AUTO_INCREMENT` FROM  INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'quanlikhohang' AND   TABLE_NAME   = 'nhomquyen'";
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
            Logger.getLogger(NhomQuyenDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
