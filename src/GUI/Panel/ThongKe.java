package GUI.Panel;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.Component.InputDate;
import GUI.Component.itemTaskbar;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.table.DefaultTableModel;
import chart1.ModelChart;
import chart1.Chart;
import chart2.CurveChart;
import chart2.ModelChart2;
import chart3.ChartLine;
import chart3.ChartPie;
import chart3.ModelChartLine;
import chart3.ModelChartPie;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

public class ThongKe extends JPanel {

    JPanel top, contentCenter, bottom, pnlChart, pnlChart2;
    JTabbedPane tabbedPane;
    Chart chart;
    CurveChart chart2;
    ChartLine chartLine;
    ChartPie chartPie;
    Color BackgroundColor = new Color(240, 247, 250);
    itemTaskbar[] listitem;
    InputDate dateStart, dateEnd, dateStart2, dateEnd2;
    
    SanPhamBUS spBus = new SanPhamBUS();
    KhachHangBUS khBus = new KhachHangBUS();
    NhanVienBUS nvBus = new NhanVienBUS();

    ArrayList<KhachHangDTO> listKH = khBus.getAll();
    ArrayList<NhanVienDTO> listNV = nvBus.getAll();
   
//    String[][] getSt = {
//        {"Sản phẩm hiện có trong kho", "productt.svg", Integer.toString(spBus.getQuantity()) },
//        {"Khách từ trước đến nay", "stafff.svg", Integer.toString(listKH.size())},
//        {"Nhân viên đang hoạt động", "customerr.svg", Integer.toString(listNV.size())},
//    };
    
        String[][] getSt = {
        {"Sản phẩm hiện có trong kho", "productt.svg", "1280" },
        {"Khách từ trước đến nay", "stafff.svg", "3214"},
        {"Nhân viên đang hoạt động", "customerr.svg", Integer.toString(listNV.size())},
    };

    public ThongKe() {
        init();
    }

    private void init() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(false);

        top = new JPanel();
        top.setBackground(BackgroundColor);
        top.setLayout(new GridLayout(1, 3, 20, 0));
        top.setBorder(new EmptyBorder(20, 20, 20, 20));
        top.setPreferredSize(new Dimension(0, 150));
        this.add(top, BorderLayout.NORTH);

        
        
        listitem = new itemTaskbar[getSt.length];
        for (int i = 0; i < getSt.length; i++) {
                listitem[i] = new itemTaskbar(getSt[i][1], getSt[i][2], getSt[i][0], 0);
                
            top.add(listitem[i]);
        }

        contentCenter = new JPanel();
        contentCenter.setLayout(new GridLayout(1, 1, 10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        displayChart();
        displayChart2();

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Doanh thu", pnlChart);
        tabbedPane.addTab("Sản phẩm", pnlChart2);
        contentCenter.add(tabbedPane);

        bottom = new JPanel();
        bottom.setBackground(Color.WHITE);
        bottom.setLayout(new BorderLayout(0, 0));
//        bottom.setLayout(new GridLayout(1, 2, 20, 0));
        bottom.setBorder(new EmptyBorder(20, 20, 20, 20));
//        bottom.setLayout(new FlowLayout(0, 10, 0));
        bottom.setPreferredSize(new Dimension(0, 250));
        this.add(bottom, BorderLayout.SOUTH);

        displayChart3();

    }

    public void displayChart() {
        pnlChart = new JPanel();
        pnlChart.setOpaque(false);
        pnlChart.setLayout(new BorderLayout(0, 0));

        JPanel right = new JPanel();
        right.setBackground(Color.white);
        right.setPreferredSize(new Dimension(200, 0));
        right.setLayout(new GridLayout(2, 1, 10, 10));
        right.setBorder(new EmptyBorder(0, 5, 180, 0));
        pnlChart.add(right, BorderLayout.EAST);

        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");

        right.add(dateStart);
        right.add(dateEnd);

        chart = new Chart();
        chart.addLegend("Vốn", new Color(245, 189, 135));
        chart.addLegend("Doanh thu", new Color(135, 189, 245));
        chart.addLegend("Lợi nhuận", new Color(189, 135, 245));
        chart.addLegend("Lỗ vốn", new Color(139, 229, 222));
        chart.addData(new ModelChart("Tháng một", new double[]{100, 150, 200, 500}));
        chart.addData(new ModelChart("Tháng hai", new double[]{600, 750, 300, 150}));
        chart.addData(new ModelChart("Tháng ba", new double[]{200, 350, 1000, 900}));
        chart.addData(new ModelChart("Tháng tư", new double[]{480, 150, 750, 700}));
        chart.addData(new ModelChart("Tháng năm", new double[]{480, 150, 750, 700}));


        pnlChart.add(chart);
    }

    public void displayChart2() {
        pnlChart2 = new JPanel();
        pnlChart2.setOpaque(false);
        pnlChart2.setLayout(new BorderLayout(0, 0));

        JPanel right = new JPanel();
        right.setBackground(Color.white);
        right.setPreferredSize(new Dimension(200, 0));
        right.setLayout(new GridLayout(2, 1, 10, 10));
        right.setBorder(new EmptyBorder(0, 5, 180, 0));
        pnlChart2.add(right, BorderLayout.EAST);

        dateStart2 = new InputDate("Từ ngày");
        dateEnd2 = new InputDate("Đến ngày");

        right.add(dateStart2);
        right.add(dateEnd2);

        chart2 = new CurveChart();
        chart2.addLegend("Sản phẩm nhập", new Color(12, 84, 175), new Color(0, 108, 247));
        chart2.addLegend("Sản phẩm xuất", new Color(54, 4, 143), new Color(104, 49, 200));
//        chart2.addLegend("Profit", new Color(5, 125, 0), new Color(95, 209, 69));
//        chart2.addLegend("Cost", new Color(186, 37, 37), new Color(241, 100, 120));
        chart2.addData(new ModelChart2("Tháng một", new double[]{500, 200}));
        chart2.addData(new ModelChart2("Tháng hai", new double[]{1000, 750}));
        chart2.addData(new ModelChart2("Tháng ba", new double[]{200, 350}));
        chart2.addData(new ModelChart2("Tháng tư", new double[]{480, 150}));
        chart2.addData(new ModelChart2("Tháng năm", new double[]{480, 150}));

        chart2.start();
        pnlChart2.add(chart2, BorderLayout.CENTER);
    }

    public void displayChart3() {
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(1, 0, 0));
        top.setOpaque(false);
        top.setPreferredSize(new Dimension(20, 20));
        bottom.add(top, BorderLayout.NORTH);

        JLabel title = new JLabel("Số khách hàng trong tuần gần đây nhất");
        title.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
//        title.setPreferredSize(new Dimension(20,19));
        top.add(title);

        JPanel center = new JPanel();
        center.setLayout(new GridLayout(1, 2, 20, 0));
        center.setOpaque(false);
        bottom.add(center, BorderLayout.CENTER);

        chartPie = new ChartPie();
        List<ModelChartPie> list1 = new ArrayList<>();
        list1.add(new ModelChartPie("Thứ hai", 10, new Color(4, 174, 243)));
        list1.add(new ModelChartPie("Thứ ba", 15, new Color(215, 39, 250)));
        list1.add(new ModelChartPie("Thứ tư", 8, new Color(44, 88, 236)));
        list1.add(new ModelChartPie("Thứ năm", 20, new Color(21, 202, 87)));
        list1.add(new ModelChartPie("Thứ sáu", 7, new Color(127, 63, 255)));
        list1.add(new ModelChartPie("Thứ bảy", 30, new Color(238, 167, 35)));
        list1.add(new ModelChartPie("Chúa Nhật", 14, new Color(245, 79, 99)));
        chartPie.setModel(list1);
        //  Test data chart line

        chartLine = new ChartLine();
        List<ModelChartLine> list = new ArrayList<>();
        list.add(new ModelChartLine("Thứ hai", 10));
        list.add(new ModelChartLine("Thứ ba", 15));
        list.add(new ModelChartLine("Thứ tư", 8));
        list.add(new ModelChartLine("Thứ năm", 20));
        list.add(new ModelChartLine("Thứ sáu", 7));
        list.add(new ModelChartLine("Thứ bảy", 30));
        list.add(new ModelChartLine("Chúa Nhật", 14));
        chartLine.setModel(list);

        center.add(chartPie);
        center.add(chartLine);
    }

}
