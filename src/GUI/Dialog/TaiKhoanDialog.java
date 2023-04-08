/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DAO.NhomQuyenDAO;
import DTO.NhomQuyenDTO;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.SelectForm;
import GUI.Panel.DonViTinh;
import GUI.Panel.TaiKhoan;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author robot
 */
public class TaiKhoanDialog extends JDialog{
    private TaiKhoan taiKhoan;
    private TaiKhoan jbTaiKhoan;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm username;
    private InputForm password;
    private SelectForm maNhomQuyen;
    private SelectForm trangthai;
    private ArrayList<NhomQuyenDTO> listNq = NhomQuyenDAO.getInstance().selectAll();
    
    public TaiKhoanDialog(TaiKhoan taiKhoan, JFrame owner, String title, boolean modal, String type){
        super(owner, title, modal);
        init(title, type);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public void init(String title, String type){
        this.setSize(new Dimension(500, 620));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(4, 1, 5, 0));
        pnmain.setBackground(Color.white);
        username = new InputForm("Tên đăng nhập");
        password = new InputForm("Mật khẩu","password");
        maNhomQuyen = new SelectForm("Nhóm quyền", getNhomQuyen());
        trangthai = new SelectForm("Trạng thái", new String[]{"Hoạt động","Ngưng hoạt động"});
        pnmain.add(username);
        pnmain.add(password);
        pnmain.add(maNhomQuyen);
        pnmain.add(trangthai);
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm đơn vị", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        
        switch (type) {
            case "create" -> pnbottom.add(btnThem);
            case "update" -> {
                pnbottom.add(btnCapNhat);
            }
            case "view" -> {
                
            }
            default -> throw new AssertionError();
        }
        
        pnbottom.add(btnHuyBo);
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
    }
    
    public String[] getNhomQuyen(){
        String[] listNhomQuyen = new String[listNq.size()];
        for(int i = 0; i < listNq.size(); i++){
            listNhomQuyen[i] = listNq.get(i).getTennhomquyen();
        }
        return listNhomQuyen;
    }
}
