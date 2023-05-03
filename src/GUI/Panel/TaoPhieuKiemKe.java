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
                setInfoPhieu(danhSachKiemke.get(index));
                actionbtn("update");
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
                    setSelectSp(sp);
                    if (checkTonTai() != null) {
                        setInfoPhieu(checkTonTai());
                        actionbtn("update");
                    } else {
                        actionbtn("add");
                    }
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
        content_right_top_cbx.add(jPanelGhiChu, BorderLayout.SOUTH);
        content_right_top.add(txtMaSp, BorderLayout.WEST);
        content_right_top.add(txtTenSp, BorderLayout.CENTER);
        content_right_top.add(content_right_top_cbx, BorderLayout.SOUTH);

        content_right_bottom = new JPanel(new CardLayout());

        JPanel card_content_two_model = new JPanel(new BorderLayout());
        card_content_two_model.setBorder(new EmptyBorder(10, 10, 10, 10));
        labelImei = new JLabel("Mã Imei");
        labelImei.setPreferredSize(new Dimension(70, 0));
        scanImei = new ButtonCustom("Quét imei", "success", 14);
        scanImei.setPreferredSize(new Dimension(110, 0));
        JPanel panelScanCenter = new JPanel(new BorderLayout());
        btnChonImei = new ButtonCustom("Chọn Imei", "success", 14);
        btnChonImei.setPreferredSize(new Dimension(100, 0));
        panelScanCenter.setBackground(Color.WHITE);
        panelScanCenter.add(btnChonImei, BorderLayout.LINE_END);
        JPanel jpanelImei = new JPanel(new BorderLayout());
        jpanelImei.setPreferredSize(new Dimension(0, 40));
        jpanelImei.setBackground(Color.WHITE);
        jpanelImei.setBorder(new EmptyBorder(0, 0, 10, 0));
        jpanelImei.add(labelImei, BorderLayout.WEST);
        jpanelImei.add(panelScanCenter, BorderLayout.CENTER);
        jpanelImei.add(scanImei, BorderLayout.EAST);
        scanImei.addActionListener(this);
        btnChonImei.addActionListener(this);
        textAreaImei = new JTextArea(6, 4);
        textAreaImei.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        textAreaImei.setEditable(false);
        card_content_two_model.setSize(new Dimension(0, 100));
        card_content_two_model.setBackground(Color.white);
        card_content_two_model.add(jpanelImei, BorderLayout.NORTH);
        card_content_two_model.add(textAreaImei, BorderLayout.CENTER);

        textAreaImei.getDocument().addDocumentListener(new DocumentListener() {

            @Override
            public void removeUpdate(DocumentEvent e) {
                String[] arrimei = textAreaImei.getText().split("\n");
                txtSoLuongImei.setText(arrimei.length + "");
            }

            @Override
            public void insertUpdate(DocumentEvent e) {
                String[] arrimei = textAreaImei.getText().split("\n");
                txtSoLuongImei.setText(arrimei.length + "");
            }

            @Override
            public void changedUpdate(DocumentEvent arg0) {

            }
        });

        content_right_bottom.add(card_content_two_model);

        content_right.add(content_right_top, BorderLayout.NORTH);
        content_right.add(content_right_bottom, BorderLayout.CENTER);

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
        txtMaphieu.setText("PN" + phieuKiemKeBUS.getAutoIncrement());
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

    public void setSelectSp(SanPhamDTO sp) {
        txtMaSp.setText(sp.getMasp() + "");
        textAreaImei.setText("");
        jTextAreaGhiChu.setText("");
        txtSoLuongImei.setText("");
        txtTenSp.setText(sp.getTensp());
        cbxCauhinh.setArr(getCauHinhPhienBan(sp.getMasp()));
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
            ChiTietKiemKeDTO ct = checkTonTai();
            if (ct == null) {
                textAreaImei.setText("");
                txtSoLuongImei.setText("");
                jTextAreaGhiChu.setText("");
                actionbtn("add");
            } else {
                setInfoPhieu(ct);
                actionbtn("update");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == scanImei) {
            if (ch.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để quét mã");
            } else {
                QRCode_Dialog qr = new QRCode_Dialog(owner, "Scan", true, textAreaImei);
            }
        }

        if (source == btnChonImei) {
            if (ch.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm để quét mã");
            } else {
                txtSoLuongImei.setText("");
                int index = cbxCauhinh.cbb.getSelectedIndex();
                PhienBanSanPhamDTO pb = ch.get(index);
                int mapb = pb.getMaphienbansp();
                ArrayList<ChiTietSanPhamDTO> ctsp = chiTietSanPhamBUS.getAllByMaPBSP(mapb);
                SelectImei sImei = new SelectImei(owner, "Chọn IMEI", true, this, ctsp);
            }
        }

        if (source == btnAddSp) {
            if (textAreaImei.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm!");
            } else {
                int index = cbxCauhinh.cbb.getSelectedIndex();
                PhienBanSanPhamDTO pb = ch.get(index);
                int maphieuke = phieuKiemKeBUS.getAutoIncrement();
                int maphienban = pb.getMaphienbansp();
                int soluong = Integer.parseInt(txtSoLuongImei.getText());
                int chechlech = soluong - phienbanBus.getSoluong(maphienban);
                String ghichu = jTextAreaGhiChu.getText();
                ChiTietKiemKeDTO chiTietKiemKeDTO = new ChiTietKiemKeDTO(maphieuke, maphienban, soluong, chechlech, ghichu);
                danhSachKiemke.add(chiTietKiemKeDTO);
                ArrayList<ChiTietSanPhamDTO> ctsp = chiTietSanPhamBUS.getAllByMaPBSP(maphienban);
                for (ChiTietSanPhamDTO chiTietSanPhamDTO : ctsp) {
                    ChiTietKiemKeSanPhamDTO ct;
                    if (checkImeiArea(chiTietSanPhamDTO.getImei())) {
                        ct = new ChiTietKiemKeSanPhamDTO(maphieuke, chiTietSanPhamDTO.getMaphienbansp(), chiTietSanPhamDTO.getImei(), 1);
                    } else {
                        ct = new ChiTietKiemKeSanPhamDTO(maphieuke, chiTietSanPhamDTO.getMaphienbansp(), chiTietSanPhamDTO.getImei(), 0);
                    }
                    danhSachKiemKeSanPham.add(ct);
                    loadPhieuKiemKe(danhSachKiemke);
                }
            }
        }

        if (source == btnDelete) {
            int index = tablePhieuKiemKe.getSelectedRow();
            if (index < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn sản phẩm cần xóa!");
            } else {
                ChiTietKiemKeDTO ctp = danhSachKiemke.get(index);
                danhSachKiemke.remove(index);
                ArrayList<ChiTietKiemKeSanPhamDTO> dsKeSanPhamDTOs = new ArrayList<>();
                for (ChiTietKiemKeSanPhamDTO chiTietKiemKeSanPhamDTO : danhSachKiemKeSanPham) {
                    if (chiTietKiemKeSanPhamDTO.getMaphienban() != ctp.getMaphienban()) {
                        dsKeSanPhamDTOs.add(chiTietKiemKeSanPhamDTO);
                    }
                }
                danhSachKiemKeSanPham = dsKeSanPhamDTOs;
                JOptionPane.showMessageDialog(null, "Xóa chi tiết phiếu thành công!");
                loadPhieuKiemKe(danhSachKiemke);
                resetForm();
                actionbtn("add");
            }
        }

        if (source == btnEditSP) {
            int index = cbxCauhinh.cbb.getSelectedIndex();
            PhienBanSanPhamDTO pb = ch.get(index);
            int maphieuke = phieuKiemKeBUS.getAutoIncrement();
            int maphienban = pb.getMaphienbansp();
            int soluong = Integer.parseInt(txtSoLuongImei.getText());
            int chechlech = soluong - phienbanBus.getSoluong(maphienban);
            String ghichu = jTextAreaGhiChu.getText();
            ChiTietKiemKeDTO chiTietKiemKeDTO = new ChiTietKiemKeDTO(maphieuke, maphienban, soluong, chechlech, ghichu);
            int vitri = getIndexPhieuByPhienBan(maphienban);
            danhSachKiemke.set(vitri, chiTietKiemKeDTO);
            ArrayList<ChiTietKiemKeSanPhamDTO> dsKeSanPhamDTOs = new ArrayList<>();
                for (ChiTietKiemKeSanPhamDTO chiTietKiemKeSanPhamDTO : danhSachKiemKeSanPham) {
                    if (chiTietKiemKeSanPhamDTO.getMaphienban() != maphienban) {
                        dsKeSanPhamDTOs.add(chiTietKiemKeSanPhamDTO);
                    }
                }
            danhSachKiemKeSanPham = dsKeSanPhamDTOs;
            ArrayList<ChiTietSanPhamDTO> ctsp = chiTietSanPhamBUS.getAllByMaPBSP(maphienban);
                for (ChiTietSanPhamDTO chiTietSanPhamDTO : ctsp) {
                    ChiTietKiemKeSanPhamDTO ct;
                    if (checkImeiArea(chiTietSanPhamDTO.getImei())) {
                        ct = new ChiTietKiemKeSanPhamDTO(maphieuke, chiTietSanPhamDTO.getMaphienbansp(), chiTietSanPhamDTO.getImei(), 1);
                    } else {
                        ct = new ChiTietKiemKeSanPhamDTO(maphieuke, chiTietSanPhamDTO.getMaphienbansp(), chiTietSanPhamDTO.getImei(), 0);
                    }
                    danhSachKiemKeSanPham.add(ct);
                    resetForm();
                    actionbtn("add");
                    loadPhieuKiemKe(danhSachKiemke);
            }
        }

        if (source == btnXacNhan) {
            if (danhSachKiemke.size() == 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng không để trống phiếu kiểm kê!");
            } else {
                PhieuKiemKeDTO phieuKiemKeDTO = new PhieuKiemKeDTO(phieuKiemKeBUS.getAutoIncrement(), nhanVien.getManv());
                phieuKiemKeBUS.insert(phieuKiemKeDTO, danhSachKiemke, danhSachKiemKeSanPham);
                JOptionPane.showMessageDialog(null, "Tạo phiếu thành công!");
                PhieuNhap pnlPhieu = new PhieuNhap(m, nhanVien);
                m.setPanel(pnlPhieu);
            }
        }
    }

    public void loadPhieuKiemKe(ArrayList<ChiTietKiemKeDTO> danhSachKiemKe) {
        tblModel.setRowCount(0);
        int size = danhSachKiemKe.size();
        for (int i = 0; i < size; i++) {
            PhienBanSanPhamDTO pb = phienbanBus.getByMaPhienBan(danhSachKiemKe.get(i).getMaphienban());
            tblModel.addRow(new Object[]{
                i + 1, pb.getMasp(), spBUS.getByMaSP(pb.getMasp()).getTensp(), ramBus.getKichThuocById(pb.getRam()) + "GB",
                romBus.getKichThuocById(pb.getRom()) + "GB", mausacBus.getTenMau(pb.getMausac()),
                danhSachKiemKe.get(i).getSoluong(), danhSachKiemKe.get(i).getChenhlech()
            });
        }
    }

    public boolean checkImeiArea(String maImei) {
        String[] arrimei = textAreaImei.getText().split("\n");
        boolean check = false;
        for (int i = 0; i < arrimei.length; i++) {
            if (arrimei[i].equals(maImei)) {
                check = true;
                return check;
            }
        }
        return check;
    }

    public void actionbtn(String type) {
        boolean val_1 = type.equals("add");
        boolean val_2 = type.equals("update");
        btnAddSp.setEnabled(val_1);
        btnImport.setEnabled(val_1);
        btnEditSP.setEnabled(val_2);
        btnDelete.setEnabled(val_2);
        content_btn.revalidate();
        content_btn.repaint();
    }

    public void setInfoPhieu(ChiTietKiemKeDTO ctp) {
        PhienBanSanPhamDTO pb = phienbanBus.getByMaPhienBan(ctp.getMaphienban());
        SanPhamDTO sanPhamSelect = spBUS.getSp(pb.getMaphienbansp());
        setSelectSp(sanPhamSelect);
        txtSoLuongImei.setText(ctp.getSoluong() + "");
        jTextAreaGhiChu.setText(ctp.getGhichu());
        textAreaImei.setText("");
        for (ChiTietKiemKeSanPhamDTO chiTietKiemKeSanPhamDTO : danhSachKiemKeSanPham) {
            if (chiTietKiemKeSanPhamDTO.getMaphienban() == ctp.getMaphienban() && chiTietKiemKeSanPhamDTO.getTrangthai() == 1) {
                textAreaImei.append(chiTietKiemKeSanPhamDTO.getMaimei() + "\n");
            }
        }
    }

    public ChiTietKiemKeDTO checkTonTai() {
        ChiTietKiemKeDTO check = null;
        int pb = ch.get(cbxCauhinh.getSelectedIndex()).getMaphienbansp();
        for (ChiTietKiemKeDTO chiTietPhieu : danhSachKiemke) {
            if (chiTietPhieu.getMaphienban() == pb) {
                return chiTietPhieu;
            }
        }
        return check;
    }

    public void resetForm() {
        txtMaSp.setText("");
        textAreaImei.setText("");
        jTextAreaGhiChu.setText("");
        txtSoLuongImei.setText("");
        txtTenSp.setText("");
        cbxCauhinh.setArr(new String[]{"Chọn sản phẩm"});
    }

    public int getIndexPhieuByPhienBan(int mapb) {
        int result = -1;
        for (int i = 0; i < danhSachKiemke.size(); i++) {
            if (danhSachKiemke.get(i).getMaphienban() == mapb) {
                result = i;
            }
        }
        return result;
    }

}
