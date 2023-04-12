package GUI.Component;

import com.formdev.flatlaf.extras.FlatSVGIcon;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

public class MainFunction extends JToolBar {

    public ButtonToolBar btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel;
    public JSeparator separator1;
    private ArrayList<ButtonToolBar> listBtn = new ArrayList<>();
    
    String[][] chucnang = {
        {"THÊM","Add_50px(1).png","create"},
        {"XÓA","Delete Trash_50px.png","delete"},
        {"SỬA","Edit_50px.png","update"},
        {"CHI TIẾT","info_50px.png","detail"},
        {"NHẬP EXCEL","xls_50px.png","create"},
        {"XUẤT EXCEL","import_csv_50px.png","view"},
    };

    private void initComponent() {
        this.setBackground(Color.WHITE);
//        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        this.setRollover(true);
//
        
        btnAdd = new ButtonToolBar("THÊM", new FlatSVGIcon("./icon/add.svg"));
        this.add(btnAdd);

        btnDelete = new ButtonToolBar("XÓA", new FlatSVGIcon("./icon/delete.svg"));
        this.add(btnDelete);

        btnEdit = new ButtonToolBar("SỬA", new FlatSVGIcon("./icon/edit.svg"));
        this.add(btnEdit);

        btnDetail = new ButtonToolBar("CHI TIẾT", new FlatSVGIcon("./icon/detail.svg"));
        this.add(btnDetail);

        btnXuatExcel = new ButtonToolBar("XUẤT EXCEL", new FlatSVGIcon("./icon/import_excel.svg"));

        btnNhapExcel = new ButtonToolBar("NHẬP EXCEL", new FlatSVGIcon("./icon/export_excel.svg"));
        this.add(btnXuatExcel);
        this.add(btnNhapExcel);
    }

    public MainFunction() {
        initComponent();
    }
}
