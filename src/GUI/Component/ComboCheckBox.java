/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;

/**
 *
 * @author robot
 */
public class ComboCheckBox extends JFrame {

    public ComboCheckBox() {
        setTitle("Custom checkbox");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        Vector v = new Vector();

        v.add("Bunda");
        v.add(new JCheckBox("Man Utd", false));
        v.add(new JCheckBox("Fifa", false));
        v.add(new JCheckBox("Madila", false));

        v.add("Vuta");
        v.add(new JCheckBox("Asus", false));
        v.add(new JCheckBox("Mac", false));
        v.add(new JCheckBox("Window", false));

        getContentPane().add(new CustomComboCheck(v));
    }

    public static void main(String[] args) {
        ComboCheckBox cb = new ComboCheckBox();
        cb.setSize(400, 400);
        cb.setLocationRelativeTo(null);
        cb.setVisible(true);
    }
}

class CustomComboCheck extends JComboBox {

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

    private void ourItemSelected() {
        Object selected = getSelectedItem();
            if (selected instanceof JCheckBox) {
            JCheckBox chk = (JCheckBox) selected;
            chk.setSelected(!chk.isSelected());
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

            repaint();

        }

    }

}

class Comborenderer implements ListCellRenderer {

    private JLabel label;

    @Override
    public Component getListCellRendererComponent(JList list, Object val, int index, boolean selected, boolean focused) {

        if (val instanceof Component) {
            Component c = (Component) val;
            if (selected) {
                c.setBackground(list.getSelectionBackground());
                c.setForeground(list.getSelectionForeground());
            } else {
                c.setBackground(list.getBackground());
                c.setForeground(list.getForeground());
            }
            return c;
        } else {
            if (label == null) {
                label = new JLabel(val.toString());
            } else {
                label.setText(val.toString());
            }
            return label;
        }
    }

}
