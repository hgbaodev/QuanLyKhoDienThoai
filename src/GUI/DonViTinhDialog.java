/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAO.DonViTinhDAO;
import component.ButtonCustom;
import component.HeaderTitle;
import component.InputForm;
import component.SelectForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DonViTinhDialog extends JDialog {

    private DonViTinh jpDVT;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm txtTenDVT;
    private JTextField txtIdDVT;

    public DonViTinhDialog(DonViTinh jpDVT, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpDVT = jpDVT;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 270));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(1, 1, 20, 0));
        pnmain.setBackground(Color.white);

        txtTenDVT = new InputForm("Tên đơn vị tính");
        pnmain.add(txtTenDVT);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm đơn vị", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = txtTenDVT.getText();
                int id = jpDVT.getAutoIncrement();
                DTO.DonViTinh dvt = new DTO.DonViTinh(id,name);
                DonViTinhDAO.getInstance().insert(dvt);
                jpDVT.addDVT(dvt);
                dispose();
            }

        });
        
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }

        });
        
        switch (type) {
            case "create" -> pnbottom.add(btnThem);
            case "update" -> pnbottom.add(btnCapNhat);
            default -> throw new AssertionError();
        } 
        pnbottom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public void setTenDonViTinh(String name){
        txtTenDVT.setText(name);
    }

    public JTextField getTxtIdDVT() {
        return txtIdDVT;
    }

    public void setTxtIdDVT(JTextField txtIdDVT) {
        this.txtIdDVT = txtIdDVT;
    }
    
    

}
