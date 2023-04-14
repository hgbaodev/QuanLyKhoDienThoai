/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;

/**
 *
 * @author robot
 */
public final class CustomComboCheck extends JComboBox {

    JTextArea text;
    
    public CustomComboCheck(Vector v) {

        super(v);

        setRenderer(new Comborenderer());

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ourItemSelected();
            }
        });
    }
    
    public CustomComboCheck(Vector v, JTextArea text) {
        super(v);
        this.text = text;
        setRenderer(new Comborenderer());

        addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                ourItemSelectedText();
            }
        });
    }

    private void ourItemSelected() {
        Object selected = getSelectedItem();
            if (selected instanceof JCheckBox) {
            JCheckBox chk = (JCheckBox) selected;
            chk.setSelected(!chk.isSelected());
            repaint();
            if (chk.isSelected() == false) {
                chk.setSelected(true);
                Object[] selections = chk.getSelectedObjects();
                if (selections != null) {
                    for (Object lastItem : selections) {
                        JOptionPane.showMessageDialog(null, "Uncheck: "+lastItem.toString());
                    }
                }
                chk.setSelected(false);
            }
            
            Object[] selections = chk.getSelectedObjects();
            if (selections != null) {
                for (Object lastItem : selections) {
                    JOptionPane.showMessageDialog(null, lastItem.toString());
                }
            }
        }

    }
    
    private void ourItemSelectedText() {
        Object selected = getSelectedItem();
            if (selected instanceof JCheckBox) {
            JCheckBox chk = (JCheckBox) selected;
            chk.setSelected(!chk.isSelected());
            repaint();
            if (chk.isSelected() == false) {
                chk.setSelected(true);
                Object[] selections = chk.getSelectedObjects();
                if (selections != null) {
                    for (Object lastItem : selections) {
                        String txt = text.getText().replaceAll("("+lastItem.toString()+")\n", "");
                        this.text.setText(txt);
                    }
                }
                chk.setSelected(false);
            }
            
            Object[] selections = chk.getSelectedObjects();
            if (selections != null) {
                for (Object lastItem : selections) {
                    this.text.append(lastItem.toString()+"\n");
                }
            }
        }

    }

}
