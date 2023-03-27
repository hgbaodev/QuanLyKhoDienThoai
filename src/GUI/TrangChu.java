package GUI;

import java.awt.*;
import javax.swing.*;
import swing.PanelShadow;

public class TrangChu extends JPanel {

    JPanel top, center,bar1,bar2;
    PanelShadow content[];
    JPanel  info[];
    JLabel title, subTit, infoDetail[], objDetail[], objDetail1[], infoIcon[];
    String ctArr[] = {"", "", ""}, tkArr[] = {"Dành cho nhà phát triẻn", "Dành cho khách hàng", "Dành cho nhân viên"};
    String iconArr[] = {"/icon/remote_desktop_120px.png", "/icon/wynk_music_120px.png", "/icon/downtown_120px.png"};
    String obj1Arr[] = {"asjf dsf er xcv vx erf e reteteddg", "asjf dsf er xcv vx erf e reteteddg", "asjf dsf er xcv vx erf e reteteddg"}, obj2Arr[] = {"sdfsdfjdsf kerfhkjrehgkrje kghjerhgkjerkgjerdfdg", "sdfsdfjdsf kerfhkjrehgkrje kghjerhgkjerkgjerdfdg", "sdfsdfjdsf kerfhkjrehgkrje kghjerhgkjerkgjerdfdg"};


    Color MainColor = new Color(255, 255, 255);
    Color FontColor = new Color(96, 125, 139);
    Color BackgroundColor = new Color(240, 247, 250);
    Color HowerFontColor = new Color(225, 230, 232);


    private void initComponent() {
        this.setBackground(new Color(24, 24, 24));
        this.setBounds(0, 200, 300, 1200);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);


        top = new JPanel();
        top.setBackground(MainColor);
        top.setPreferredSize(new Dimension(1100, 200));
//        top.setLayout(new FlowLayout(1, 100, 20));
        top.setLayout(new BorderLayout(300,0));

//        title = new JLabel("CÔNG TY THIẾT KẾ PHẦN MỀM ĐẸP SỐ 1 TRIỆU THẾ GIỚI");
//        title.setFont(new Font("Segoe UI", Font.BOLD, 20));
//        title.setPreferredSize(new Dimension(1000, 40));
//        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/logo-dep.png")));
//        top.add(title);
//
//        subTit = new JLabel("Cảm ơn các bạn đã tin tưởng chúng tôi");
//        subTit.setFont(new Font("Segoe UI", Font.PLAIN, 18));
//        subTit.setPreferredSize(new Dimension(800, 24));
//        subTit.setForeground(FontColor);
//        top.add(subTit);

        title = new JLabel();
        title.setIcon(new javax.swing.ImageIcon(getClass().getResource("/img/slogan.png")));
        top.add(title, BorderLayout.CENTER);


        this.add(top, BorderLayout.NORTH);

        center = new JPanel();
        center.setBackground(BackgroundColor);
        center.setPreferredSize(new Dimension(1100, 800));
        center.setLayout(new FlowLayout(1, 50, 50));

        content = new PanelShadow[3];
        info = new JPanel[3];
        infoDetail = new JLabel[3];
        objDetail = new JLabel[3];        
        objDetail1 = new JLabel[3];

        infoIcon = new JLabel[3];

        for (int i = 0; i < ctArr.length; i++) {
            content[i] = new PanelShadow();
            content[i].setPreferredSize(new Dimension(300, 500));
            content[i].setBackground(MainColor);
            content[i].setLayout(new FlowLayout(1, 0, 10));

            info[i] = new JPanel();
            info[i].setPreferredSize(new Dimension(250, 150));
            info[i].setBackground(BackgroundColor);
            info[i].setLayout(null);

            infoIcon[i] = new JLabel();
            infoIcon[i].setBounds(60,20,120,120);
            infoIcon[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(iconArr[i])));
            info[i].add(infoIcon[i]);

            content[i].add(info[i]);

            infoDetail[i] = new JLabel(tkArr[i]);
            infoDetail[i].setPreferredSize(new Dimension(190, 60));
            infoDetail[i].setFont(new Font("Segoe UI", Font.BOLD, 16));
            content[i].add(infoDetail[i]);
            
            objDetail[i] = new JLabel(obj1Arr[i]);
            objDetail[i].setPreferredSize(new Dimension(220, 20));
            objDetail[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
            content[i].add(objDetail[i]);
            
            objDetail[i] = new JLabel(obj2Arr[i]);
            objDetail[i].setPreferredSize(new Dimension(220, 20));
            objDetail[i].setFont(new Font("Segoe UI", Font.PLAIN, 15));
            content[i].add(objDetail[i]);

            center.add(content[i]);

        }

        this.add(center, BorderLayout.CENTER);

    }

    public TrangChu() {
        initComponent();
    }

}
