/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import DTO.ChiTietQuyenDTO;
import DTO.DanhMucChucNangDTO;
import com.formdev.flatlaf.FlatLightLaf;
import component.ButtonCustom;
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
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
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
    private ButtonCustom btnAddNhomQuyen, btnHuybo;
    private ArrayList<ChiTietQuyenDTO> ctQuyen;
    private int sizeDmCn, sizeHanhdong;
    private ArrayList<DanhMucChucNangDTO> dmcn;

    public void initComponents() {
        dmcn = new ArrayList<>();

        // Fake data (Phần này sẽ đổ từ bảng danh mục chức năng lên)
        dmcn.add(new DanhMucChucNangDTO("kho", "Quản lý kho"));
        dmcn.add(new DanhMucChucNangDTO("nhaphang", "Quản lý nhập hàng"));
        dmcn.add(new DanhMucChucNangDTO("xuathang", "Quản lý xuất hàng"));
        dmcn.add(new DanhMucChucNangDTO("chuyenhang", "Quản lý chuyển hàng"));
        dmcn.add(new DanhMucChucNangDTO("kiemke", "Quản lý kiểm kê"));
        dmcn.add(new DanhMucChucNangDTO("sanpham", "Quản lý sản phẩm"));
        dmcn.add(new DanhMucChucNangDTO("loaisanpham", "Quản lý loại sản phẩm"));
        dmcn.add(new DanhMucChucNangDTO("donvitinh", "Quản lý đơn vị tính"));
        dmcn.add(new DanhMucChucNangDTO("nhanvien", "Quản lý nhân viên"));
        dmcn.add(new DanhMucChucNangDTO("taikhoan", "Quản lý tài khoản"));
        dmcn.add(new DanhMucChucNangDTO("nhacungcap", "Quản lý nhà cung cấp"));
        String[] hanhdong = {"Xem", "Tạo mới", "Cập nhật", "Xoá"};

        this.setTitle("Phân quyền");
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
        btnAddNhomQuyen = new ButtonCustom("Thêm nhóm quyền", "success", 14);
        btnHuybo = new ButtonCustom("Huỷ bỏ", "danger", 14);
        btnAddNhomQuyen.addActionListener(this);
        btnHuybo.addActionListener(this);
        jpBottom.add(btnAddNhomQuyen);
        jpBottom.add(btnHuybo);

        this.add(jpTop, BorderLayout.NORTH);
        this.add(jpLeft, BorderLayout.WEST);
        this.add(jpCen, BorderLayout.CENTER);
        this.add(jpBottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public PhanQuyenDialog(PhanQuyen jpPhanQuyen, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        initComponents();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnAddNhomQuyen) {
            ctQuyen = new ArrayList<>();
            String[] mahanhdong = {"view", "create", "update", "delete"};
            for (int i = 0; i < sizeDmCn; i++) {
                for (int j = 0; j < sizeHanhdong; j++) {
                    if (listCheckBox[i][j].isSelected()) {
                        ctQuyen.add(new ChiTietQuyenDTO(1, dmcn.get(i).getMachucnang(), mahanhdong[j]));
                    }
                }
            }
            JOptionPane.showMessageDialog(null, ctQuyen);
        } else if (e.getSource() == btnHuybo) {
            dispose();
        }
    }

    public void initUpdate(ArrayList<ChiTietQuyenDTO> ctquyen) {
        
    }
}
