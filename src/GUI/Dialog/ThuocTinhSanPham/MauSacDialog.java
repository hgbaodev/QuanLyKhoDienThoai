/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog.ThuocTinhSanPham;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
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
import DTO.ThuocTinhSanPham.MauSacDTO;
import DAO.MauSacDAO;
import BUS.MauSacBUS;
import GUI.Panel.MauSac;
/**
 *
 * @author 84907
 */
public class MauSacDialog extends JDialog implements ActionListener{
    private MauSac jpms;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenmau;
    private MauSacDTO mausac;

    public MauSacDialog(MauSac jpms, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpms = jpms;
        initComponents(title, type);
    }

    public MauSacDialog(MauSac jpms, JFrame owner, String title, boolean modal, String type, MauSacDTO mausac) {
        super(owner, title, modal);
        this.jpms = jpms;
        this.mausac = mausac;
        initComponents(title, type,mausac);
    }

    public void initComponents(String title, String type,MauSacDTO mausac) {
        this.setSize(new Dimension(500, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        tenmau = new InputForm("Tên màu");

        pnmain.add(tenmau);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm màu sắc", "success", 14);
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
               tenmau.setDisable();
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
        tenmau = new InputForm("Tên màu", "password");
        
        pnmain.add(tenmau);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm màu sắc", "success", 14);
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
        tenmau.setText(mausac.getTenmau());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            int mamau = MauSacDAO.getInstance().getAutoIncrement();
            String tenmau = this.tenmau.getText();
            jpms.msBUS.add(new MauSacDTO(mamau, tenmau));
            jpms.loadDataTable(jpms.listmausac);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String tenmau = this.tenmau.getText();
            jpms.msBUS.update(new MauSacDTO(mausac.getMamau(), tenmau));
            jpms.loadDataTable(jpms.listmausac);
            dispose();
        }
    }
}
