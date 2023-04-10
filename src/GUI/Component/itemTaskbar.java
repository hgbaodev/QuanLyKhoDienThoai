package GUI.Component;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class itemTaskbar extends JPanel implements MouseListener {

    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);
    JLabel lblIcon, pnlContent;
    public boolean isSelected;

    public itemTaskbar(String linkIcon, String content) {
        this.setLayout(new FlowLayout(1, 10, 7));
        this.setPreferredSize(new Dimension(250, 45));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(30, 30));
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource(linkIcon)));
        this.add(lblIcon);
        
        

        pnlContent = new JLabel(content);
        pnlContent.setPreferredSize(new Dimension(170, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent.setForeground(FontColor);
        this.add(pnlContent);
    }

    public itemTaskbar(String linkIcon, String content1, String content2) {
        this.setLayout(new FlowLayout(0, 20, 50));
//        this.setPreferredSize(new Dimension(250, 45));
        this.setBackground(DefaultColor);
        this.addMouseListener(this);
        
        lblIcon = new JLabel();
        lblIcon.setPreferredSize(new Dimension(30, 30));
        lblIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource(linkIcon)));
        this.add(lblIcon);

        pnlContent = new JLabel(content1);
        pnlContent.setPreferredSize(new Dimension(170, 30));
        pnlContent.putClientProperty("FlatLaf.style", "font: 150% $medium.font");
        pnlContent.setForeground(FontColor);
        this.add(pnlContent);

//        box[i].setBorder(new EmptyBorder(20, 20, 20, 20));

    }

    @Override
    public void mouseClicked(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mousePressed(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(235, 237, 240));
            setCursor(new Cursor(Cursor.HAND_CURSOR));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (!isSelected) {
            setBackground(new Color(255, 255, 255));
        }
    }
}
