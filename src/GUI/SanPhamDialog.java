package GUI;

import component.ButtonCustom;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import component.HeaderTitle;
import component.InputForm;
import component.SelectForm;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Tran Nhat Sinh
 */
public class SanPhamDialog extends JFrame {

    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThemSanPham, btnCapNhat, btnHuyBo;

    public void initComponents(String type) {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Thêm sản phẩm");
        this.setSize(new Dimension(810, 330));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle("THÊM SẢN PHẨM MỚI");
        pnmain = new JPanel(new GridLayout(2, 3, 20, 0));
        pnmain.setBackground(Color.white);

        for (int i = 0; i < 5; i++) {
            InputForm input = new InputForm("Mã sản phẩm");
            pnmain.add(input);
        }

        String[] dvt = {"Cái", "Thùng", "Hộp"};
        SelectForm slfDvt = new SelectForm("Đơn vị tính", dvt);
        pnmain.add(slfDvt);
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThemSanPham = new ButtonCustom("Thêm sản phẩm", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        pnbottom.add(btnThemSanPham);
        pnbottom.add(btnCapNhat);
        pnbottom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public SanPhamDialog(String type) {
        initComponents(type);
        switch (type) {
            case "create" -> {
                this.btnThemSanPham.setVisible(true);
                this.btnCapNhat.setVisible(false);
            }
            case "update" -> {
                this.btnThemSanPham.setVisible(false);
                this.btnCapNhat.setVisible(true);
                // Set thông tin sản phẩm lên form
            }
            default -> throw new AssertionError();
        }
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        new SanPhamDialog("create");
    }

}
