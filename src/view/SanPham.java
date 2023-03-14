package view;

import java.awt.*;
import javax.swing.*;
import swing.PanelBorderRadius;

public class SanPham extends JPanel {

    PanelBorderRadius contentTop, contentCenter;
    JTable tableSanPham;
    
    Color BackgroundColor = new Color(240, 247, 250);

    private void initComponent() {
        this.setBackground(BackgroundColor);
        this.setLayout(new FlowLayout(1, 0, 15));
        this.setOpaque(true);

        contentTop = new PanelBorderRadius();
        contentTop.setPreferredSize(new Dimension(1100, 200));
        this.add(contentTop);

        contentCenter = new PanelBorderRadius();
        contentCenter.setPreferredSize(new Dimension(1100, 600));
        contentCenter.setLayout(new FlowLayout(1,20,20));
        this.add(contentCenter);
        
        String[][] data = {
            {"Hoang Gia Bao", "2003", "Binh Dinh"},
            {"Dinh Ngoc An", "2003", "Tp HCM"}
        };
        
        String[] colummNames = {"Ho Ten", "Nam Sinh", "Que Quan"};
        JScrollPane sr = new JScrollPane();
        
        tableSanPham = new JTable(data, colummNames);
        tableSanPham.setPreferredSize(new Dimension(500,500));
        sr.add(tableSanPham);
        contentCenter.add(sr);
    }

    public SanPham() {
        initComponent();
    }

}
