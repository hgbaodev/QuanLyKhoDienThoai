package GUI.Component;

import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Cursor;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class ButtonCustom extends JButton {

    public void initComponent(String type, String text, int fontsize, int width, int height) {
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
            case "return":
                color = "Actions.Orange";
                break;
            case "ok":
                color = "Actions.Black";
                break;
            default:
                color = "Actions.White";
        }
        this.putClientProperty("JButton.buttonType", "roundRect");
        this.putClientProperty("JButton.buttonType", "borderless");
        this.setBackground(javax.swing.UIManager.getDefaults().getColor(color));
        this.setFont(new java.awt.Font(FlatRobotoFont.FAMILY, 0, fontsize));
        this.setForeground(new java.awt.Color(255, 255, 255));
        this.setCursor(new Cursor(Cursor.HAND_CURSOR));
        this.setPreferredSize(new Dimension(width, height));
    }

    public ButtonCustom(String text, String type, int fontsize) {
        initComponent(type, text, fontsize, 150, 40);
    }

    public ButtonCustom(String text, String type, int fontsize, int w, int h) {
        initComponent(type, text, fontsize, w, h);
    }

    public ButtonCustom(String text, String type, int fontsize, String linkIcon) {
        initComponent(type, text, fontsize, 150, 40);
        this.setIcon(new ImageIcon(getClass().getResource(linkIcon)));
    }

    public ButtonCustom(String text, String type, int fontsize, String linkIcon, int width, int height) {
        initComponent(type, text, fontsize, width, height);
        this.setIcon(new ImageIcon(getClass().getResource(linkIcon)));
    }

    public void setVisible(Boolean value) {
        this.setVisible(value);
    }
}
