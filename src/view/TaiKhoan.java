package view;

import java.awt.*;
import javax.swing.*;

public class TaiKhoan extends JPanel {

    private void initComponent() {
        this.setBackground(new Color(244,234,244));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setOpaque(true);

                
        JLabel lbl = new JLabel("TÀI KHOẢN");
        lbl.setPreferredSize(new Dimension(100,850));
        this.add(lbl);
    }

    public TaiKhoan() {
        initComponent();
    }

}