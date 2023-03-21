package GUI;

import java.awt.*;
import javax.swing.*;

public class QuanLyKho extends JPanel {

    private void initComponent() {
        this.setBackground(new Color(211,244,204));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new FlowLayout(1, 0, 0));
        this.setOpaque(true);

                
        JLabel lbl = new JLabel("QUẢN LÝ KHO");
        lbl.setPreferredSize(new Dimension(100,850));
        this.add(lbl);
    }

    public QuanLyKho() {
        initComponent();
    }

}
