/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;

import BUS.MauSacBUS;
import DAO.MauSacDAO;
import DTO.ThuocTinhSanPham.MauSacDTO;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.QuanLyThuocTinhSP;
import com.formdev.flatlaf.FlatIntelliJLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.ScrollPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class MauSacDialog extends JDialog implements MouseListener {

    QuanLyThuocTinhSP qlttsp;
    HeaderTitle headTite;
    JPanel top, main, bottom, all;
    InputForm ms;
    DefaultTableModel tblModel;
    JTable table;
    JScrollPane scrollTable;
    JButton add, del, update;
    MauSacBUS msBUS = new MauSacBUS();
    ArrayList<MauSacDTO> list = msBUS.getAll();

    public MauSacDialog(JFrame onwer, QuanLyThuocTinhSP qltt , String title, boolean modal) {
        super(onwer, title, modal);
        initComponent(qltt);
        loadDataTable(list);
    }

    public void initComponent(QuanLyThuocTinhSP qlttsp) {
        this.qlttsp  =qlttsp;
        this.setSize(new Dimension(425, 500));
        this.setLayout(new BorderLayout(0, 0));
        headTite = new HeaderTitle("Quản lý màu sắc sản phẩm");
        this.setBackground(Color.white);
        top = new JPanel();
        main = new JPanel();
        bottom = new JPanel();

        ms = new InputForm("Tên màu sắc");
        ms.setPreferredSize(new Dimension(150, 70));
        top.setLayout(new FlowLayout(0));
        top.setBackground(Color.WHITE);
        top.setPreferredSize(new Dimension(0, 150));
        top.add(headTite);
        top.add(ms);

        main.setBackground(Color.WHITE);
        main.setPreferredSize(new Dimension(420, 200));
        table = new JTable();
        table.setBackground(Color.WHITE);
        table.addMouseListener(this);
        scrollTable = new JScrollPane();
        scrollTable.setBackground(Color.WHITE);
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"Mã màu", "Tên màu"};
        tblModel.setColumnIdentifiers(header);
        table.setModel(tblModel);
        scrollTable.setViewportView(table);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        TableColumnModel columnModel = table.getColumnModel();
        columnModel.getColumn(0).setCellRenderer(centerRenderer);
        columnModel.getColumn(1).setCellRenderer(centerRenderer);
        main.add(scrollTable);

        add = new JButton("Thêm");
        add.addMouseListener(this);
        del = new JButton("Xóa");
        del.addMouseListener(this);
        update = new JButton("Sửa");
        update.addMouseListener(this);
        bottom.setBackground(Color.white);
        bottom.setLayout(new FlowLayout(1, 50, 20));
        bottom.add(add);
        bottom.add(del);
        bottom.add(update);
        bottom.setPreferredSize(new Dimension(0, 70));

        this.add(top, BorderLayout.NORTH);
        this.add(main, BorderLayout.CENTER);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
    }

    public void loadDataTable(ArrayList<MauSacDTO> result) {
        tblModel.setRowCount(0);
        for (MauSacDTO ncc : result) {
            tblModel.addRow(new Object[]{
                ncc.getMamau(), ncc.getTenmau()
            });
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == add) {
            if (this.ms.getText() == "") {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên màu mới");
            } else {
                int id = MauSacDAO.getInstance().getAutoIncrement();
                String tenmau = ms.getText();
                msBUS.add(new MauSacDTO(id, tenmau));
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == del) {
            int index = getRowSelected();
            if (index != -1) {
                msBUS.delete(list.get(index), index);
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == update) {
            int index = getRowSelected();
            if (index != -1) {
                String tenmau = ms.getText();
                msBUS.update(new MauSacDTO(list.get(index).getMamau(), tenmau));
                loadDataTable(list);
                ms.setText("");
            }
        } else if (e.getSource() == table) {
            int index = table.getSelectedRow();
            ms.setText(list.get(index).getTenmau());
        }
    }

    public int getRowSelected() {
        int index = table.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn màu sắc!");
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
