package GUI.Panel;

import BUS.ChiTietSanPhamBUS;
import BUS.PhienBanSanPhamBUS;
import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.MauSacBUS;
import BUS.NhaCungCapBUS;
import BUS.PhieuKiemKeBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietKiemKeDTO;
import DTO.ChiTietKiemKeSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.NhanVienDTO;
import DTO.PhieuKiemKeDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import GUI.Dialog.QRCode_Dialog;
import GUI.Dialog.SelectImei;
import GUI.Main;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public final class TaoPhieuKiemKe extends JPanel implements ItemListener, ActionListener {

    PanelBorderRadius right, left;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left_top, main, content_right_bottom, content_btn;
    JTable tablePhieuKiemKe, tableSanPham;
    JScrollPane scrollTablePhieuKK, scrollTableSanPham;
    DefaultTableModel tblModel, tblModelSP;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnImport, btnXacNhan;
    InputForm txtMaphieu, txtNhanVien, txtMaSp, txtTenSp, txtMaImeiTheoLo, txtSoLuongImei;
    SelectForm cbxNhaCungCap, cbxTrangThai, cbxCauhinh, cbxPtNhap;
    JTextField txtTimKiem;
    JLabel labelImei, lbltongtien;
    public JTextArea textAreaImei;
    Main m;
    Color BackgroundColor = new Color(240, 247, 250);
    JFrame owner = (JFrame) SwingUtilities.getWindowAncestor(this);

    SanPhamBUS spBUS = new SanPhamBUS();
    NhaCungCapBUS nccBus = new NhaCungCapBUS();
    PhienBanSanPhamBUS phienbanBus = new PhienBanSanPhamBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    PhieuNhapBUS phieunhapBus = new PhieuNhapBUS();
    MauSacBUS mausacBus = new MauSacBUS();
    ChiTietSanPhamBUS chiTietSanPhamBUS = new ChiTietSanPhamBUS();
    PhieuKiemKeBUS phieuKiemKeBUS = new PhieuKiemKeBUS();
    NhanVienDTO nhanVien;

    ArrayList<DTO.SanPhamDTO> listSP = spBUS.getAll();
    ArrayList<PhienBanSanPhamDTO> ch = new ArrayList<>();
    ArrayList<ChiTietKiemKeDTO> danhSachKiemke = new ArrayList<>();
    ArrayList<ChiTietKiemKeSanPhamDTO> danhSachKiemKeSanPham = new ArrayList<>();
    HashMap<ChiTietKiemKeDTO, ArrayList<ChiTietKiemKeSanPhamDTO>> chiTietPhieu;
    int maphieukiemke;
    int rowPhieuSelect = -1;
    private ButtonCustom scanImei;
    private ButtonCustom btnChonImei;
    private JTextArea jTextAreaGhiChu;

    public TaoPhieuKiemKe(NhanVienDTO nv, String type, Main m) {
        this.nhanVien = nv;
        this.m = m;
        initComponent(type);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 5));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 5));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(5, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(5, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent(String type) {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // Phiếu nhập
        tablePhieuKiemKe = new JTable();
        scrollTablePhieuKK = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "RAM", "ROM", "Màu sắc", "Số lượng", "Chênh lệch"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuKiemKe.setModel(tblModel);
        tablePhieuKiemKe.setFocusable(false);
        scrollTablePhieuKK.setViewportView(tablePhieuKiemKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tablePhieuKiemKe.getColumnModel();
        for (int i = 0; i < 8; i++) {
            if (i != 2) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tablePhieuKiemKe.getColumnModel().getColumn(2).setPreferredWidth(200);
        tablePhieuKiemKe.setDefaultEditor(Object.class, null);
        scrollTablePhieuKK.setViewportView(tablePhieuKiemKe);

        tablePhieuKiemKe.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tablePhieuKiemKe.getSelectedRow();
            }
        });

        // Table sản phẩm
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSP = new DefaultTableModel();
        String[] headerSP = new String[]{"Mã SP", "Tên sản phẩm"};
        tblModelSP.setColumnIdentifiers(headerSP);
        tableSanPham.setModel(tblModelSP);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(300);
        tableSanPham.setDefaultEditor(Object.class, null);
        scrollTableSanPham.setViewportView(tableSanPham);
        tableSanPham.setFocusable(false);
        tableSanPham.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = tableSanPham.getSelectedRow();
                if (index >= 0) {
                    SanPhamDTO sp = spBUS.getByIndex(index);
                }
            }
        });
        tableSanPham.setFocusable(false);
        loadDsSanPham();
        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        left.setLayout(new BorderLayout(0, 5));
        left.setBackground(Color.white);

        left_top = new JPanel(); // Chứa tất cả phần ở phía trái trên cùng
        left_top.setLayout(new BorderLayout());
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        left_top.setOpaque(false);

        JPanel content_top, content_left, content_right, content_right_top;
        content_top = new JPanel(new GridLayout(1, 2, 5, 5));
        content_top.setOpaque(false);
        content_left = new JPanel(new BorderLayout(5, 5));
        content_left.setOpaque(false);
        content_left.setPreferredSize(new Dimension(0, 300));

        txtTimKiem = new JTextField();
        txtTimKiem.putClientProperty("JTextField.placeholderText", "Tên sản phẩm, mã sản phẩm...");
        txtTimKiem.putClientProperty("JTextField.showClearButton", true);
        txtTimKiem.putClientProperty("JTextField.leadingIcon", new FlatSVGIcon("./icon/search.svg"));

        txtTimKiem.setPreferredSize(new Dimension(100, 40));
        content_left.add(txtTimKiem, BorderLayout.NORTH);
        content_left.add(scrollTableSanPham, BorderLayout.CENTER);

        content_right = new JPanel(new BorderLayout(5, 5));
        content_right.setOpaque(false);

        content_right_top = new JPanel(new BorderLayout());
        content_right_top.setPreferredSize(new Dimension(100, 260));
        txtMaSp = new InputForm("Mã SP");
        txtMaSp.setEditable(false);
        txtTenSp = new InputForm("Tên sản phẩm");
        txtTenSp.setEditable(false);

        String[] arrCauhinh = {"Chọn sản phẩm"};
        JPanel content_right_top_cbx = new JPanel(new BorderLayout());
        content_right_top_cbx.setPreferredSize(new Dimension(100, 180));
        cbxCauhinh = new SelectForm("Cấu hình", arrCauhinh);
        cbxCauhinh.cbb.addItemListener(this);
        txtSoLuongImei = new InputForm("Số lượng hiện tại");
        txtSoLuongImei.setEditable(false);
        txtSoLuongImei.setPreferredSize(new Dimension(50, 80));
        JPanel jPanelGhiChu = new JPanel(new BorderLayout());
        jPanelGhiChu.setBackground(Color.WHITE);
        jPanelGhiChu.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel jLabelGhiChu = new JLabel("Ghi chú");
        jLabelGhiChu.setBackground(Color.WHITE);
        jLabelGhiChu.setPreferredSize(new Dimension(0, 30));
        jTextAreaGhiChu = new JTextArea();
        jTextAreaGhiChu.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        jPanelGhiChu.add(jLabelGhiChu, BorderLayout.NORTH);
        jPanelGhiChu.add(jTextAreaGhiChu, BorderLayout.CENTER);
        jPanelGhiChu.setPreferredSize(new Dimension(0, 100));

        JPanel jpDonGia = new JPanel();
        jpDonGia.setBackground(Color.WHITE);
        content_right_top_cbx.add(cbxCauhinh, BorderLayout.WEST);
        content_right_top_cbx.add(txtSoLuongImei, BorderLayout.CENTER);
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(content_right_top_cbx, BorderLayout.SOUTH);

        content_right.add(content_right_top, BorderLayout.NORTH);

        content_top.add(content_left);
        content_top.add(content_right);

        content_btn = new JPanel();
        content_btn.setPreferredSize(new Dimension(0, 47));
        content_btn.setLayout(new GridLayout(1, 4, 5, 5));
        content_btn.setBorder(new EmptyBorder(8, 5, 0, 10));
        content_btn.setOpaque(false);
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        btnImport = new ButtonCustom("Nhập Excel", "excel", 14);
        btnImport.setVisible(false);
        btnAddSp.addActionListener(this);
        btnEditSP.addActionListener(this);
        btnDelete.addActionListener(this);
        btnImport.addActionListener(this);
        btnEditSP.setEnabled(false);
        btnDelete.setEnabled(false);
        content_btn.add(btnAddSp);
        content_btn.add(btnImport);
        content_btn.add(btnEditSP);
        content_btn.add(btnDelete);

        left_top.add(content_top, BorderLayout.CENTER);

        main = new JPanel();
        main.setOpaque(false);
        main.setPreferredSize(new Dimension(0, 250));
        main.setBorder(new EmptyBorder(0, 5, 10, 10));
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.add(scrollTablePhieuKK);
        left.add(left_top, BorderLayout.CENTER);
        left.add(main, BorderLayout.SOUTH);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(320, 0));
        right.setBorder(new EmptyBorder(5, 5, 5, 5));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        right_top = new JPanel(new GridLayout(4, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 360));
        right_top.setOpaque(false);
        txtMaphieu = new InputForm("Mã phiếu");
        txtMaphieu.setText("PKK" + phieuKiemKeBUS.getAutoIncrement());
        txtMaphieu.setEditable(false);
        txtNhanVien = new InputForm("Nhân viên");
        txtNhanVien.setText(nhanVien.getHoten());
        txtNhanVien.setEditable(false);
        String[] arrTrangThai = {"Đã nhập", "Huỷ"};
        cbxTrangThai = new SelectForm("Trạng thái", arrTrangThai);
        right_top.add(txtMaphieu);
        right_top.add(txtNhanVien);

        right_center = new JPanel();
        right_center.setPreferredSize(new Dimension(100, 100));
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(2, 1));
        right_bottom.setPreferredSize(new Dimension(300, 100));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        switch (type) {
            case "create" -> {
                btnXacNhan = new ButtonCustom("Xác nhận", "excel", 14);
                btnXacNhan.addActionListener(this);
                right_bottom.add(btnXacNhan);
                left_top.add(content_btn, BorderLayout.SOUTH);
            }
        }

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
    }

    public void loadDsSanPham() {
        tblModelSP.setRowCount(0);
        for (SanPhamDTO sanPhamDTO : spBUS.getAll()) {
            tblModelSP.addRow(new Object[]{
                sanPhamDTO.getMasp(), sanPhamDTO.getTensp()
            });
        }
    }

    

    public String[] getCauHinhPhienBan(int masp) {
        ch = phienbanBus.getAll(masp);
        int size = ch.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = romBus.getKichThuocById(ch.get(i).getRom()) + "GB - "
                    + ramBus.getKichThuocById(ch.get(i).getRam()) + "GB - " + mausacBus.getTenMau(ch.get(i).getMausac());
        }
        return arr;
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        Object source = e.getSource();
        if (source == cbxCauhinh.cbb) {
           
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        
    }
}
