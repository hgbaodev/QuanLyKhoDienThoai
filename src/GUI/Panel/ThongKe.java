/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import BUS.KhachHangBUS;
import BUS.NhanVienBUS;
import BUS.SanPhamBUS;
import DTO.KhachHangDTO;
import DTO.NhanVienDTO;
import DTO.SanPhamDTO;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ThongKe extends JPanel {

    JTabbedPane tabbedPane;
    JPanel tongquan, nhacungcap, khachhang, doanhthu, nhapxuat;
    PanelBorderRadius nhapxuat_left, nhapxuat_center;
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
        initComponent();
    }

    private void init() {
        this.setBackground(BackgroundColor);

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
        tabbedPane.addTab("Tổng quan", tongquan);
        tabbedPane.addTab("Tồn kho", nhapxuat);
        tabbedPane.addTab("Doanh thu", doanhthu);
        tabbedPane.addTab("Nhà cung cấp", nhacungcap);
        tabbedPane.addTab("Khách hàng", khachhang);

        this.add(tabbedPane);
    }

    public void initNhapXuat() {
        nhapxuat = new JPanel(new BorderLayout(10, 10));
        nhapxuat.setOpaque(false);
        nhapxuat.setBorder(new EmptyBorder(10, 10, 10, 10));
        nhapxuat_left = new PanelBorderRadius();
        nhapxuat_left.setPreferredSize(new Dimension(300, 100));
        nhapxuat_left.setLayout(new BorderLayout());
        nhapxuat_left.setBorder(new EmptyBorder(0,0,0,5));
        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300,360));
        nhapxuat_left.add(left_content,BorderLayout.NORTH);
        InputForm tensanpham;
        InputDate start_date, end_date;
        ButtonCustom export;

        tensanpham = new InputForm("Tên sản phẩm");
        tensanpham.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");
        JPanel btn_layout = new JPanel(new BorderLayout());
        btn_layout.setPreferredSize(new Dimension(30,36));
        btn_layout.setBorder(new EmptyBorder(20,10,0,10));
        btn_layout.setBackground(Color.white);
        export = new ButtonCustom("Xuất Excel", "excel", 14);
        btn_layout.add(export,BorderLayout.NORTH);

        left_content.add(tensanpham);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        nhapxuat_center = new PanelBorderRadius();

        nhapxuat.add(nhapxuat_left, BorderLayout.WEST);
        nhapxuat.add(nhapxuat_center, BorderLayout.CENTER);

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
