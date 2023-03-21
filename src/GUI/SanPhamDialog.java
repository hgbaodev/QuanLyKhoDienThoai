package GUI;

import component.ButtonCustom;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import component.HeaderTitle;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
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

    public void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Thêm sản phẩm");
        this.setSize(new Dimension(810, 330));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle("THÊM SẢN PHẨM MỚI");
        pnmain = new JPanel(new GridLayout(2, 3, 20, 0));
        pnmain.setBackground(Color.white);

        for (int i = 0; i < 6; i++) {
            JPanel inputform = new JPanel(new GridLayout(2, 1));
            inputform.setBackground(Color.white);
            inputform.setBorder(new EmptyBorder(10, 10, 10, 10));
            JLabel lbltext = new JLabel("Mã sản phẩm");
            JTextField txtinput = new JTextField();
            inputform.add(lbltext);
            inputform.add(txtinput);
            pnmain.add(inputform);
        }

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10,0,10,0));
        pnbottom.setBackground(Color.white);
        ButtonCustom btnThemSanPham = new ButtonCustom("Thêm sản phẩm","sucess",14);
        ButtonCustom btnHuyBo = new ButtonCustom("Huỷ bỏ","danger",14);
        
        pnbottom.add(btnThemSanPham);
        pnbottom.add(btnHuyBo);
        
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);

        this.setVisible(true);
    }

    public SanPhamDialog() {
        initComponents();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        new SanPhamDialog();
    }
}
