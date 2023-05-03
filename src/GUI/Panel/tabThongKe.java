package GUI.Panel;

import GUI.Component.ButtonCustom;
import GUI.Component.InputDate;
import GUI.Component.InputForm;
import GUI.Component.PanelBorderRadius;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class tabThongKe extends JPanel {

    PanelBorderRadius left, center;
    InputForm inputForm;
    InputDate start_date, end_date;
    ButtonCustom export;

    public tabThongKe(String title, String type) {
        init(title, type);
    }

    public void init(String title, String type) {
        this.setLayout(new BorderLayout(10, 10));
        this.setOpaque(false);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));

        left = new PanelBorderRadius();
        left.setPreferredSize(new Dimension(300, 100));
        left.setLayout(new BorderLayout());
        left.setBorder(new EmptyBorder(0, 0, 0, 5));

        JPanel left_content = new JPanel(new GridLayout(4, 1));
        left_content.setPreferredSize(new Dimension(300, 360));
        left.add(left_content, BorderLayout.NORTH);

        inputForm = new InputForm(title);
        inputForm.getTxtForm().putClientProperty("JTextField.showClearButton", true);
        
        start_date = new InputDate("Từ ngày");
        end_date = new InputDate("Đến ngày");
        
        JPanel btn_layout = new JPanel(new BorderLayout());
        btn_layout.setPreferredSize(new Dimension(30, 36));
        btn_layout.setBorder(new EmptyBorder(20, 10, 0, 10));
        btn_layout.setBackground(Color.white);
        
        export = new ButtonCustom("Xuất Excel", "excel", 14);
        btn_layout.add(export, BorderLayout.NORTH);

        left_content.add(inputForm);
        left_content.add(start_date);
        left_content.add(end_date);
        left_content.add(btn_layout);

        center = new PanelBorderRadius();

        this.add(left, BorderLayout.WEST);
        this.add(center, BorderLayout.CENTER);
    }
}
