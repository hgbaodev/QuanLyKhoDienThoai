package GUI.Panel.ThongKe;

import GUI.Component.ButtonCustom;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class ThongKeNhaCungCap extends JPanel {

    PanelBorderRadius left, center;
    JTable tblNhaCungCap;
    JScrollPane scrollTblNhaCungCap;
    DefaultTableModel tblModel;
    InputForm tennhacungcap;
    InputDate start_date, end_date;
    ButtonCustom export;

    public ThongKeNhaCungCap() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        left = new PanelBorderRadius();
        left.setPreferredSize(new Dimension(300, 100));
        left.setLayout(new BorderLayout());
        left.setBorder(new EmptyBorder(0, 0, 0, 5));
        
        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        left.add(left_content, BorderLayout.NORTH);

        tennhacungcap = new InputForm("Tìm kiếm nhà cung cấp");
        tennhacungcap.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");
        
        JPanel btn_layout = new JPanel(new BorderLayout());
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setBackground(Color.white);
        export = new ButtonCustom("Xuất Excel", "excel", 14);
        btn_layout.add(export, BorderLayout.NORTH);

        left_content.add(tennhacungcap);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        center = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(center, BoxLayout.Y_AXIS);
        center.setLayout(boxly);

        tblNhaCungCap = new JTable();
        scrollTblNhaCungCap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã NCC", "Tên nhà cung cấp", "Kỳ", "Số lượng nhập", "Tổng tiền"};
        tblModel.setColumnIdentifiers(header);
        tblNhaCungCap.setModel(tblModel);
        tblNhaCungCap.setAutoCreateRowSorter(true);
        tblNhaCungCap.setDefaultEditor(Object.class, null);
        scrollTblNhaCungCap.setViewportView(tblNhaCungCap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblNhaCungCap.setDefaultRenderer(Object.class, centerRenderer);
        tblNhaCungCap.setFocusable(false);
        tblNhaCungCap.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblNhaCungCap.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblNhaCungCap.getColumnModel().getColumn(2).setPreferredWidth(200);
        center.add(scrollTblNhaCungCap);

        this.add(left, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
    }
}
