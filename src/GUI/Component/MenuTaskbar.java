package GUI.Component;

import DTO.DanhMucChucNangDTO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
//import GUI.Component.itemTaskbar;

public class MenuTaskbar extends JPanel {
    String st[] = {"Trang chủ", "Sản phẩm", "Đơn vị tính", "Loại hàng", "Khu vực kho", "Kiểm kê", "Phiếu nhập", "Phiếu xuất", "Khách hàng", "Nhà cung cấp", "Nhân viên", "Tài khoản", "Phân quyền", "Đăng xuất"};
    String iconst[] = {"/icon/home_30px.png", "/icon/product_30px.png", "/icon/length_30px.png", "/icon/categorize_30px.png", "/icon/account_30px.png", "/icon/estimates_30px.png", "/icon/In Transit_30px.png", "/icon/supply_chain_30px.png", "/icon/Staff_30px.png", "/icon/Supplier_30px.png", "/icon/tool_30px.png", "/icon/data_provider_30px.png", "/icon/user_rights_30px.png", "/icon/logout_30px.png"};
    
    String[][] itemMenu = {
        {"Trang chủ","home_30px","trangchu"},
        {"Sản phẩm","product_30px","sanpham"},
        {"Đơn vị tính", "length_30px", "donvitinh"}
    };
    
    ArrayList<DanhMucChucNangDTO> dmcn;
    public itemTaskbar[] listitem;
 
    JLabel info;
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
        listitem = new itemTaskbar[st.length];
        this.setOpaque(true);
        this.setBackground(DefaultColor);
        this.setLayout(new BorderLayout(0, 0));

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
            if (i + 1 == st.length) {
                listitem[i] = new itemTaskbar(iconst[i], st[i]);
                pnlBottom.add(listitem[i]);
            } else {
                listitem[i] = new itemTaskbar(iconst[i], st[i]);
                pnlCenter.add(listitem[i]);
            }
        }
        
        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setForeground(HowerFontColor);

        for (int i = 0; i < st.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    pnlMenuTaskbarMouseClicked(evt);
                }
            });
        }
    }


    public void pnlMenuTaskbarMouseClicked(MouseEvent evt) {
        for (int i = 0; i < st.length; i++) {
            if (evt.getSource() == listitem[i]) {
                listitem[i].isSelected = true;
                listitem[i].setBackground(HowerBackgroundColor);
                listitem[i].setForeground(HowerFontColor);
            } else {
                listitem[i].isSelected = false;
                listitem[i].setBackground(DefaultColor);
                listitem[i].setForeground(FontColor);
            }
        }
    }
}
