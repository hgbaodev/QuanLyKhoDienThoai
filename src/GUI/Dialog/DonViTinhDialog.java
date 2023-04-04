/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DTO.DonViTinhDTO;
import GUI.Panel.DonViTinh;
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
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DonViTinhDialog extends JDialog implements ActionListener {

    private DonViTinh jpDVT;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenDv;
    private JTextField idDvt;
    private DonViTinhDTO dvtDTO;

    public DonViTinhDialog(DonViTinh jpDVT, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpDVT = jpDVT;
        idDvt = new JTextField("");
        initComponents(title, type);
    }

    public DonViTinhDialog(DonViTinh jpDVT, JFrame owner, String title, boolean modal, String type, DTO.DonViTinhDTO dvt) {
        super(owner, title, modal);
        this.jpDVT = jpDVT;
        this.dvtDTO = dvt;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 270));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(1, 1, 20, 0));
        pnmain.setBackground(Color.white);
        tenDv = new InputForm("Tên đơn vị tính");
        pnmain.add(tenDv);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm đơn vị", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addActionListener(this);
        btnCapNhat.addActionListener(this);
        btnHuyBo.addActionListener(this);

        switch (type) {
            case "create" -> pnbottom.add(btnThem);
            case "update" -> {
                initInfo();
                pnbottom.add(btnCapNhat);
            }
            case "view" -> initInfo();
            default -> throw new AssertionError();
        }
        
        pnbottom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void initInfo() {
        tenDv.setText(dvtDTO.getTenDVT());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            String name = tenDv.getText();
            jpDVT.dvtBUS.add(name);
            jpDVT.loadDataTalbe(jpDVT.listdvt);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String name = tenDv.getText();
            int id = dvtDTO.getMaDVT();
            DTO.DonViTinhDTO dvt = new DTO.DonViTinhDTO(id, name);
            jpDVT.dvtBUS.update(dvt);
            jpDVT.loadDataTalbe(jpDVT.listdvt);
            dispose();
        }
    }
}
