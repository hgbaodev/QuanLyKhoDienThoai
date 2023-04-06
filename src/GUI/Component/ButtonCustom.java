package GUI.Component;

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
public class ButtonCustom extends JButton {

    public ButtonCustom(String text, String type, int fontsize) {
        this.setText(text);
        String color = null;
        switch (type) {
            case "success":
                color = "Actions.Blue";
                break;
            case "danger":
                color = "Actions.Red";
                break;
            case "warning":
                color = "Actions.Yellow";
                break;
            case "excel":
                color = "Actions.Green";
                break;
            default:
                throw new AssertionError();
        }
        this.setBackground(javax.swing.UIManager.getDefaults().getColor(color));
        this.setFont(new java.awt.Font("Segoe UI", 0, fontsize)); // NOI18N
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setBorder(null);
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(150, 35));
    }

    public ButtonCustom(String text, String colorBtn, int fontsize, String linkIcon) {
        this.setText(text);
        String color = null;
        switch (colorBtn) {
            case "blue":
                color = "Actions.Blue";
                break;
            case "red":
                color = "Actions.Red";
                break;
            case "yellow":
                color = "Actions.Yellow";
                break;
            case "green":
                color = "Actions.Green";
                break;
            default:
                throw new AssertionError();
        }
        this.setBackground(javax.swing.UIManager.getDefaults().getColor(color));
        this.putClientProperty("JButton.buttonType", "roundRect");
        this.putClientProperty("JButton.buttonType", "borderless");
        this.setFont(new java.awt.Font("Segoe UI", 0, fontsize)); // NOI18N
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(150, 35));
        this.setIcon(new javax.swing.ImageIcon(getClass().getResource(linkIcon)));
    }

    public void setVisible(Boolean value) {
        this.setVisible(value);
    }
}
