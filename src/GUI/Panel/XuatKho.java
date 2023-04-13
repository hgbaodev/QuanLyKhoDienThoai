package GUI.Panel;

import DAO.KhachHangDAO;
import DAO.SanPhamDAO;
import BUS.SanPhamBUS;
import DTO.PhienBanSanPhamDTO;
import DAO.PhienBanSanPhamDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuDTO;
import DTO.KhachHangDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.InputFormInline;
import GUI.Component.InputImage;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.streaming.SXSSFRow;

public class XuatKho extends JPanel implements ActionListener, ItemListener {

    private final int n = 3;

    String text[] = {"Mã phiếu xuất", "Mã nhân viên", "Tên Khách Hàng"};

    PanelBorderRadius left_top, left_center, left_bottom, main_top, main_center, main_bottom;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left, main, pnl[], box1, box2, box3, pnlMaSeri;
    JTable tableSanPham, tableXuatKho;
    JScrollPane scrollTableSanPham, scrTableNhapKho;
    JLabel lbl1, lblImage, lbl[], lbl2, lblTongTien;
    JTextField txtSoLuong, txt[];
    DefaultTableModel tblModelSanPham, tblModelXuatKho;
    JButton btnReturn, btnChonSanPham, btnNhapExcel, btnEditSoLuong, btnDeleteSanPham, btnXuatHang, btnXuatKho, btnAddMaSerial;
    JComboBox<String> slfKhachHang;
    InputImage hinhanh;
    InputFormInline txtcauhinhsanpham, maSerial;
    InputForm maDM, tenDM, xuatxu, hedieuhanh, thuonghieu, khuvuckho;
    JRadioButton jrbXuatTheoLo, jrbXuatTungCai;
    ButtonGroup g1;

    public SanPhamBUS danhmucsanphamBUS = new SanPhamBUS();
    public ArrayList<SanPhamDTO> listdmsp = danhmucsanphamBUS.getAll();
    SanPhamDTO dmsp = new SanPhamDTO();

    MainFunction mainFunction;
    IntegratedSearch search;

    SanPhamDTO sp = new SanPhamDTO();
    public ArrayList<ChiTietPhieuDTO> CTPhieu = new ArrayList<>();

    Color BackgroundColor = new Color(240, 247, 250);
    Color buttonColor = new Color(1, 87, 155);

    Main m;
    PhieuXuat phieuXuat;

    public XuatKho(Main m) {
        initComponent();
        this.m = m;
        loadDataTableSanPham(listdmsp);
    }

    public String[] getCauhinhsanpham() {
        ArrayList<PhienBanSanPhamDTO> chsp = PhienBanSanPhamDAO.getInstance().selectAll(getMaDM());
        String maCHSP[] = new String[chsp.size()];
        for (int i = 0; i < chsp.size(); i++) {
            maCHSP[i] = String.valueOf(chsp.get(i).getMaphienbansp());
        }
        return maCHSP;
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
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new JPanel();
        left.setLayout(new BorderLayout(0, 5));
        left.setOpaque(false);
        contentCenter.add(left, BorderLayout.CENTER);

        left_top = new PanelBorderRadius();
        left_top.setPreferredSize(new Dimension(0, 100));
        BoxLayout b1 = new BoxLayout(left_top, BoxLayout.Y_AXIS);
        left_top.setLayout(b1);
        left_top.setBorder(new EmptyBorder(5, 20, 20, 20));
        left.add(left_top, BorderLayout.NORTH);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = search.txtSearchForm.getText();
//                listsp = sanphamBUS.search(txt);
//                loadDataTableSanPham(listsp);
            }
        });
        left_top.add(search);

        left_center = new PanelBorderRadius();
        BoxLayout b2 = new BoxLayout(left_center, BoxLayout.Y_AXIS);
        left_center.setLayout(b2);
        left_center.setBorder(new EmptyBorder(20, 20, 20, 20));
        left.add(left_center, BorderLayout.CENTER);

        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModelSanPham = new DefaultTableModel();
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"};
        tblModelSanPham.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModelSanPham);
        scrollTableSanPham.setViewportView(tableSanPham);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableSanPham.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);

        left_center.add(scrollTableSanPham);

        left_bottom = new PanelBorderRadius();
        left_bottom.setPreferredSize(new Dimension(0, 60));
        left_bottom.setLayout(new FlowLayout(1, 60, 8));
        left.add(left_bottom, BorderLayout.SOUTH);

        btnReturn = new ButtonCustom("Trở lại", "warning", 14, "/icon/return_25px.png", 160, 40);
        btnReturn.addActionListener(this);
        left_bottom.add(btnReturn);

        btnChonSanPham = new ButtonCustom("Chọn sản phẩm", "success", 13, "/icon/Plus_25px.png", 160, 40);
        btnChonSanPham.addActionListener(this);
        left_bottom.add(btnChonSanPham);

        // main là phần ở dưới để thống kê bảng biểu
        main = new JPanel();
        main.setPreferredSize(new Dimension(500, 0));
        main.setLayout(new BorderLayout(0, 5));
        main.setOpaque(false);
        contentCenter.add(main, BorderLayout.EAST);

        main_top = new PanelBorderRadius();
        main_top.setPreferredSize(new Dimension(0, 220));
        BoxLayout b3 = new BoxLayout(main_top, BoxLayout.Y_AXIS);
        main_top.setLayout(b3);
        main_top.setBorder(new EmptyBorder(5, 20, 20, 20));
//        main.add(main_top, BorderLayout.NORTH);

        pnl = new JPanel[n];
        lbl = new JLabel[n];
        txt = new JTextField[n];

        for (int i = 0; i < text.length; i++) {
            pnl[i] = new JPanel();
            pnl[i].setLayout(new FlowLayout(1, 20, 10));
            pnl[i].setOpaque(false);
            main_top.add(pnl[i]);

            lbl[i] = new JLabel(text[i]);
            lbl[i].setPreferredSize(new Dimension(130, 30));
            lbl[i].setFont(new Font("Segoe UI", Font.BOLD, 16));
            pnl[i].add(lbl[i]);

            if (i + 1 == txt.length) {
                break;
            }

            txt[i] = new JTextField();
            txt[i].setPreferredSize(new Dimension(350, 35));
            pnl[i].add(txt[i]);

        }

        txt[0].setEditable(false);
        txt[0].setEnabled(false);
        txt[0].setFocusable(false);

        txt[1].setEditable(false);
        txt[1].setEnabled(false);
        txt[1].setFocusable(false);

        main_center = new PanelBorderRadius();
//        BoxLayout b4 = new BoxLayout(main_center, BoxLayout.Y_AXIS);
//        main_center.setLayout(b4);
//        main_center.setBorder(new EmptyBorder(20, 20, 20, 20));
        main_center.setLayout(new BorderLayout(10, 10));
        main.add(main_center, BorderLayout.CENTER);

        showInfoSanPham();

        main_bottom = new PanelBorderRadius();
        main_bottom.setPreferredSize(new Dimension(0, 100));
        main_bottom.setLayout(new FlowLayout(1, 10, 3));
        main_bottom.setBorder(new EmptyBorder(5, 20, 20, 20));
        main.add(main_bottom, BorderLayout.SOUTH);

        JPanel pnl1 = new JPanel();
        pnl1.setOpaque(false);
        pnl1.setPreferredSize(new Dimension(400, 30));
        pnl1.setLayout(new FlowLayout(1, 40, 0));
        main_bottom.add(pnl1);

        lbl2 = new JLabel("Tổng tiền");
        lbl2.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        pnl1.add(lbl2);

        lblTongTien = new JLabel("0 VNĐ");
        lblTongTien.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        lblTongTien.setForeground(Color.RED);
        pnl1.add(lblTongTien);

        btnXuatKho = new ButtonCustom("THÊM VÀO PHIẾU XUẤT", "excel", 15, "/icon/insert_25px.png", 300, 40);
        main_bottom.add(btnXuatKho);

    }

    public void loadDataTableSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSanPham.setRowCount(0);
        for (DTO.SanPhamDTO sanPham : result) {
            tblModelSanPham.addRow(new Object[]{
                sanPham.getMasp(), sanPham.getTensp(), sanPham.getHedieuhanh(), sanPham.getThuonghieu()
            });
        }
    }

    public void showInfoSanPham() {
        box1 = new JPanel();
        box1.setPreferredSize(new Dimension(210, 0));
        box1.setOpaque(false);
        box1.setLayout(new FlowLayout(0, 0, 0));
        main_center.add(box1, BorderLayout.WEST);
        box2 = new JPanel();
        box2.setOpaque(false);
        main_center.add(box2, BorderLayout.CENTER);
        box3 = new JPanel();
        box3.setOpaque(false);
        box3.setLayout(new FlowLayout(0, 20, 0));
        box3.setPreferredSize(new Dimension(0, 270));
        main_center.add(box3, BorderLayout.SOUTH);

        maDM = new InputForm("Mã danh mục", 200, 100);
        tenDM = new InputForm("Tên danh mục", 200, 100);
        hedieuhanh = new InputForm("Hệ điều hành", 200, 100);
        thuonghieu = new InputForm("Thương hiệu", 200, 100);
        khuvuckho = new InputForm("Khu vực kho", 200, 100);
        hinhanh = new InputImage("Hình minh họa");
        txtcauhinhsanpham = new InputFormInline("Cấu hình", getCauhinhsanpham());

        box1.add(maDM);
        box1.add(tenDM);
        box1.add(khuvuckho);
        box1.add(hedieuhanh);
        box1.add(thuonghieu);
        box2.add(hinhanh);
        box3.add(txtcauhinhsanpham);

        maSerial = new InputFormInline("Nhập số Seri: ", 170, 40);

        btnAddMaSerial = new ButtonCustom("Thêm mã Seri", "success", 13, "/icon/Plus_25px.png", 150, 40);
        btnAddMaSerial.addActionListener(this);

        pnlMaSeri = new JPanel();
        pnlMaSeri.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Mã Seri", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        pnlMaSeri.setOpaque(false);
        pnlMaSeri.setPreferredSize(new Dimension(440, 100));
        box3.add(pnlMaSeri);

        btnDeleteSanPham = new ButtonCustom("Hủy sản phẩm", "danger", 13, "/icon/delete_25px.png", 150, 40);
        btnDeleteSanPham.addActionListener(this);

        jrbXuatTheoLo = new JRadioButton("Xuât từng cái");
        jrbXuatTheoLo.addItemListener(this);
        jrbXuatTungCai = new JRadioButton("Xuất theo lô");
        g1 = new ButtonGroup();
        g1.add(jrbXuatTheoLo);
        g1.add(jrbXuatTungCai);

        box3.add(maSerial);
        box3.add(btnAddMaSerial);
        box3.add(btnDeleteSanPham);
        box3.add(jrbXuatTheoLo);
        box3.add(jrbXuatTungCai);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btn = e.getSource();
        if (btn == btnChonSanPham) {
            ActionBtnChoose();
        }
        if (btn == btnReturn) {
            ActionBtnReturn();
        }

        if (btn == btnDeleteSanPham) {
            ActionBtnDeleteSanPham();
        }
    }

    public void ItemStateChanged(ItemEvent e) {

    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        if ( e.getStateChange()==ItemEvent.SELECTED) {
            System.err.println("12");
        }
        else {
                        System.err.println("13");

        }
    }

    public void ActionBtnChoose() {
        int index = tableSanPham.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
        } else {
            setMaDM(Integer.toString(listdmsp.get(index).getMasp()));
            setTenDM(listdmsp.get(index).getTensp());
//            setHediehanh(listdmsp.get(index).getHedieuhanh());
            setThuonghieu(Integer.toString(listdmsp.get(index).getThuonghieu()));
            setKhuvuckho(Integer.toString(listdmsp.get(index).getKhuvuckho()));
            setHinhanh(listdmsp.get(index).getHinhanh());

        }

    }

    public void ActionBtnReturn() {
        phieuXuat = new PhieuXuat(this.m);
        m.setPanel(phieuXuat);
        System.err.println("dfgdS");

    }

    public void ActionBtnDeleteSanPham() {
        setMaDM("");
        setTenDM("");
        setHediehanh("");
        setThuonghieu("");
        setKhuvuckho("");
        setHinhanh("");
    }

    public String getMaDM() {
        return maDM.getText();
    }

    public void setMaDM(String id) {
        this.maDM.setText(id);
    }

    public String getTenDM() {
        return tenDM.getText();
    }

    public void setTenDM(String id) {
        this.tenDM.setText(id);
    }

    public String getHedieuhanh() {
        return hedieuhanh.getText();
    }

    public void setHediehanh(String id) {
        this.hedieuhanh.setText(id);
    }

    public String getThuonghieu() {
        return thuonghieu.getText();
    }

    public void setThuonghieu(String id) {
        this.thuonghieu.setText(id);
    }

    public String getKhuvuckho() {
        return khuvuckho.getText();
    }

    public void setKhuvuckho(String id) {
        this.khuvuckho.setText(id);
    }

    public String getHinhanh() {
        return hinhanh.getUrl_img();
    }

    public void setHinhanh(String id) {
        this.hinhanh.setUrl_img(id);
    }

}
