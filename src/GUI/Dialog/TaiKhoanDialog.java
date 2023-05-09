/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DAO.NhomQuyenDAO;
import DAO.TaiKhoanDAO;
import DTO.NhomQuyenDTO;
import DTO.TaiKhoanDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.SelectForm;
import GUI.Panel.TaiKhoan;
import helper.BCrypt;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author robot
 */
public class TaiKhoanDialog extends JDialog {

    private TaiKhoan taiKhoan;
    private TaiKhoan jbTaiKhoan;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm username;
    private InputForm password;
    private SelectForm maNhomQuyen;
    private SelectForm trangthai;
    int manv;
    private ArrayList<NhomQuyenDTO> listNq = NhomQuyenDAO.getInstance().selectAll();
    private ArrayList<TaiKhoanDTO> listTK = TaiKhoanDAO.getInstance().selectAll();

    public TaiKhoanDialog(TaiKhoan taiKhoan, JFrame owner, String title, boolean modal, String type, int manv) {
        super(owner, title, modal);
        init(title, type);
        this.manv = manv;
        this.taiKhoan = taiKhoan;
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public TaiKhoanDialog(TaiKhoan taiKhoan, JFrame owner, String title, boolean modal, String type, TaiKhoanDTO tk) {
        super(owner, title, modal);
        init(title, type);
        this.manv = tk.getManv();
        this.taiKhoan = taiKhoan;
        username.setText(tk.getUsername());
        password.setPass(tk.getMatkhau());
        maNhomQuyen.setSelectedItem(NhomQuyenDAO.getInstance().selectById(tk.getManhomquyen() + "").getTennhomquyen());
        trangthai.setSelectedIndex(tk.getTrangthai());
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init(String title, String type) {
        this.setSize(new Dimension(500, 620));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(4, 1, 5, 0));
        pnmain.setBackground(Color.white);
        username = new InputForm("Tên đăng nhập");
        password = new InputForm("Mật khẩu", "password");
        maNhomQuyen = new SelectForm("Nhóm quyền", getNhomQuyen());
        trangthai = new SelectForm("Trạng thái", new String[]{"Ngưng hoạt động", "Hoạt động"});
        pnmain.add(username);
        pnmain.add(password);
        pnmain.add(maNhomQuyen);
        pnmain.add(trangthai);
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm tài khoản", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validateInput()) {
                    String tendangnhap = username.getText();
                    int check = 0;
                    for (TaiKhoanDTO i : listTK) {
                        if (i.getUsername().equals(username.getText())) {
                            check++;
                            break;
                        }
                    }
                    if (check == 0) {
                        String pass = BCrypt.hashpw(password.getPass(), BCrypt.gensalt(12));
                        int manhom = listNq.get(maNhomQuyen.getSelectedIndex()).getManhomquyen();
                        int tt = trangthai.getSelectedIndex();
                        TaiKhoanDTO tk = new TaiKhoanDTO(manv, tendangnhap, pass, manhom, tt);
                        TaiKhoanDAO.getInstance().insert(tk);
                        taiKhoan.taiKhoanBus.addAcc(tk);
                        taiKhoan.loadTable(taiKhoan.taiKhoanBus.getTaiKhoanAll());
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Tên tài khoản đã tồn tại. Vui lòng đổi tên khác!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
                        username.getFocusCycleRootAncestor();
                    }

                }
            }
        });
        btnCapNhat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!(username.getText().length() == 0)) {
                    String tendangnhap = username.getText();
                    String pass = BCrypt.hashpw(password.getPass(), BCrypt.gensalt(12));
                    int manhom = listNq.get(maNhomQuyen.getSelectedIndex()).getManhomquyen();
                    int tt = trangthai.getSelectedIndex();
                    TaiKhoanDTO tk = new TaiKhoanDTO(manv, tendangnhap, pass, manhom, tt);
                    TaiKhoanDAO.getInstance().update(tk);
                    taiKhoan.taiKhoanBus.updateAcc(taiKhoan.getRowSelected(), tk);
                    taiKhoan.loadTable(taiKhoan.taiKhoanBus.getTaiKhoanAll());
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng không để trống tên");
                }
            }
        });
        btnHuyBo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        switch (type) {
            case "create" ->
                pnbottom.add(btnThem);
            case "update" -> {
                pnmain.remove(password);
                pnbottom.add(btnCapNhat);
                password.setDisablePass();
            }
            case "view" -> {
                pnmain.remove(password);
                username.setEditable(false);
//                password.setEditable(false);
                maNhomQuyen.setDisable();
                trangthai.setDisable();
                this.setSize(new Dimension(500, 550));
            }
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
    }

    public String[] getNhomQuyen() {
        String[] listNhomQuyen = new String[listNq.size()];
        for (int i = 0; i < listNq.size(); i++) {
            listNhomQuyen[i] = listNq.get(i).getTennhomquyen();
        }
        return listNhomQuyen;
    }

    public boolean validateInput() {
        if (username.getText().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống tên đăng nhập");
            return false;
        } else if (username.getText().length() < 6) {
            JOptionPane.showMessageDialog(this, "Tên đăng nhập ít nhất 6 kí tự");
            return false;
        } else if (password.getPass().length() == 0) {
            JOptionPane.showMessageDialog(this, "Vui lòng không để trống mật khẩu");
            return false;
        } else if (password.getPass().length() < 6) {
            JOptionPane.showMessageDialog(this, "Mật khẩu ít nhất 6 ký tự");
            return false;
        }
        return true;
    }

}
