package GUI;

import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import swing.PanelBorderRadius;

public class DonViTinh extends JPanel {
    PanelBorderRadius box1, box2, main, left, right;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;

    Color BackgroundColor = new Color(244, 245, 218);
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
        left.setPreferredSize(new Dimension(300, 00));
        left.setLayout(new FlowLayout(1, 15, 40));
        contentCenter.add(left, BorderLayout.WEST);

        main = new PanelBorderRadius();
        main.setLayout(new FlowLayout(1, 0, 15));
        contentCenter.add(main, BorderLayout.CENTER);

        lbl1 = new JLabel("Đơn vị tính");
        lbl1.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        lbl1.setPreferredSize(new Dimension(300, 22));
        main.add(lbl1);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();

        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));

        scrollTableSanPham.setPreferredSize(new Dimension(700, 450));

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"001", "Nồi cơm điện siêu to khổng lồ", "Việt Nam", "201000", "240000"},
                    {"002", "Công", "nhan zien", "2001"},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã sản phẩm", "Tên sản phẩm", "Xuất xứ", "Giá nhập", "Giá bán",
                }
        ));
        scrollTableSanPham.setViewportView(tableSanPham);
        
        right = new PanelBorderRadius();
        right.setPreferredSize(new Dimension(300, 00));
        right.setLayout(new FlowLayout(1, 15, 40));
        contentCenter.add(right, BorderLayout.EAST);
    }

    public DonViTinh() {
        initComponent();
    }

}