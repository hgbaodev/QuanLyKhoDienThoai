package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DAO.SanPhamDAO;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeTungNgayTrongThangDTO;
import GUI.Component.TableSorter;
import GUI.Component.itemTaskbar;
import GUI.Component.Chart.CurveChart.CurveChart;
import GUI.Component.Chart.CurveChart.ModelChart2;
import helper.Formater;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKeTongQuan extends JPanel {

    ThongKeBUS thongkebus;
    JPanel jp_top, jp_center, pnlChart;
    itemTaskbar[] listitem;
    CurveChart chart;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    ArrayList<ThongKeTungNgayTrongThangDTO> dataset;
    String[][] getSt = {
        {"Sản phẩm hiện có trong kho", "productt.svg", Integer.toString(SanPhamDAO.getInstance().selectAll().size())},
        {"Khách từ trước đến nay", "stafff.svg", Integer.toString(KhachHangDAO.getInstance().selectAll().size())},
        {"Nhân viên đang hoạt động", "customerr.svg", Integer.toString(NhanVienDAO.getInstance().selectAll().size())}};

    public ThongKeTongQuan(ThongKeBUS thongkebus) {
        this.thongkebus = thongkebus;
        this.dataset = thongkebus.getThongKe7NgayGanNhat();
        initComponent();
        loadDataTalbe(this.dataset);
    }

    public void loadDataChart() {
        for (ThongKeTungNgayTrongThangDTO i : dataset) {
            chart.addData(new ModelChart2(i.getNgay() + "", new double[]{i.getChiphi(), i.getDoanhthu(), i.getLoinhuan()}));
        }
    }

    public void loadDataTalbe(ArrayList<ThongKeTungNgayTrongThangDTO> list) {
        tblModel.setRowCount(0);
        for (ThongKeTungNgayTrongThangDTO i : dataset) {
            tblModel.addRow(new Object[]{
                i.getNgay(), Formater.FormatVND(i.getChiphi()), Formater.FormatVND(i.getDoanhthu()), Formater.FormatVND(i.getLoinhuan())
            });
        }
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        jp_top = new JPanel();
        jp_top.setLayout(new GridLayout(1, 3, 20, 0));
        jp_top.setOpaque(false);
        jp_top.setBorder(new EmptyBorder(10, 10, 10, 10));
        jp_top.setPreferredSize(new Dimension(0, 120));

        listitem = new itemTaskbar[getSt.length];
        for (int i = 0; i < getSt.length; i++) {
            listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][2], getSt[i][0], 0);
            jp_top.add(listitem[i]);
        }

        jp_center = new JPanel(new BorderLayout());
        jp_center.setBackground(Color.WHITE);
        JPanel jp_center_top = new JPanel(new FlowLayout());
        jp_center_top.setBorder(new EmptyBorder(10, 0, 0, 10));
        jp_center_top.setOpaque(false);
        JLabel txtChartName = new JLabel("Thống kê doanh thu 8 ngày gần nhất");
        txtChartName.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        jp_center_top.add(txtChartName);

        pnlChart = new JPanel();
        pnlChart.setOpaque(false);
        pnlChart.setLayout(new BorderLayout(0, 0));
        chart = new CurveChart();
        chart.addLegend("Vốn", new Color(12, 84, 175), new Color(0, 108, 247));
        chart.addLegend("Doanh thu", new Color(54, 4, 143), new Color(104, 49, 200));
        chart.addLegend("Lợi nhuận", new Color(211, 84, 0), new Color(230, 126, 34));

        loadDataChart();

        chart.start();
        pnlChart.add(chart, BorderLayout.CENTER);

        jp_center.add(jp_center_top, BorderLayout.NORTH);
        jp_center.add(pnlChart, BorderLayout.CENTER);

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Ngày", "Vốn", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 250));

        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.DATE_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        this.add(jp_top, BorderLayout.NORTH);
        this.add(jp_center, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);
    }
}
