package GUI.Dialog;

import BUS.ChiTietSanPhamBUS;
import BUS.DungLuongRamBUS;
import BUS.DungLuongRomBUS;
import BUS.MauSacBUS;
import BUS.PhienBanSanPhamBUS;
import DTO.ChiTietSanPhamDTO;
import DTO.PhienBanSanPhamDTO;
import DTO.SanPhamDTO;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.SelectForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ChiTietSanPhamDialog extends JDialog implements KeyListener, ItemListener {

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_top_left, pnmain_top_right;
    SelectForm cbxPhienBan, cbxTinhTrang;
    InputForm txtSearch,txtSoluong;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ChiTietSanPhamBUS ctspbus = new ChiTietSanPhamBUS();
    ArrayList<ChiTietSanPhamDTO> listctsp = new ArrayList<>();
    ArrayList<PhienBanSanPhamDTO> ch = new ArrayList<>();
    PhienBanSanPhamBUS phienbanBus = new PhienBanSanPhamBUS();
    DungLuongRamBUS ramBus = new DungLuongRamBUS();
    DungLuongRomBUS romBus = new DungLuongRomBUS();
    MauSacBUS mausacBus = new MauSacBUS();

    SanPhamDTO spdto;

    public ChiTietSanPhamDialog(JFrame owner, String title, boolean modal, SanPhamDTO sp) {
        super(owner, title, modal);
        this.spdto = sp;
        initComponent(title);
        loadDataTable(listctsp);
        for (ChiTietSanPhamDTO chiTietSanPhamDTO : listctsp) {
            System.out.println(chiTietSanPhamDTO);
        }
        this.setVisible(true);
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(900, 460));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());

        pnmain = new JPanel(new BorderLayout());

        pnmain_top = new JPanel(new BorderLayout());
        pnmain_top_left = new JPanel(new GridLayout(1, 3));
        String[] arrPb = {"Tất cả"};
        cbxPhienBan = new SelectForm("Phiên bản", arrPb);
        cbxPhienBan.cbb.addItemListener(this);
        cbxPhienBan.setArr(getCauHinhPhienBan(spdto.getMasp()));

        String[] arrTinhTrang = {"Tất cả", "Đã bán", "Tồn kho"};
        cbxTinhTrang = new SelectForm("Tình trạng", arrTinhTrang);
        cbxTinhTrang.cbb.addItemListener(this);
        
        txtSoluong = new InputForm("Số lượng");
        txtSoluong.setEditable(false);
        
        pnmain_top_left.add(cbxPhienBan);
        pnmain_top_left.add(cbxTinhTrang);
        pnmain_top_left.add(txtSoluong);

        pnmain_top_right = new JPanel(new GridLayout(1, 1));
        txtSearch = new InputForm("Nội dung tìm kiếm...");
        txtSearch.getTxtForm().addKeyListener(this);
        pnmain_top_right.add(txtSearch);

        pnmain_top.add(pnmain_top_left, BorderLayout.WEST);
        pnmain_top.add(pnmain_top_right, BorderLayout.CENTER);

        pnmain_bottom = new JPanel(new GridLayout(1, 1));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Imei", "Mã phiếu nhập", "Mã phiếu xuất", "Tình trạng"};
        tblModel.setColumnIdentifiers(header);
        listctsp = ctspbus.FilterPBvaAll(txtSearch.getText(), spdto.getMasp(), ch.get(cbxPhienBan.cbb.getSelectedIndex()).getMaphienbansp());
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        columnModel.getColumn(2).setCellRenderer(centerRenderer);
        columnModel.getColumn(3).setCellRenderer(centerRenderer);
        pnmain_bottom.add(scrollTable);

        pnmain.add(pnmain_top, BorderLayout.NORTH);
        pnmain.add(pnmain_bottom, BorderLayout.CENTER);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<ChiTietSanPhamDTO> result) {
        tblModel.setRowCount(0);
        for (ChiTietSanPhamDTO ctsp : result) {
            tblModel.addRow(new Object[]{
                ctsp.getImei(), ctsp.getMaphieunhap(), ctsp.getMaphieuxuat() == 0 ? "Chưa xuất kho" : ctsp.getMaphieuxuat(), ctsp.getTinhtrang() == 1 ? "Tồn kho" : "Đã bán"
            });
        }
        this.txtSoluong.setText(result.size()+"");
    }

    public String[] getCauHinhPhienBan(int masp) {
        ch = phienbanBus.getAll(masp);
        int size = ch.size();
        String[] arr = new String[size];
        for (int i = 0; i < size; i++) {
            arr[i] = romBus.getKichThuocById(ch.get(i).getRom()) + "GB - "
                    + ramBus.getKichThuocById(ch.get(i).getRam()) + "GB - " + mausacBus.getTenMau(ch.get(i).getMausac());
        }
        return arr;
    }



    public void Filter() throws ParseException{
        this.listctsp = new ArrayList<>();
        String text = txtSearch.getText() != null ? txtSearch.getText() : "";
        int tt = cbxTinhTrang.getSelectedIndex();
        if (tt != 0) {
            listctsp = ctspbus.FilterPBvaTT(text, spdto.getMasp(), ch.get(cbxPhienBan.cbb.getSelectedIndex()).getMaphienbansp(), tt - 1);
        } else {
            listctsp = ctspbus.FilterPBvaAll(text, spdto.getMasp(), ch.get(cbxPhienBan.cbb.getSelectedIndex()).getMaphienbansp());
        }
        loadDataTable(listctsp);
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        try {
            Filter();
        } catch (ParseException ex) {
            Logger.getLogger(ChiTietSanPhamDialog.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Filter();
        } catch (ParseException ex) {
            Logger.getLogger(ChiTietSanPhamDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
