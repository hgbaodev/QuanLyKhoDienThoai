/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DAO.KhachHangDAO;
import DAO.NhanVienDAO;
import DTO.KhachHangDTO;
import DTO.KhachHangDTO;
import GUI.Component.ButtonCustom;
import GUI.Panel.KhachHang;
import GUI.Panel.TaiKhoan;
import GUI.Panel.TaoPhieuXuat;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author robot
 */
public class ListKhachHang extends JDialog implements MouseListener {

    private TaoPhieuXuat taoPhieuXuat;
    private JTable tableKhachHang;
    private JScrollPane scrollTableSanPham;
    private DefaultTableModel tblModel;
    private ArrayList<KhachHangDTO> listKh = KhachHangDAO.getInstance().selectAll();
    DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
    
    public ListKhachHang(TaoPhieuXuat taoPhieuXuat, JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        this.taoPhieuXuat=taoPhieuXuat;
        init();
        loadDataTalbe(search(""));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void init(){
        this.setSize(new Dimension(850,600));
        this.setLayout(new BorderLayout());
        JPanel panelSearch = new JPanel(new BorderLayout());
        panelSearch.setSize(new Dimension(0,150));
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        JLabel jLabelSearch = new JLabel("Tìm kiếm  ");
        jLabelSearch.setSize(new Dimension(100,0));
        JTextField jTextFieldSearch = new JTextField();
        jTextFieldSearch.setFont(new Font(FlatRobotoFont.FAMILY, 0, 13));
        jTextFieldSearch.putClientProperty("JTextField.placeholderText", "Tìm kiếm nhân viên....");
        jTextFieldSearch.putClientProperty("JTextField.showClearButton", true);
        jTextFieldSearch.addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                String txt = jTextFieldSearch.getText();
                listKh = search(txt);
                loadDataTalbe(listKh);
            }
        });
        ButtonCustom buttonAdd = new ButtonCustom("Chọn khách hàng", "success", 14);
        buttonAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                if(getRow()<0){
                    int input = JOptionPane.showConfirmDialog(null, 
                "Vui lòng chọn khách hàng!:)", "Thông báo", JOptionPane.DEFAULT_OPTION);
                } else{
                    taoPhieuXuat.setKhachHang(listKh.get(getRow()).getMaKH());
                    dispose();
                }
            }
            
        });
        panelSearch.add(jLabelSearch,BorderLayout.WEST);
        panelSearch.add(jTextFieldSearch,BorderLayout.CENTER);
        panelSearch.add(buttonAdd,BorderLayout.EAST);
        this.add(panelSearch,BorderLayout.NORTH);
        JPanel jPanelTable = new JPanel();
        panelSearch.setBorder(new EmptyBorder(20, 20, 20, 20));
        jPanelTable.setLayout(new GridLayout(1,1));
        tableKhachHang = new JTable();
        tableKhachHang.setFocusable(false);
        scrollTableSanPham = new JScrollPane();
        tableKhachHang.setFont(new java.awt.Font("Segoe UI", 0, 14));
        tableKhachHang = new JTable();
        tableKhachHang.setFocusable(false);
        tableKhachHang.setDefaultEditor(Object.class, null);
        tableKhachHang.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{},
                new String[]{}
        ));
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã KH","Họ tên","Địa chỉ","Số điện thoại","Ngày tham gia"};
        tblModel.setColumnIdentifiers(header);
        tableKhachHang.setDefaultRenderer(Object.class, centerRenderer);
        tableKhachHang.setModel(tblModel);
        scrollTableSanPham.setViewportView(tableKhachHang);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tableKhachHang.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tableKhachHang.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        jPanelTable.add(scrollTableSanPham);
        this.add(jPanelTable,BorderLayout.CENTER);
        
    }
    
    public int getRow(){
        return tableKhachHang.getSelectedRow();
    }
    
    public void loadDataTalbe(ArrayList<KhachHangDTO> list) {
        listKh = list;
        tblModel.setRowCount(0);
        for (KhachHangDTO kh : listKh) {
            tblModel.addRow(new Object[]{
                kh.getMaKH(),kh.getHoten(),kh.getDiachi(),kh.getSdt(),kh.getNgaythamgia()
            });
        }
    }
    
    public ArrayList<KhachHangDTO> search(String text) {
        if(text.length()>0){
            text = text.toLowerCase();
        ArrayList<KhachHangDTO> result = new ArrayList<>();
        for(KhachHangDTO i : listKh) {
           if(i.getHoten().toLowerCase().contains(text) || i.getDiachi().toLowerCase().contains(text)
                   || i.getSdt().toLowerCase().contains(text)){
               result.add(i);
           }
        }
        return result;
        } else {
            return KhachHangDAO.getInstance().selectAll();
        }
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
