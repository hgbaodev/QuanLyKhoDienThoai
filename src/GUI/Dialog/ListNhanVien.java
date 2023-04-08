/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import GUI.Panel.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author robot
 */
public class ListNhanVien extends JDialog implements MouseListener {

    private JTable tableNhanVien;
    private JScrollPane scrollTableSanPham;
    private DefaultTableModel tblModel;
    
    public ListNhanVien(TaiKhoan taiKhoan, JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        init();
    }

    public void init(){
        this.setSize(new Dimension(750,600));
        this.setLayout(new BorderLayout());
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.setSize(new Dimension(0,150));
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel jLabelSearch = new JLabel("Tìm kiếm  ");
        jLabelSearch.setSize(new Dimension(100,0));
        JTextField jTextFieldSearch = new JTextField("");
        panelSearch.add(jLabelSearch,BorderLayout.WEST);
        panelSearch.add(jTextFieldSearch,BorderLayout.CENTER);
        this.add(panelSearch,BorderLayout.NORTH);
        JPanel jPanelTable = new JPanel();
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        jPanelTable.setLayout(new GridLayout(1,1));
        tableNhanVien = new JTable();
        scrollTableSanPham = new JScrollPane();
        tableNhanVien.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableNhanVien = new JTable();
        tableNhanVien.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"MNV","Họ tên","Giới tính","Ngày Sinh","SDT","Email"};
        tblModel.setColumnIdentifiers(header);
        tableNhanVien.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableNhanVien);
        jPanelTable.add(scrollTableSanPham);
        this.add(jPanelTable,BorderLayout.CENTER);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
