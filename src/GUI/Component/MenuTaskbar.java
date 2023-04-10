package GUI.Component;

import DAO.ChiTietQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.DanhMucChucNangDTO;
import DTO.TaiKhoanDTO;
import GUI.Main;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import javax.swing.*;
//import GUI.Component.itemTaskbar;

public class MenuTaskbar extends JPanel {
    String[][] getSt = {
        {"Trang chủ", "home_30px.png", "trangchu"},
        {"Sản phẩm", "product_30px.png", "trangchu"},
        {"Thương hiệu", "categorize_30px.png", "thuonghieu"},
        {"Khu vực kho", "account_30px.png", "khuvuckho"},
        {"Kiểm kê", "estimates_30px.png", "kiemke"},
        {"Phiếu nhập", "In Transit_30px.png", "phieunhap"},
        {"Phiếu xuất", "supply_chain_30px.png", "phieuxuat"},
        {"Nhà cung cấp", "Staff_30px.png", "nhacungcap"},
        {"Khách hàng", "Supplier_30px.png", "khachang"},
        {"Nhân viên", "tool_30px.png", "nhanvien"},
        {"Tài khoản", "data_provider_30px.png", "taikhoan"},
        {"Phân quyền", "user_rights_30px.png", "phanquyen"},
        {"Đăng xuất", "logout_30px.png", "dangxuat"},};

    Main main;
    TaiKhoanDTO user;
    public itemTaskbar[] listitem;

    JLabel info;
    JScrollPane scrollPane;

    //tasbarMenu chia thành 3 phần chính là pnlCenter, pnlTop, pnlBottom
    JPanel pnlCenter, pnlTop, pnlBottom, bar1, bar2;

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    Color HowerFontColor = new Color(1, 87, 155);
    Color HowerBackgroundColor = new Color(187, 222, 251);
    private ArrayList<ChiTietQuyenDTO> listQuyen;

    public MenuTaskbar() {
        initComponent();
    }

    public MenuTaskbar(Main main, TaiKhoanDTO tk) {
        this.main = main;
        this.user = tk;
        listQuyen = ChiTietQuyenDAO.getInstance().selectAll(tk.getManhomquyen() + "");
        initComponent();
    }

    private void initComponent() {
        listitem = new itemTaskbar[getSt.length];
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
        pnlCenter.setPreferredSize(new Dimension(250, 600));
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
        for (int i = 0; i < getSt.length; i++) {
            if (i + 1 == getSt.length) {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlBottom.add(listitem[i]);
            } else {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][0]);
                pnlCenter.add(listitem[i]);
//                if(!checkRole(getSt[i][2])) listitem[i].setVisible(false);
            }
        }

        listitem[0].setBackground(HowerBackgroundColor);
        listitem[0].setForeground(HowerFontColor);

        for (int i = 0; i < getSt.length; i++) {
            listitem[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent evt) {
                    pnlMenuTaskbarMousePress(evt);
                }
            });
        }
    }

    public boolean checkRole(String s) {
        boolean check = false;
        for(int i = 0; i < listQuyen.size(); i++){
            if (listQuyen.get(i).getHanhdong().equals("view")) {
                if(s.equals(listQuyen.get(i).getMachucnang())) check = true;
            }
        }
        return check;
    }

    public void pnlMenuTaskbarMousePress(MouseEvent evt) {
        for (int i = 0; i < getSt.length; i++) {
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
