/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ThongKe.ThongKeTonKhoDTO;
import config.JDBCUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeDAO {

    public static HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> getThongKeTonKho(Date timeStart, Date timeEnd) {
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = new HashMap<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = "WITH nhap AS (\n"
                    + "  SELECT maphienbansp, SUM(soluong) AS sl_nhap\n"
                    + "  FROM ctphieunhap\n"
                    + "  JOIN phieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap\n"
                    + "  WHERE thoigian BETWEEN ? AND ?\n"
                    + "  GROUP BY maphienbansp\n"
                    + "),\n"
                    + "xuat AS (\n"
                    + "  SELECT maphienbansp, SUM(soluong) AS sl_xuat\n"
                    + "  FROM ctphieuxuat\n"
                    + "  JOIN phieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "  WHERE thoigian BETWEEN ? AND ?\n"
                    + "  GROUP BY maphienbansp\n"
                    + "),\n"
                    + "nhap_dau AS (\n"
                    + "  SELECT ctphieunhap.maphienbansp, SUM(ctphieunhap.soluong) AS sl_nhap_dau\n"
                    + "  FROM phieunhap\n"
                    + "  JOIN ctphieunhap ON phieunhap.maphieunhap = ctphieunhap.maphieunhap\n"
                    + "  WHERE phieunhap.thoigian < ?\n"
                    + "  GROUP BY ctphieunhap.maphienbansp\n"
                    + "),\n"
                    + "xuat_dau AS (\n"
                    + "  SELECT ctphieuxuat.maphienbansp, SUM(ctphieuxuat.soluong) AS sl_xuat_dau\n"
                    + "  FROM phieuxuat\n"
                    + "  JOIN ctphieuxuat ON phieuxuat.maphieuxuat = ctphieuxuat.maphieuxuat\n"
                    + "  WHERE phieuxuat.thoigian < ?\n"
                    + "  GROUP BY ctphieuxuat.maphienbansp\n"
                    + "),\n"
                    + "dau_ky AS (\n"
                    + "  SELECT\n"
                    + "    COALESCE(nhap_dau.maphienbansp, xuat_dau.maphienbansp) as maphienbansp,\n"
                    + "    (COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0)) AS soluongdauky\n"
                    + "  FROM nhap_dau\n"
                    + "  LEFT JOIN xuat_dau ON nhap_dau.maphienbansp = xuat_dau.maphienbansp\n"
                    + "  UNION\n"
                    + "  SELECT\n"
                    + "    COALESCE(nhap_dau.maphienbansp, xuat_dau.maphienbansp) as maphienbansp,\n"
                    + "    (COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0)) AS soluongdauky\n"
                    + "  FROM nhap_dau\n"
                    + "  RIGHT JOIN xuat_dau ON nhap_dau.maphienbansp = xuat_dau.maphienbansp\n"
                    + ")\n"
                    + "SELECT sanpham.masp, phienbansanpham.maphienbansp, sanpham.tensp, dau_ky.soluongdauky, COALESCE(nhap.sl_nhap, 0) AS soluongnhap, COALESCE(xuat.sl_xuat, 0)  AS soluongxuat, (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky, kichthuocram, kichthuocrom, tenmau\n"
                    + "FROM dau_ky\n"
                    + "LEFT JOIN nhap ON dau_ky.maphienbansp = nhap.maphienbansp\n"
                    + "LEFT JOIN xuat ON dau_ky.maphienbansp = xuat.maphienbansp\n"
                    + "JOIN phienbansanpham ON phienbansanpham.maphienbansp = dau_ky.maphienbansp\n"
                    + "JOIN sanpham ON phienbansanpham.masp = sanpham.masp\n"
                    + "JOIN dungluongram ON phienbansanpham.ram = dungluongram.madlram\n"
                    + "JOIN dungluongrom ON phienbansanpham.rom = dungluongrom.madlrom\n"
                    + "JOIN mausac ON phienbansanpham.mausac = mausac.mamau";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(timeEnd.getTime()));
            pst.setTimestamp(3, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(4, new Timestamp(timeEnd.getTime()));
            pst.setTimestamp(5, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(6, new Timestamp(timeStart.getTime()));

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
            // TODO: handle exception
        }
        return result;
    }
    public static void main(String[] args) {
        Date aDate = new Date(2023,5,3);
        Date ab = new Date(2023,5,8);
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = getThongKeTonKho(aDate,ab);
        System.out.println(result);
    }
}
