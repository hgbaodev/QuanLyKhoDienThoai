package GUI.Panel;

import BUS.NhaCungCapBUS;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Main;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.SelectForm;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class TaoPhieuXuatd extends JPanel implements ActionListener {

    PanelBorderRadius main, right, left_top;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left;
    JTable tablePhieuNhap;
    JScrollPane scrollTablePhieuNhap;
    DefaultTableModel tblModel;
    NhapKho nhapKho;
    ButtonCustom btnAddSp, btnEditSP, btnDelete, btnImport, btnXuatHang, btnChooseKhachHang;
    InputForm txtMaphieu;
    SelectForm cbxNhaCungCap;
    NhaCungCapBUS nccBus = new NhaCungCapBUS();

    Color BackgroundColor = new Color(240, 247, 250);
    Main m;
    XuatKho xuatKho;

    public TaoPhieuXuatd(Main m) {
        initComponent();
        this.m = m;
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 20));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 20));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(20, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(20, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã sản phẩm", "Tên sản phẩm", "RAM", "ROM", "Màu sắc", "Đơn giá", "Số lượng"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuNhap.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablePhieuNhap.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new JPanel(new BorderLayout(0, 10));
        left.setOpaque(false);

        left_top = new PanelBorderRadius();
        left_top.setPreferredSize(new Dimension(0, 60));
        left_top.setLayout(new GridLayout(1, 4, 5, 5));
        left_top.setBorder(new EmptyBorder(5, 5, 10, 10));
        btnAddSp = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnEditSP = new ButtonCustom("Sửa sản phẩm", "warning", 14);
        btnDelete = new ButtonCustom("Xoá sản phẩm", "danger", 14);
        btnImport = new ButtonCustom("Nhập Excel", "excel", 14);
        btnAddSp.addActionListener(this);
        left_top.add(btnAddSp);
        left_top.add(btnEditSP);
        left_top.add(btnDelete);
        left_top.add(btnImport);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        main.add(scrollTablePhieuNhap);
        left.add(left_top, BorderLayout.NORTH);
        left.add(main, BorderLayout.CENTER);

        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(350, 0));
        right.setBorder(new EmptyBorder(10, 10, 10, 10));
        right.setLayout(new BorderLayout());

        JPanel right_top, right_center, right_bottom, pn_tongtien;
        right_top = new JPanel(new GridLayout(2, 1, 0, 0));
        right_top.setPreferredSize(new Dimension(300, 180));
        txtMaphieu = new InputForm("Mã phiếu xuất");
        btnChooseKhachHang = new ButtonCustom("CHỌN KHÁCH HÀNG", "success", 14);
        right_top.add(txtMaphieu);
        right_top.add(btnChooseKhachHang);

        right_center = new JPanel();
        right_center.setPreferredSize(new Dimension(100, 100));
        right_center.setOpaque(false);

        right_bottom = new JPanel(new GridLayout(2, 1));
        right_bottom.setPreferredSize(new Dimension(300, 100));
        right_bottom.setBorder(new EmptyBorder(10, 10, 10, 10));
        right_bottom.setOpaque(false);

        pn_tongtien = new JPanel(new FlowLayout(1, 20, 0));
        pn_tongtien.setOpaque(false);
        JLabel lbltien = new JLabel("TỔNG TIỀN: ");
        lbltien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        JLabel lbltongtien = new JLabel("10.000.000đ");
        lbltongtien.setFont(new Font(FlatRobotoFont.FAMILY, 1, 18));
        lbltien.setForeground(new Color(255, 51, 51));
        pn_tongtien.add(lbltien);
        pn_tongtien.add(lbltongtien);

        btnXuatHang = new ButtonCustom("Nhập hàng", "excel", 14);
        right_bottom.add(pn_tongtien);
        right_bottom.add(btnXuatHang);

        right.add(right_top, BorderLayout.NORTH);
        right.add(right_center, BorderLayout.CENTER);
        right.add(right_bottom, BorderLayout.SOUTH);

        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(right, BorderLayout.EAST);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        Object btn = e.getSource();
//        if (btn == btnAddSp) {
//            xuatKho = new XuatKho(m,tk);
//            m.setPanel(xuatKho);
//        }
    }
}
