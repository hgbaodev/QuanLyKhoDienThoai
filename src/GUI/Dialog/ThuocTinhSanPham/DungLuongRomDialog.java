/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;

import DAO.DungLuongRomDAO;
import DTO.ThuocTinhSanPham.DungLuongRomDTO;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Panel.DungLuongRom;
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
public class DungLuongRomDialog extends JDialog implements ActionListener{
    private DungLuongRom jpdlrom;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm kichthuoc;
    private DungLuongRomDTO dlrom;

    public DungLuongRomDialog(DungLuongRom jpdlrom, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpdlrom = jpdlrom;
        initComponents(title, type);
    }

    public DungLuongRomDialog(DungLuongRom jpdlrom, JFrame owner, String title, boolean modal, String type, DungLuongRomDTO dlrom) {
        super(owner, title, modal);
        this.jpdlrom = jpdlrom;
        this.dlrom = dlrom;
        initComponents(title, type,dlrom);
    }

    public void initComponents(String title, String type,DungLuongRomDTO jpdlrom) {
        this.setSize(new Dimension(500, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        kichthuoc = new InputForm("Kích thước Rom");

        pnmain.add(kichthuoc);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm dung lượng Rom", "success", 14);
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
        kichthuoc = new InputForm("Kích thước Rom", "password");
        
        pnmain.add(kichthuoc);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm dung lượng Rom", "success", 14);
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
        kichthuoc.setText(String.valueOf(dlrom.getDungluongrom()));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            int mamau = DungLuongRomDAO.getInstance().getAutoIncrement();
            int kichthuoc = Integer.parseInt(this.kichthuoc.getText());
            jpdlrom.dlrBUS.add(new DungLuongRomDTO(mamau, kichthuoc));
            jpdlrom.loadDataTable(jpdlrom.listdlr);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            int kichthuoc = Integer.parseInt(this.kichthuoc.getText());
            jpdlrom.dlrBUS.update(new DungLuongRomDTO(dlrom.getMadungluongrom(),kichthuoc));
            jpdlrom.loadDataTable(jpdlrom.listdlr);
            dispose();
        }
    }
}

