package GUI.Panel;

import BUS.PhieuNhapBUS;
import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import DTO.NhaCungCapDTO;
import DAO.NhaCungCapDAO;
import DTO.ChiTietPhieuDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputFormInline;
import GUI.Component.IntegratedSearch;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import helper.Formater;
import helper.Validation;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import static javax.swing.JOptionPane.QUESTION_MESSAGE;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public final class NhapKho extends JPanel implements ActionListener {

    PanelBorderRadius left_top, left_center, left_bottom, main_top, main_center, main_bottom;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter, left, main, pnl1;
    JTable tableSanPham, tableNhapKho;
    JScrollPane scrollTableSanPham, scrTableNhapKho;
    JLabel lbl1, lbl2, lblTongTien;
    JSpinner txtSoLuong;
    DefaultTableModel tblModelSanPham, tblModelNhapKho;
    JButton btnAddSoLuong, btnNhapExcel, btnEditSoLuong, btnDeleteSanPham, btnNhapKho;
    IntegratedSearch search;
    Color BackgroundColor = new Color(240, 247, 250);
    InputFormInline txtmaphieu, txtnhanvien, txtnhacungcap;
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();

    public SanPhamBUS sanphamBUS = new SanPhamBUS();
    public PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    public ArrayList<SanPhamDTO> listsp = sanphamBUS.getAll();
    public ArrayList<ChiTietPhieuDTO> chitietphieu = new ArrayList<>();
    private int maphieu = phieunhapBUS.phieunhapDAO.getAutoIncrement();
    private double tongtien = 0;

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
        String[] header = new String[]{"Mã SP", "Tên sản phẩm", "Số lượng", "Giá xuất"};
        tblModelSanPham.setColumnIdentifiers(header);
        tableSanPham.setModel(tblModelSanPham);
        scrollTableSanPham.setViewportView(tableSanPham);
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = tableSanPham.getColumnModel();
        for (int i = 0; i < 4; i++) {
            if (i != 1) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tableSanPham.getColumnModel().getColumn(1).setPreferredWidth(400);
        tableSanPham.setFocusable(false);
        tableSanPham.setDefaultEditor(Object.class, null);
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
        btnAddSoLuong.addActionListener(this);
        left_bottom.add(btnAddSoLuong);
    }

    public void initRightContent() {
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

        txtmaphieu = new InputFormInline("Mã phiếu nhập");
        txtnhanvien = new InputFormInline("Nhân viên nhập");
        txtnhacungcap = new InputFormInline("Nhà cung cấp", getnhacungcap());
        main_top.add(txtmaphieu);
        main_top.add(txtnhanvien);
        main_top.add(txtnhacungcap);

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
        TableColumnModel columnModel = tableNhapKho.getColumnModel();
        for (int i = 0; i < 4; i++) {
            if (i != 2) {
                columnModel.getColumn(i).setCellRenderer(centerRenderer);
            }
        }
        tableNhapKho.getColumnModel().getColumn(2).setPreferredWidth(400);
        tableNhapKho.setFocusable(false);
        tableNhapKho.setDefaultEditor(Object.class, null);

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
        btnNhapExcel.addActionListener(this);
        main_Panel_bottom.add(btnNhapExcel);

        btnEditSoLuong = new ButtonCustom("Sửa số lượng", "warning", 14, "/icon/edit_25px.png", 160, 40);
        btnEditSoLuong.addActionListener(this);
        main_Panel_bottom.add(btnEditSoLuong);

        btnDeleteSanPham = new ButtonCustom("Xóa sản phẩm", "danger", 14, "/icon/delete_25px.png", 160, 40);
        btnDeleteSanPham.addActionListener(this);
        main_Panel_bottom.add(btnDeleteSanPham);

        pnl1 = new JPanel();
        pnl1.setOpaque(false);
        
        pnl1.setLayout(new FlowLayout(1, 60, 0));
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
        initRightContent();
        initPhieuNhap();
    }

    public void initPhieuNhap() {
        this.txtmaphieu.setText(Integer.toString(this.maphieu));
        this.txtnhanvien.setText("Nhat Sinh");
        this.txtmaphieu.setEditable(false);
        this.txtnhanvien.setEditable(false);
    }

    public void loadDataTableSanPham(ArrayList<DTO.SanPhamDTO> result) {
        tblModelSanPham.setRowCount(0);
        for (DTO.SanPhamDTO sanPham : result) {
            tblModelSanPham.addRow(new Object[]{
//                sanPham.getMasp(), sanPham.getTensp(), sanPham.getSoluong(), Formater.FormatVND(sanPham.getGiaxuat())
            });
        }
    }

    public void loadDataTableChiTietPhieu(ArrayList<ChiTietPhieuDTO> result) {
        tblModelNhapKho.setRowCount(0);
        for (int i = 0; i < result.size(); i++) {
//            SanPhamDTO sp = sanphamBUS.getByMaSP(result.get(i).getMasanpham());
            tblModelNhapKho.addRow(new Object[]{
//                i + 1, result.get(i).getMasanpham(), sp.getTensp(), result.get(i).getSoluong(), Formater.FormatVND(sp.getGiaxuat())
            });
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object btn = e.getSource();
        if (btn == btnAddSoLuong) {
            ActionBtnAdd();
        } else if(btn == btnDeleteSanPham) {
            ActionBtnDelete();
        } else if(btn == btnEditSoLuong) {
            ActionBtnEdit();
        }
    }

    public void ActionBtnAdd() {
        int index = getRowSelected(tableSanPham);
        if (index != -1) {
            int soluong = (int) txtSoLuong.getValue();
            if (soluong > 0) {
//                ChiTietPhieuDTO ctphieu = phieunhapBUS.findCT(chitietphieu, listsp.get(index).getMasp());
//                if (ctphieu == null) {
////                    ctphieu = new ChiTietPhieuDTO(maphieu, listsp.get(index).getMadanhmuc(), soluong, listsp.get(index).getGiaxuat());
//                    this.chitietphieu.add(ctphieu);
//                } else {
////                    ctphieu.setSoluong(ctphieu.getSoluong() + soluong);
//                }
                loadDataNhapHang(chitietphieu);
            } else {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên và lớn hơn 0");
            }
        }
    }

    public void ActionBtnDelete() {
        int index = getRowSelected(tableNhapKho);
        if (index != -1) {
            int input = JOptionPane.showConfirmDialog(null,
                    "Bạn có chắc chắn muốn xóa sản phẩm khỏi phiếu", "Xóa sản phẩm khỏi phiếu",
                    JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
            if (input == 0) {
                chitietphieu.remove(index);
                loadDataNhapHang(chitietphieu);
            }
        }
    }
    
    public void ActionBtnEdit() {
        int index = getRowSelected(tableNhapKho);
        if (index != -1) {
            String newSL = JOptionPane.showInputDialog(this, "Nhập số lượng cần thay đổi", "Thay đổi số lượng", QUESTION_MESSAGE);
            int soluong = 00;
            if (soluong > 0) {
//                chitietphieu.get(index).setSoluong(soluong);
                loadDataNhapHang(chitietphieu);
            } else {
                JOptionPane.showMessageDialog(this, "Số lượng phải là số nguyên và lớn hơn 0");
            }
        }
    }

    public void loadDataNhapHang(ArrayList<ChiTietPhieuDTO> ctphieu) {
        loadDataTableChiTietPhieu(ctphieu);
//        lblTongTien.setText(Formater.FormatVND(phieunhapBUS.getTongTien(ctphieu)));
    }

    public int getRowSelected(JTable tbl) {
        int index = tbl.getSelectedRow();
        if (tbl.getSelectedRow() < 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
            return -1;
        }
        return index;
    }
}
