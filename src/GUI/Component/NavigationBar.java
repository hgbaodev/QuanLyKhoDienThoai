package GUI.Component;

import javax.swing.JFrame;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class NavigationBar extends JPanel implements MouseListener{


    private JFrame f;
    JLabel lbl;
    JPanel control, btnExit, btnMinimize;
    Color MainColor = new Color(250, 250, 250);

    public NavigationBar(JFrame f) {
        initComponent();
        this.f = f;
    }

    private void initComponent() {
        this.setLayout(new BorderLayout(0,0));
        this.setBackground(Color.black);
        
        lbl = new JLabel("QUẢN LÝ KHO HÀNG TỔNG HỢP");
//        lbl.setPreferredSize(new Dimension(100,30));
        lbl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbl.setForeground(MainColor);
        this.add(lbl, BorderLayout.CENTER);
        
        btnExit = new JPanel();
        btnExit.setPreferredSize(new Dimension(20, 20));
        btnExit.setForeground(Color.red);
                btnExit.setBackground(Color.red);
        btnExit.addMouseListener(this);
        this.add(btnExit, BorderLayout.EAST);
        
        btnMinimize = new JPanel();
        btnMinimize.setPreferredSize(new Dimension(20, 20));
        btnMinimize.setForeground(Color.yellow);
        this.add(btnMinimize);

        control = new JPanel();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(e.getSource()==btnExit) {
            f.dispose();
        }
        if(e.getSource()==btnMinimize)
            f.setExtendedState(JFrame.NORMAL);
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    

}
