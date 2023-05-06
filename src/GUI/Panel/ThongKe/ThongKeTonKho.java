/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Panel.ThongKe;

import BUS.ThongKeBUS;
import DTO.ThongKe.ThongKeTonKhoDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import GUI.Component.TableSorter;
import GUI.Dialog.ThongKePBSPTonKho;
import helper.JTableExporter;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
public final class ThongKeTonKho extends JPanel implements ActionListener, KeyListener, PropertyChangeListener {

    PanelBorderRadius nhapxuat_left, nhapxuat_center;
    JTable tblTonKho;
    JScrollPane scrollTblTonKho;
    DefaultTableModel tblModel;
    InputForm tensanpham;
    InputDate start_date, end_date;
    ButtonCustom export, reset;
    HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> listSp;
    ThongKeBUS thongkeBUS;

    public ThongKeTonKho(ThongKeBUS thongkeBUS) {
        this.thongkeBUS = thongkeBUS;
        listSp = thongkeBUS.getTonKho();
        initComponent();
        loadDataTalbe(listSp);
    }

    public void initComponent() {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        nhapxuat_left = new PanelBorderRadius();
        nhapxuat_left.setPreferredSize(new Dimension(300, 100));
        nhapxuat_left.setLayout(new BorderLayout());
        nhapxuat_left.setBorder(new EmptyBorder(0, 0, 0, 5));
        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        nhapxuat_left.add(left_content, BorderLayout.NORTH);

        tensanpham = new InputForm("Tìm kiếm sản phẩm");
        tensanpham.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");

        tensanpham.getTxtForm().addKeyListener(this);
        start_date.getDateChooser().addPropertyChangeListener(this);
        end_date.getDateChooser().addPropertyChangeListener(this);

        JPanel btn_layout = new JPanel(new BorderLayout());
        JPanel btninner = new JPanel(new GridLayout(1, 2));
        btninner.setOpaque(false);
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setBackground(Color.white);
        export = new ButtonCustom("Xuất Excel", "excel", 14);
        reset = new ButtonCustom("Làm mới", "danger", 14);

        export.addActionListener(this);
        reset.addActionListener(this);

        btninner.add(export);
        btninner.add(reset);
        btn_layout.add(btninner, BorderLayout.NORTH);

        left_content.add(tensanpham);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        nhapxuat_center = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(nhapxuat_center, BoxLayout.Y_AXIS);
        nhapxuat_center.setLayout(boxly);

        tblTonKho = new JTable();
        scrollTblTonKho = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã SP", "Tên sản phẩm", "Tồn đầu kỳ", "Nhập trong kỳ", "Xuất trong kỳ", "Tồn cuối kỳ"};
        tblModel.setColumnIdentifiers(header);
        tblTonKho.setModel(tblModel);
        tblTonKho.setAutoCreateRowSorter(true);
        tblTonKho.setDefaultEditor(Object.class, null);
        scrollTblTonKho.setViewportView(tblTonKho);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tblTonKho.setDefaultRenderer(Object.class, centerRenderer);
        tblTonKho.setFocusable(false);
        tblTonKho.getColumnModel().getColumn(0).setPreferredWidth(10);
        tblTonKho.getColumnModel().getColumn(1).setPreferredWidth(10);
        tblTonKho.getColumnModel().getColumn(2).setPreferredWidth(200);

        TableSorter.configureTableColumnSorter(tblTonKho, 0, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 1, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 3, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 4, TableSorter.INTEGER_COMPARATOR);
        TableSorter.configureTableColumnSorter(tblTonKho, 5, TableSorter.INTEGER_COMPARATOR);

        TableSorter.configureTableColumnSorter(tblTonKho, 6, TableSorter.INTEGER_COMPARATOR);

        nhapxuat_center.add(scrollTblTonKho);

        tblTonKho.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblTonKhoClicked(evt);
            }
        });

        this.add(nhapxuat_left, BorderLayout.WEST);
        this.add(nhapxuat_center, BorderLayout.CENTER);
    }

    private void tblTonKhoClicked(java.awt.event.MouseEvent evt) {
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            if (tblTonKho.getSelectedRow() == -1) {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn sản phẩm");
            } else {
                int masp = (int) tblTonKho.getModel().getValueAt(tblTonKho.getSelectedRow(), 1);
                ThongKePBSPTonKho sppp = new ThongKePBSPTonKho((JFrame) javax.swing.SwingUtilities.getWindowAncestor(this), "Chi tiết tồn kho từng cấu hình", true, listSp.get(masp));
            }
        }
    }

    public void Fillter() throws ParseException {
        if (validateSelectDate()) {
            String input = tensanpham.getText() != null ? tensanpham.getText() : "";
            Date time_start = start_date.getDate() != null ? start_date.getDate() : new Date(0);
            Date time_end = end_date.getDate() != null ? end_date.getDate() : new Date(System.currentTimeMillis());
            this.listSp = thongkeBUS.filterTonKho(input, time_start, time_end);
            loadDataTalbe(this.listSp);
        }
    }

    public void resetForm() throws ParseException {
        tensanpham.setText("");
        start_date.getDateChooser().setCalendar(null);
        end_date.getDateChooser().setCalendar(null);
        Fillter();
    }

    public boolean validateSelectDate() throws ParseException {
        Date time_start = start_date.getDate();
        Date time_end = end_date.getDate();

        Date current_date = new Date();
        if (time_start != null && time_start.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            start_date.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_end != null && time_end.after(current_date)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc không được lớn hơn ngày hiện tại", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            end_date.getDateChooser().setCalendar(null);
            return false;
        }
        if (time_start != null && time_end != null && time_start.after(time_end)) {
            JOptionPane.showMessageDialog(this, "Ngày kết thúc phải lớn hơn ngày bắt đầu", "Lỗi !", JOptionPane.ERROR_MESSAGE);
            end_date.getDateChooser().setCalendar(null);
            return false;
        }
        return true;
    }

    private void loadDataTalbe(HashMap<Integer, ArrayList<ThongKeTonKhoDTO>> list) {
        tblModel.setRowCount(0);
        int size = list.size();
        int index = 1;
        for (int i : list.keySet()) {
            int[] soluong = thongkeBUS.getSoluong(list.get(i));
            tblModel.addRow(new Object[]{
                index + 1, i, list.get(i).get(0).getTensanpham(), soluong[0], soluong[1], soluong[2], soluong[3]
            });
            index++;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == export) {
            try {
                JTableExporter.exportJTableToExcel(tblTonKho);
            } catch (IOException ex) {
                Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (source == reset) {
            try {
                resetForm();
            } catch (ParseException ex) {
                Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(ThongKeTonKho.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
