/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;

import BUS.DungLuongRomBUS;
import DAO.DungLuongRomDAO;
import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.QuanLyThuocTinhSP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author 84907
 */
public class DungLuongRomDialog extends JDialog implements MouseListener {

    HeaderTitle headTite;
    JPanel top, main, bottom, all;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    ButtonCustom add, del, update;
    DungLuongRomBUS dlrBUS = new DungLuongRomBUS();
    ArrayList<DungLuongRomDTO> list = dlrBUS.getAll();
    QuanLyThuocTinhSP qltt;

    public DungLuongRomDialog(JFrame owner,QuanLyThuocTinhSP qltt,String title,boolean modal) {
        super(owner, title, modal);
        initComponent(qltt);
        loadDataTable(list);
    }

    public void initComponent(QuanLyThuocTinhSP qltt) {
        this.qltt=qltt;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        headTite = new HeaderTitle("Quản lý ROM");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();

        //top.setLayout(new FlowLayout(0));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 70));
        top.add(headTite);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        ms = new InputForm("Dung lượng ROM");
        ms.setPreferredSize(new Dimension(250, 70));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã Rom", "Dung lượng"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        main.add(ms);
        main.add(scrollTable);

        add = new ButtonCustom("Thêm", "excel",15,100,40);
        add.addMouseListener(this);
        del = new ButtonCustom("Xóa", "danger",15,100,40);
        del.addMouseListener(this);
        update = new ButtonCustom("Sửa", "success",15,100,40);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1,20,20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<DungLuongRomDTO> result) {
        tblModel.setRowCount(0);
        for (DungLuongRomDTO dlr : result) {
            tblModel.addRow(new Object[]{
                dlr.getMadungluongrom(), dlr.getDungluongrom()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (this.ms.getText() == "") {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên màu mới");
            } else {
                int id = DungLuongRomDAO.getInstance().getAutoIncrement();
                String kichthuoc = ms.getText();
                dlrBUS.add(new DungLuongRomDTO(id, Integer.parseInt(kichthuoc)));
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                dlrBUS.delete(list.get(index), index);
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                String kichthuoc = ms.getText();
                dlrBUS.update(new DungLuongRomDTO(list.get(index).getMadungluongrom(), Integer.parseInt(kichthuoc)));
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(String.valueOf(list.get(index).getDungluongrom()));
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn dung lượng rom!");
        }
        return index;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
