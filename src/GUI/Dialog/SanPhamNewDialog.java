package GUI.Dialog;

import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.SelectForm;
import GUI.Panel.SanPham;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class SanPhamNewDialog extends JDialog implements ActionListener {

    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain, pncard2;
    private ButtonCustom btnThemCHMS, btnHuyBo;
    private ButtonCustom btnaddch, btneditch, btndeletech;
    private ButtonCustom btnaddms, btneditms, btndeletems;
    InputForm tenSP, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, thoigianbaohanh, phienbanhdh, camerasau, cameratruoc;
    InputForm dungluong, ram, mausac;
    SelectForm thuonghieu, khuvuc;
    InputImage hinhanh;
    JTable tblcauhinh, tblmausac;
    JScrollPane scrolltblcauhinh, scrolltblmausac;
    DefaultTableModel tblModelch, tblModelmausac;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;
    String[] arrkhuvuc = {"Khu vực A", "Khu vực B", "Khu vực C"};
    String[] arrthuonghieu = {"Apple", "Oppo", "Xiaomi", "Samsung"};

    public SanPhamNewDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpSP = jpSP;
        initComponents(title, type);
    }

    public void initCardOne() {
        pnCenter = new JPanel(new BorderLayout());
        pninfosanpham = new JPanel(new GridLayout(3, 4, 0, 0));
        pninfosanpham.setBackground(Color.WHITE);
        pnCenter.add(pninfosanpham, BorderLayout.CENTER);

        pninfosanphamright = new JPanel();
        pninfosanphamright.setBackground(Color.WHITE);
        pninfosanphamright.setPreferredSize(new Dimension(300, 600));
        pninfosanphamright.setBorder(new EmptyBorder(0, 10, 0, 10));
        pnCenter.add(pninfosanphamright, BorderLayout.WEST);

        tenSP = new InputForm("Tên sản phẩm");
        xuatxu = new InputForm("Xuất xứ");
        chipxuly = new InputForm("Chip xử lý");
        dungluongpin = new InputForm("Dung lượng pin");
        kichthuocman = new InputForm("Kích thước màn");
        hedieuhanh = new InputForm("Hệ điều hành");
        phienbanhdh = new InputForm("Phiên bản hđh");
        thoigianbaohanh = new InputForm("Thời gian bảo hành");
        camerasau = new InputForm("Camera sau");
        cameratruoc = new InputForm("Camera trước");
        thuonghieu = new SelectForm("Thương hiệu", arrthuonghieu);
        khuvuc = new SelectForm("Khu vực kho", arrkhuvuc);
        hinhanh = new InputImage("Hình minh họa");

        pninfosanpham.add(tenSP);
        pninfosanpham.add(xuatxu);
        pninfosanpham.add(chipxuly);
        pninfosanpham.add(dungluongpin);
        pninfosanpham.add(kichthuocman);
        pninfosanpham.add(hedieuhanh);
        pninfosanpham.add(camerasau);
        pninfosanpham.add(cameratruoc);
        pninfosanpham.add(phienbanhdh);
        pninfosanpham.add(thoigianbaohanh);
        pninfosanpham.add(thuonghieu);
        pninfosanpham.add(khuvuc);
        pninfosanphamright.add(hinhanh);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThemCHMS = new ButtonCustom("Tạo cấu hình", "success", 14);
        btnThemCHMS.addActionListener(this);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        pnbottom.add(btnThemCHMS);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    public void initCardTwo() {
        JPanel pnch_ms, pncauhinh, pnmausac,jpch_top, jpch_top_left, jpch_top_right, jpch_bottom, jpms_top, jpms_center, jpms_bottom;
        pncard2 = new JPanel(new BorderLayout());

        pnch_ms = new JPanel(new GridLayout(1, 2));
        pncauhinh = new JPanel(new BorderLayout());
        pncauhinh.setBackground(Color.WHITE);

        jpch_top = new JPanel(new BorderLayout());
        jpch_top.setBackground(Color.WHITE);
        jpch_top.setPreferredSize(new Dimension(100, 300));

        jpch_top_left = new JPanel(new GridLayout(2, 1));
        dungluong = new InputForm("Dung lượng lưu trữ");
        ram = new InputForm("Dung lượng RAM");
        jpch_top_left.add(dungluong);
        jpch_top_left.add(ram);

        jpch_top_right = new JPanel(new GridLayout(3, 1, 20, 20));
        jpch_top_right.setBorder(new EmptyBorder(15, 5, 15, 5));
        jpch_top_right.setBackground(Color.WHITE);
        btnaddch = new ButtonCustom("Thêm cấu hình", "success", 14);
        btneditch = new ButtonCustom("Sửa cấu hình", "warning", 14);
        btndeletech = new ButtonCustom("Xoá cấu hình", "danger", 14);
        jpch_top_right.add(btnaddch);
        jpch_top_right.add(btneditch);
        jpch_top_right.add(btndeletech);

        jpch_top.add(jpch_top_left, BorderLayout.CENTER);
        jpch_top.add(jpch_top_right, BorderLayout.EAST);

        jpch_bottom = new JPanel(new FlowLayout());
        jpch_bottom.setBackground(Color.WHITE);
        tblcauhinh = new JTable();
        tblcauhinh.setPreferredSize(new Dimension(550, 80));
        scrolltblcauhinh = new JScrollPane();
        tblModelch = new DefaultTableModel();
        String[] header = new String[]{"STT", "Dung lượng lưu trữ", "RAM"};
        tblModelch.setColumnIdentifiers(header);
        tblcauhinh.setModel(tblModelch);
        scrolltblcauhinh.setViewportView(tblcauhinh);
        tblcauhinh.setDefaultRenderer(Object.class, centerRenderer);

        tblcauhinh.setPreferredScrollableViewportSize(tblcauhinh.getPreferredSize());
        tblcauhinh.setFillsViewportHeight(true);
        jpch_bottom.add(scrolltblcauhinh);

        pncauhinh.add(jpch_top, BorderLayout.CENTER);
        pncauhinh.add(jpch_bottom, BorderLayout.SOUTH);

        pnch_ms.add(pncauhinh, BorderLayout.CENTER);

        pnmausac = new JPanel(new BorderLayout());
        pnmausac.setBackground(Color.WHITE);

        jpms_top = new JPanel(new GridLayout(1, 1));
        jpms_top.setPreferredSize(new Dimension(100, 100));
        mausac = new InputForm("Màu sắc");
        jpms_top.add(mausac);

        jpms_center = new JPanel(new FlowLayout(1, 10, 10));
        jpms_center.setBackground(Color.WHITE);
        btnaddms = new ButtonCustom("Thêm màu sắc", "success", 14);
        btneditms = new ButtonCustom("Sửa màu sắc", "warning", 14);
        btndeletems = new ButtonCustom("Xoá màu sắc", "danger", 14);
        jpms_center.add(btnaddms);
        jpms_center.add(btneditms);
        jpms_center.add(btndeletems);

        jpms_bottom = new JPanel();
        jpms_bottom.setBackground(Color.WHITE);
        tblmausac = new JTable();
        tblmausac.setPreferredSize(new Dimension(550, 80));
        scrolltblmausac = new JScrollPane();
        tblModelmausac = new DefaultTableModel();
        String[] headerms = new String[]{"STT", "Dung lượng lưu trữ", "RAM"};
        tblModelmausac.setColumnIdentifiers(headerms);
        tblmausac.setModel(tblModelmausac);
        scrolltblmausac.setViewportView(tblmausac);
        tblmausac.setDefaultRenderer(Object.class, centerRenderer);

        tblmausac.setPreferredScrollableViewportSize(tblmausac.getPreferredSize());
        tblmausac.setFillsViewportHeight(true);
        jpms_bottom.add(scrolltblmausac);

        pnmausac.add(jpms_top, BorderLayout.NORTH);
        pnmausac.add(jpms_center, BorderLayout.CENTER);
        pnmausac.add(jpms_bottom, BorderLayout.SOUTH);
        pnch_ms.add(pnmausac, BorderLayout.EAST);
        
        JPanel pnbottom1 = new JPanel(new FlowLayout());
        pnbottom1.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom1.setBackground(Color.white);
        ButtonCustom btnThemCHMS1 = new ButtonCustom("Tạo giá cấu hình", "success", 14);
        btnThemCHMS1.addActionListener(this);
        ButtonCustom btnHuyBo1 = new ButtonCustom("Huỷ bỏ", "danger", 14);
        pnbottom1.add(btnThemCHMS1);
        pnbottom1.add(btnHuyBo1);
        
        pncard2.add(pnch_ms,BorderLayout.CENTER);
        pncard2.add(pnbottom1, BorderLayout.SOUTH);

    }

    public void initComponents(String title, String type) {
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        this.setSize(new Dimension(1150, 480));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new CardLayout());

        initCardOne();
        initCardTwo();

        pnmain.add(pnCenter);
        pnmain.add(pncard2);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public String addImage(String urlImg) {
        Random randomGenerator = new Random();
        int ram = randomGenerator.nextInt(1000);
        File sourceFile = new File(urlImg);
        String destPath = "./src/img_product";
        File destFolder = new File(destPath);
        String newName = ram + sourceFile.getName();
        try {
            Path dest = Paths.get(destFolder.getPath(), newName);
            Files.copy(sourceFile.toPath(), dest);
        } catch (IOException e) {
        }
        return newName;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnThemCHMS) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        }
    }

}
