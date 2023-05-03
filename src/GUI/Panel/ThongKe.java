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
    tabThongKe tongquan, nhacungcap, khachhang, doanhthu, nhapxuat;
    PanelBorderRadius nhapxuat_left, nhapxuat_center;
    Color BackgroundColor = new Color(240, 247, 250);

    public ThongKe() {
        initComponent();
    }

    public void initComponent() {
        this.setLayout(new GridLayout(1, 1));
        this.setBackground(BackgroundColor);

        nhapxuat = new tabThongKe("Tồn kho", "khachhang");
        doanhthu = new tabThongKe("Doanh thu", "khachhang");
        nhacungcap = new tabThongKe("Nhà cung cấp", "khachhang");
        khachhang = new tabThongKe("Khách hàng", "khachhang");

        tabbedPane = new JTabbedPane();
        tabbedPane.setOpaque(false);
        tabbedPane.addTab("Tổng quan", tongquan);
        tabbedPane.addTab("Tồn kho", nhapxuat);
        tabbedPane.addTab("Doanh thu", doanhthu);
        tabbedPane.addTab("Nhà cung cấp", nhacungcap);
        tabbedPane.addTab("Khách hàng", khachhang);

        this.add(tabbedPane);
    }
}