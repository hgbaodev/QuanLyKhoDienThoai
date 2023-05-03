/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;
import DTO.KhachHangDTO;
import DTO.PhieuXuatDTO;
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
import java.sql.Date;
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
                    + "    phienbansanpham.maphienbansp,\n"
                    + "    COALESCE(nhap_dau.sl_nhap_dau, 0) - COALESCE(xuat_dau.sl_xuat_dau, 0) AS soluongdauky\n"
                    + "  FROM phienbansanpham\n"
                    + "  LEFT JOIN nhap_dau ON phienbansanpham.maphienbansp = nhap_dau.maphienbansp\n"
                    + "  LEFT JOIN xuat_dau ON phienbansanpham.maphienbansp = xuat_dau.maphienbansp\n"
                    + "),\n"
                    + "temp_table AS (\n"
                    + "SELECT sanpham.masp, phienbansanpham.maphienbansp, sanpham.tensp, dau_ky.soluongdauky, COALESCE(nhap.sl_nhap, 0) AS soluongnhap, COALESCE(xuat.sl_xuat, 0)  AS soluongxuat, (dau_ky.soluongdauky + COALESCE(nhap.sl_nhap, 0) - COALESCE(xuat.sl_xuat, 0)) AS soluongcuoiky, kichthuocram, kichthuocrom, tenmau\n"
                    + "FROM dau_ky\n"
                    + "LEFT JOIN nhap ON dau_ky.maphienbansp = nhap.maphienbansp\n"
                    + "LEFT JOIN xuat ON dau_ky.maphienbansp = xuat.maphienbansp\n"
                    + "JOIN phienbansanpham ON phienbansanpham.maphienbansp = dau_ky.maphienbansp\n"
                    + "JOIN sanpham ON phienbansanpham.masp = sanpham.masp\n"
                    + "JOIN dungluongram ON phienbansanpham.ram = dungluongram.madlram\n"
                    + "JOIN dungluongrom ON phienbansanpham.rom = dungluongrom.madlrom\n"
                    + "JOIN mausac ON phienbansanpham.mausac = mausac.mamau\n"
                    + ")\n"
                    + "SELECT * FROM temp_table\n"
                    + "WHERE soluongdauky <> 0 OR soluongnhap <> 0 OR soluongxuat <> 0 OR soluongcuoiky <> 0\n"
                    + "ORDER BY masp;";
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
            e.printStackTrace();
        }
        return result;
    }

    public static ThongKeDAO getInstance() {
        return new ThongKeDAO();
    }

    public static ArrayList<ThongKeKhachHangDTO> getThongKeKhachHang(Date timeStart, Date timeEnd) {
        ArrayList<ThongKeKhachHangDTO>  result = new ArrayList<>();
        try {
            Connection con = JDBCUtil.getConnection();
            String sql = " WITH kh AS (\n"
                    + "SELECT khachhang.makh, khachhang.tenkhachhang , COUNT(phieuxuat.maphieuxuat ) AS tongsophieu, SUM(phieuxuat.tongtien) AS tongsotien\n"
                    + "FROM khachhang\n"
                    + "JOIN phieuxuat ON khachhang.makh = phieuxuat.makh\n"
                    + "WHERE phieuxuat.thoigian BETWEEN ? AND ? \n"
                    + "GROUP BY khachhang.makh, khachhang.tenkhachhang"
                    + ")\n"
                    + "SELECT makh,tenkhachhang,COALESCE(kh.tongsophieu, 0) AS soluong ,COALESCE(kh.tongsotien, 0) AS total \n"
                    + "FROM kh;";

            PreparedStatement pst = con.prepareStatement(sql);
            pst.setTimestamp(1, new Timestamp(timeStart.getTime()));
            pst.setTimestamp(2, new Timestamp(timeEnd.getTime()));

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

    public static void main(String[] args) {
        Date aDate = new Date(123, 4, 1);
        Date bDate = new Date(123, 4, 4);
        System.out.println(aDate);
        Timestamp a =new Timestamp(aDate.getTime());
        Timestamp b =new Timestamp(bDate.getTime());
        System.out.println(a);
        System.out.println(b);
        HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> result = getThongKeTonKho(aDate,bDate);
        ArrayList<ThongKeKhachHangDTO> result2 = getThongKeKhachHang(bDate, bDate);
        System.out.println(result);
        System.out.println(result2);
    }
}
