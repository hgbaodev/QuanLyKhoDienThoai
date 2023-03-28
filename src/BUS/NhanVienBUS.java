/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import GUI.NhanVien;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author robot
 */
public class NhanVienBUS implements ActionListener, DocumentListener{
    private GUI.NhanVien nv;
    private JTextField textField;
    
    public NhanVienBUS(NhanVien nv){
        this.nv = nv;
    }
    
    public NhanVienBUS(JTextField textField) {
        this.textField = textField;
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        System.out.println("Bạn đang nhấn nút "+ btn);
        if(btn.equals("THÊM")){
//           s DonViTinhDialog dvtDialog = new DonViTinhDialog(this, this.nv.owner, "Thêm đơn vị tính", true, "create");
        }
    }

    @Override
    public void insertUpdate(DocumentEvent e) {
        System.out.println("Text field changed: " + textField.getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        System.out.println("Text field changed: " + textField.getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
//        System.out.println("Text field changed: " + textField.getText());
    }

}
