/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.NhanVienBUS;
import DAO.NhanVienDAO;
import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;
import component.ButtonCustom;
import component.HeaderTitle;
import component.InputDate;
import component.InputForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;
import javax.swing.BoxLayout;
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
    private InputDate jcBd;
    
    public NhanVienDialog(NhanVienBUS nv, JFrame owner, boolean modal, String title, String type){
        super(owner,title,modal);
        this.nv = nv;
        init(title,type);
    }
    
    public void init(String title, String type){
        this.setSize(new Dimension(500,500));
        this.setLayout(new BorderLayout(0,0));
        
        
        titlePage = new HeaderTitle(title.toUpperCase());
        
        main = new JPanel();
        main.setLayout(new BoxLayout(main,BoxLayout.Y_AXIS));
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
        jgender.setSize(new Dimension(500,80));
        jgender.setBackground(Color.white);
        jgender.add(male);
        jgender.add(female);
        JLabel labelGender = new JLabel("Giới tính");
        jpanelG.add(labelGender);
        jpanelG.add(jgender);
        JPanel jpaneljd = new JPanel();
        jpaneljd.setBorder(new EmptyBorder(10, 10, 10, 10));
        JLabel lbBd = new JLabel("Ngày sinh");
        lbBd.setSize(new Dimension(100,100));
        jpaneljd.setSize(new Dimension(500,100));
        jpaneljd.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpaneljd.setBackground(Color.white);
        jcBd = new InputDate("Ngày sinh");
        jcBd.setSize(new Dimension(100,100));
        jpaneljd.add(lbBd);
        jpaneljd.add(jcBd);
        main.add(name);
        main.add(sdt);
        main.add(jpanelG);
        main.add(jcBd);
        
        bottom = new JPanel(new FlowLayout());
        bottom.setBorder(new EmptyBorder(10,0,10,0));
        bottom.setBackground(Color.white);
        btnAdd = new ButtonCustom("Thêm người dùng", "success", 14);
        btnEdit =  new ButtonCustom("Lưu thông tin","success", 14);
        btnExit = new ButtonCustom("Hủy bỏ", "danger", 14);
        btnExit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        btnAdd.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Name: "+ name.getText() );
                System.out.println("Phone: " + sdt.getText());
                System.out.println("Birthday: " + jcBd.getDate());
                int txt_gender = -1;
                if(male.isSelected()){
                    System.out.println("Nam");
                    txt_gender = 1;
                } else if(female.isSelected()){
                    System.out.println("Nữ");
                    txt_gender = 0;
                }
                String txtName = name.getText();
                String txtSdt = sdt.getText();
                Date birthDay = jcBd.getDate();
                DTO.NhanVien nv = new DTO.NhanVien(txtName, txt_gender, birthDay, txtSdt);
                NhanVienDAO.getInstance().insert(nv);
            }
        });
        
        btnEdit.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
            }
        });
        
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
