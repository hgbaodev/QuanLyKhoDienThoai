/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.SanPhamBUS;
import DAO.KhuVucKhoDAO;
import DTO.KhuVucKhoDTO;
import DTO.SanPhamDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.KhuVucKho;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

public class KhuVucKhoDialog extends JDialog implements ActionListener {

    private KhuVucKho jpkvk;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenkhuvuc;
    private InputForm ghichu;
    private KhuVucKhoDTO kvk;

    public KhuVucKhoDialog(KhuVucKho jpkvk, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpkvk = jpkvk;
        initComponents(title, type);
    }

    public KhuVucKhoDialog(KhuVucKho jpkvk, JFrame owner, String title, boolean modal, String type, KhuVucKhoDTO kvk) {
        super(owner, title, modal);
        this.jpkvk = jpkvk;
        this.kvk = kvk;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        tenkhuvuc = new InputForm("Tên khu vực kho");
        ghichu = new InputForm("Ghi chú");

        pnmain.add(tenkhuvuc);
        pnmain.add(ghichu);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm khu vực kho", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuyBo.addActionListener(this);

        switch (type) {
            case "create":
                pnbottom.add(btnThem);
                break;
            case "update":
                pnbottom.add(btnCapNhat);
                initInfo();
                break;
            case "view":
                this.setSize(new Dimension(500, 400));
                BoxLayout boxly = new BoxLayout(pnmain, BoxLayout.Y_AXIS);
                pnmain.setLayout(boxly);
                pnmain.setBorder(new EmptyBorder(20, 20, 20, 20));

                JScrollPane scrollTableSanPham = new JScrollPane();
                JTable tableSanPham = new JTable();
                tableSanPham.setSize(new Dimension(500,300));
                SanPhamBUS spBUS = new SanPhamBUS();
                DefaultTableModel tblModel = new DefaultTableModel();
                ArrayList<SanPhamDTO> spbus = spBUS.getAll();
                tableSanPham.setModel(new javax.swing.table.DefaultTableModel(
                        new Object[][]{},
                        new String[]{}
                ));
                tableSanPham.setFont(new java.awt.Font("Segoe UI", 0, 14));
                tblModel = new DefaultTableModel();
                String[] header = new String[]{"Mã sản phẩm", "Tên sản phẩm", "Tên đơn vị tính","Tên loại hàng","Tên khu vực"};
                tblModel.setColumnIdentifiers(header);
                tableSanPham.setModel(tblModel);
                scrollTableSanPham.setViewportView(tableSanPham);
                DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
                centerRenderer.setHorizontalAlignment(JLabel.CENTER);
                TableColumnModel columnModel = tableSanPham.getColumnModel();
                columnModel.getColumn(0).setCellRenderer(centerRenderer);
                columnModel.getColumn(1).setCellRenderer(centerRenderer);
                columnModel.getColumn(2).setCellRenderer(centerRenderer);
                tblModel.setRowCount(0);
                for (DTO.SanPhamDTO sp : spbus) {
                    tblModel.addRow(new Object[]{
                        sp.getMasp(), sp.getTensp(), sp.getMaDVT(), sp.getMaloaihang(), sp.getMakhuvuc()
                    });
                }
                scrollTableSanPham.add(tableSanPham);
                scrollTableSanPham.setBackground(Color.red);
                pnmain.add(scrollTableSanPham);
                btnCapNhat.setVisible(false);
                btnThem.setVisible(false);
                tenkhuvuc.setVisible(false);
                ghichu.setVisible(false);
                break;
            default:
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

    }

    public void initInfo() {
        tenkhuvuc.setText(kvk.getTenkhuvuc());
        ghichu.setText(kvk.getGhichu());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            int makhuvuc = KhuVucKhoDAO.getInstance().getAutoIncrement();
            String tenkhuvuc = this.tenkhuvuc.getText();
            String ghichu = this.ghichu.getText();
            jpkvk.kvkBUS.add(new KhuVucKhoDTO(makhuvuc, tenkhuvuc, ghichu));
            jpkvk.loadDataTalbe(jpkvk.listKVK);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String tenkhuvuc = this.tenkhuvuc.getText();
            String ghichu = this.ghichu.getText();
            jpkvk.kvkBUS.update(new KhuVucKhoDTO(kvk.getMakhuvuckho(), tenkhuvuc, ghichu));
            jpkvk.loadDataTalbe(jpkvk.listKVK);
            dispose();
        }
    }

}
