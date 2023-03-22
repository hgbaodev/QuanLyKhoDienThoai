package GUI;

import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import swing.PanelBorderRadius;

public class SanPham extends JPanel {

    PanelBorderRadius box1, box2, main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
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

        // functionBar là thanh bên trên chứa các nút chức năng như thêm xóa sửa, và tìm kiếm
        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1,2));
        functionBar.setBorder(new EmptyBorder(2,2,2,2));

        mainFunction = new MainFunction(this);
        functionBar.add(mainFunction);
        mainFunction.setupEvent();

        search = new IntegratedSearch();
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        // main là phần ở dưới để thống kê bảng biểu
        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20,20,20,20));
        contentCenter.add(main, BorderLayout.CENTER);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();

        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));

//        scrollTableSanPham.setPreferredSize(new Dimension(1000, 0));

        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                    {"001", "Nồi cơm điện siêu to khổng lồ", "Việt Nam", "201000", "240000", "img", "011", "111", "141"},
                    {"002", "Công", "nhan zien", "2001"},
                    {null, null, null, null},
                    {null, null, null, null}
                },
                new String[]{
                    "Mã sản phẩm", "Tên sản phẩm", "Xuất xứ", "Giá nhập", "Giá bán", "Hình ảnh", "Mã đơn vị tính", "Mã loại hàng", "Mã khu vực"
                }
        ));
        scrollTableSanPham.setViewportView(tableSanPham);

        main.add(scrollTableSanPham);


    }

    public SanPham() {
        initComponent();
    }

    
    public void add() {
        JOptionPane.showMessageDialog(null, "Sanr pham");
    }
}
