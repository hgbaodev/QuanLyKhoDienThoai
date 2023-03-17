package component;

import java.awt.*;
import javax.swing.*;

public class MainFunction extends JToolBar {

    JButton btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel;
    JSeparator separator1;

    private void initComponent() {
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(450, 120));
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        this.setRollover(true);

        btnAdd = new JButton();
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnAdd.setForeground(new java.awt.Color(102, 255, 51));
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add_50px.png")));
        btnAdd.setText("THÊM");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnAdd);

        btnDelete = new JButton();
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnDelete.setForeground(new java.awt.Color(255, 0, 0));
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete Trash_50px.png")));
        btnDelete.setText("XÓA");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnDelete);

        btnEdit = new JButton();
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnEdit.setForeground(new java.awt.Color(222, 238, 79));
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit_50px.png")));
        btnEdit.setText("SỬA");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnEdit);

        btnDetail = new JButton();
        btnDetail.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnDetail.setForeground(new java.awt.Color(0, 204, 204));
        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/View Details_50px.png")));
        btnDetail.setText("CHI TIẾT");
        btnDetail.setFocusable(false);
        btnDetail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnDetail);

        separator1 = new JSeparator(SwingConstants.VERTICAL);
        this.add(separator1);

        btnNhapExcel = new JButton();
        btnNhapExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhapExcel.setForeground(new java.awt.Color(0, 153, 51));
        btnNhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xls_50px.png"))); // NOI18N
        btnNhapExcel.setText("XUẤT EXCEL");
        btnNhapExcel.setFocusable(false);
        btnNhapExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNhapExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnNhapExcel);

        btnXuatExcel = new JButton();
        btnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatExcel.setForeground(new java.awt.Color(0, 153, 0));
        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/import_csv_50px.png"))); // NOI18N
        btnXuatExcel.setText("NHẬP EXCEL");
        btnXuatExcel.setFocusable(false);
        btnXuatExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnXuatExcel);

    }

    public MainFunction() {
        initComponent();
    }
}
