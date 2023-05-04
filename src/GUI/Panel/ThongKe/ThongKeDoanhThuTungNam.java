package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import DTO.ThongKe.ThongKeTonKhoDTO;
import GUI.Component.PanelBorderRadius;
import chart1.Chart;
import chart1.ModelChart;
import helper.Formater;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKeDoanhThuTungNam extends JPanel {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ThongKeBUS thongkeBUS;
    JTextField yearchooser_start, yearchooser_end;
    Chart chart;
    JButton btnreset;

    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private ArrayList<ThongKeDoanhThuDTO> dataset;

    public ThongKeDoanhThuTungNam(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        int year = LocalDate.now().getYear();
        this.dataset = this.thongkeBUS.getDoanhThuTheoTungNam(year - 5, year);
        initComponent();
        loadDataTalbe(dataset);
    }

    public void loadDataTalbe(ArrayList<ThongKeDoanhThuDTO> list) {
        tblModel.setRowCount(0);
        for (ThongKeDoanhThuDTO i : dataset) {
            tblModel.addRow(new Object[]{
                i.getThoigian(), Formater.FormatVND(i.getVon()), Formater.FormatVND(i.getDoanhthu()), Formater.FormatVND(i.getLoinhuan())
            });
        }
    }

    public void loadDataChart(ArrayList<ThongKeDoanhThuDTO> list) {
        for (ThongKeDoanhThuDTO i : dataset) {
            chart.addData(new ModelChart("Năm " + i.getThoigian(), new double[]{i.getVon(), i.getDoanhthu(), i.getLoinhuan()}));
        }
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNamBatDau, lblChonNamKetThuc;
        lblChonNamBatDau = new JLabel("Từ năm");
        lblChonNamKetThuc = new JLabel("Đến năm");

        yearchooser_start = new JTextField();
        yearchooser_end = new JTextField();

        btnreset = new JButton("Làm mới");

        pnl_top.add(lblChonNamBatDau);
        pnl_top.add(yearchooser_start);
        pnl_top.add(lblChonNamKetThuc);
        pnl_top.add(yearchooser_end);
        pnl_top.add(btnreset);

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);

        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));

        loadDataChart(dataset);
        pnlChart.add(chart);

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Năm", "Vốn", "Doanh thu", "Lợi nhuận"};
        tblModel.setColumnIdentifiers(header);
        tableThongKe.setModel(tblModel);
        tableThongKe.setAutoCreateRowSorter(true);
        tableThongKe.setDefaultEditor(Object.class, null);
        scrollTableThongKe.setViewportView(tableThongKe);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableThongKe.setDefaultRenderer(Object.class, centerRenderer);
        tableThongKe.setFocusable(false);
        scrollTableThongKe.setPreferredSize(new Dimension(0, 300));
        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
    }
}
