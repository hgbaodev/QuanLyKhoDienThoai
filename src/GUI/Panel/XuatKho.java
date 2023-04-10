package GUI.Panel;

import BUS.DanhMucSanPhamBUS;
import DAO.KhachHangDAO;
import DAO.DanhMucSanPhamDAO;
import DTO.ChiTietPhieuDTO;
import DTO.ChiTietPhieuDTO;
import DTO.KhachHangDTO;
import DTO.DanhMucSanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import GUI.Component.PanelBorderRadius;
import GUI.Main;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.apache.poi.xssf.streaming.SXSSFRow;

public class XuatKho extends JPanel implements ActionListener {

    private final int n = 3;

    String text[] = {"Mã phiếu xuất", "Mã nhân viên", "Tên Khách Hàng"};

    PanelBorderRadius left_top, left_center, left_bottom, main_top, main_center, main_bottom;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left, main, pnl[];
    JTable tableSanPham, tableXuatKho;
    JScrollPane scrollTableSanPham, scrTableNhapKho;
    JLabel lbl1, lblImage, lbl[], lbl2, lblTongTien;
    JTextField txtSoLuong, txt[], maDM;
    InputForm tenDM, xuatxu, hedieuhanh, thuonghieu, khuvuckho;
    DefaultTableModel tblModelSanPham, tblModelXuatKho;
    JButton btnReturn, btnChonSanPham, btnNhapExcel, btnEditSoLuong, btnDeleteSanPham, btnXuatHang, btnXuatKho;
    JComboBox<String> slfKhachHang;

    public DanhMucSanPhamBUS danhmucsanphamBUS = new DanhMucSanPhamBUS();
    public ArrayList<DanhMucSanPhamDTO> listkh = danhmucsanphamBUS.getAll();
    DanhMucSanPhamDTO dmsp = new DanhMucSanPhamDTO();

    MainFunction mainFunction;
    IntegratedSearch search;

    public DanhMucSanPhamBUS sanphamBUS = new DanhMucSanPhamBUS();
    public ArrayList<DanhMucSanPhamDTO> listsp = sanphamBUS.getAll();
    DanhMucSanPhamDTO sp = new DanhMucSanPhamDTO();
    public ArrayList<ChiTietPhieuDTO> CTPhieu = new ArrayList<>();

    Color BackgroundColor = new Color(245, 229, 240);
    Color buttonColor = new Color(1, 87, 155);

    Main m;
    PhieuXuat phieuXuat;

    public String[] getKhachHang() {
        ArrayList<KhachHangDTO> khachhang = KhachHangDAO.getInstance().selectAll();
        String tenKH[] = new String[khachhang.size()];
        for (int i = 0; i < khachhang.size(); i++) {
            tenKH[i] = khachhang.get(i).getHoten();
        }
        return tenKH;
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
                listsp = sanphamBUS.search(txt);
                loadDataTableSanPham(listsp);
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
        left_bottom.setPreferredSize(new Dimension(0, 80));
        left_bottom.setLayout(new FlowLayout(1, 30, 20));
        left.add(left_bottom, BorderLayout.SOUTH);

        btnReturn = new ButtonCustom("Trở lại", "success", 14, "/icon/Plus_25px.png", 160, 40);
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

        slfKhachHang = new JComboBox(getKhachHang());
        slfKhachHang.setPreferredSize(new Dimension(350, 35));
        pnl[2].add(slfKhachHang);

        txt[0].setEditable(false);
        txt[0].setEnabled(false);
        txt[0].setFocusable(false);

        txt[1].setEditable(false);
        txt[1].setEnabled(false);
        txt[1].setFocusable(false);

        main_center = new PanelBorderRadius();
        BoxLayout b4 = new BoxLayout(main_center, BoxLayout.Y_AXIS);
        main_center.setLayout(b4);
        main_center.setBorder(new EmptyBorder(20, 20, 20, 20));
        main.add(main_center, BorderLayout.CENTER);

        main_bottom = new PanelBorderRadius();
        main_bottom.setPreferredSize(new Dimension(0, 140));
        main_bottom.setLayout(new FlowLayout(1, 5, 5));
        main_bottom.setBorder(new EmptyBorder(5, 20, 20, 20));
        main.add(main_bottom, BorderLayout.SOUTH);

        JPanel main_Panel_bottom = new JPanel();
        main_Panel_bottom.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        main_Panel_bottom.setOpaque(false);
        main_Panel_bottom.setPreferredSize(new Dimension(550, 70));
        main_bottom.add(main_Panel_bottom);

        btnNhapExcel = new ButtonCustom("Nhập Excel", "excel", 13, "/icon/xls_25px.png", 150, 40);
        main_Panel_bottom.add(btnNhapExcel);

        btnEditSoLuong = new ButtonCustom("Sửa số lượng", "warning", 13, "/icon/edit_25px.png", 150, 40);
        main_Panel_bottom.add(btnEditSoLuong);

        btnDeleteSanPham = new ButtonCustom("Xóa sản phẩm", "danger", 13, "/icon/delete_25px.png", 150, 40);
        main_Panel_bottom.add(btnDeleteSanPham);

        JPanel pnl1 = new JPanel();
        pnl1.setOpaque(false);
        pnl1.setLayout(new FlowLayout(1, 40, 0));
        main_bottom.add(pnl1);

        lbl2 = new JLabel("Tổng tiền");
        lbl2.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        pnl1.add(lbl2);

        lblTongTien = new JLabel("0 VNĐ");
        lblTongTien.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 20));
        lblTongTien.setForeground(Color.RED);
        pnl1.add(lblTongTien);

        btnXuatKho = new JButton("XUẤT KHO");
        btnXuatKho.setPreferredSize(new Dimension(180, 40));
        btnXuatKho.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 18));
        btnXuatKho.setBackground(new Color(0, 0, 0));
        btnXuatKho.setForeground(Color.white);
        pnl1.add(btnXuatKho);

    }

    public void loadDataTableSanPham(ArrayList<DTO.DanhMucSanPhamDTO> result) {
        tblModelSanPham.setRowCount(0);
        for (DTO.DanhMucSanPhamDTO sanPham : result) {
            tblModelSanPham.addRow(new Object[]{
                sanPham.getMadanhmuc(), sanPham.getTendanhmuc(), sanPham.getHedieuhanh(), sanPham.getThuonghieu()
            });
        }
    }

    public XuatKho(Main m) {
        initComponent();
        this.m = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btn = e.getSource();
        if (btn == btnChonSanPham) {
            ActionBtnChoose();
        }
        if (btn == btnChonSanPham) {
            ActionBtnReturn();
        }

//        if(e.getSource()==btnChonSanPham){
//            int selectedIndex = tableSanPham.getSelectedRow();
//        if (listsp.isEmpty()) {
//            JOptionPane.showMessageDialog(this, "Danh sách sản phẩm rỗng");
//        } else if (selectedIndex < 0) {
//            JOptionPane.showMessageDialog(this, "Hãy chọn 1 sản phẩm");
//        } else {
//                int ma = Integer.parseInt(tableSanPham.getValueAt(selectedIndex, 0).toString());
//                String sluong = tableSanPham.getValueAt(selectedIndex, 4).toString();
//                DanhMucSanPhamDTO sp = DanhMucSanPhamDAO.getInstance().selectById(String.valueOf(ma));
//                
//                if (Integer.parseInt(txtSoLuong.getText()) > sp.getSoluong()) {
//                    JOptionPane.showMessageDialog(this, "Kho không thể đáp ứng số lượng\nVui lòng nhập số lượng thấp hơn");
//                    txtSoLuong.setText("1");
//                } else {
//                    int flag = 0;
//                    int tmp = Integer.parseInt(txtSoLuong.getText());
//                    for (ChiTietPhieu a : CTPhieu) {
//                        if (a.getMasanpham()==ma) {
//                            flag = 1;
////                            a.setSoLuong(a.getSoluong()+ tmp);
////                            a.setTong(a.getSoluong()* a.getDongia());
//                            txtSoLuong.setText("1");
////                            showExport();
//                        }
//                    }
//                    if (flag == 0) {
//                        ChiTietPhieu x = new ChiTietPhieu();
//                        x.setMasanpham(ma);
//                        x.setSoluong(tmp);
////                        x.setDonGia(a.getGiaban());
////                        x.setTong(x.getSoluong()*x.getDongia());
//                        CTPhieu.add(x);
//                        tmp = 1;
//                        txtSoLuong.setText("1");
////                        ShowExport();
//                    }
//                }
//            
//        }
//        }
    }

    public void ActionBtnChoose() {
        maDM = new JTextField("");
        setMaDM(Integer.toString(dmsp.getMadanhmuc()));
        tenDM = new InputForm("Tên danh mục");
        setTenDM(dmsp.getTendanhmuc());
        hedieuhanh = new InputForm("Hệ điều hành");
        setHediehanh(dmsp.getHedieuhanh());
        thuonghieu = new InputForm("Thương hiệu");
        setThuonghieu(Integer.toString(dmsp.getThuonghieu()));
        khuvuckho = new InputForm("Khu vực kho");
        setKhuvuckho(Integer.toString(dmsp.getKhuvuckho()));

        main_center.add(maDM);
        main_center.add(tenDM);
        main_center.add(hedieuhanh);
        main_center.add(thuonghieu);
        main_center.add(khuvuckho);

    }

    public void ActionBtnReturn() {
        phieuXuat = new PhieuXuat(this.m);
        m.setPanel(phieuXuat);
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

}
