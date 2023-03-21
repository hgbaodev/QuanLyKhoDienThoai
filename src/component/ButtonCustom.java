package component;


import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.JButton;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Tran Nhat Sinh
 */
public class ButtonCustom extends JButton{ 
    public ButtonCustom(String text, String type, int fontsize) {
        this.setText(text);
        String color = null;
        switch (type) {
            case "sucess":
                color = "Actions.Blue";
                break;
            case "danger":
                color = "Actions.Red";
                break;
            default:
                throw new AssertionError();
        }
        this.setBackground(javax.swing.UIManager.getDefaults().getColor(color));
        this.setFont(new java.awt.Font("Segoe UI", 0, fontsize)); // NOI18N
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setBorder(null);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(150,35));
    }
}
