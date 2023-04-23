package GUI.Dialog;

import BUS.ChiTietSanPhamBUS;
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
import java.util.ArrayList;
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
public class ChiTietSanPhamDialog extends JDialog {
    
    HeaderTitle titlePage;
    JPanel pnmain, pnmain_top, pnmain_bottom, pnmain_top_left, pnmain_top_right;
    SelectForm cbxPhienBan, cbxTinhTrang;
    InputForm txtSearch;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ChiTietSanPhamBUS ctspbus=new ChiTietSanPhamBUS();
    ArrayList<ChiTietSanPhamDTO> ctspdto;
    ArrayList<ChiTietSanPhamDTO> listctsp;
    SanPhamDTO spdto;

    public ChiTietSanPhamDialog(JFrame owner, String title, boolean modal,SanPhamDTO sp) {
        super(owner, title, modal);
        this.spdto=sp;
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
        pnmain_top_left = new JPanel(new GridLayout(1, 2));
        String[] arrPb = {"Tất cả", "128GB - 8GB - Đỏ", "128GB - 8GB - Hồng"};
        cbxPhienBan = new SelectForm("Phiên bản", arrPb);
        String[] arrTinhTrang = {"Tất cả", "Tồn kho", "Đã bán"};
        cbxTinhTrang = new SelectForm("Tình trạng", arrTinhTrang);
        pnmain_top_left.add(cbxPhienBan);
        pnmain_top_left.add(cbxTinhTrang);
        
        pnmain_top_right = new JPanel(new GridLayout(1,1));
        txtSearch = new InputForm("Nội dung tìm kiếm...");
        pnmain_top_right.add(txtSearch);

        pnmain_top.add(pnmain_top_left, BorderLayout.WEST);
        pnmain_top.add(pnmain_top_right, BorderLayout.CENTER);

        pnmain_bottom = new JPanel(new GridLayout(1,1));
        pnmain_bottom.setBorder(new EmptyBorder(5,5,5,5));
        pnmain_bottom.setBackground(Color.WHITE);
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Imei", "Mã phiếu nhập", "Mã phiếu xuất", "Tình trạng"};
        tblModel.setColumnIdentifiers(header);
        listctsp=ctspbus.getCTSPbyMasp(spdto.getMasp());
        
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
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
                ctsp.getImei(),ctsp.getMaphieunhap(),ctsp.getMaphieuxuat(),ctsp.getTinhtrang()
            });
        }
    }
}
