package GUI;

import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;

public class LoaiHang extends JPanel {

    private final int n = 20;
    PanelBorderRadius box[];
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel lbl1, lblImage, lbl[], lblIcon[];
    JScrollPane scrPane;

    String st[] = {"Trang chủ", "Sản phẩm", "Loại hàng", "Quản lý kho", "Nhập kho", "Xuất kho", "Khách hàng", "Nhà cung cấp", "Nhân viên", "Tài khoản", "Đơn vị tính", "Đăng xuất"};
    String iconst[] = {"/icon/home_30px.png", "/icon/product_30px.png", "/icon/categorize_30px.png", "/icon/account_30px.png", "/icon/supply_chain_30px.png", "/icon/handle_with_care_30px.png", "/icon/Staff_30px.png", "/icon/Supplier_30px.png", "/icon/tool_30px.png", "/icon/data_provider_30px.png", "/icon/length_30px.png", "/icon/logout_30px.png"};

    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);

    private void initComponent() {

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

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
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new GridLayout(4, 4, 20, 20));

        scrPane = new JScrollPane(contentCenter);
        scrPane.setBorder(new EmptyBorder(0, 0, 0, 0));
        this.add(scrPane, BorderLayout.CENTER);

        box = new PanelBorderRadius[n];
        lbl = new JLabel[n];
        lblIcon = new JLabel[n];
        for (int i = 0; i < st.length; i++) {
            box[i] = new PanelBorderRadius();
            box[i].setLayout(new FlowLayout(0, 20, 50));
//            box[i].setPreferredSize(new Dimension(250, 250));
            box[i].setBackground(DefaultColor);

            lblIcon[i] = new JLabel();
            lblIcon[i].setPreferredSize(new Dimension(30, 30));
            lblIcon[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(iconst[i])));

            box[i].add(lblIcon[i]);

            lbl[i] = new JLabel(st[i]);
            lbl[i].setPreferredSize(new Dimension(170, 30));
            lbl[i].putClientProperty("FlatLaf.style", "font: 150% $medium.font");
            lbl[i].setForeground(FontColor);

            box[i].add(lbl[i]);

            contentCenter.add(box[i]);
        }
    }

    public LoaiHang() {
        initComponent();
    }

}
