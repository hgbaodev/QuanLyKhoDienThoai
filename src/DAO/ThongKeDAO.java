/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeKhachHangDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Date;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeDAO {

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public static HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getThongKeTonKho(String text, Date timeStart, Date timeEnd) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = new HashMap<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                         WITH nhap AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_nhap
                           FROM ctphieunhap
                           JOIN phieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         xuat AS (
                           SELECT maphienbansp, SUM(soluong) AS sl_xuat
                           FROM ctphieuxuat
                           JOIN phieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE thoigian BETWEEN ? AND ?
                           GROUP BY maphienbansp
                         ),
                         nhap_dau AS (
                           SELECT ctphieunhap.maphienbansp, SUM(ctphieunhap.soluong) AS sl_nhap_dau
                           FROM phieunhap
                           JOIN ctphieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap
                           WHERE phieunhap.thoigian < ?
                           GROUP BY ctphieunhap.maphienbansp
                         ),
                         xuat_dau AS (
                           SELECT ctphieuxuat.maphienbansp, SUM(ctphieuxuat.soluong) AS sl_xuat_dau
                           FROM phieuxuat
                           JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                           WHERE phieuxuat.thoigian < ?
                           GROUP BY ctphieuxuat.maphienbansp
                         ),
                         dau_ky AS (
                           SELECT
                             phienbansanpham.maphienbansp,
                             COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0) AS soluongdauky
                           FROM phienbansanpham
                           LEFT JOIN nhap_dau ON phienbansanpham.maphienbansp = nhap_dau.maphienbansp
                           LEFT JOIN xuat_dau ON phienbansanpham.maphienbansp = xuat_dau.maphienbansp
                         ),
                         temp_table AS (
                         SELECT sanpham.masp, phienbansanpham.maphienbansp, sanpham.tensp, dau_ky.soluongdauky, COALESCE(nhap.sl_nhap, 0) AS soluongnhap, COALESCE(xuat.sl_xuat, 0)  AS soluongxuat, (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky, kichthuocram, kichthuocrom, tenmau
                         FROM dau_ky
                         LEFT JOIN nhap ON dau_ky.maphienbansp = nhap.maphienbansp
                         LEFT JOIN xuat ON dau_ky.maphienbansp = xuat.maphienbansp
                         JOIN phienbansanpham ON phienbansanpham.maphienbansp = dau_ky.maphienbansp
                         JOIN sanpham ON phienbansanpham.masp = sanpham.masp
                         JOIN dungluongram ON phienbansanpham.ram = dungluongram.madlram
                         JOIN dungluongrom ON phienbansanpham.rom = dungluongrom.madlrom
                         JOIN mausac ON phienbansanpham.mausac = mausac.mamau
                         )
                         SELECT * FROM temp_table
                         WHERE tensp LIKE ? OR masp LIKE ?
                         ORDER BY masp;""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(timeEnd.getTime()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(timeEnd.getTime()));
            pst.setTimestamp(5, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(6, new Timestamp(timeStart.getTime()));
            pst.setString(7, "%" + text + "%");
            pst.setString(8, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int masp = rs.getInt("masp");
                int maphienbansp = rs.getInt("maphienbansp");
                String tensp = rs.getString("tensp");
                int soluongdauky = rs.getInt("soluongdauky");
                int soluongnhap = rs.getInt("soluongnhap");
                int soluongxuat = rs.getInt("soluongxuat");
                int soluongcuoiky = rs.getInt("soluongcuoiky");
                int ram = rs.getInt("kichthuocram");
                int rom = rs.getInt("kichthuocrom");
                String mausac = rs.getString("tenmau");
                ThongKeTonKhoDTO p = new ThongKeTonKhoDTO(masp, maphienbansp, tensp, ram, rom, mausac, soluongdauky, soluongnhap, soluongxuat, soluongcuoiky);
                result.computeIfAbsent(masp, k -> new ArrayList<>()).add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public ArrayList<ThongKeDoanhThuDTO> getDoanhThuTheoTungNam(int year_start, int year_end) {
        ArrayList<ThongKeDoanhThuDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sqlSetStartYear = "SET @start_year = ?;";
            String sqlSetEndYear = "SET @end_year = ?;";
            String sqlSelect = """
                     WITH RECURSIVE years(year) AS (
                       SELECT @start_year
                       UNION ALL
                       SELECT year + 1
                       FROM years
                       WHERE year < @end_year
                     )
                     SELECT 
                       years.year AS nam,
                       COALESCE(SUM(ctphieunhap.dongia), 0) AS chiphi, 
                       COALESCE(SUM(ctphieuxuat.dongia), 0) AS doanhthu
                     FROM years
                     LEFT JOIN phieuxuat ON YEAR(phieuxuat.thoigian) = years.year
                     LEFT JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat
                     LEFT JOIN ctsanpham ON ctsanpham.maphieuxuat = ctphieuxuat.maphieuxuat AND ctsanpham.maphienbansp = ctphieuxuat.maphienbansp
                     LEFT JOIN ctphieunhap ON ctsanpham.maphieunhap = ctphieunhap.maphieunhap AND ctsanpham.maphienbansp = ctphieunhap.maphienbansp
                     GROUP BY years.year
                     ORDER BY years.year;""";
            PreparedStatement pstStartYear = con.prepareStatement(sqlSetStartYear);
            PreparedStatement pstEndYear = con.prepareStatement(sqlSetEndYear);
            PreparedStatement pstSelect = con.prepareStatement(sqlSelect);

            pstStartYear.setInt(1, year_start);
            pstEndYear.setInt(1, year_end);

            pstStartYear.execute();
            pstEndYear.execute();

            ResultSet rs = pstSelect.executeQuery();
            while (rs.next()) {
                int thoigian = rs.getInt("nam");
                Long chiphi = rs.getLong("chiphi");
                Long doanhthu = rs.getLong("doanhthu");
                ThongKeDoanhThuDTO x = new ThongKeDoanhThuDTO(thoigian, chiphi, doanhthu, doanhthu - chiphi);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(String text, Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO> result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = """
                          WITH kh AS (
                         SELECT khachhang.makh, khachhang.tenkhachhang , COUNT(phieuxuat.maphieuxuat ) AS tongsophieu, SUM(phieuxuat.tongtien) AS tongsotien
                         FROM khachhang
                         JOIN phieuxuat ON khachhang.makh = phieuxuat.makh
                         WHERE phieuxuat.thoigian BETWEEN ? AND ? 
                         GROUP BY khachhang.makh, khachhang.tenkhachhang)
                         SELECT makh,tenkhachhang,COALESCE(kh.tongsophieu, 0) AS soluong ,COALESCE(kh.tongsotien, 0) AS total 
                         FROM kh WHERE tenkhachhang LIKE ? OR makh LIKE ?""";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(timeEnd.getTime()));
            pst.setString(3, "%" + text + "%");
            pst.setString(4, "%" + text + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                int makh = rs.getInt("makh");
                String tenkh = rs.getString("tenkhachhang");
                int soluong = rs.getInt("soluong");
                long tongtien = rs.getInt("total");
                ThongKeKhachHangDTO x = new ThongKeKhachHangDTO(makh, tenkh, soluong, tongtien);
                result.add(x);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }
}
