/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;

import DAO.DungLuongRamDAO;
import DTO.ThuocTinhSanPham.DungLuongRamDTO;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.DungLuongRam;
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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

/**
 *
 * @author 84907
 */
public class DungLuongRamDialog extends JDialog implements ActionListener{
    private DungLuongRam jpdlram;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm kichthuoc;
    private DungLuongRamDTO dlram;

    public DungLuongRamDialog(DungLuongRam jpdlram, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpdlram = jpdlram;
        initComponents(title, type);
    }

    public DungLuongRamDialog(DungLuongRam jpdlram, JFrame owner, String title, boolean modal, String type, DungLuongRamDTO dlram) {
        super(owner, title, modal);
        this.jpdlram = jpdlram;
        this.dlram = dlram;
        initComponents(title, type,dlram);
    }

    public void initComponents(String title, String type,DungLuongRamDTO jpdlram) {
        this.setSize(new Dimension(500, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        kichthuoc = new InputForm("Kích thước Ram");

        pnmain.add(kichthuoc);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm dung lượng Ram", "success", 14);
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
               kichthuoc.setDisable();
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
    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        kichthuoc = new InputForm("Kích thước Ram", "password");
        
        pnmain.add(kichthuoc);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm dung lượng Ram", "success", 14);
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
        kichthuoc.setText(String.valueOf(dlram.getDungluongram()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            int mamau = DungLuongRamDAO.getInstance().getAutoIncrement();
            int kichthuoc = Integer.parseInt(this.kichthuoc.getText());
            jpdlram.dlrBUS.add(new DungLuongRamDTO(mamau, kichthuoc));
            jpdlram.loadDataTable(jpdlram.listdlr);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            int kichthuoc = Integer.parseInt(this.kichthuoc.getText());
            jpdlram.dlrBUS.update(new DungLuongRamDTO(dlram.getMadlram(),kichthuoc));
            jpdlram.loadDataTable(jpdlram.listdlr);
            dispose();
        }
    }
}
