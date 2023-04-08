/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class InputForm extends JPanel {

    private JLabel lblTitle;
    private JTextField txtForm;
    private JPasswordField txtPass;

    public InputForm(String title) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(0, 10, 10, 10));
        lblTitle = new JLabel(title);
        txtForm = new JTextField();
        this.add(lblTitle);
        this.add(txtForm);
    }
    
    public InputForm(String title, String style){
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        lblTitle = new JLabel(title);
        this.add(lblTitle);
        if(style.equals("password")){
            txtPass = new JPasswordField();
            this.add(txtPass);
        }
    }

    public String getText() {
        return txtForm.getText();
    }

    public void setText(String content) {
        txtForm.setText(content);
    }

    public void setDisable() {
        txtForm.setEnabled(false);
    }

    public void setEditable(boolean value) {
        txtForm.setEditable(value);
    }

}
