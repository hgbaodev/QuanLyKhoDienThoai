/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import GUI.Component.CheckListItem;
import GUI.Component.CheckListRenderer;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author robot
 */
public class SelectImei extends JDialog{
    
    public SelectImei(JFrame owner, String title, boolean modal){
        super(owner, title, modal);
        init();
        setVisible(true);
    }
    public void init(){
        setSize(new Dimension(100,300));
        setLayout(new BorderLayout());
        JList list = new JList(new CheckListItem[] { new CheckListItem("apple"),
                new CheckListItem("orange"), new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("mango"),
                new CheckListItem("paw paw"), new CheckListItem("banana") });
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());// Get index of item
                // clicked
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected()); // Toggle selected state
                list.repaint(list.getCellBounds(index, index));// Repaint cell
            }
        });
        this.getContentPane().add(new JScrollPane(list));
        setLocationRelativeTo(null);
    }
    
}
