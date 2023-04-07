package GUI.Panel;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import DTO.NhaCungCapDTO;
import DAO.NhaCungCapDAO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputForm;
import GUI.Component.InputFormInline;
import GUI.Component.IntegratedSearch;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.border.TitledBorder;
import static javax.swing.border.TitledBorder.DEFAULT_POSITION;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class NhapKho extends JPanel {

    PanelBorderRadius left_top, left_center, left_bottom, main_top, main_center, main_bottom;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left, main, pnl[], pnl1;
    JTable tableSanPham, tableNhapKho;
    JScrollPane scrollTableSanPham, scrTableNhapKho;
    JLabel lbl1, lbl[], lbl2, lblTongTien;
    JTextField txt[];
    JSpinner txtSoLuong;
    DefaultTableModel tblModelSanPham, tblModelNhapKho;
    JButton btnAddSoLuong, btnNhapExcel, btnEditSoLuong, btnDeleteSanPham, btnNhapKho;
    JComboBox slfNhaCungCap;
    IntegratedSearch search;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    public SanPhamBUS sanphamBUS = new SanPhamBUS();
    public ArrayList<SanPhamDTO> listsp = sanphamBUS.getAll();
    SanPhamDTO sp = new SanPhamDTO();
    Color BackgroundColor = new Color(240, 247, 250);

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

    public void initPadding() {
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
    }

    public void initLeftContent() {
        left = new JPanel();
        left.setPreferredSize(new Dimension(500, 0));
        left.setLayout(new BorderLayout(0, 5));
        left.setOpaque(false);
        contentCenter.add(left, BorderLayout.WEST);

        // Add search bar
        left_top = new PanelBorderRadius();
        left_top.setPreferredSize(new Dimension(0, 90));
        left_top.setLayout(new GridLayout(1, 1));
        left_top.setBorder(new EmptyBorder(5, 5, 5, 5));
        left.add(left_top, BorderLayout.NORTH);

        search = new IntegratedSearch(new String[]{"Tất cả"});
        search.txtSearchForm.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                String txt = search.txtSearchForm.getText();
                listsp = sanphamBUS.search(txt);
                loadDataTableSanPham(listsp);
            }
        });
        left_top.add(search);

        // Add table product
        left_center = new PanelBorderRadius();
        BoxLayout b2 = new BoxLayout(left_center, BoxLayout.Y_AXIS);
        left_center.setLayout(b2);
        left_center.setBorder(new EmptyBorder(20, 20, 20, 20));
        left.add(left_center, BorderLayout.CENTER);
        // Table
        tableSanPham = new JTable();
        scrollTableSanPham = new JScrollPane();
        tblModelSanPham = new DefaultTableModel();
        String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Giá nhập", "Giá bán"};
        tblModelSanPham.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModelSanPham);
        scrollTableSanPham.setViewportView(tableSanPham);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableSanPham.setDefaultRenderer(Object.class, centerRenderer);
        left_center.add(scrollTableSanPham);

        // Add bottom bar
        left_bottom = new PanelBorderRadius();
        left_bottom.setPreferredSize(new Dimension(0, 80));
        left_bottom.setLayout(new FlowLayout(1, 30, 20));
        left.add(left_bottom, BorderLayout.SOUTH);

        lbl1 = new JLabel("Số lượng");
        left_bottom.add(lbl1);
        txtSoLuong = new JSpinner();
        txtSoLuong.setValue(1);
        txtSoLuong.setPreferredSize(new Dimension(70, 30));
        left_bottom.add(txtSoLuong);
        btnAddSoLuong = new ButtonCustom("Thêm vào phiếu", "success", 13, "/icon/Plus_25px.png", 160, 40);
        left_bottom.add(btnAddSoLuong);
    }

    public void initrightContent() {
        main = new JPanel();
        main.setLayout(new BorderLayout(0, 5));
        main.setOpaque(false);
        contentCenter.add(main, BorderLayout.CENTER);

        main_top = new PanelBorderRadius();
        main_top.setPreferredSize(new Dimension(0, 160));
        BoxLayout b3 = new BoxLayout(main_top, BoxLayout.Y_AXIS);
        main_top.setLayout(b3);
        main_top.setBorder(new EmptyBorder(5, 20, 20, 20));
        main.add(main_top, BorderLayout.NORTH);

        InputFormInline maphieu = new InputFormInline("Mã phiếu nhập");
        InputFormInline nhanvien = new InputFormInline("Mã phiếu nhập");
        InputFormInline nhacungcap = new InputFormInline("Nhà cung cấp", getnhacungcap());
        main_top.add(maphieu);
        main_top.add(nhanvien);
        main_top.add(nhacungcap);

        main_center = new PanelBorderRadius();
        BoxLayout b4 = new BoxLayout(main_center, BoxLayout.Y_AXIS);
        main_center.setLayout(b4);
        main_center.setBorder(new EmptyBorder(20, 20, 20, 20));
        main.add(main_center, BorderLayout.CENTER);

        tableNhapKho = new JTable();
        scrTableNhapKho = new JScrollPane();
        tblModelNhapKho = new DefaultTableModel();
        String[] header1 = new String[]{"STT", "Mã SP", "Tên sẩn phẩm", "Số lượng", "Đơn giá"};
        tblModelNhapKho.setColumnIdentifiers(header1);
        tableNhapKho.setModel(tblModelNhapKho);
        scrTableNhapKho.setViewportView(tableNhapKho);
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment(JLabel.CENTER);
        tableNhapKho.setDefaultRenderer(Object.class, centerRenderer);

        main_center.add(scrTableNhapKho);

        main_bottom = new PanelBorderRadius();
        main_bottom.setPreferredSize(new Dimension(0, 140));
        main_bottom.setLayout(new FlowLayout(1, 5, 5));
        main_bottom.setBorder(new EmptyBorder(5, 20, 20, 20));
        main.add(main_bottom, BorderLayout.SOUTH);

        JPanel main_Panel_bottom = new JPanel();
        main_Panel_bottom.setOpaque(false);
        main_Panel_bottom.setPreferredSize(new Dimension(550, 70));
        main_bottom.add(main_Panel_bottom);

        btnNhapExcel = new ButtonCustom("Nhập Excel", "excel", 14, "/icon/xls_25px.png");
        main_Panel_bottom.add(btnNhapExcel);

        btnEditSoLuong = new ButtonCustom("Sửa số lượng", "warning", 14, "/icon/edit_25px.png", 160, 40);
        main_Panel_bottom.add(btnEditSoLuong);

        btnDeleteSanPham = new ButtonCustom("Xóa sản phẩm", "danger", 14, "/icon/delete_25px.png", 160, 40);
        main_Panel_bottom.add(btnDeleteSanPham);

        pnl1 = new JPanel();
        pnl1.setOpaque(false);
        pnl1.setLayout(new FlowLayout(1, 40, 0));
        main_bottom.add(pnl1);

        lbl2 = new JLabel("Tổng tiền");
        lbl2.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 18));
        pnl1.add(lbl2);

        lblTongTien = new JLabel("0 VNĐ");
        lblTongTien.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 20));
        lblTongTien.setForeground(Color.RED);
        pnl1.add(lblTongTien);

        btnNhapKho = new JButton("NHẬP KHO");
        btnNhapKho.setPreferredSize(new Dimension(180, 40));
        btnNhapKho.setFont(new Font(FlatRobotoFont.FAMILY, Font.BOLD, 18));
        btnNhapKho.setBackground(new Color(0, 0, 0));
        btnNhapKho.setForeground(Color.white);
        pnl1.add(btnNhapKho);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        // Padding main 
        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(5, 5));
        this.add(contentCenter, BorderLayout.CENTER);

        initLeftContent();
        initrightContent();

    }

    public void loadDataTableSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSanPham.setRowCount(0);
        for (DTO.SanPhamDTO sanPham : result) {
            tblModelSanPham.addRow(new Object[]{
                sanPham.getMasp(), sanPham.getTensp(), sanPham.getGianhap(), sanPham.getGiaxuat()
            });
        }
    }

}
