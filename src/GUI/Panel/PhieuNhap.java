package GUI.Panel;

import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuNhapBUS;
import DTO.NhanVienDTO;
import DTO.PhieuNhapDTO;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Main;
import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import helper.Formater;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.text.ParseException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class PhieuNhap extends JPanel implements ActionListener, KeyListener, PropertyChangeListener{

    PanelBorderRadius main, functionBar, box;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tablePhieuNhap;
    JScrollPane scrollTablePhieuNhap;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;
    InputDate dateStart, dateEnd;
    InputForm moneyMin, moneyMax;
    JTextField a;

    TaoPhieuNhap nhapKho;
    Main m;
    NhanVienDTO nv;

    PhieuNhapBUS phieunhapBUS = new PhieuNhapBUS();
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    ArrayList<PhieuNhapDTO> listPhieu;

    Color BackgroundColor = new Color(240, 247, 250);

    public PhieuNhap(Main m, NhanVienDTO nv) {
        this.m = m;
        this.nv = nv;
        this.listPhieu = phieunhapBUS.getAll();
        initComponent();
        loadDataTalbe(this.listPhieu);
    }

    public void initPadding() {
        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 10));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 10));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(10, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(10, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);
    }

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        tablePhieuNhap = new JTable();
        scrollTablePhieuNhap = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập", "Thời gian", "Tổng tiền"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuNhap.setModel(tblModel);
        tablePhieuNhap.setAutoCreateRowSorter(true);
        tablePhieuNhap.setDefaultEditor(Object.class, null);
        scrollTablePhieuNhap.setViewportView(tablePhieuNhap);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuNhap.setDefaultRenderer(Object.class, centerRenderer);
        tablePhieuNhap.setFocusable(false);
        tablePhieuNhap.getColumnModel().getColumn(0).setPreferredWidth(10);
        tablePhieuNhap.getColumnModel().getColumn(1).setPreferredWidth(10);
        tablePhieuNhap.getColumnModel().getColumn(2).setPreferredWidth(200);

        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(tblModel);
        sorter.setComparator(5, (String s1, String s2) -> {
            String cleanO1 = s1.replaceAll("[^\\d]", "");
            String cleanO2 = s2.replaceAll("[^\\d]", "");
            if (cleanO1.isEmpty() && cleanO2.isEmpty()) {
                return 0;
            } else if (cleanO1.isEmpty()) {
                return -1;
            } else if (cleanO2.isEmpty()) {
                return 1;
            }
            Double n1 = Double.valueOf(cleanO1);
            Double n2 = Double.valueOf(cleanO2);
            return Double.compare(n1, n2);
        });

        tablePhieuNhap.setRowSorter(sorter);

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        initPadding();

        contentCenter = new JPanel();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new BorderLayout(10, 10));
        this.add(contentCenter, BorderLayout.CENTER);

        functionBar = new PanelBorderRadius();
        functionBar.setPreferredSize(new Dimension(0, 100));
        functionBar.setLayout(new GridLayout(1, 2, 50, 0));
        functionBar.setBorder(new EmptyBorder(10, 10, 10, 10));

        String[] action = {"create", "detail", "delete", "cancel", "export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(), "nhaphang", action);
        functionBar.add(mainFunction);

        //Add Event MouseListener
        for (String ac : action) {
            mainFunction.btn.get(ac).addActionListener(this);
        }

        String[] objToSearch = {"Tất cả", "Mã phiếu nhập", "Nhà cung cấp", "Nhân viên nhập"};
        search = new IntegratedSearch(objToSearch);
        search.txtSearchForm.addKeyListener(this);
        search.btnReset.addActionListener(this);
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        box = new PanelBorderRadius();
        box.setPreferredSize(new Dimension(250, 0));
        box.setLayout(new GridLayout(6, 1, 10, 0));
        box.setBorder(new EmptyBorder(0, 5, 150, 5));
        contentCenter.add(box, BorderLayout.WEST);

        JLabel lbl1 = new JLabel("Lọc theo ngày");
        lbl1.putClientProperty("FlatLaf.style", "font: 130% $semibold.font");
        JLabel lbl2 = new JLabel("Lọc theo giá");
        lbl2.putClientProperty("FlatLaf.style", "font: 130% $semibold.font");
        dateStart = new InputDate("Từ ngày");
        dateEnd = new InputDate("Đến ngày");
        moneyMin = new InputForm("Từ số tiền (VND)");
        moneyMax = new InputForm("Đến số tiền (VND)");

        dateStart.getDateChooser().addPropertyChangeListener(this);
        dateEnd.getDateChooser().addPropertyChangeListener(this);
        moneyMin.getTxtForm().addKeyListener(this);
        moneyMax.getTxtForm().addKeyListener(this);

        box.add(dateStart);
        box.add(dateEnd);
        box.add(moneyMin);
        box.add(moneyMax);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(0, 0, 0, 0));
        contentCenter.add(main, BorderLayout.CENTER);
        main.add(scrollTablePhieuNhap);
    }

    public void loadDataTalbe(ArrayList<PhieuNhapDTO> listphieunhap) {
        tblModel.setRowCount(0);
        int size = listphieunhap.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1, listphieunhap.get(i).getMaphieu(),
                nccBUS.getTenNhaCungCap(listphieunhap.get(i).getManhacungcap()),
                nvBUS.getNameById(listphieunhap.get(i).getManguoitao()),
                Formater.FormatTime(listphieunhap.get(i).getThoigiantao()),
                Formater.FormatVND(listphieunhap.get(i).getTongTien())
            });
        }
    }

    public int getRowSelected() {
        int index = tablePhieuNhap.getSelectedRow();
        if (index == -1) {
            JOptionPane.showMessageDialog(this, "Vui lòng chọn phiếu nhập");
        }
        return index;
    }

    public void Fillter() throws ParseException {
        int type = search.cbxChoose.getSelectedIndex();
        String input = search.txtSearchForm.getText() != null ? search.txtSearchForm.getText() : "";
        Date time_start = dateStart.getDate() != null ? dateStart.getDate() : new Date(0);
        Date time_end = dateEnd.getDate() != null ? dateEnd.getDate() : new Date(System.currentTimeMillis());
        String min_price = moneyMin.getText();
        String max_price = moneyMax.getText();
        this.listPhieu = phieunhapBUS.fillerPhieuNhap(type, input, time_start, time_end, min_price, max_price);
        loadDataTalbe(listPhieu);
    }
    
    public void resetForm() {
        search.cbxChoose.setSelectedIndex(0);
        search.txtSearchForm.setText("");
        moneyMin.setText("");
        moneyMax.setText("");
        dateStart.getDateChooser().setCalendar(null);
        dateStart.getDateChooser().setCalendar(null);
        this.listPhieu = phieunhapBUS.getAllList();
        loadDataTalbe(listPhieu);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == mainFunction.btn.get("create")) {
            nhapKho = new TaoPhieuNhap(nv, "create", m);
            m.setPanel(nhapKho);
        } else if (source == mainFunction.btn.get("detail")) {
            int index = getRowSelected();
            if (index != -1) {
                nhapKho = new TaoPhieuNhap(nv, "view", listPhieu.get(index), m);
                m.setPanel(nhapKho);
            }
        } else if (source == mainFunction.btn.get("cancel")) {
            int index = getRowSelected();
            if (index != -1) {
                int input = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn huỷ phiếu ?\nThao tác này không thể hoàn tác nên hãy suy nghĩ kĩ !", "Huỷ phiếu", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
                if (input == 0) {
                    JOptionPane.showMessageDialog(null, "ok");
                }
            }
        } else if (source == search.btnReset){
            resetForm();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            Fillter();
        } catch (ParseException ex) {
            Logger.getLogger(PhieuNhap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
