package GUI.Dialog;

import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.MauSacBUS;
import BUS.PhienBanSanPhamBUS;
import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.ChiTietPhieuNhapDTO;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.PhieuNhapDTO;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.SelectForm;
import helper.Formater;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.HashMap;
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

public class ChiTietPhieuDialog extends JDialog {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_bottom_right, pnmain_bottom_left;
    InputForm txtMaPhieu, txtNhanVien, txtNhaCungCap, txtThoiGian;
    DefaultTableModel tblModel, tblModelImei;
    JTable table, tblImei;
    JScrollPane scrollTable, scrollTableImei;
    PhieuNhapDTO phieunhap;
    PhienBanSanPhamBUS phienbanBus = new PhienBanSanPhamBUS();
    PhieuNhapBUS phieunhapBus = new PhieuNhapBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    MauSacBUS mausacBus = new MauSacBUS();
    SanPhamBUS sanPhamBUS = new SanPhamBUS();

    ArrayList<ChiTietPhieuNhapDTO> chitietphieu;
    HashMap<Integer, ArrayList<ChiTietSanPhamDTO>> chitietsanpham = new HashMap<>();

    public ChiTietPhieuDialog(JFrame owner, String title, boolean modal, PhieuNhapDTO phieunhapDTO) {
        super(owner, title, modal);
        this.phieunhap = phieunhapDTO;
        chitietphieu = phieunhapBus.getChiTietPhieu(phieunhapDTO.getMaphieu());
        chitietsanpham = phieunhapBus.getChiTietSanPham(phieunhapDTO.getMaphieu());
        initComponent(title);
        initPhieuNhap();
        loadDataTableChiTietPhieu(chitietphieu);
        this.setVisible(true);
    }

    public void initPhieuNhap() {
        txtMaPhieu.setText("PN" + Integer.toString(this.phieunhap.getMaphieu()));
        txtNhaCungCap.setText(phieunhap.getManhacungcap() + "");
        txtNhanVien.setText(phieunhap.getManguoitao() + "");
        txtThoiGian.setText(Formater.FormatTime(phieunhap.getThoigiantao()));
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuNhapDTO> ctPhieu) {
        tblModel.setRowCount(0);
        int size = ctPhieu.size();
        for (int i = 0; i < size; i++) {
            PhienBanSanPhamDTO pb = phienbanBus.getByMaPhienBan(ctPhieu.get(i).getMaphienbansp());
            tblModel.addRow(new Object[]{
                i + 1, pb.getMasp(), sanPhamBUS.getByMaSP(pb.getMasp()).getTensp(), ramBus.getKichThuocById(pb.getRam()) + "GB",
                romBus.getKichThuocById(pb.getRom()) + "GB", mausacBus.getTenMau(pb.getMausac()),
                Formater.FormatVND(ctPhieu.get(i).getDongia()), ctPhieu.get(i).getSoluong()
            });
        }
    }
    
    public void loadDataTableImei(ArrayList<ChiTietSanPhamDTO> dssp) {
        tblModelImei.setRowCount(0);
        int size = dssp.size();
        for(int i = 0; i < size; i++) {
            tblModelImei.addRow(new Object[]{
                i + 1, dssp.get(i).getImei()
            });
        }
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(1100, 460));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new GridLayout(1, 4));
        txtMaPhieu = new InputForm("Mã phiếu");
        txtNhanVien = new InputForm("Nhân viên nhập");
        txtNhaCungCap = new InputForm("Nhà cung cấp");
        txtThoiGian = new InputForm("Thời gian tạo");

        txtMaPhieu.setEditable(false);
        txtNhanVien.setEditable(false);
        txtNhaCungCap.setEditable(false);
        txtThoiGian.setEditable(false);

        pnmain_top.add(txtMaPhieu);
        pnmain_top.add(txtNhanVien);
        pnmain_top.add(txtNhaCungCap);
        pnmain_top.add(txtThoiGian);

        pnmain_bottom = new JPanel(new BorderLayout(5, 5));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);

        pnmain_bottom_left = new JPanel(new GridLayout(1, 1));
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên SP", "RAM", "ROM", "Màu sắc", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        table.setFocusable(false);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        table.setDefaultRenderer(Object.class, centerRenderer);
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int index = table.getSelectedRow();
                if (index != -1) {
                    loadDataTableImei(chitietsanpham.get(chitietphieu.get(index).getMaphienbansp()));
                }
            }
        });
        pnmain_bottom_left.add(scrollTable);

        pnmain_bottom_right = new JPanel(new GridLayout(1, 1));
        pnmain_bottom_right.setPreferredSize(new Dimension(200, 10));
        tblImei = new JTable();
        scrollTableImei = new JScrollPane();
        tblModelImei = new DefaultTableModel();
        tblModelImei.setColumnIdentifiers(new String[]{"STT", "Mã Imei"});
        tblImei.setModel(tblModelImei);
        tblImei.setFocusable(false);
        tblImei.setDefaultRenderer(Object.class, centerRenderer);
        tblImei.getColumnModel().getColumn(1).setPreferredWidth(170);
        scrollTableImei.setViewportView(tblImei);
        pnmain_bottom_right.add(scrollTableImei);

        pnmain_bottom.add(pnmain_bottom_left, BorderLayout.CENTER);
        pnmain_bottom.add(pnmain_bottom_right, BorderLayout.EAST);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }
}
