/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import component.ButtonCustom;
import component.HeaderTitle;
import component.InputForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author 84907
 */
public class LoaiHangDialog extends JDialog implements MouseListener {

    private LoaiHang jpLH;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenLH;
    private JTextField maLH;

    public LoaiHangDialog(LoaiHang jpLH, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpLH = jpLH;
        tenLH = new InputForm("Tên loại hàng hóa mới");
        maLH = new JTextField("");
        initComponents(title, type);
    }

    public LoaiHangDialog(LoaiHang jpLH, JFrame owner, String title, boolean modal, String type, DTO.LoaiHangDTO lh) {
        super(owner, title, modal);
        tenLH = new InputForm("Tên loại hàng hóa mới");
        maLH = new JTextField("");
        setTenLoaihang(lh.getTenloaihang());
        setMaloaihang(Integer.toString(lh.getMaloaihang()));
        this.jpLH = jpLH;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 270));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(1, 1, 20, 0));
        pnmain.setBackground(Color.white);

        pnmain.add(tenLH);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm loại hàng", "success", 14);
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
                tenLH.setDisable();
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

    public void setTenLoaihang(String name) {
        tenLH.setText(name);
    }

    public String getTenLoaihang() {
        return tenLH.getText();
    }

    public void setMaloaihang(String id) {
        this.maLH.setText(id);
    }

    public String getMaLoaihang() {
        return maLH.getText();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThem) {
            String name = tenLH.getText();
            int id = 0;
            DTO.LoaiHangDTO lh = new DTO.LoaiHangDTO(id, name);
            jpLH.lhBUS.add(lh);
            jpLH.loadDataTalbe(jpLH.listLH);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String name = tenLH.getText();
            int id = Integer.parseInt(getMaLoaihang());
            DTO.LoaiHangDTO lh = new DTO.LoaiHangDTO(id, name);
            jpLH.lhBUS.update(lh);
            jpLH.loadDataTalbe(jpLH.listLH);
            dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
