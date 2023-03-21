/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class PhanQuyen extends JFrame {

    private JLabel lblTennhomquyen;
    private JTextField txtTennhomquyen;
    private JPanel jpTop;

    public void initComponents() {
        this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("Phân quyền");
        this.setSize(new Dimension(1200, 750));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));
        jpTop = new JPanel(new BorderLayout(20,10));
        jpTop.setBorder(new EmptyBorder(20,20,20,20));
        jpTop.setBackground(Color.WHITE);
        lblTennhomquyen = new JLabel("Tên nhóm quyền");
        txtTennhomquyen = new JTextField();
        txtTennhomquyen.setPreferredSize(new Dimension(150,35));
        jpTop.add(lblTennhomquyen,BorderLayout.WEST);
        jpTop.add(txtTennhomquyen,BorderLayout.CENTER);

        this.add(jpTop,BorderLayout.NORTH);
        this.setVisible(true);
    }

    public PhanQuyen() {
        initComponents();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        UIManager.setLookAndFeel(new FlatLightLaf());
        new PhanQuyen();
    }
}
