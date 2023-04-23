package GUI.Panel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.SanPhamDTO;
import BUS.SanPhamBUS;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;

import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Main;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class KiemKe extends JPanel implements ActionListener {

    PanelBorderRadius box1, box2, main, functionBar, left, right;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    DefaultTableModel tblModel;
    public SanPhamBUS sanphamBUS = new SanPhamBUS();
    public ArrayList<SanPhamDTO> listsp = sanphamBUS.getAll();
    SanPhamDTO sp = new SanPhamDTO();
    Main m;
    NhanVienDTO nv;

    PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    ArrayList<PhieuNhapDTO> listPhieu;

    Color BackgroundColor = new Color(239, 235, 233);

    public KiemKe(Main m, NhanVienDTO nv) {
        initComponent();
        this.m =m;
                this.nv = nv;
        this.listPhieu = phieunhapBUS.getAll();
        loadDataTable(listPhieu);
    }

    private void initComponent() {

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu kiểm kê", "Nhân viên kiểm kê", "Thời gian", "Số lượng thực tế", "Chênh lệch"};
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        mainFunction = new MainFunction();
        functionBar.add(mainFunction);

        //        //Add Event MouseListener
        mainFunction.btnAdd.addActionListener(this);
        mainFunction.btnDelete.addActionListener(this);
        mainFunction.btnDetail.addActionListener(this);
        mainFunction.btnEdit.addActionListener(this);
        mainFunction.btnNhapExcel.addActionListener(this);
        mainFunction.btnXuatExcel.addActionListener(this);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        scrollTableSanPham.setViewportView(tableSanPham);

        main.add(scrollTableSanPham);
    }

    public void loadDataTable(ArrayList<PhieuNhapDTO> listphieunhap) {
 tblModel.setRowCount(0);
        int size = listphieunhap.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1, listphieunhap.get(i).getMaphieu(),
                nccBUS.getTenNhaCungCap(listphieunhap.get(i).getManhacungcap()),
                nvBUS.getNameById(listphieunhap.get(i).getManguoitao()),
                Formater.FormatTime(listphieunhap.get(i).getThoigiantao()),
            });
        }
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

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainFunction.btnAdd) {
            TaoPhieuKiemKe phieukiemke = new TaoPhieuKiemKe(nv, "create", m);
            m.setPanel(phieukiemke);
        } else if (source == mainFunction.btnDetail) {
            int index = getRowSelected();
            if (index != -1) {
                TaoPhieuKiemKe phieukiemke = new TaoPhieuKiemKe(nv, "view", listPhieu.get(index), m);
                m.setPanel(phieukiemke);
            }
        } else if (source == mainFunction.btnEdit) {
            int index = getRowSelected();
            if (index != -1) {
                TaoPhieuKiemKe phieukiemke = new TaoPhieuKiemKe(nv, "update", listPhieu.get(index), m);
                m.setPanel(phieukiemke);
            }
        }

    }
    
        public int getRowSelected() {
        int index = tableSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu kiểm kê");
        }
        return index;
    }

}
