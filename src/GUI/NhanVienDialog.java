/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import component.ButtonCustom;
import component.HeaderTitle;
import component.InputForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author robot
 */
public class NhanVienDialog extends JDialog{
    private NhanVienBUS nv;
    private HeaderTitle titlePage;
    private JPanel main, bottom;
    private ButtonCustom btnAdd, btnEdit, btnExit;
    private InputForm name;
    private InputForm sdt;
    private ButtonGroup gender;
    private JRadioButton male;
    private JRadioButton female;
    
    public NhanVienDialog(NhanVienBUS nv, JFrame owner, boolean modal, String title, String type){
        super(owner,title,modal);
        this.nv = nv;
        init(title,type);
    }
    
    public void init(String title, String type){
        this.setSize(new Dimension(500,390));
        this.setLayout(new BorderLayout(0,0));
        
        
        titlePage = new HeaderTitle(title.toUpperCase());
        
        main = new JPanel(new GridLayout(3,1,20,0));
        main.setBackground(Color.white);
        name = new InputForm("Họ và tên");
        sdt = new InputForm("Số điện thoại");
        male = new JRadioButton("Nam");
        female = new JRadioButton("Nữ");
        gender = new ButtonGroup();
        gender.add(male);
        gender.add(female);
        JPanel jpanelG = new JPanel(new GridLayout(2, 1,0,2));
        jpanelG.setBackground(Color.white);
        jpanelG.setBorder(new EmptyBorder(10, 10, 10, 10));
        JPanel jgender = new JPanel(new GridLayout(1, 2));
        jgender.setBackground(Color.white);
        jgender.add(male);
        jgender.add(female);
        JLabel labelGender = new JLabel("Giới tính");
        jpanelG.add(labelGender);
        jpanelG.add(jgender);
        main.add(name);
        main.add(jpanelG);
        main.add(sdt);
        
        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10,0,10,0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm người dùng", "success", 14);
        btnEdit =  new ButtonCustom("Lưu thông tin","success", 14);
        btnExit = new ButtonCustom("Hủy bỏ", "danger", 14);
        
        switch (type) {
            case "create":
                bottom.add(btnAdd);
                break;
            case "update":
                bottom.add(btnEdit);
            break;
            default:
                throw new AssertionError();
        }
        
        bottom.add(btnExit);
        
        this.add(titlePage,BorderLayout.NORTH);
        this.add(main,BorderLayout.CENTER);
        this.add(bottom,BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
}
