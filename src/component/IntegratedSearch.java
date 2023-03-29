package component;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class IntegratedSearch extends JPanel {

    public JComboBox<String> cbxChoose;
    public JButton btnReset;
    public JTextField txtSearchForm;

    private void initComponent(String str[]) {

        this.setBackground(Color.WHITE);
        this.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Tìm kiếm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N
        BoxLayout bx = new BoxLayout(this, BoxLayout.Y_AXIS);
        this.setLayout(bx);

        JPanel jpSearch = new JPanel(new BorderLayout(5,10));
        jpSearch.setBorder(new EmptyBorder(15,15,15,15));
        jpSearch.setBackground(Color.white);
        cbxChoose = new JComboBox();
        cbxChoose.setModel(new DefaultComboBoxModel<>(str));
        cbxChoose.setPreferredSize(new Dimension(130, 0));
        cbxChoose.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jpSearch.add(cbxChoose,BorderLayout.WEST);

        txtSearchForm = new JTextField();
        txtSearchForm.setFont(new java.awt.Font("Segoe UI", 0, 16));
        jpSearch.add(txtSearchForm);

        btnReset = new JButton("Làm mới");
        btnReset.setFont(new java.awt.Font("Segoe UI", Font.BOLD, 14));
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset_30px.png")));
        btnReset.setPreferredSize(new Dimension(130, 0));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent e) {
                btnResetActionPerformed(e);
            }
        });
        jpSearch.add(btnReset,BorderLayout.EAST);
        this.add(jpSearch);
    }

    public IntegratedSearch(String str[]) {
        initComponent(str);
    }

    private void btnResetActionPerformed(java.awt.event.ActionEvent e) {
        txtSearchForm.setText("");
        cbxChoose.setSelectedIndex(0);
    }
}
