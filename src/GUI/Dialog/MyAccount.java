/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this main2late
 */
package GUI.Dialog;

import BUS.NhanVienBUS;
import BUS.TaiKhoanBUS;
import DAO.NhanVienDAO;
import DAO.TaiKhoanDAO;
import DTO.NhanVienDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.MenuTaskbar;
import helper.BCrypt;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

public class MyAccount extends JDialog implements ActionListener {

    CardLayout card;
    ButtonCustom save, cancel;
    HeaderTitle title;
    JPanel top, center, top_center, main_center, bottom;
    InputForm current_pwd, phone, email, new_pwd, confirm;
    NhanVienDTO nv;
    TaiKhoanBUS tkbus;
    NhanVienBUS nvbus;
    MenuTaskbar menuTaskbar;
    JRadioButton[] jbr;
    JPanel[] panel;

    public MyAccount(JFrame owner, MenuTaskbar menutaskbar, String title, boolean modal) {
        super(owner, title, modal);
        initComponent(menutaskbar);
        this.setLocationRelativeTo(null);
    }

    public void initComponent(MenuTaskbar menutaskbar) {
        tkbus = new TaiKhoanBUS();
        nvbus = new NhanVienBUS();
        this.menuTaskbar = menutaskbar;
        this.setSize(400, 300);
        this.setLayout(new BorderLayout(0, 0));
        this.setBackground(Color.WHITE);
        this.setResizable(false);
        nv = menuTaskbar.nhanVienDTO;
        top = new JPanel();
        top.setBackground(Color.WHITE);
        top.setLayout(new FlowLayout(0,0,0));
        title = new HeaderTitle("CHỈNH SỬA THÔNG TIN");
        top.add(title);
        this.add(top, BorderLayout.NORTH);

        top_center = new JPanel(new FlowLayout(1, 40, 0));
        top_center.setBorder(new EmptyBorder(20,0,0,0));
        top_center.setBackground(Color.WHITE);
        main_center = new JPanel();
        main_center.setBackground(Color.WHITE);

        ButtonGroup bg = new ButtonGroup();
        String opt[] = {"Số điện thoại", "Email", "Mật khẩu"};
        jbr = new JRadioButton[3];
        for (int i = 0; i < jbr.length; i++) {
            jbr[i] = new JRadioButton();
            jbr[i].addActionListener(this);
            jbr[i].setText(opt[i]);
            top_center.add(jbr[i]);
            bg.add(jbr[i]);
        }
        jbr[0].setSelected(true);

        center = new JPanel();
        center.setLayout(new BorderLayout());
        center.add(top_center, BorderLayout.NORTH);
        center.add(main_center, BorderLayout.CENTER);

        panel = new JPanel[3];
        panel[0] = new JPanel(new GridLayout(1, 1));
        panel[0].setPreferredSize(new Dimension(400, 100));
        phone = new InputForm("Số điện thoại");
        phone.setText(nv.getSdt());
        panel[0].add(phone);

        panel[1] = new JPanel(new GridLayout(1, 1));
        panel[1].setPreferredSize(new Dimension(400, 100));
        email = new InputForm("Email");
        email.setText(nv.getEmail());
        panel[1].add(email);
        main_center.add(panel[0]);

        panel[2] = new JPanel(new GridLayout(3, 1));
        panel[2].setPreferredSize(new Dimension(400, 300));
        current_pwd = new InputForm("Mật khẩu hiện tại", "password");
        new_pwd = new InputForm("Mật khẩu mới", "password");
        confirm = new InputForm("Nhập lại mật khẩu mới", "password");
        panel[2].add(current_pwd);
        panel[2].add(new_pwd);
        panel[2].add(confirm);

        this.add(center, BorderLayout.CENTER);

        bottom = new JPanel(new FlowLayout(1, 20, 10));
        bottom.setBackground(Color.WHITE);

        cancel = new ButtonCustom("Hủy", "danger", 15);
        cancel.addActionListener(this);
        bottom.add(cancel);
        save = new ButtonCustom("Lưu", "success", 15);
        save.addActionListener(this);
        bottom.add(save);
        this.add(bottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cancel) {
            this.dispose();
        }
        for (int i = 0; i < 3; i++) {
            if (e.getSource() == jbr[i]) {
                if (i == 2) {
                    this.setSize(new Dimension(400, 500));
                    this.setLocationRelativeTo(null);
                } else {
                    this.setSize(400, 300);
                }
                main_center.removeAll();
                main_center.add(panel[i]);
                main_center.repaint();
                main_center.validate();
            }
        }

        if (e.getSource() == save) {
            if (jbr[0].isSelected()) {
                if (checknull(phone, "Số điện thoại") == true) {
                    String sdt = phone.getText();
                    NhanVienDTO nvdto = new NhanVienDTO(nv.getManv(), nv.getHoten(), nv.getGioitinh(), nv.getNgaysinh(), sdt, nv.getTrangthai(), nv.getEmail());
                    NhanVienDAO.getInstance().update(nvdto);
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    this.dispose();
                }
            }
            if (jbr[1].isSelected()) {
                if (checknull(phone, "Email") == true) {
                    String emailString = email.getText();
                    NhanVienDTO nvdto = new NhanVienDTO(nv.getManv(), nv.getHoten(), nv.getGioitinh(), nv.getNgaysinh(), nv.getSdt(), nv.getTrangthai(), emailString);
                    NhanVienDAO.getInstance().update(nvdto);
                    JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                    this.dispose();
                }
            }
            if (jbr[2].isSelected()) {
                TaiKhoanDTO tkdto = tkbus.getTaiKhoan(tkbus.getTaiKhoanByMaNV(nv.getManv()));
                if (checknull(current_pwd, "mật khẩu hiện tại") == true) {
                    if (current_pwd.getPass().trim() != tkdto.getMatkhau()) {
                        JOptionPane.showMessageDialog(this, "Mật khẩu hiện tại không đúng");
                    } else {
                        if (checknull(new_pwd, "mật khẩu mới") || checknull(confirm, "xác nhận mật khẩu mới")) {
                            if (new_pwd.getPass().trim() != confirm.getText().trim()) {
                                JOptionPane.showMessageDialog(this, "Mật khẩu nhập lại không khớp");
                            } else {
                                String pass = BCrypt.hashpw(confirm.getPass(), BCrypt.gensalt(12));
                                TaiKhoanDTO tk = new TaiKhoanDTO(tkdto.getManv(), tkdto.getUsername(), pass, tkdto.getManhomquyen(), tkdto.getTrangthai());
                                TaiKhoanDAO.getInstance().update(tk);
                                JOptionPane.showMessageDialog(this, "Cập nhật thành công");
                                this.dispose();
                            }
                        }
                    }
                }
            }
        }
    } 

    public boolean checknull(InputForm x, String object) {
        if ("".equals(x.getText().trim()) || "".equals(x.getPass().trim())) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập " + object);
            return false;
        } else {
            return true;
        }
    }
}
