package GUI;

import DTO.SanPhamDTO;
import BUS.SanPhamBUS;

import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;

public class KiemKe extends JPanel {

    PanelBorderRadius box1, box2, main, left, right;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;
    DefaultTableModel tblModel;
    public SanPhamBUS sanphamBUS = new SanPhamBUS();
//    public ArrayList<SanPhamDTO> listsp = sanphamBUS.getAll();
//    SanPhamDTO sp = new SanPhamDTO();

    Color BackgroundColor = new Color(239, 235, 233);

    public KiemKe() {
        initComponent();
//        loadDataTable(listsp);
    }

    private void initComponent() {

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4 chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
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

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new PanelBorderRadius();
        BoxLayout boxlyLeft = new BoxLayout(left, BoxLayout.Y_AXIS);
        left.setLayout(boxlyLeft);
        left.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(left, BorderLayout.CENTER);
        contentCenter.add(left, BorderLayout.WEST);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Hình ảnh"};
        tblModel.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        left.add(scrollTableSanPham);

    }

    public void loadDataTable(ArrayList<DTO.SanPhamDTO> result) {
        tblModel.setRowCount(0);
        for (DTO.SanPhamDTO sanPham : result) {
            tblModel.addRow(new Object[]{
                sanPham.getMasp(), sanPham.getTensp(), sanPham.getGianhap(), sanPham.getGiaban()
            });
        }
    }

}
