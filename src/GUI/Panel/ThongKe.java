/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel;

import GUI.Component.ButtonCustom;
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

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        initNhapXuat();

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

    }
}
