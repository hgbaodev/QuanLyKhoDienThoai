package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTonKhoDTO;
import GUI.Component.PanelBorderRadius;
import chart1.Chart;
import chart1.ModelChart;
import com.toedter.calendar.JYearChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
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
public final class ThongKeDoanhThuTungThang extends JPanel {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    ThongKeBUS thongkeBUS;
    JYearChooser yearchooser;
    Chart chart;
    private JTable tableThongKe;
    private JScrollPane scrollTableThongKe;
    private DefaultTableModel tblModel;

    public ThongKeDoanhThuTungThang(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNam = new JLabel("Chọn năm thống kê");
        yearchooser = new JYearChooser();
        yearchooser.addPropertyChangeListener("year", new PropertyChangeListener() {
            public void propertyChange(PropertyChangeEvent e) {
                int year = (Integer) e.getNewValue();
                
            }
        });
        pnl_top.add(lblChonNam);
        pnl_top.add(yearchooser);

        pnlChart = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(pnlChart, BoxLayout.Y_AXIS);
        pnlChart.setLayout(boxly);

        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        chart.addData(new ModelChart("Tháng 1", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 2", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 3", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 4", new double[]{480, 150, 750}));
        chart.addData(new ModelChart("Tháng 5", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 6", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 7", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 8", new double[]{480, 150, 750}));
        chart.addData(new ModelChart("Tháng 9", new double[]{100, 150, 200}));
        chart.addData(new ModelChart("Tháng 10", new double[]{600, 750, 300}));
        chart.addData(new ModelChart("Tháng 11", new double[]{200, 350, 1000}));
        chart.addData(new ModelChart("Tháng 12", new double[]{480, 150, 750}));
        pnlChart.add(chart);

        tableThongKe = new JTable();
        scrollTableThongKe = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu nhập", "Nhà cung cấp"};
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
    }
}
