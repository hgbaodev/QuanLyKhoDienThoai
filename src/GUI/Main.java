package GUI;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
import component.MenuTaskbar;
import component.NavigationBar;
import GUI.PhanQuyen_pnl;

public class Main extends javax.swing.JFrame {

    JPanel pnlThanhDuoi, MainContent;
    Color MainColor = new Color(250, 250, 250);
    TrangChu trangChu;
    SanPham sanPham;
    DonViTinh donViTinh;
    LoaiHang loaiHang;
    QuanLyKho quanLyKho;
    NhapKho nhapKho;
    XuatKho xuatKho;
    KhachHang khachHang;
    NhaCungCap nhacungcap;
    NhanVien nhanVien;
    TaiKhoan taiKhoan;
    PhanQuyen_pnl phanQuyen;
    JLabel lbl;

    private MenuTaskbar menuTaskbar;
    private NavigationBar navbar;

    private void initComponent() {
        this.setSize(new Dimension(1400, 900));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Hệ thống quản lý kho hàng ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        lbl = new JLabel("Quản lý kho hàng hoá"
                + "                nguyenthanhsangsgu@gmail.com");
        lbl.setBounds(10, 10, 100, 20);
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 14));
        lbl.setForeground(MainColor);

        pnlThanhDuoi = new JPanel();
        pnlThanhDuoi.setPreferredSize(new Dimension(1100, 30));
        pnlThanhDuoi.setBackground(new Color(69, 90, 100));

        pnlThanhDuoi.add(lbl);

        this.add(pnlThanhDuoi, BorderLayout.SOUTH);

        menuTaskbar = new MenuTaskbar();
        menuTaskbar.setPreferredSize(new Dimension(250, 1400));

        this.add(menuTaskbar, BorderLayout.WEST);

        MainContent = new JPanel();
        MainContent.setBackground(MainColor);
        MainContent.setLayout(new BorderLayout(0, 0));

        trangChu = new TrangChu();
        trangChu.setPreferredSize(new Dimension(1100, 850));
        JScrollPane jc = new JScrollPane(trangChu);
        MainContent.add(jc);
        this.add(MainContent, BorderLayout.CENTER);

        menuTaskbar.pnl[0].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                trangChu = new TrangChu();
                MainContent.removeAll();
                MainContent.add(trangChu).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });

        menuTaskbar.pnl[1].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sanPham = new SanPham();
                MainContent.removeAll();
                MainContent.add(sanPham).setVisible(true);
                MainContent.repaint();
                MainContent.validate();

            }
        });
        menuTaskbar.pnl[2].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                donViTinh = new DonViTinh();
                MainContent.removeAll();
                MainContent.add(donViTinh).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[3].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loaiHang = new LoaiHang();
                MainContent.removeAll();
                MainContent.add(loaiHang).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[4].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quanLyKho = new QuanLyKho();
                MainContent.removeAll();
                MainContent.add(quanLyKho).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[5].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhapKho = new NhapKho();
                MainContent.removeAll();
                MainContent.add(nhapKho).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[6].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xuatKho = new XuatKho();
                MainContent.removeAll();
                MainContent.add(xuatKho).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[7].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khachHang = new KhachHang();
                MainContent.removeAll();
                MainContent.add(khachHang).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[8].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhacungcap = new NhaCungCap();
                MainContent.removeAll();
                MainContent.add(nhacungcap).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[9].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhanVien = new NhanVien();
                MainContent.removeAll();
                MainContent.add(nhanVien).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[10].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taiKhoan = new TaiKhoan();
                MainContent.removeAll();
                MainContent.add(taiKhoan).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[11].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phanQuyen = new PhanQuyen_pnl();
                MainContent.removeAll();
                MainContent.add(phanQuyen).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });

    }

    public Main() {
        initComponent();
    }

    public static void main(String[] args) {
        FlatLightLaf.setup();
        Main main = new Main();
        main.setVisible(true);
    }
}
