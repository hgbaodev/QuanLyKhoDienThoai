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
        this.setPreferredSize(new Dimension(650, 120));
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        this.setLayout(new FlowLayout(1, 20, 20));

        cbxChoose = new JComboBox();
        cbxChoose.setModel(new DefaultComboBoxModel<>(str));
        cbxChoose.setPreferredSize(new Dimension(100, 40));
        cbxChoose.setFont(new java.awt.Font("Segoe UI", 0, 14));
        this.add(cbxChoose);

        txtSearchForm = new JTextField();
        txtSearchForm.setFont(new java.awt.Font("Segoe UI", 0, 16));
        txtSearchForm.setPreferredSize(new Dimension(300, 40));
        this.add(txtSearchForm);

        btnReset = new JButton("Làm mới");
        btnReset.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset_30px.png")));
        btnReset.setPreferredSize(new Dimension(130, 40));
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
