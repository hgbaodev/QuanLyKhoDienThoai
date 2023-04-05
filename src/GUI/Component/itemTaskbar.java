package GUI.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class itemTaskbar {

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);

    public itemTaskbar(JPanel pnlItem, JLabel lblIcon, JLabel pnlContent, String linkIcon, String content, JPanel pnlMain) {
        pnlItem = new JPanel();
        pnlItem.setLayout(new FlowLayout(1, 10, 7));
        pnlItem.setPreferredSize(new Dimension(250, 45));
        pnlItem.setBackground(DefaultColor);

        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(30, 30));
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("linkIcon")));

        pnlItem.add(lblIcon);

        pnlContent = new JLabel("content");
        pnlContent.setPreferredSize(new Dimension(170, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent.setForeground(FontColor);

        pnlItem.add(pnlContent);

        pnlMain.add(pnlItem);
    }
    
        public void setVisible(Boolean value) {
        this.setVisible(value);
    }
}
