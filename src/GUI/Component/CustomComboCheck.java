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
import javax.swing.JTextArea;

/**
 *
 * @author robot
 */
public final class CustomComboCheck extends JComboBox {
    private Object firstItem;
    JTextArea text;

    public CustomComboCheck(Vector v, JTextArea text) {
        super(v);
        this.text = text;
        setRenderer(new ComboRenderer());
        addActionListener((ActionEvent event) -> {
            ourItemSelectedText();
        });
        if (!v.isEmpty()) {
        firstItem = v.get(0);
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
                        String txt = text.getText().replaceAll("(" + lastItem.toString() + ")\n", "");
                        this.text.setText(txt);
                    }
                }
                chk.setSelected(false);
            }

            Object[] selections = chk.getSelectedObjects();
            if (selections != null) {
                for (Object lastItem : selections) {
                    this.text.append(lastItem.toString() + "\n");
                }
            }
        }
        if (!getSelectedItem().equals(firstItem)) {
            setSelectedItem(firstItem);
            repaint();
        }

    }

}
