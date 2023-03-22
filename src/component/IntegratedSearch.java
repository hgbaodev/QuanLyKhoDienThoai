package component;

import java.awt.*;
import javax.swing.*;

public class IntegratedSearch extends JPanel {

    JComboBox<String> cbxChoose;
    String str[] = {"Tất cả", "Mã số", "Họ tên", "Năm sinh"};
    JButton btnReset;
    JTextField txtSearchForm;

    private void initComponent() {

        this.setBackground(Color.WHITE);
        this.setSize(new Dimension(530,100));
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        this.setLayout(new FlowLayout(1, 10, 10));

        cbxChoose = new JComboBox();
        cbxChoose.setModel(new DefaultComboBoxModel<>(str));
        cbxChoose.setPreferredSize(new Dimension(this.getWidth()/5, 40));
        cbxChoose.setFont(new java.awt.Font("Segoe UI", 0, 14));
        this.add(cbxChoose);

        txtSearchForm = new JTextField();
        txtSearchForm.setFont(new java.awt.Font("Segoe UI", 0, 16));
        txtSearchForm.setPreferredSize(new Dimension(3*this.getWidth()/5, 40));
        this.add(txtSearchForm);

        btnReset = new JButton("Làm mới");
        btnReset.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset_30px.png")));
        btnReset.setPreferredSize(new Dimension(9/5*this.getWidth()/5, 40));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                btnResetActionPerformed(e);
            }
        });

        this.add(btnReset);

    }

    public IntegratedSearch() {
        initComponent();
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent e) {
        txtSearchForm.setText("");
        cbxChoose.setSelectedIndex(0);
    }
}
