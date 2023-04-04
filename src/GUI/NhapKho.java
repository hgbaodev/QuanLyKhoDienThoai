package GUI;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import DTO.NhaCungCapDTO;
import DAO.NhaCungCapDAO;

import component.ButtonMajor;
import component.ButtonToolBar;
import component.IntegratedSearch;
import component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import component.PanelBorderRadius;
import component.SelectForm;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NhapKho extends JPanel {

    private final int n = 3;

    String text[] = {"Mã phiếu nhập", "Mã nhân viên", "Mã nhà cung cấp"};

    PanelBorderRadius left_top, left_center, left_bottom, main_top, main_center, main_bottom;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left, main, pnl[];
    JTable tableSanPham, tableNhapKho;
    JScrollPane scrollTableSanPham, scrTableNhapKho;
    JLabel lbl1, lblImage, lbl[];
    JTextField txtSoLuong, txt[];
    DefaultTableModel tblModelSanPham, tblModelNhapKho;
    JButton btnAddSoLuong, btnNhapExcel, btnEditSoLuong, btnDeleteSanPham, btnNhapHang;
    SelectForm slfNhaCungCap;

    MainFunction mainFunction;
    IntegratedSearch search;

    public SanPhamBUS sanphamBUS = new SanPhamBUS();
    public ArrayList<SanPhamDTO> listsp = sanphamBUS.getAll();
    SanPhamDTO sp = new SanPhamDTO();

    Color BackgroundColor = new Color(239, 235, 233);
    Color buttonColor = new Color(1, 87, 155);

    public NhapKho() {
        initComponent();
        loadDataTableSanPham(listsp);

    }

    public String[] getnhacungcap() {
        ArrayList<NhaCungCapDTO> ncc = NhaCungCapDAO.getInstance().selectAll();
        String tenNCC[] = new String[ncc.size()];
        for (int i = 0; i < ncc.size(); i++) {
            tenNCC[i] = ncc.get(i).getTenncc();
        }
        return tenNCC;
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
        contentCenter.setLayout(new BorderLayout(20, 20));
        this.add(contentCenter, BorderLayout.CENTER);

        left = new JPanel();
        left.setPreferredSize(new Dimension(500, 0));
        left.setLayout(new BorderLayout(0, 10));
        left.setOpaque(false);
        contentCenter.add(left, BorderLayout.WEST);

        left_top = new PanelBorderRadius();
        left_top.setPreferredSize(new Dimension(0, 120));
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
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá nhập", "Giá bán"};
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

        lbl1 = new JLabel("Số lượng");
        left_bottom.add(lbl1);
        txtSoLuong = new JTextField();
        txtSoLuong.setPreferredSize(new Dimension(70, 30));
        left_bottom.add(txtSoLuong);
        btnAddSoLuong = new ButtonMajor("THÊM", "/icon/Plus_25px.png");
        left_bottom.add(btnAddSoLuong);

        // main là phần ở dưới để thống kê bảng biểu
        main = new JPanel();
        main.setLayout(new BorderLayout(0, 10));
        main.setOpaque(false);
        contentCenter.add(main, BorderLayout.CENTER);

        main_top = new PanelBorderRadius();
        main_top.setPreferredSize(new Dimension(0, 220));
        BoxLayout b3 = new BoxLayout(main_top, BoxLayout.Y_AXIS);
        main_top.setLayout(b3);
        main_top.setBorder(new EmptyBorder(5, 20, 20, 20));
        main.add(main_top, BorderLayout.NORTH);

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

        slfNhaCungCap = new SelectForm("", getnhacungcap());
        slfNhaCungCap.setPreferredSize(new Dimension(360, 50));
        pnl[2].add(slfNhaCungCap);

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

        tableNhapKho = new JTable();
        scrTableNhapKho = new JScrollPane();
        tableNhapKho.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tableNhapKho.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tblModelNhapKho = new DefaultTableModel();
        String[] header1 = new String[]{"Số thứ tự", "Mã sản phẩm", "Tên sẩn phẩm", "Số lượng", "Đơn giá"};
        tblModelNhapKho.setColumnIdentifiers(header1);
        tableNhapKho.setModel(tblModelNhapKho);
        scrTableNhapKho.setViewportView(tableNhapKho);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        tableNhapKho.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableNhapKho.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableNhapKho.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableNhapKho.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableNhapKho.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);

        main_center.add(scrTableNhapKho);

        main_bottom = new PanelBorderRadius();
        main_bottom.setPreferredSize(new Dimension(0, 80));
        main_bottom.setLayout(new BorderLayout(10,10));
        main.add(main_bottom, BorderLayout.SOUTH);
        
        JPanel main_Panel_bottom = new JPanel();
        main_Panel_bottom.setBackground(Color.WHITE);
        main_Panel_bottom.setLayout(new FlowLayout(1, 15, 20));
        lbl1 = new JLabel("Số lượng");
        btnNhapExcel = new ButtonMajor("Nhập Excel", "/icon/xls_25px.png");
        main_Panel_bottom.add(btnNhapExcel);

        btnEditSoLuong = new ButtonMajor("Sửa số lượng", "/icon/edit_25px.png");
        main_Panel_bottom.add(btnEditSoLuong);

        btnDeleteSanPham = new ButtonMajor("Xóa sản phẩm", "/icon/delete_25px.png");
        main_Panel_bottom.add(btnDeleteSanPham);
        main_bottom.add(main_Panel_bottom, BorderLayout.CENTER);
    }

    public void loadDataTableSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSanPham.setRowCount(0);
        for (DTO.SanPhamDTO sanPham : result) {
            tblModelSanPham.addRow(new Object[]{
                sanPham.getMasp(), sanPham.getTensp(), sanPham.getGianhap(), sanPham.getGiaban()
            });
        }
    }

}
