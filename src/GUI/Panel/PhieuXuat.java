package GUI.Panel;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import DTO.PhieuXuatDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.Notification;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuXuat extends JPanel implements ActionListener {

    PanelBorderRadius box1, box2, main, functionBar, box;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tablePhieuXuat;
    JScrollPane scrollTablePhieuXuat;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    InputDate dateStart, dateEnd;
    InputForm moneyMin, moneyMax;

    Main m;
    TaoPhieuXuat taoPhieuXuat;
    TaiKhoanDTO tk;

    Color BackgroundColor = new Color(240, 247, 250);

    ArrayList<PhieuXuatDTO> listPhieuXuat;

    NhanVienBUS nvBUS = new NhanVienBUS();
    PhieuXuatBUS pxBUS = new PhieuXuatBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();

    public PhieuXuat(Main m, TaiKhoanDTO tk) {
        this.m = m;
        this.tk = tk;
        initComponent();
        initComponent();
        loadDataTalbe(pxBUS.getAll());
    }

    private void initComponent() {

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "detail", "cancel", "export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(), "xuathang", action);
        functionBar.add(mainFunction);

        //Add Event MouseListener
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        search = new IntegratedSearch(new String[]{"Tất cả"});
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        leftFunc();

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
//        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tablePhieuXuat = new JTable();
        scrollTablePhieuXuat = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu xuất", "Khách hàng", "Nhân viên nhập", "Thời gian", "Tổng tiền"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuXuat.setModel(tblModel);
        tablePhieuXuat.setFocusable(false);
        scrollTablePhieuXuat.setViewportView(tablePhieuXuat);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuXuat.setDefaultRenderer(Object.class, centerRenderer);
        scrollTablePhieuXuat.setViewportView(tablePhieuXuat);
        tablePhieuXuat.setFocusable(false);

        main.add(scrollTablePhieuXuat);

    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    public void leftFunc() {
        box = new PanelBorderRadius();
        box.setPreferredSize(new Dimension(250, 0));
        box.setLayout(new GridLayout(6, 1, 10, 0));
//        box.setLayout(new FlowLayout(0, 10, 10));
        box.setBorder(new EmptyBorder(0, 5, 150, 5));
        contentCenter.add(box, BorderLayout.WEST);

        JLabel lbl1 = new JLabel("Lọc theo ngày");
        lbl1.putClientProperty("FlatLaf.style", "font: 130% $semibold.font");
        JLabel lbl2 = new JLabel("Lọc theo giá");
        lbl2.putClientProperty("FlatLaf.style", "font: 130% $semibold.font");
        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");
        moneyMin = new InputForm("Từ số tiền (VND)");
        moneyMax = new InputForm("Đến số tiền (VND)");

        moneyMin.getTxtForm().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String min = moneyMin.getText();
                String max = moneyMax.getText();
                listPhieuXuat = pxBUS.filterByMoney(min, max);
                loadDataTalbe(listPhieuXuat);
            }
        });
        moneyMax.getTxtForm().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String min = moneyMin.getText();
                String max = moneyMax.getText();
                listPhieuXuat = pxBUS.filterByMoney(min, max);
                loadDataTalbe(listPhieuXuat);
            }
        });

//        box.add(lbl1);
        box.add(dateStart);
        box.add(dateEnd);
//        box.add(lbl2);
        box.add(moneyMin);
        box.add(moneyMax);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            taoPhieuXuat = new TaoPhieuXuat(m, tk, "create");
            m.setPanel(taoPhieuXuat);
        } else if (e.getSource() == mainFunction.btn.get("detail")) {
            if (getRow() < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu cần xem!");
            } else {
                taoPhieuXuat = new TaoPhieuXuat(m, tk, pxBUS.getSelect(getRow()), "detail");
                m.setPanel(taoPhieuXuat);
            }
        } else if (e.getSource() == mainFunction.btn.get("cancel")) {
            if (tablePhieuXuat.getSelectedRow() < 0) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu!");
            } else {
                int n = JOptionPane.showConfirmDialog(null, "Bạn có chắc muốn xóa phiếu này?", "Xóa phiếu", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    PhieuXuatDTO px = pxBUS.getSelect(tablePhieuXuat.getSelectedRow());
                    pxBUS.cancel(px.getMaphieu());
                    pxBUS.remove(tablePhieuXuat.getSelectedRow());
                    loadDataTalbe(pxBUS.getAll());
                    Notification notification = new Notification(m, Notification.Type.SUCCESS, Notification.Location.TOP_CENTER, "Hủy phiếu thành công");
                    notification.showNotification();
                }
            }
        }
    }

    public void loadDataTalbe(ArrayList<PhieuXuatDTO> listphieuxuat) {
        tblModel.setRowCount(0);
        int size = listphieuxuat.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1,
                listphieuxuat.get(i).getMaphieu(),
                khachHangBUS.getTenKhachHang(listphieuxuat.get(i).getMakh()),
                nvBUS.getNameById(listphieuxuat.get(i).getManguoitao()),
                Formater.FormatTime(listphieuxuat.get(i).getThoigiantao()),
                Formater.FormatVND(listphieuxuat.get(i).getTongTien()),});
        }
    }

    public int getRow() {
        return tablePhieuXuat.getSelectedRow();
    }

}
