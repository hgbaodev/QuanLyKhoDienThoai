package GUI.Component;

import java.awt.*;
import javax.swing.*;

public class MainFunction extends JToolBar {

    public JButton btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel;
    public JSeparator separator1;
    
    String[][] chucnang = {
        {"THÊM","Add_50px(1).png","create"},
        {"XÓA","Delete Trash_50px.png","delete"},
        {"SỬA","Edit_50px.png","update"},
        {"CHI TIẾT","info_50px.png","detail"},
        {"NHẬP EXCEL","xls_50px.png","import"},
        {"XUẤT EXCEL","import_csv_50px.png","export"},
    };

    private void initComponent() {
        this.setBackground(Color.WHITE);
//        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        this.setRollover(true);

        ButtonToolBar[] listButton;
        
        btnAdd = new ButtonToolBar("THÊM", "/icon/Add_50px(1).png");
        this.add(btnAdd);

        btnDelete = new ButtonToolBar("XÓA", "/icon/Delete Trash_50px.png");
        this.add(btnDelete);

        btnEdit = new ButtonToolBar("SỬA", "/icon/Edit_50px.png");
        this.add(btnEdit);

        btnDetail = new ButtonToolBar("CHI TIẾT", "/icon/info_50px.png");
        this.add(btnDetail);

//        separator1 = new JSeparator(SwingConstants.VERTICAL);
//        this.add(separator1);
        btnXuatExcel = new ButtonToolBar("XUẤT EXCEL", "/icon/xls_50px.png");

        btnNhapExcel = new ButtonToolBar("NHẬP EXCEL", "/icon/import_csv_50px.png");
        this.add(btnXuatExcel);
        this.add(btnNhapExcel);
    }

    public MainFunction() {
        initComponent();
    }
}
