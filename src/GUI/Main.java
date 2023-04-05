package GUI;

import GUI.Panel.TrangChu;
import GUI.Panel.TaiKhoan;
import GUI.Panel.XuatKho;
import GUI.Panel.PhieuNhap;
import GUI.Panel.NhapKho;
import GUI.Panel.KhachHang;
import GUI.Panel.DonViTinh;
import GUI.Panel.SanPham;
import GUI.Panel.KiemKe;
import GUI.Panel.NhanVien;
import GUI.Panel.LoaiHang;
import GUI.Panel.PhanQuyen;
import GUI.Panel.NhaCungCap;
import GUI.Panel.ChuyenKho;
import GUI.Panel.QuanLyKho;
import GUI.Panel.PhieuXuat;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.*;
import javax.swing.*;
import GUI.Component.MenuTaskbar;
import GUI.Component.NavigationBar;
import java.awt.event.MouseAdapter;

public class Main extends javax.swing.JFrame {

    JPanel pnlThanhDuoi;
    public JPanel MainContent;
    Color MainColor = new Color(250, 250, 250);
    TrangChu trangChu;
    SanPham sanPham;
    DonViTinh donViTinh;
    LoaiHang loaiHang;
    QuanLyKho quanLyKho;
    ChuyenKho chuyenKho;
    KiemKe kiemKe;
    NhapKho nhapKho;
    PhieuNhap phieuNhap;
    XuatKho xuatKho;
    PhieuXuat phieuXuat;
    KhachHang khachHang;
    NhaCungCap nhacungcap;
    NhanVien nhanVien;
    TaiKhoan taiKhoan;
    PhanQuyen phanQuyen;
    JLabel lbl;

    private MenuTaskbar menuTaskbar;
    private NavigationBar navbar;

    private void initComponent() {
        this.setSize(new Dimension(1400, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Hệ thống quản lý kho hàng ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        menuTaskbar = new MenuTaskbar();
        menuTaskbar.setPreferredSize(new Dimension(250, 1400));

        this.add(menuTaskbar, BorderLayout.WEST);

        MainContent = new JPanel();
        MainContent.setBackground(MainColor);
        MainContent.setLayout(new BorderLayout(0, 0));

        this.add(MainContent, BorderLayout.CENTER);

        trangChu = new TrangChu();
        MainContent.add(trangChu).setVisible(true);

        menuTaskbar.pnl[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                trangChu = new TrangChu();
                setPanel(trangChu);
            }
        });

        menuTaskbar.pnl[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                sanPham = new SanPham();
                setPanel(sanPham);

            }
        });
        menuTaskbar.pnl[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                donViTinh = new DonViTinh();
                setPanel(donViTinh);
            }
        });
        menuTaskbar.pnl[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                loaiHang = new LoaiHang();
                setPanel(loaiHang);
            }
        });
        menuTaskbar.pnl[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quanLyKho = new QuanLyKho();
                setPanel(quanLyKho);
            }
        });
        menuTaskbar.pnl[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                chuyenKho = new ChuyenKho();
                setPanel(chuyenKho);
            }
        });

        menuTaskbar.pnl[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhapKho = new NhapKho();
                setPanel(nhapKho);
            }
        });
        menuTaskbar.pnl[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phieuNhap = new PhieuNhap(Main.this);
                setPanel(phieuNhap);
            }
        });
        menuTaskbar.pnl[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xuatKho = new XuatKho();
                setPanel(xuatKho);
            }
        });
        menuTaskbar.pnl[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phieuXuat = new PhieuXuat(Main.this);
                setPanel(phieuXuat);
            }
        });
        menuTaskbar.pnl[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khachHang = new KhachHang();
                setPanel(nhacungcap);

            }
        });
        menuTaskbar.pnl[11].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhacungcap = new NhaCungCap();
                setPanel(nhacungcap);
            }
        });

        menuTaskbar.pnl[12].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {

                nhanVien = new NhanVien();
                setPanel(nhanVien);
            }
        });
        menuTaskbar.pnl[13].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taiKhoan = new TaiKhoan();
                setPanel(taiKhoan);
            }
        });
        menuTaskbar.pnl[14].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phanQuyen = new PhanQuyen();
                setPanel(phanQuyen);
            }
        });
    }

    public Main() {
        initComponent();
    }

    public void setPanel(JPanel pn) {
        MainContent.removeAll();
        MainContent.add(pn).setVisible(true);
        MainContent.repaint();
        MainContent.validate();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("ScrollBar.track", new Color(0xe0e0e0));
        UIManager.put("Table.selectionBackground", new Color(1, 4, 77));
        Main main = new Main();
        main.setVisible(true);
    }
}
