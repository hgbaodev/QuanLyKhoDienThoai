package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTonKhoDTO;
import GUI.Component.PanelBorderRadius;
import chart1.Chart;
import chart1.ModelChart;
import com.toedter.calendar.JYearChooser;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class ThongKeDoanhThuTungThang extends JPanel {

    PanelBorderRadius pnlChart;
    JPanel pnl_top;
    HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> listSp;
    ThongKeBUS thongkeBUS;
    JYearChooser yearchooser;
    Chart chart;

    public ThongKeDoanhThuTungThang(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        listSp = thongkeBUS.getTonKho();
        initComponent();

    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        pnl_top = new JPanel(new FlowLayout());
        JLabel lblChonNam = new JLabel("Chọn năm thống kê");
        yearchooser = new JYearChooser();
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

        this.add(pnl_top, BorderLayout.NORTH);
        this.add(pnlChart, BorderLayout.CENTER);
    }
}
