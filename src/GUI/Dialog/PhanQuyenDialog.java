/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import BUS.ChiTietQuyenBUS;
import BUS.NhomQuyenBUS;
import DAO.ChiTietQuyenDAO;
import DAO.DanhMucChucNangDAO;
import DAO.NhomQuyenDAO;
import DTO.ChiTietQuyenDTO;
import DTO.DanhMucChucNangDTO;
import DTO.NhomQuyenDTO;
import GUI.Panel.PhanQuyen;
import GUI.Component.ButtonCustom;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhanQuyenDialog extends JDialog implements ActionListener {

    private JLabel lblTennhomquyen;
    private JTextField txtTennhomquyen;
    private JPanel jpTop, jpLeft, jpCen, jpBottom;
    private JCheckBox[][] listCheckBox;
    private ButtonCustom btnAddNhomQuyen, btnUpdateNhomQuyen,btnHuybo;
    private int sizeDmCn, sizeHanhdong;
    private ArrayList<DanhMucChucNangDTO> dmcn;
    
    String[] mahanhdong = {"view", "create", "update", "delete"};
    private ArrayList<ChiTietQuyenDTO> ctQuyen;
    private NhomQuyenDTO nhomquyenDTO;
    private NhomQuyenBUS nhomquyenBUS = new NhomQuyenBUS();

    public void initComponents(String type) {
        dmcn = DanhMucChucNangDAO.getInstance().selectAll();

        String[] hanhdong = {"Xem", "Tạo mới", "Cập nhật", "Xoá"};

        this.setSize(new Dimension(1000, 580));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        // Hiển thị tên nhóm quyền
        jpTop = new JPanel(new BorderLayout(20, 10));
        jpTop.setBorder(new EmptyBorder(20, 20, 20, 20));
        jpTop.setBackground(Color.WHITE);
        lblTennhomquyen = new JLabel("Tên nhóm quyền");
        txtTennhomquyen = new JTextField();
        txtTennhomquyen.setPreferredSize(new Dimension(150, 35));
        jpTop.add(lblTennhomquyen, BorderLayout.WEST);
        jpTop.add(txtTennhomquyen, BorderLayout.CENTER);

        // Hiển thị danh mục chức năng
        jpLeft = new JPanel(new GridLayout(dmcn.size() + 1, 1));
        jpLeft.setBackground(Color.WHITE);
        jpLeft.setBorder(new EmptyBorder(0, 20, 0, 14));
        JLabel dmcnl = new JLabel("Danh mục chức năng ");
        dmcnl.setFont(new Font("Segoe UI", Font.BOLD, 15));
        jpLeft.add(dmcnl);
        for (DanhMucChucNangDTO i : dmcn) {
            JLabel lblTenchucnang = new JLabel(i.getTenchucnang());
            jpLeft.add(lblTenchucnang);
        }
        // Hiển thị chức năng CRUD        
        sizeDmCn = dmcn.size();
        sizeHanhdong = hanhdong.length;
        jpCen = new JPanel(new GridLayout(sizeDmCn + 1, sizeHanhdong));
        jpCen.setBackground(Color.WHITE);
        listCheckBox = new JCheckBox[sizeDmCn][sizeHanhdong];
        for (int i = 0; i < sizeHanhdong; i++) {
            JLabel lblhanhdong = new JLabel(hanhdong[i]);
            lblhanhdong.setHorizontalAlignment(SwingConstants.CENTER);
            jpCen.add(lblhanhdong);
        }
        for (int i = 0; i < sizeDmCn; i++) {
            for (int j = 0; j < sizeHanhdong; j++) {
                listCheckBox[i][j] = new JCheckBox();
                listCheckBox[i][j].setHorizontalAlignment(SwingConstants.CENTER);
                jpCen.add(listCheckBox[i][j]);
            }
        }

        // Hiển thị nút thêm
        jpBottom = new JPanel(new FlowLayout());
        jpBottom.setBackground(Color.white);
        jpBottom.setBorder(new EmptyBorder(20, 0, 20, 0));
        
        switch (type) {
            case "create" -> {
                btnAddNhomQuyen = new ButtonCustom("Thêm nhóm quyền", "success", 14);
                btnAddNhomQuyen.addActionListener(this);
                jpBottom.add(btnAddNhomQuyen);
            }
            case "update" -> {
                btnUpdateNhomQuyen = new ButtonCustom("Cập nhật nhóm quyền", "success", 14);
                btnUpdateNhomQuyen.addActionListener(this);
                jpBottom.add(btnUpdateNhomQuyen);
                initUpdate();
            }
            default -> throw new AssertionError();
        }
        
        
        btnHuybo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnHuybo.addActionListener(this);
        
        jpBottom.add(btnHuybo);

        this.add(jpTop, BorderLayout.NORTH);
        this.add(jpLeft, BorderLayout.WEST);
        this.add(jpCen, BorderLayout.CENTER);
        this.add(jpBottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public PhanQuyenDialog(PhanQuyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        initComponents(type);
    }
    
    public PhanQuyenDialog(PhanQuyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type, NhomQuyenDTO nhomquyendto) {
        super(owner, title, modal);
        this.nhomquyenDTO = nhomquyendto;
        this.ctQuyen = ChiTietQuyenDAO.getInstance().selectAll(Integer.toString(nhomquyendto.getManhomquyen()));
        initComponents(type);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddNhomQuyen) {
            ctQuyen = this.getListChiTietQuyen();
            nhomquyenBUS.add(txtTennhomquyen.getText(),ctQuyen);
            JOptionPane.showMessageDialog(null, ctQuyen);
        } else if (e.getSource() == btnHuybo) {
            dispose();
        }
    }

    public ArrayList<ChiTietQuyenDTO> getListChiTietQuyen() {
        ArrayList<ChiTietQuyenDTO> result = new ArrayList<>();
        for (int i = 0; i < sizeDmCn; i++) {
            for (int j = 0; j < sizeHanhdong; j++) {
                if (listCheckBox[i][j].isSelected()) {
                    result.add(new ChiTietQuyenDTO(NhomQuyenDAO.getInstance().getAutoIncrement(), dmcn.get(i).getMachucnang(), mahanhdong[j]));
                }
            }
        }
        return result;
    }

    public void initUpdate() {
        this.txtTennhomquyen.setText(nhomquyenDTO.getTennhomquyen());
        System.out.println(ctQuyen);
        for (ChiTietQuyenDTO k : ctQuyen) {
            for (int i = 0; i < sizeDmCn; i++) {
                for (int j = 0; j < sizeHanhdong; j++) {
                    if(k.getHanhdong().equals(mahanhdong[j]) && k.getMachucnang().equals(dmcn.get(i).getMachucnang())) {
                        listCheckBox[i][j].setSelected(true);
                    }
                }
            }
        }
    }
}
