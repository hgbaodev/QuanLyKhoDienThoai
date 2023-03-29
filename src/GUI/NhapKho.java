package GUI;

import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;

public class NhapKho extends JPanel {

    PanelBorderRadius box1, box2, main, left, right;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage;

    Color BackgroundColor = new Color(239, 235, 233);

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
        left.setPreferredSize(new Dimension(600, 0));
        left.setLayout(new FlowLayout(1, 15, 40));
        contentCenter.add(left, BorderLayout.WEST);


        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20,20,20,20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();

        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));

        scrollTableSanPham.setPreferredSize(new Dimension(500, 600));

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"001", "SamSung", "Hàn Cuốc", "091852471"},
                    {"002", "Công", "nhan zien", "091852471"},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã khách hàng", "Tên khách hàng", "Địa chỉ", "Số điện thoại"
                }
        ));
        scrollTableSanPham.setViewportView(tableSanPham);

        main.add(scrollTableSanPham);

    }

    public NhapKho() {
        initComponent();
    }

}
