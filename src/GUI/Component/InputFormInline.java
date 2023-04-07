/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class InputFormInline extends JPanel {

    private JTextField txt;
    private JLabel lbl;
    private JComboBox cbx;

    public void initComponent(String text) {
        this.setLayout(new FlowLayout(1, 20, 10));
        this.setOpaque(false);
        lbl = new JLabel(text);
        lbl.setPreferredSize(new Dimension(150, 30));
        lbl.putClientProperty("FlatLaf.style", "font: $h3.font");
        this.add(lbl);
    }

    public InputFormInline(String text) {
        initComponent(text);
        txt = new JTextField();
        txt.setPreferredSize(new Dimension(360, 35));
        this.add(txt);
    }

    public InputFormInline(String text, String[] list) {
        initComponent(text);
        cbx = new JComboBox(list);
        cbx.setPreferredSize(new Dimension(360, 35));
        this.add(cbx);
    }

    public void setEditable(boolean value) {
        this.txt.setEditable(value);
    }

    public void setText(String text) {
        this.txt.setText(text);
    }

    public Object getSelectedItem() {
        return cbx.getSelectedItem();
    }

    public int getSelectedIndex() {
        return cbx.getSelectedIndex();
    }

    public void setSelectedIndex(int i) {
        cbx.setSelectedIndex(i);
    }

    public void setSelectedItem(Object a) {
        cbx.setSelectedItem(a);
    }
}
