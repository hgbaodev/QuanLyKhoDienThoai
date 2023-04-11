package GUI.Dialog;

import BUS.CauHinhSanPhamBUS;
import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.KhuVucKhoBUS;
import BUS.MauSacBUS;
import BUS.ThuongHieuBUS;
import DAO.CauHinhSanPhamDAO;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
    private ButtonCustom btnThemCHMS, btnHuyBo, btnAddCauHinh, btnEditCauHinh, btnDeleteCauHinh, btnResetCauHinh, btnAddSanPham, btnBack, btnViewCauHinh;
    InputForm tenSP, xuatxu, chipxuly, dungluongpin, kichthuocman, thoigianbaohanh, phienbanhdh, camerasau, cameratruoc;
    InputForm txtgianhap, txtgiaxuat;
    SelectForm cbxRom, cbxRam, cbxMausac, hedieuhanh;
    SelectForm thuonghieu, khuvuc;
    InputImage hinhanh;
    JTable tblcauhinh;
    JScrollPane scrolltblcauhinh;
    DefaultTableModel tblModelch;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    GUI.Panel.SanPham jpSP;

    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    MauSacBUS mausacBus = new MauSacBUS();

    ArrayList<CauHinhSanPhamDTO> listch = new ArrayList<>();
    SanPhamDTO sp;
    String[] arrkhuvuc;
    String[] arrthuonghieu;
    int masp;
    int mach;

    public void init(SanPham jpSP) {
        this.jpSP = jpSP;
        masp = jpSP.spBUS.spDAO.getAutoIncrement();
        mach = CauHinhSanPhamDAO.getInstance().getAutoIncrement();
        arrkhuvuc = jpSP.kvkhoBus.getArrTenKhuVuc();
        arrthuonghieu = jpSP.thuonghieuBus.getArrTenThuongHieu();
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        init(jpSP);
        initComponents(title, type);
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        init(jpSP);
        this.sp = sp;
        this.listch = jpSP.spBUS.cauhinhBus.getAll(sp.getMasp());
        initComponents(title, type);
    }

    public void initCardOne(String type) {
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
        phienbanhdh = new InputForm("Phiên bản hđh");
        thoigianbaohanh = new InputForm("Thời gian bảo hành");
        camerasau = new InputForm("Camera sau");
        cameratruoc = new InputForm("Camera trước");
        String[] arrhdh = {"Android", "IOS"};
        hedieuhanh = new SelectForm("Hệ điều hành", arrhdh);
        thuonghieu = new SelectForm("Thương hiệu", arrthuonghieu);
        khuvuc = new SelectForm("Khu vực kho", arrkhuvuc);
        hinhanh = new InputImage("Hình minh họa");

        pninfosanpham.add(tenSP);
        pninfosanpham.add(xuatxu);
        pninfosanpham.add(chipxuly);
        pninfosanpham.add(dungluongpin);
        pninfosanpham.add(kichthuocman);
        pninfosanpham.add(camerasau);
        pninfosanpham.add(cameratruoc);
        pninfosanpham.add(hedieuhanh);
        pninfosanpham.add(phienbanhdh);
        pninfosanpham.add(thoigianbaohanh);
        pninfosanpham.add(thuonghieu);
        pninfosanpham.add(khuvuc);
        pninfosanphamright.add(hinhanh);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(20, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        switch (type) {
            case "view" -> {
                btnViewCauHinh = new ButtonCustom("Xem cấu hình", "warning", 14);
                btnViewCauHinh.addActionListener(this);
                pnbottom.add(btnViewCauHinh);
            }
            case "update" -> {
                btnEditCauHinh = new ButtonCustom("Sửa cấu hình", "warning", 14);
                btnEditCauHinh.addActionListener(this);
                pnbottom.add(btnEditCauHinh);
            }
            case "create" -> {
                btnThemCHMS = new ButtonCustom("Tạo cấu hình", "success", 14);
                btnThemCHMS.addActionListener(this);
                pnbottom.add(btnThemCHMS);
            }
        }

        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        pnbottom.add(btnHuyBo);
        pnCenter.add(pnbottom, BorderLayout.SOUTH);
    }

    public void initCardTwo(String type) {
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

        JPanel cauhinhcenter = new JPanel(new BorderLayout());

        JPanel cauhinhcenter_left = new JPanel();
        BoxLayout bl = new BoxLayout(cauhinhcenter_left, BoxLayout.Y_AXIS);
        cauhinhcenter_left.setPreferredSize(new Dimension(100, 226));
        cauhinhcenter_left.setBorder(new EmptyBorder(10, 10, 10, 10));
        cauhinhcenter_left.setLayout(bl);
        cauhinhcenter_left.setBackground(Color.WHITE);
        tblcauhinh = new JTable();
        tblcauhinh.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = getRowCauHinh();
                if (index != -1) {
                    setInfoCauHinh(listch.get(index));
                }
            }
        });

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
        btnResetCauHinh = new ButtonCustom("Làm mới", "excel", 14);

        btnAddCauHinh.addActionListener(this);
        btnEditCauHinh.addActionListener(this);
        btnDeleteCauHinh.addActionListener(this);
        btnResetCauHinh.addActionListener(this);

        cauhinhcenter_right.add(btnAddCauHinh);
        cauhinhcenter_right.add(btnEditCauHinh);
        cauhinhcenter_right.add(btnDeleteCauHinh);
        cauhinhcenter_right.add(btnResetCauHinh);
        cauhinhcenter.add(cauhinhcenter_left, BorderLayout.CENTER);
        cauhinhcenter.add(cauhinhcenter_right, BorderLayout.EAST);

        JPanel cauhinhbottom = new JPanel(new FlowLayout());
        cauhinhbottom.setBackground(Color.white);
        cauhinhbottom.setBorder(new EmptyBorder(0, 0, 10, 0));

        switch (type) {
            case "view" -> loadDataToTableCauHinh(listch);
            case "update" -> loadDataToTableCauHinh(listch);
            case "create" -> {
                btnAddSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
                btnAddSanPham.addActionListener(this);
                cauhinhbottom.add(btnAddSanPham);
            }
        }
        
        btnBack = new ButtonCustom("Quay lại trang trước", "warning", 14);
        btnBack.addActionListener(this);
        cauhinhbottom.add(btnBack);

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

        initCardOne(type);
        initCardTwo(type);

        pnmain.add(pnCenter);
        pnmain.add(pncard2);

        switch (type) {
            case "view" -> setInfo(sp);
            case "update" -> setInfo(sp);
            default -> {
            }
        }
//                throw new AssertionError();

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
        if (source == btnThemCHMS && validateCardOne()) {
            System.out.println(getInfo());
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        } else if (source == btnBack) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.previous(pnmain);
        } else if (source == btnAddCauHinh) {
            if (validateCardTwo() && checkTonTai()) {
                listch.add(getCauHinh());
                loadDataToTableCauHinh(this.listch);
                resetFormCauHinh();
            }
        } else if (source == btnResetCauHinh) {
            resetFormCauHinh();
            loadDataToTableCauHinh(this.listch);
        } else if (source == btnDeleteCauHinh) {
            int index = getRowCauHinh();
            this.listch.remove(index);
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
        } else if (source == btnEditCauHinh) {
            eventEditCauHinh();
        } else if (source == btnAddSanPham) {
            eventAddSanPham();
        } else if (source == btnViewCauHinh) {
            CardLayout c = (CardLayout) pnmain.getLayout();
            c.next(pnmain);
        }
    }

    public void eventAddSanPham() {
        SanPhamDTO sp = getInfo();
        sp.setHinhanh(addImage(sp.getHinhanh()));
        if (jpSP.spBUS.add(sp, listch)) {
            JOptionPane.showMessageDialog(this, "Thêm sản phẩm thành công !");
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        };
    }

    public void eventEditCauHinh() {
        if (validateCardTwo()) {
            int index = getRowCauHinh();
            listch.get(index).setMausac(mausacBus.getByIndex(cbxMausac.getSelectedIndex()).getMamau());
            listch.get(index).setRam(ramBus.getByIndex(cbxRam.getSelectedIndex()).getMadlram());
            listch.get(index).setRom(romBus.getByIndex(cbxRom.getSelectedIndex()).getMadungluongrom());
            listch.get(index).setGianhap(Integer.parseInt(txtgianhap.getText()));
            listch.get(index).setGiaxuat(Integer.parseInt(txtgiaxuat.getText()));
            loadDataToTableCauHinh(this.listch);
            resetFormCauHinh();
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
        String hdh = hedieuhanh.getValue();
        String camsau = camerasau.getText();
        String camtruoc = cameratruoc.getText();
        int tgbh = Integer.parseInt(thoigianbaohanh.getText());
        int pb = Integer.parseInt(phienbanhdh.getText());
        int vthuonghieu = jpSP.thuonghieuBus.getAll().get(this.thuonghieu.getSelectedIndex()).getMathuonghieu();
        int khuvuckho = jpSP.kvkhoBus.getAll().get(this.khuvuc.getSelectedIndex()).getMakhuvuckho();
        SanPhamDTO result = new SanPhamDTO(masp, vtensp, hinhanh, vxuatxu, vchipxuly, vdungluongpin, ktman, hdh, pb, camsau, camtruoc, tgbh, vthuonghieu, khuvuckho, 0);
        return result;
    }

    public void setInfo(SanPhamDTO sp) {
        hinhanh.setUrl_img(sp.getHinhanh());
        tenSP.setText(sp.getTensp());
        xuatxu.setText(sp.getXuatxu());
        chipxuly.setText(sp.getXuatxu());
        dungluongpin.setText(Integer.toString(sp.getDungluongpin()));
        kichthuocman.setText(Double.toString(sp.getKichthuocman()));
        hedieuhanh.setSelectedItem(sp.getHedieuhanh());
        camerasau.setText(sp.getCamerasau());
        cameratruoc.setText(sp.getCameratruoc());
        thoigianbaohanh.setText(Integer.toString(sp.getThoigianbaohanh()));
        phienbanhdh.setText(Integer.toString(sp.getPhienbanhdh()));
        thuonghieu.setSelectedIndex(jpSP.thuonghieuBus.getIndexByMaLH(sp.getThuonghieu()));
        khuvuc.setSelectedIndex(jpSP.spBUS.getIndexByMaSP(sp.getKhuvuckho()));
    }

    public CauHinhSanPhamDTO getCauHinh() {
        int rom = romBus.getByIndex(cbxRom.getSelectedIndex()).getMadungluongrom();
        int ram = ramBus.getByIndex(cbxRam.getSelectedIndex()).getMadlram();
        int mausac = mausacBus.getByIndex(cbxMausac.getSelectedIndex()).getMamau();
        int gianhap = Integer.parseInt(txtgianhap.getText());
        int giaban = Integer.parseInt(txtgiaxuat.getText());
        CauHinhSanPhamDTO chsp = new CauHinhSanPhamDTO(mach, masp, ram, rom, mausac, gianhap, giaban);
        mach++;
        return chsp;
    }

    public boolean validateCardOne() {
        boolean check = true;
        if (Validation.isEmpty(tenSP.getText()) || Validation.isEmpty(xuatxu.getText())
                || Validation.isEmpty(chipxuly.getText()) || Validation.isEmpty(dungluongpin.getText())
                || Validation.isEmpty(kichthuocman.getText()) || Validation.isEmpty(hedieuhanh.getValue())
                || Validation.isEmpty(camerasau.getText()) || Validation.isEmpty(cameratruoc.getText())
                || Validation.isEmpty(thoigianbaohanh.getText()) || Validation.isEmpty(phienbanhdh.getText())) {
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

    public boolean checkTonTai() {
        boolean check = true;
        if (CauHinhSanPhamBUS.checkDuplicate(listch, getCauHinh())) {
            JOptionPane.showMessageDialog(this, "Cấu hình đã tồn tại !");
            check = false;
        }
        return check;
    }

    public void loadDataToTableCauHinh(ArrayList<CauHinhSanPhamDTO> ch) {
        tblModelch.setRowCount(0);
        for (int i = 0; i < ch.size(); i++) {
            String mau = mausacBus.getTenMau(ch.get(i).getMausac());
            int ram = ramBus.getKichThuocById(ch.get(i).getRam());
            int rom = romBus.getKichThuocById(ch.get(i).getRom());
            tblModelch.addRow(new Object[]{i + 1, ram + "GB", rom + "GB",
                mau, Formater.FormatVND(ch.get(i).getGianhap()), Formater.FormatVND(ch.get(i).getGiaxuat())
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

    public void setInfoCauHinh(CauHinhSanPhamDTO ch) {
        cbxMausac.setSelectedIndex(mausacBus.getIndexByMaMau(ch.getMausac()));
        cbxRam.setSelectedIndex(ramBus.getIndexByMaRam(ch.getRam()));
        cbxRom.setSelectedIndex(romBus.getIndexByMaRom(ch.getRom()));
        txtgianhap.setText(Integer.toString(ch.getGianhap()));
        txtgiaxuat.setText(Integer.toString(ch.getGiaxuat()));
    }
}
