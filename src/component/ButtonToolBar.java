/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.Color;
import javax.swing.JButton;

/**
 *
 * @author Tran Nhat Sinh
 */
public class ButtonToolBar extends JButton{
    public ButtonToolBar(String text, String linkicon) {
        this.setFont(new java.awt.Font("Segoe UI", 1, 14));
        this.setForeground(new Color(1, 88, 155));
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource(linkicon)));
        this.setText(text);
        this.setFocusable(false);
        this.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        this.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
    }
}
