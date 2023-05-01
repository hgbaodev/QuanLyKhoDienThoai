/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.accessibility.AccessibleContext;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.event.EventListenerList;
import javax.swing.plaf.ComponentUI;

/**
 *
 * @author Tran Nhat Sinh
 */
public final class InputForm extends JPanel {

    private JLabel lblTitle;
    private JTextField txtForm;
    private JPasswordField txtPass;

    public InputForm() {
    }

    public InputForm(String title) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(0, 10, 5, 10));
        this.setPreferredSize(new Dimension(100, 100));
        lblTitle = new JLabel(title);
        txtForm = new JTextField();
        this.add(lblTitle);
        this.add(txtForm);
    }

    public InputForm(String title, String style) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 5, 10));
        lblTitle = new JLabel(title);
        this.add(lblTitle);
        if (style.equals("password")) {
            txtPass = new JPasswordField();
            this.add(txtPass);
        }
    }

    public InputForm(String title, int w, int h) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
//        this.setBorder(new EmptyBorder(0, 10, 5, 10));
        this.setPreferredSize(new Dimension(w, h));
        lblTitle = new JLabel(title);
        txtForm = new JTextField();
        this.add(lblTitle);
        this.add(txtForm);
    }
    
    public void setTitle(String title) {
        this.lblTitle.setText(title);
    }

    
    public String getPass() {
        return txtPass.getText();
    }

    public JLabel getLblTitle() {
        return lblTitle;
    }

    public void setLblTitle(JLabel lblTitle) {
        this.lblTitle = lblTitle;
    }

    public JTextField getTxtForm() {
        return txtForm;
    }

    public void setTxtForm(JTextField txtForm) {
        this.txtForm = txtForm;
    }

    public JPasswordField getTxtPass() {
        return txtPass;
    }

    public void setTxtPass(JPasswordField txtPass) {
        this.txtPass = txtPass;
    }

    public ComponentUI getUi() {
        return ui;
    }

    public void setUi(ComponentUI ui) {
        this.ui = ui;
    }

    public EventListenerList getListenerList() {
        return listenerList;
    }

    public void setListenerList(EventListenerList listenerList) {
        this.listenerList = listenerList;
    }

    @Override
    public AccessibleContext getAccessibleContext() {
        return accessibleContext;
    }

    public void setAccessibleContext(AccessibleContext accessibleContext) {
        this.accessibleContext = accessibleContext;
    }

    public void setPass(String s) {
        txtPass.setText(s);
    }

    public String getText() {
        return txtForm.getText();
    }

    public void setText(String content) {
        txtForm.setText(content);
    }

    public void setDisablePass() {
        txtPass.setEnabled(false);
    }

    public void setDisable() {
        txtForm.setEnabled(false);
    }

    public void setEditable(boolean value) {
        txtForm.setEditable(value);
    }

    public String getDocument() {
        return txtForm.getText();
    }

}
