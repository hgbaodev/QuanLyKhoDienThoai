/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;

import DAO.NhanVienDAO;
import GUI.Panel.NhanVien;
import GUI.Dialog.NhanVienDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author robot
 */
public class NhanVienBUS implements ActionListener, DocumentListener {

    private GUI.Panel.NhanVien nv;
    private JTextField textField;
    public ArrayList<DTO.NhanVien> listNv = NhanVienDAO.getInstance().selectAll();

    public NhanVienBUS(NhanVien nv) {
        this.nv = nv;
    }

    public NhanVienBUS(JTextField textField) {
        this.textField = textField;
    }
    
    public ArrayList<DTO.NhanVien> getAll(){
        return this.listNv;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String btn = e.getActionCommand();
        System.out.println("Bạn đang nhấn nút " + btn);

        switch (btn) {
            case "THÊM":
                NhanVienDialog nvthem = new NhanVienDialog(this, nv.owner, true, "Thêm nhân viên", "create");
                break;
            case "SỬA":
                NhanVienDialog nvsua = new NhanVienDialog(this, nv.owner, true, "Sửa nhân viên", "update");
                break;
            case "XÓA":

                break;
            case "NHẬP EXCEL":

                break;
            case "XUẤT EXCEL":

                break;

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
