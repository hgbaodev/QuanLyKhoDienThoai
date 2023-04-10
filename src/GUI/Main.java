package GUI;

import DTO.TaiKhoanDTO;
import GUI.Panel.TrangChu;
import GUI.Panel.TaiKhoan;
import GUI.Panel.PhieuNhap;
import GUI.Panel.KhachHang;
import GUI.Panel.SanPham;
import GUI.Panel.NhanVien;
import GUI.Panel.ThuongHieu;
import GUI.Panel.PhanQuyen;
import GUI.Panel.NhaCungCap;
import GUI.Panel.ChuyenKho;
import GUI.Panel.KhuVucKho;
import GUI.Panel.PhieuXuat;
import java.awt.*;
import javax.swing.*;
import GUI.Component.MenuTaskbar;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {
    public JPanel MainContent;
    TaiKhoanDTO user;
    Color MainColor = new Color(250, 250, 250);
    TrangChu trangChu;
    SanPham sanPham;
    ThuongHieu loaiHang;
    KhuVucKho quanLyKho;
    ChuyenKho chuyenKho;
    PhieuNhap phieuNhap;
    PhieuXuat phieuXuat;
    KhachHang khachHang;
    NhaCungCap nhacungcap;
    NhanVien nhanVien;
    TaiKhoan taiKhoan;
    PhanQuyen phanQuyen;

    private MenuTaskbar menuTaskbar;

    private void initComponent() {
        this.setSize(new Dimension(1400, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Hệ thống quản lý kho hàng ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(user!=null){
            menuTaskbar = new MenuTaskbar(this,user);
        } else {
            menuTaskbar = new MenuTaskbar();
        }
        
        menuTaskbar.setPreferredSize(new Dimension(250, 1400));

        this.add(menuTaskbar, BorderLayout.WEST);

        MainContent = new JPanel();
        MainContent.setBackground(MainColor);
        MainContent.setLayout(new BorderLayout(0, 0));

        this.add(MainContent, BorderLayout.CENTER);

        trangChu = new TrangChu();
        MainContent.add(trangChu).setVisible(true);

        menuTaskbar.listitem[0].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                trangChu = new TrangChu();
                setPanel(trangChu);
            }
        });

        menuTaskbar.listitem[1].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                sanPham = new SanPham();
                setPanel(sanPham);

            }
        });
        menuTaskbar.listitem[2].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                loaiHang = new ThuongHieu();
                setPanel(loaiHang);
            }
        });
        menuTaskbar.listitem[3].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                quanLyKho = new KhuVucKho();
                setPanel(quanLyKho);
            }
        });
        menuTaskbar.listitem[4].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                chuyenKho = new ChuyenKho();
                setPanel(chuyenKho);
            }
        });

        menuTaskbar.listitem[5].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuNhap = new PhieuNhap(Main.this);
                setPanel(phieuNhap);
            }
        });
        menuTaskbar.listitem[6].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phieuXuat = new PhieuXuat(Main.this);
                setPanel(phieuXuat);
            }
        });
        menuTaskbar.listitem[7].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                khachHang = new KhachHang();
                setPanel(khachHang);
            }
        });
        menuTaskbar.listitem[8].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhacungcap = new NhaCungCap();
                setPanel(nhacungcap);
            }
        });

        menuTaskbar.listitem[9].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                nhanVien = new NhanVien();
                setPanel(nhanVien);
            }
        });
        menuTaskbar.listitem[10].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                taiKhoan = new TaiKhoan();
                setPanel(taiKhoan);
            }
        });
        menuTaskbar.listitem[11].addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                phanQuyen = new PhanQuyen();
                setPanel(phanQuyen);
            }
        });
    }

    public Main() {
        initComponent();
    }
    
    public Main(TaiKhoanDTO user) {
        this.user = user;
        initComponent();
    }

    public void setPanel(JPanel pn) {
        MainContent.removeAll();
        MainContent.add(pn).setVisible(true);
        MainContent.repaint();
        MainContent.validate();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put( "PasswordField.showRevealButton", true );
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.selectionBackground", new Color(1, 4, 77));
        Main main = new Main();
        main.setVisible(true);
    }
}
