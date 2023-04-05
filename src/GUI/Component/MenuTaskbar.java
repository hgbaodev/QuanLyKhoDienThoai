package GUI.Component;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
//import GUI.Component.itemTaskbar;

public class MenuTaskbar extends JPanel {

    private final int n = 20;
    String st[] = {"Trang chủ", "Sản phẩm", "Đơn vị tính", "Loại hàng", "Khu vực kho", "Kiểm kê", "Phiếu nhập", "Phiếu xuất", "Khách hàng", "Nhà cung cấp", "Nhân viên", "Tài khoản", "Phân quyền", "Đăng xuất"};
    String iconst[] = {"/icon/home_30px.png", "/icon/product_30px.png", "/icon/length_30px.png", "/icon/categorize_30px.png", "/icon/account_30px.png", "/icon/estimates_30px.png", "/icon/In Transit_30px.png", "/icon/supply_chain_30px.png", "/icon/Staff_30px.png", "/icon/Supplier_30px.png", "/icon/tool_30px.png", "/icon/data_provider_30px.png", "/icon/user_rights_30px.png", "/icon/logout_30px.png"};

    public JPanel pnl[], pnlDangxuat;
    JLabel lbl[], lblIcon[], info;
    JScrollPane scrollPane;

    //tasbarMenu chia thành 3 phần chính là pnlCenter, pnlTop, pnlBottom
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2;

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(187, 222, 251);

    public MenuTaskbar() {
        initComponent();
    }

    private void initComponent() {
        FlatLightLaf.setup();
        System.setProperty("sun.java2d.uiScale", "1.0");

        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));

        pnl = new JPanel[n];
        lbl = new JLabel[n];
        lblIcon = new JLabel[n];

        // bar1, bar là các đường kẻ mỏng giữa taskbarMenu và MainContent
        pnlTop = new JPanel();
        pnlTop.setPreferredSize(new Dimension(250, 80));
        pnlTop.setBackground(DefaultColor);
        pnlTop.setLayout(new BorderLayout(0, 0));

        // Cái info này bỏ vô cho đẹp tí, mai mốt có gì xóa đi đê hiển thị thông tin tài khoản và quyền
        info = new JLabel();
        info.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/permission.png")));
        pnlTop.add(info);

        bar1 = new JPanel();
        bar1.setBackground(new Color(204, 214, 219));
        bar1.setPreferredSize(new Dimension(1, 1));
        pnlTop.add(bar1, BorderLayout.EAST);

        pnlTop.add(info);

        this.add(pnlTop);

        this.add(pnlTop, BorderLayout.NORTH);

        pnlCenter = new JPanel();
        pnlCenter.setPreferredSize(new Dimension(250, 720));
        pnlCenter.setBackground(DefaultColor);
        pnlCenter.setLayout(new FlowLayout(0, 0, 0));

        scrollPane = new JScrollPane(pnlCenter, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        this.add(scrollPane, BorderLayout.CENTER);

        pnlBottom = new JPanel();
        pnlBottom.setPreferredSize(new Dimension(250, 50));
        pnlBottom.setBackground(DefaultColor);
        pnlBottom.setLayout(new BorderLayout(0, 0));

        bar2 = new JPanel();
        bar2.setBackground(new Color(204, 214, 219));
        bar2.setPreferredSize(new Dimension(1, 1));
        pnlBottom.add(bar2, BorderLayout.EAST);

        this.add(pnlBottom, BorderLayout.SOUTH);

        // Dùng vòng lặp đẻ hiển thị, nếu đến "Đăng xuất" thì sẽ được add vào pnlBottom để nó xuống dưới cuối
        for (int i = 0; i < st.length; i++) {
            pnl[i] = new JPanel();
            lblIcon[i] = new JLabel();
            lbl[i] = new JLabel(st[i]);

            if (i + 1 == st.length) {
                itemTaskbar(pnl[i], lblIcon[i], lbl[i], iconst[i], st[i], pnlBottom);
            } else {
                itemTaskbar(pnl[i], lblIcon[i], lbl[i], iconst[i], st[i], pnlCenter);
            }

        }
        pnl[0].setBackground(HowerBackgroundColor);
        lbl[0].setForeground(HowerFontColor);

        for (int i = 0; i < st.length; i++) {
            pnl[i].addMouseListener(new java.awt.event.MouseAdapter() {
                @Override
                public void mousePressed(java.awt.event.MouseEvent evt) {
                    pnlMenuTaskbarMouseClicked(evt);

                }

                @Override
                public void mouseClicked(java.awt.event.MouseEvent evt) {
//                    pnlMenuTaskbarMouseEntered(evt);
                }

            });
        }

    }

    public void pnlMenuTaskbarMouseEntered(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < st.length; i++) {
            if (evt.getSource() == pnl[i]) {
                pnl[i].setBackground(HowerBackgroundColor);
                lbl[i].setForeground(HowerFontColor);
//                lbl[i].putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
            } else {
                pnl[i].setBackground(DefaultColor);
                lbl[i].setForeground(FontColor);
//                lbl[i].putClientProperty("FlatLaf.style", "font: 150% $medium.font");
            }
        }
    }

    public void pnlMenuTaskbarMouseClicked(java.awt.event.MouseEvent evt) {
        for (int i = 0; i < st.length; i++) {
            if (evt.getSource() == pnl[i]) {
                pnl[i].setBackground(HowerBackgroundColor);
                lbl[i].setForeground(HowerFontColor);
//                lbl[i].putClientProperty("FlatLaf.style", "font: 150% $semibold.font");
            } else {
                pnl[i].setBackground(DefaultColor);
                lbl[i].setForeground(FontColor);
//                lbl[i].putClientProperty("FlatLaf.style", "font: 150% $medium.font");
            }

        }
    }

    private void itemTaskbar(JPanel pnlItem, JLabel lblIcon, JLabel pnlContent, String linkIcon, String content, JPanel pnlMain) {
//        pnlItem = new JPanel();
        pnlItem.setLayout(new FlowLayout(1, 10, 7));
        pnlItem.setPreferredSize(new Dimension(250, 45));
        pnlItem.setBackground(DefaultColor);

//        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(30, 30));
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource(linkIcon)));

        pnlItem.add(lblIcon);

//        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(170, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent.setForeground(FontColor);

        pnlItem.add(pnlContent);

        pnlMain.add(pnlItem);
    }

}
