package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeDoanhThuDTO;
import GUI.Component.NumericDocumentFilter;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Component.Chart.BarChart.Chart;
import GUI.Component.Chart.BarChart.ModelChart;
import helper.Formater;
import helper.JTableExporter;
import helper.Validation;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.PlainDocument;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKeDoanhThuTungNam extends JPanel implements ActionListener {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ThongKeBUS thongkeBUS;
    JTextField yearchooser_start, yearchooser_end;
    Chart chart;
    JButton btnreset, btnthongke, btnexport;

    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;
    private ArrayList<ThongKeDoanhThuDTO> dataset;
    private int current_year;

    public ThongKeDoanhThuTungNam(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        this.current_year = LocalDate.now().getYear();
        this.dataset = this.thongkeBUS.getDoanhThuTheoTungNam(current_year - 5, current_year);
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
        pnlChart.removeAll();
        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        for (ThongKeDoanhThuDTO i : dataset) {
            chart.addData(new ModelChart("Năm " + i.getThoigian(), new double[]{i.getVon(), i.getDoanhthu(), i.getLoinhuan()}));
        }
        chart.repaint();
        chart.validate();
        pnlChart.add(chart);
        pnlChart.repaint();
        pnlChart.validate();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNamBatDau, lblChonNamKetThuc;
        lblChonNamBatDau = new JLabel("Từ năm");
        lblChonNamKetThuc = new JLabel("Đến năm");

        yearchooser_start = new JTextField("");
        yearchooser_end = new JTextField("");

        PlainDocument doc_start = (PlainDocument) yearchooser_start.getDocument();
        doc_start.setDocumentFilter(new NumericDocumentFilter());
        PlainDocument doc_end = (PlainDocument) yearchooser_end.getDocument();
        doc_end.setDocumentFilter(new NumericDocumentFilter());

        btnthongke = new JButton("Thống kê");
        btnreset = new JButton("Làm mới");
        btnexport = new JButton("Xuất excel");
        btnthongke.addActionListener(this);
        btnreset.addActionListener(this);
        btnexport.addActionListener(this);

        pnl_top.add(lblChonNamBatDau);
        pnl_top.add(yearchooser_start);
        pnl_top.add(lblChonNamKetThuc);
        pnl_top.add(yearchooser_end);
        pnl_top.add(btnthongke);
        pnl_top.add(btnreset);
        pnl_top.add(btnexport);

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);

        loadDataChart(dataset);

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

        TableSorter.configureTableColumnSorter(tableThongKe, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 1, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 2, TableSorter.VND_CURRENCY_COMPARATOR);
        TableSorter.configureTableColumnSorter(tableThongKe, 3, TableSorter.VND_CURRENCY_COMPARATOR);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
        this.add(scrollTableThongKe, BorderLayout.SOUTH);

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == btnthongke) {
            System.out.println(yearchooser_start.getText());
            if (!Validation.isEmpty(yearchooser_start.getText()) || !Validation.isEmpty(yearchooser_end.getText())) {
                int nambd = Integer.parseInt(yearchooser_start.getText());
                int namkt = Integer.parseInt(yearchooser_end.getText());
                if (nambd > current_year || namkt > current_year) {
                    JOptionPane.showMessageDialog(this, "Năm không được lớn hơn năm hiện tại");
                } else {
                    if (namkt < nambd || namkt <= 2015 || nambd <= 2015) {
                        JOptionPane.showMessageDialog(this, "Năm kết thúc không được bé hơn năm bắt đầu và phải lớn hơn 2015");
                    } else {
                        this.dataset = this.thongkeBUS.getDoanhThuTheoTungNam(nambd, namkt);
                        loadDataChart(dataset);
                        loadDataTalbe(dataset);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ !");
            }

        } else if (source == btnreset) {
            yearchooser_start.setText("");
            yearchooser_end.setText("");
            this.dataset = this.thongkeBUS.getDoanhThuTheoTungNam(current_year - 5, current_year);
            loadDataChart(dataset);
            loadDataTalbe(dataset);
        } else if (source == btnexport) {
            try {
                JTableExporter.exportJTableToExcel(tableThongKe);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeDoanhThuTungNam.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
