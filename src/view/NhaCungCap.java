package view;

import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class NhaCungCap extends JPanel {

    private void initComponent() {
        this.setBackground(new Color(244,234,244));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setOpaque(true);

                
        JLabel lbl = new JLabel("NHA CUNG CẤP");
        lbl.setPreferredSize(new Dimension(100,850));
        this.add(lbl);
    }

    public NhaCungCap() {
        initComponent();
    }

}