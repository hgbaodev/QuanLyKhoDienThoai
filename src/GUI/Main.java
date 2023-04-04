package GUI;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.awt.*;
import javax.swing.*;
import component.MenuTaskbar;
import component.NavigationBar;

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
    PhanQuyen_pnl phanQuyen;
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
                chuyenKho = new ChuyenKho();
                MainContent.removeAll();
                MainContent.add(chuyenKho).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });

//        menuTaskbar.pnl[6].addMouseListener(new java.awt.event.MouseAdapter() {
//            @Override
//            public void mousePressed(java.awt.event.MouseEvent evt) {
//                kiemKe  = new KiemKe();
//                MainContent.removeAll();
//                MainContent.add(kiemKe).setVisible(true);
//                MainContent.repaint();
//                MainContent.validate();
//            }
//        });
        menuTaskbar.pnl[6].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhapKho = new NhapKho();
                MainContent.removeAll();
                MainContent.add(nhapKho).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[7].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phieuNhap = new PhieuNhap(Main.this);
                MainContent.removeAll();
                MainContent.add(phieuNhap).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[8].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                xuatKho = new XuatKho();
                MainContent.removeAll();
                MainContent.add(xuatKho).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[9].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                phieuXuat = new PhieuXuat(Main.this);
                MainContent.removeAll();
                MainContent.add(phieuXuat).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[10].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                khachHang = new KhachHang();
                MainContent.removeAll();
                MainContent.add(khachHang).setVisible(true);
                MainContent.repaint();
                MainContent.validate();

            }
        });
        menuTaskbar.pnl[11].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                nhacungcap = new NhaCungCap();
                MainContent.removeAll();
                MainContent.add(nhacungcap).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });

        menuTaskbar.pnl[12].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {

                nhanVien = new NhanVien();
                MainContent.removeAll();
                MainContent.add(nhanVien).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[13].addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                taiKhoan = new TaiKhoan();
                MainContent.removeAll();
                MainContent.add(taiKhoan).setVisible(true);
                MainContent.repaint();
                MainContent.validate();
            }
        });
        menuTaskbar.pnl[14].addMouseListener(new java.awt.event.MouseAdapter() {
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

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatMacLightLaf());
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("ScrollBar.trackInsets", new Insets(2, 4, 2, 4));
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("ScrollBar.track", new Color(0xe0e0e0));
        
        UIManager.put( "Table.selectionBackground", new Color(1,4,77));
        Main main = new Main();
        main.setVisible(true);
    }
}
