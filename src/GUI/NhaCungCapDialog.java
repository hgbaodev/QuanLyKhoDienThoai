/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DAO.NhaCungCapDAO;
import DTO.NhaCungCapDTO;
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
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class NhaCungCapDialog extends JDialog implements ActionListener {

    private NhaCungCap jpNcc;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenNcc;
    private InputForm diachi;
    private InputForm email;
    private InputForm sodienthoai;
    private NhaCungCapDTO nccDTO;

    public NhaCungCapDialog(NhaCungCap jpNcc, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpNcc = jpNcc;
        initComponents(title, type);
    }

    public NhaCungCapDialog(NhaCungCap jpNcc, JFrame owner, String title, boolean modal, String type, NhaCungCapDTO nccdto) {
        super(owner, title, modal);
        this.jpNcc = jpNcc;
        this.nccDTO = nccdto;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(900, 360));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(2, 2, 20, 0));
        pnmain.setBackground(Color.white);
        tenNcc = new InputForm("Tên nhà cung cấp");
        diachi = new InputForm("Địa chỉ");
        email = new InputForm("Email");
        sodienthoai = new InputForm("Số điện thoại");

        pnmain.add(tenNcc);
        pnmain.add(diachi);
        pnmain.add(email);
        pnmain.add(sodienthoai);

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
            case "create":
                pnbottom.add(btnThem);
                break;
            case "update":
                pnbottom.add(btnCapNhat);
                initInfo();
                break;
            case "view":
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
        tenNcc.setText(nccDTO.getTenncc());
        diachi.setText(nccDTO.getDiachi());
        email.setText(nccDTO.getEmail());
        sodienthoai.setText(nccDTO.getSdt());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            int mancc = NhaCungCapDAO.getInstance().getAutoIncrement();
            String tenNcc = this.tenNcc.getText();
            String diachi = this.diachi.getText();
            String email = this.email.getText();
            String sodienthoai = this.sodienthoai.getText();
            jpNcc.nccBUS.add(new NhaCungCapDTO(mancc, tenNcc, diachi, email, sodienthoai));
            jpNcc.loadDataTalbe(jpNcc.listncc);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String tenNcc = this.tenNcc.getText();
            String diachi = this.diachi.getText();
            String email = this.email.getText();
            String sodienthoai = this.sodienthoai.getText();
            jpNcc.nccBUS.update(new NhaCungCapDTO(nccDTO.getMancc(),tenNcc, diachi, email, sodienthoai));
            jpNcc.loadDataTalbe(jpNcc.listncc);
            dispose();
        }
    }
}
