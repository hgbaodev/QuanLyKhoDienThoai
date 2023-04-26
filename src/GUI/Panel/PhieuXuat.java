package GUI.Panel;

import BUS.KhachHangBUS;
import BUS.NhaCungCapBUS;
import BUS.NhanVienBUS;
import BUS.PhieuXuatBUS;
import DTO.PhieuXuatDTO;
import DTO.TaiKhoanDTO;
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
import java.util.ArrayList;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class PhieuXuat extends JPanel implements ActionListener {

    PanelBorderRadius main, functionBar;
    JPanel pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tablePhieuXuat;
    JScrollPane scrollTablePhieuXuat;
    MainFunction mainFunction;
    IntegratedSearch search;
    DefaultTableModel tblModel;

    Main m;
    TaoPhieuXuat taoPhieuXuat;
    TaiKhoanDTO tk;

    Color BackgroundColor = new Color(240, 247, 250);
    
    NhaCungCapBUS nccBUS = new NhaCungCapBUS();
    NhanVienBUS nvBUS = new NhanVienBUS();
    PhieuXuatBUS pxBUS = new PhieuXuatBUS();
    KhachHangBUS khachHangBUS = new KhachHangBUS();

    public PhieuXuat(Main m,TaiKhoanDTO tk) {
        this.m = m;
        this.tk = tk;
        initComponent();
        loadDataTalbe(pxBUS.getAll());
    }

    private void initComponent() {

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

        String[] action = {"create","detail","cancel","export"};
        mainFunction = new MainFunction(m.user.getManhomquyen(),"xuathang",action);
        functionBar.add(mainFunction);
        
        //Add Event MouseListener
        for(String ac : action){
            mainFunction.btn.get(ac).addActionListener(this);
        }

        search = new IntegratedSearch(new String[]{"Tất cả"});
        functionBar.add(search);

        contentCenter.add(functionBar, BorderLayout.NORTH);

        main = new PanelBorderRadius();
        BoxLayout boxly = new BoxLayout(main, BoxLayout.Y_AXIS);
        main.setLayout(boxly);
        main.setBorder(new EmptyBorder(20, 20, 20, 20));
        contentCenter.add(main, BorderLayout.CENTER);

        tablePhieuXuat = new JTable();
        scrollTablePhieuXuat = new JScrollPane();
        tblModel = new DefaultTableModel();
        String[] header = new String[]{"STT", "Mã phiếu xuất", "Khách hàng", "Nhân viên nhập", "Thời gian", "Tổng tiền"};
        tblModel.setColumnIdentifiers(header);
        tablePhieuXuat.setModel(tblModel);
        scrollTablePhieuXuat.setViewportView(tablePhieuXuat);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(JLabel.CENTER);
        tablePhieuXuat.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);
        tablePhieuXuat.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
        tablePhieuXuat.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
        tablePhieuXuat.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
        tablePhieuXuat.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
        tablePhieuXuat.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
        scrollTablePhieuXuat.setViewportView(tablePhieuXuat);

        main.add(scrollTablePhieuXuat);

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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == mainFunction.btn.get("create")) {
            taoPhieuXuat = new TaoPhieuXuat(m,tk,"create");
            m.setPanel(taoPhieuXuat);
        } else if(e.getSource() == mainFunction.btn.get("detail")){
            if(getRow()<0){
                JOptionPane.showMessageDialog(null, "Vui lòng chọn phiếu cần xem!");
            } else {
                taoPhieuXuat = new TaoPhieuXuat(m,tk,pxBUS.getSelect(getRow()),"detail");
                m.setPanel(taoPhieuXuat);
            }
        }
    }
    
    public void loadDataTalbe(ArrayList<PhieuXuatDTO> listphieuxuat) {
        tblModel.setRowCount(0);
        int size = listphieuxuat.size();
        for (int i = 0; i < size; i++) {
            tblModel.addRow(new Object[]{
                i + 1, 
                listphieuxuat.get(i).getMaphieu(),
                khachHangBUS.getTenKhachHang(listphieuxuat.get(i).getMakh()),
                nvBUS.getNameById(listphieuxuat.get(i).getManguoitao()),
                Formater.FormatTime(listphieuxuat.get(i).getThoigiantao()),
                Formater.FormatVND(listphieuxuat.get(i).getTongTien()),
            });
        }
    }
    
    public int getRow(){
        return tablePhieuXuat.getSelectedRow();
    }

}
