package view;

import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import swing.PanelBorderRadius;

public class SanPham extends JPanel {

    PanelBorderRadius box1, box2, main, functionBar;
    JPanel pnlTop, pnlBottom, pnlLeft, pnlRight, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;

    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // pnlTop, pnlBottom, pnlLeft, pnlRight chỉ để thêm contentCenter ở giữa mà contentCenter không bị dính sát vào các thành phần khác
        
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(0, 40));
        pnlTop.setBackground(BackgroundColor);
        this.add(pnlTop, BorderLayout.NORTH);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(0, 40));
        pnlBottom.setBackground(BackgroundColor);
        this.add(pnlBottom, BorderLayout.SOUTH);

        pnlLeft = new JPanel();
        pnlLeft.setPreferredSize(new Dimension(40, 0));
        pnlLeft.setBackground(BackgroundColor);
        this.add(pnlLeft, BorderLayout.EAST);

        pnlRight = new JPanel();
        pnlRight.setPreferredSize(new Dimension(40, 0));
        pnlRight.setBackground(BackgroundColor);
        this.add(pnlRight, BorderLayout.WEST);

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 200));
        functionBar.setLayout(new FlowLayout(1, 15, 40));

        mainFunction = new MainFunction();
        functionBar.add(mainFunction);

        search = new IntegratedSearch();
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        
        main = new PanelBorderRadius();
        main.setLayout(new FlowLayout(1, 0, 15));
        contentCenter.add(main, BorderLayout.CENTER);

        lbl1 = new JLabel("BẢNG THỐNG KÊ SẢN PHẨM");
        lbl1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        lbl1.setPreferredSize(new Dimension(600,22));
        main.add(lbl1);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();

        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));

        scrollTableSanPham.setPreferredSize(new Dimension(1000, 450));

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"001", "Chin", "quan lý", "2003"},
                    {"002", "Công", "nhan zien", "2001"},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã số", "Họ tên", "Chức vụ", "Năm Sinh"
                }
        ));
        scrollTableSanPham.setViewportView(tableSanPham);

        main.add(scrollTableSanPham);

        lblImage = new JLabel();
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/sanPham.jpg")));
        lblImage.setPreferredSize(new Dimension(500, 500));
        main.add(lblImage);

    }

    public SanPham() {
        initComponent();
    }

}
