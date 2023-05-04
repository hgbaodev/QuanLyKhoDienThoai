package GUI.Dialog;

import DTO.ThongKe.ThongKeTonKhoDTO;
import GUI.Component.HeaderTitle;
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
public final class ThongKePBSPTonKho extends JDialog{

    HeaderTitle titlePage;
    JPanel pnmain, pnmain_bottom;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ArrayList<ThongKeTonKhoDTO> list_pb;

    public ThongKePBSPTonKho(JFrame owner, String title, boolean modal, ArrayList<ThongKeTonKhoDTO> list_pb) {
        super(owner, title, modal);
        this.list_pb = list_pb;
        initComponent(title);
        loadDataTable(list_pb);
        this.setVisible(true);
    }

    public void initComponent(String title) {
        this.setSize(new Dimension(900, 460));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase() + ": " + list_pb.get(0).getTensanpham());

        pnmain = new JPanel(new BorderLayout());

        pnmain_bottom = new JPanel(new GridLayout(1, 1));
        pnmain_bottom.setBorder(new EmptyBorder(5, 5, 5, 5));
        pnmain_bottom.setBackground(Color.WHITE);
        table = new JTable();
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã phiên bản", "RAM", "ROM", "Màu sắc", "Tồn đầu kỳ", "Nhập trong kỳ", "Xuất trong kỳ", "Tồn cuối kỳ"};
        tblModel.setColumnIdentifiers(header);

        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        table.setDefaultEditor(Object.class, null);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        table.setDefaultRenderer(Object.class, centerRenderer);
        TableColumnModel columnModel = table.getColumnModel();
        
        pnmain_bottom.add(scrollTable);

        pnmain.add(pnmain_bottom, BorderLayout.CENTER);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<ThongKeTonKhoDTO> result) {
        tblModel.setRowCount(0);
        for (ThongKeTonKhoDTO ctsp : result) {
            tblModel.addRow(new Object[]{
                ctsp.getMaphienbansp(), ctsp.getRam(), ctsp.getRom(), ctsp.getMausac(), ctsp.getTondauky(),
                ctsp.getNhaptrongky(),ctsp.getXuattrongky(),ctsp.getToncuoiky()
            });
        }
    }
}
