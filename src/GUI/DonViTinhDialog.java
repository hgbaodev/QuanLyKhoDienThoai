/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAO.DonViTinhDAO;
import component.ButtonCustom;
import component.HeaderTitle;
import component.InputForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class DonViTinhDialog extends JDialog implements MouseListener {

    private DonViTinh jpDVT;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenDv;
    private JTextField idDvt;

    public DonViTinhDialog(DonViTinh jpDVT, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpDVT = jpDVT;
        tenDv = new InputForm("Tên đơn vị tính");
        idDvt = new JTextField("");
        initComponents(title, type);
    }

    public DonViTinhDialog(DonViTinh jpDVT, JFrame owner, String title, boolean modal, String type, DTO.DonViTinh dvt) {
        super(owner, title, modal);
        tenDv = new InputForm("Tên đơn vị tính");
        idDvt = new JTextField("");
        setTenDv(dvt.getTenDVT());
        setIdDvt(Integer.toString(dvt.getMaDVT()));
        this.jpDVT = jpDVT;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 270));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(1, 1, 20, 0));
        pnmain.setBackground(Color.white);

        pnmain.add(tenDv);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm đơn vị", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" ->
                pnbottom.add(btnThem);
            case "update" ->
                pnbottom.add(btnCapNhat);
            case "view" -> 
                tenDv.setDisable();
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public void setTenDv(String name) {
        tenDv.setText(name);
    }

    public String getTenDonViTinh() {
        return tenDv.getText();
    }

    public String getIdDvt() {
        return idDvt.getText();
    }

    public void setIdDvt(String id) {
        this.idDvt.setText(id);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThem) {
            String name = tenDv.getText();
            int id = jpDVT.getAutoIncrement();
            DTO.DonViTinh dvt = new DTO.DonViTinh(id, name);
            DonViTinhDAO.getInstance().insert(dvt);
            jpDVT.addDVT(dvt);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String name = tenDv.getText();
            int id = Integer.parseInt(getIdDvt());
            DTO.DonViTinh dvt = new DTO.DonViTinh(id, name);
            DonViTinhDAO.getInstance().update(dvt);
            jpDVT.updateDVT(dvt);
            dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
