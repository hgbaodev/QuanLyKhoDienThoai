
package component;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;

public class ButtonMajor extends JButton{
    public ButtonMajor(String text, String linkicon) {
        this.setFont(new java.awt.Font("Segoe UI", 1, 14));
        this.setForeground(new Color(255,255,255));
        this.setBackground(new Color(1, 88, 155));
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource(linkicon)));
        this.setText(text);
        this.setFocusable(false);
//        this.setPreferredSize(new Dimension(120,35));
    }
}
