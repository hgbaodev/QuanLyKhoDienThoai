package view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;
import java.awt.event.WindowEvent;

public class Log_In extends javax.swing.JFrame {

    JPanel pnlMain, pnlLogIn;
    JLabel lblImage, lbl1, lbl2, lbl3, lbl4, lbl5, lbl6, lbl7;
    JTextField txtUsername, txtPassword;

    Color FontColor = new Color(96, 125, 139);



    public Log_In() {
        initComponent();

    }

    private void initComponent() {
        this.setSize(new Dimension(1000, 740));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));

        pnlMain = new JPanel();
        pnlMain.setBackground(Color.white);
        pnlMain.setPreferredSize(new Dimension(495, 740));
        pnlMain.setLayout(new FlowLayout(1, 0, 10));

        lbl1 = new JLabel("HỆ THỐNG QUẢN LÝ");
        lbl1.setPreferredSize(new Dimension(400, 100));
        lbl1.setFont(new Font("Segoe UI Black", Font.BOLD, 36));
        pnlMain.add(lbl1);

        lbl2 = new JLabel("KHO Ô TÔ");
        lbl2.setPreferredSize(new Dimension(250, 50));
        lbl2.setFont(new Font("Segoe UI Black", Font.BOLD, 48));
        pnlMain.add(lbl2);

        lbl3 = new JLabel("Đăng nhập để tiếp tục");
        lbl3.setPreferredSize(new Dimension(400, 50));
        lbl3.setFont(new Font("Segoe UI Light", Font.BOLD, 24));
        pnlMain.add(lbl3);

        lbl4 = new JLabel("Tài khoản");
        lbl4.setPreferredSize(new Dimension(400,50));
        lbl4.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
        pnlMain.add(lbl4);

        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(400, 35));
        txtUsername.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pnlMain.add(txtUsername);

        lbl5 = new JLabel("Mật khẩu");
        lbl5.setPreferredSize(new Dimension(400, 50));
        lbl5.setFont(new Font("Segoe UI Light", Font.BOLD, 20));
        pnlMain.add(lbl5);

        txtPassword = new JTextField();
        txtPassword.setPreferredSize(new Dimension(400, 35));
        txtPassword.setFont(new Font("Segoe UI", Font.PLAIN, 18));
        pnlMain.add(txtPassword);

        lbl6 = new JLabel("ĐĂNG NHẬP");
        lbl6.setPreferredSize(new Dimension(150, 40));
        lbl6.setFont(new Font("Segoe UI", Font.BOLD, 24));
        lbl6.setForeground(Color.white);

        pnlLogIn = new JPanel();
        pnlLogIn.setBackground(Color.black);
        pnlLogIn.setPreferredSize(new Dimension(400, 40));
        pnlLogIn.setLayout(new FlowLayout(1, 0, 0));
        
        pnlLogIn.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                pnlLogInMouseEntered(evt);
            }

            @Override
            public void mousePressed(java.awt.event.MouseEvent evt) {
                pnlLogInMousePressed(evt);
            }
            
            @Override
            public void mouseExited(java.awt.event.MouseEvent evt) {
                pnlLogInMouseExited(evt);            }
        });
        pnlLogIn.add(lbl6);

        lbl7 = new JLabel("Quên mật khẩu", JLabel.RIGHT);
        lbl7.setPreferredSize(new Dimension(400, 50));
        lbl7.setFont(new Font("Segoe UI", Font.ITALIC, 18));
        pnlMain.add(lbl7);

        pnlMain.add(pnlLogIn);

        this.add(pnlMain, BorderLayout.EAST);

        lblImage = new JLabel();
        lblImage.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/login.jpg")));
        lblImage.setPreferredSize(new Dimension(500, 740));
        this.add(lblImage, BorderLayout.WEST);

    }

    public void checkLogin() {
        String usernameCheck = txtUsername.getText();
        String passwordCheck = txtPassword.getText();
        if (usernameCheck.equals("") || passwordCheck.equals("")) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập thông tin đầy đủ", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        }
        else if(usernameCheck.equals("admin") && passwordCheck.equals("123")) {
            Main main = new Main();
            this.setVisible(false);
            main.setVisible(true);
            
        }
        else {
            JOptionPane.showMessageDialog(this, "Sai tai khoan hoac mat khau!", "Cảnh báo!", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void pnlLogInMousePressed(java.awt.event.MouseEvent evt) {
        
        checkLogin();
    }
    private void pnlLogInMouseEntered(java.awt.event.MouseEvent evt) {
        pnlLogIn.setBackground(FontColor);
        pnlLogIn.setForeground(Color.black);

    }
    
        private void pnlLogInMouseExited(java.awt.event.MouseEvent evt) {
        
        pnlLogIn.setBackground(Color.black);
        pnlLogIn.setForeground(Color.white);
        }
        
    public static void main(String[] args) {
        FlatLightLaf.setup();

        Log_In login = new Log_In();
        login.setVisible(true);
    }
}
