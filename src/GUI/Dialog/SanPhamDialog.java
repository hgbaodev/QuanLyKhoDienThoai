package GUI.Dialog;

import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.KhuVucKhoBUS;
import BUS.MauSacBUS;
import BUS.ThuongHieuBUS;
import DTO.CauHinhSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.SelectForm;
import GUI.Panel.SanPham;
import helper.Formater;
import helper.Validation;
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
import java.util.ArrayList;
import java.util.Random;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class SanPhamDialog extends JDialog implements ActionListener {

    private HeaderTitle titlePage;
    private JPanel pninfosanpham, pnbottom, pnCenter, pninfosanphamright, pnmain, pncard2;
    private ButtonCustom btnThemCHMS, btnHuyBo, btnAddCauHinh, btnEditCauHinh, btnDeleteCauHinh, btnAddSanPham, btnBack;
    InputForm tenSP, xuatxu, chipxuly, dungluongpin, kichthuocman, hedieuhanh, thoigianbaohanh, phienbanhdh, camerasau, cameratruoc;
    InputForm txtgianhap, txtgiaxuat;
    SelectForm cbxRom, cbxRam, cbxMausac;
    SelectForm thuonghieu, khuvuc;
    InputImage hinhanh;
    JTable tblcauhinh;
    JScrollPane scrolltblcauhinh;
    DefaultTableModel tblModelch;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;

    KhuVucKhoBUS kvkhoBus = new KhuVucKhoBUS();
    ThuongHieuBUS thuonghieuBus = new ThuongHieuBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    MauSacBUS mausacBus = new MauSacBUS();

    ArrayList<CauHinhSanPhamDTO> listch = new ArrayList<>();

    String[] arrkhuvuc = kvkhoBus.getArrTenKhuVuc();
    String[] arrthuonghieu = thuonghieuBus.getArrTenThuongHieu();
    final int maloaisp;

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpSP = jpSP;
        maloaisp = jpSP.spBUS.spDAO.getAutoIncrement();
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
        pncard2 = new JPanel(new BorderLayout());
        JPanel cauhinhtop = new JPanel(new GridLayout(1, 5));
        cbxRom = new SelectForm("ROM", romBus.getArrKichThuoc());
        cbxRam = new SelectForm("RAM", ramBus.getArrKichThuoc());
        cbxMausac = new SelectForm("Màu sắc", mausacBus.getArrTenMauSac());
        txtgianhap = new InputForm("Giá nhập");
        txtgiaxuat = new InputForm("Giá xuất");
        cauhinhtop.add(cbxRom);
        cauhinhtop.add(cbxRam);
        cauhinhtop.add(cbxMausac);
        cauhinhtop.add(txtgianhap);
        cauhinhtop.add(txtgiaxuat);

        JPanel cauhinhbottom = new JPanel(new FlowLayout());
        cauhinhbottom.setBackground(Color.white);
        cauhinhbottom.setBorder(new EmptyBorder(0, 0, 10, 0));
        btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnBack = new ButtonCustom("Quay lại trang trước", "warning", 14);
        btnBack.addActionListener(this);
        cauhinhbottom.add(btnBack);
        cauhinhbottom.add(btnAddSanPham);

        JPanel cauhinhcenter = new JPanel(new BorderLayout());

        JPanel cauhinhcenter_left = new JPanel();
        BoxLayout bl = new BoxLayout(cauhinhcenter_left, BoxLayout.Y_AXIS);
        cauhinhcenter_left.setPreferredSize(new Dimension(100, 226));
        cauhinhcenter_left.setBorder(new EmptyBorder(10, 10, 10, 10));
        cauhinhcenter_left.setLayout(bl);
        cauhinhcenter_left.setBackground(Color.WHITE);
        tblcauhinh = new JTable();
        scrolltblcauhinh = new JScrollPane(tblcauhinh);
        tblModelch = new DefaultTableModel();
        String[] header = new String[]{"STT", "RAM", "ROM", "Màu sắc", "Giá nhập", "Giá xuất"};
        tblModelch.setColumnIdentifiers(header);
        tblcauhinh.setModel(tblModelch);
        scrolltblcauhinh.setViewportView(tblcauhinh);
        tblcauhinh.setDefaultRenderer(Object.class, centerRenderer);
        cauhinhcenter_left.add(scrolltblcauhinh);

        JPanel cauhinhcenter_right = new JPanel(new FlowLayout());
        cauhinhcenter_right.setPreferredSize(new Dimension(180, 10));
        cauhinhcenter_right.setBackground(Color.white);
        cauhinhcenter_right.setBorder(new EmptyBorder(0, 0, 0, 10));
        btnAddCauHinh = new ButtonCustom("Thêm cấu hình", "success", 14);
        btnEditCauHinh = new ButtonCustom("Sửa cấu hình", "warning", 14);
        btnDeleteCauHinh = new ButtonCustom("Xoá cấu hình", "danger", 14);

        btnAddCauHinh.addActionListener(this);
        btnEditCauHinh.addActionListener(this);
        btnDeleteCauHinh.addActionListener(this);

        cauhinhcenter_right.add(btnAddCauHinh);
        cauhinhcenter_right.add(btnEditCauHinh);
        cauhinhcenter_right.add(btnDeleteCauHinh);
        cauhinhcenter.add(cauhinhcenter_left, BorderLayout.CENTER);
        cauhinhcenter.add(cauhinhcenter_right, BorderLayout.EAST);

        pncard2.add(cauhinhtop, BorderLayout.NORTH);
        pncard2.add(cauhinhcenter, BorderLayout.CENTER);
        pncard2.add(cauhinhbottom, BorderLayout.SOUTH);

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
        } else if (source == btnBack) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.previous(pnmain);
        } else if (source == btnAddCauHinh) {
            if(validateCardTwo()) {
                listch.add(getCauHinh());
                loadDataToTableCauHinh();
                resetFormCauHinh();
            }
        }
    }

    public int getRowCauHinh() {
        int index = tblcauhinh.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn cấu hình !");
        }
        return index;
    }

    public SanPhamDTO getInfo() {
        String hinhanh = this.hinhanh.getUrl_img();
        String vtensp = tenSP.getText();
        String vxuatxu = xuatxu.getText();
        String vchipxuly = chipxuly.getText();
        int vdungluongpin = Integer.parseInt(dungluongpin.getText());
        double ktman = Double.parseDouble(kichthuocman.getText());
        String hdh = hedieuhanh.getText();
        String camsau = camerasau.getText();
        String camtruoc = cameratruoc.getText();
        int tgbh = Integer.parseInt(thoigianbaohanh.getText());
        int pb = Integer.parseInt(phienbanhdh.getText());
        int vthuonghieu = thuonghieuBus.getAll().get(this.thuonghieu.getSelectedIndex()).getMathuonghieu();
        int khuvuckho = kvkhoBus.getAll().get(this.khuvuc.getSelectedIndex()).getMakhuvuckho();
        SanPhamDTO result = new SanPhamDTO(maloaisp, vtensp, hinhanh, vxuatxu, vchipxuly, vdungluongpin, ktman, hdh, pb, camsau, camtruoc, tgbh, vthuonghieu, khuvuckho, 0);
        JOptionPane.showMessageDialog(this, result);
        return result;
    }

    public CauHinhSanPhamDTO getCauHinh() {
        int rom = romBus.getByIndex(cbxRom.getSelectedIndex()).getMadungluongrom();
        int ram = ramBus.getByIndex(cbxRam.getSelectedIndex()).getMadlram();
        int mausac = mausacBus.getByIndex(cbxMausac.getSelectedIndex()).getMamau();
        int gianhap = Integer.parseInt(txtgianhap.getText());
        int giaban = Integer.parseInt(txtgiaxuat.getText());
        CauHinhSanPhamDTO chsp = new CauHinhSanPhamDTO(0, 0, ram, rom, mausac, gianhap, giaban);
        return chsp;
    }

    public boolean validateCardOne() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) && Validation.isEmpty(xuatxu.getText())
                && Validation.isEmpty(chipxuly.getText()) && Validation.isEmpty(dungluongpin.getText())
                && Validation.isEmpty(kichthuocman.getText()) && Validation.isEmpty(hedieuhanh.getText())
                && Validation.isEmpty(camerasau.getText()) && Validation.isEmpty(cameratruoc.getText())
                && Validation.isEmpty(thoigianbaohanh.getText()) && Validation.isEmpty(phienbanhdh.getText())) {
            check = false;
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
        } else {
            // Check number 
        }
        return check;
    }

    public boolean validateCardTwo() {
        boolean check = true;
        if (Validation.isEmpty(txtgianhap.getText()) && Validation.isEmpty(txtgiaxuat.getText())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin !");
            check = false;
        } else {
            
        }
        return check;
    }

    public void loadDataToTableCauHinh() {
        tblModelch.setRowCount(0);
        for (int i = 0; i < listch.size(); i++) {
            String mau = mausacBus.getTenMau(listch.get(i).getMausac());
            int ram = ramBus.getKichThuocById(listch.get(i).getRam());
            int rom = romBus.getKichThuocById(listch.get(i).getRom());
            tblModelch.addRow(new Object[]{i + 1, ram+"GB", rom+"GB", 
                mau, Formater.FormatVND(listch.get(i).getGianhap()), Formater.FormatVND(listch.get(i).getGiaxuat())
            });
        }
    }
    
    public void resetFormCauHinh() {
        cbxMausac.setSelectedIndex(0);
        cbxRam.setSelectedIndex(0);
        cbxRom.setSelectedIndex(0);
        txtgianhap.setText("");
        txtgiaxuat.setText("");
    }
} 
