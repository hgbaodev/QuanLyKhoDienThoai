/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public class InputForm extends JPanel {

    private JLabel lblTitle;
    private JTextField txtForm;

    public InputForm(String title) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblTitle = new JLabel(title);
        txtForm = new JTextField();
        
        this.add(lblTitle);
        this.add(txtForm);
    }
    
    public String getText() {
        return txtForm.getText();
    }
}
