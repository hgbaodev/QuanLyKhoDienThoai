package component;

import GUI.SanPham;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class MainFunction extends JToolBar{
   public SanPham a;
   public JButton btnAdd, btnDelete, btnEdit, btnDetail, btnNhapExcel, btnXuatExcel;
   public JSeparator separator1;
    
    
    Color textButtonColor = new Color(1, 88, 155);
    Color backgroundButtonColor = new Color(255,255,255);

    private void initComponent() {
        this.setBackground(Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Chức năng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14)));
        this.setRollover(true);

        btnAdd = new JButton();
        btnAdd.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnAdd.setForeground(textButtonColor);
        btnAdd.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Add_50px(1).png")));
        btnAdd.setText("THÊM");
        btnAdd.setFocusable(false);
        btnAdd.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnAdd.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnAdd);

        btnDelete = new JButton();
        btnDelete.setFont(new java.awt.Font("Segoe UI", 1, 14));
                btnDelete.setForeground(textButtonColor);
        btnDelete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Delete Trash_50px.png")));
        btnDelete.setText("XÓA");
        btnDelete.setFocusable(false);
        btnDelete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDelete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnDelete);

        btnEdit = new JButton();
        btnEdit.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnEdit.setForeground(textButtonColor);
        btnEdit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/Edit_50px.png")));
        btnEdit.setText("SỬA");
        btnEdit.setFocusable(false);
        btnEdit.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnEdit.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnEdit);

        btnDetail = new JButton();
        btnDetail.setFont(new java.awt.Font("Segoe UI", 1, 14));
        btnDetail.setForeground(textButtonColor);
        btnDetail.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/View Details_50px.png")));
        btnDetail.setText("CHI TIẾT");
        btnDetail.setFocusable(false);
        btnDetail.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnDetail.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnDetail);

//        separator1 = new JSeparator(SwingConstants.VERTICAL);
//        this.add(separator1);

        btnNhapExcel = new JButton();
        btnNhapExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnNhapExcel.setForeground(textButtonColor);
        btnNhapExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/xls_50px.png"))); // NOI18N
        btnNhapExcel.setText("XUẤT EXCEL");
        btnNhapExcel.setFocusable(false);
        btnNhapExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnNhapExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnNhapExcel);

        btnXuatExcel = new JButton();
        btnXuatExcel.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnXuatExcel.setForeground(textButtonColor);
        btnXuatExcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/import_csv_50px.png"))); // NOI18N
        btnXuatExcel.setText("NHẬP EXCEL");
        btnXuatExcel.setFocusable(false);
        btnXuatExcel.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnXuatExcel.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        this.add(btnXuatExcel);
       
    }
    
    public void setupEvent() {
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                a.add();
            }
        });
    }
    
    public MainFunction() {
        initComponent();
    }

    public MainFunction(SanPham a) {
        initComponent();
        this.a = a;
        setupEvent();      
    }
}
