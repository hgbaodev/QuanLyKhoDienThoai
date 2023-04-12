
package GUI.Panel;

import GUI.Component.IntegratedSearch;
import GUI.Component.MainFunction;
import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import GUI.Component.PanelBorderRadius;
import GUI.Component.itemTaskbar;
import GUI.Dialog.ThuocTinhSanPham.DungLuongRamDialog;
import GUI.Dialog.ThuocTinhSanPham.DungLuongRomDialog;
import GUI.Dialog.ThuocTinhSanPham.MauSacDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class QuanLyThuocTinhSP extends JPanel{

    private final int n = 20;
    JPanel box[], pnlBorder1, pnlBorder2, pnlBorder3, pnlBorder4, contentCenter;
    JTable tableSanPham;
    JScrollPane scrollTableSanPham;
    MainFunction mainFunction;
    IntegratedSearch search;
    JLabel  lbl1, lblImage;
    JLabel lbl[], lblIcon[], info;
    JScrollPane scrPane;
    DungLuongRamDialog dlram;
    DungLuongRomDialog dlrom;
    MauSacDialog mausac;
    public itemTaskbar[] listitem;
    
    //String st[] = {"Trang chủ", "Sản phẩm", "Loại hàng", "Quản lý kho", "Nhập kho", "Xuất kho", "Khách hàng", "Nhà cung cấp", "Nhân viên", "Tài khoản", "Đơn vị tính", "Đăng xuất"};
    String iconst[] = {"/icon/home_30px.png", "/icon/product_30px.png", "/icon/categorize_30px.png", "/icon/account_30px.png", "/icon/supply_chain_30px.png"};

    String header[]={"Xuất xứ","Hệ điều hành","Dung lượng Ram","Dung lượng Rom","Màu sắc"};
    Color BackgroundColor = new Color(240, 247, 250);
    Color FontColor = new Color(96, 125, 139);
    Color DefaultColor = new Color(255, 255, 255);


    private void initComponent() {
        listitem = new itemTaskbar[header.length];

        this.setBackground(BackgroundColor);
        this.setLayout(new BorderLayout(0, 0));
        this.setOpaque(true);

        pnlBorder1 = new JPanel();
        pnlBorder1.setPreferredSize(new Dimension(0, 40));
        pnlBorder1.setBackground(BackgroundColor);
        this.add(pnlBorder1, BorderLayout.NORTH);

        pnlBorder2 = new JPanel();
        pnlBorder2.setPreferredSize(new Dimension(0, 40));
        pnlBorder2.setBackground(BackgroundColor);
        this.add(pnlBorder2, BorderLayout.SOUTH);

        pnlBorder3 = new JPanel();
        pnlBorder3.setPreferredSize(new Dimension(40, 0));
        pnlBorder3.setBackground(BackgroundColor);
        this.add(pnlBorder3, BorderLayout.EAST);

        pnlBorder4 = new JPanel();
        pnlBorder4.setPreferredSize(new Dimension(40, 0));
        pnlBorder4.setBackground(BackgroundColor);
        this.add(pnlBorder4, BorderLayout.WEST);


        
        contentCenter = new JPanel();
        contentCenter.setBackground(BackgroundColor);
        contentCenter.setLayout(new GridLayout(4,4, 20, 20));
        
        scrPane = new JScrollPane(contentCenter);
        scrPane.setBorder(new EmptyBorder(0,0,0,0));
        this.add(scrPane, BorderLayout.CENTER);

        box = new JPanel[n];
        lbl = new JLabel[n];
        lblIcon = new JLabel[n];
        for (int i = 0; i < header.length; i++) {
//            box[i] = new JPanel();
//            box[i].setLayout(new FlowLayout(0, 20, 50));
//            box[i].setBackground(DefaultColor);
//            box[i].setBorder(new EmptyBorder(20,20,20,20));
//
//            lblIcon[i] = new JLabel();
//            lblIcon[i].setPreferredSize(new Dimension(30, 30));
//            lblIcon[i].setIcon(new javax.swing.ImageIcon(getClass().getResource(iconst[i])));
//
//            box[i].add(lblIcon[i]);
//
//            lbl[i] = new JLabel(st[i]);
//            lbl[i].setPreferredSize(new Dimension(170, 30));
//            lbl[i].putClientProperty("FlatLaf.style", "font: 150% $medium.font");
//            lbl[i].setForeground(FontColor);
//
//            box[i].add(lbl[i]);
//
//            contentCenter.add(box[i]);
                listitem[i] = new itemTaskbar(iconst[i], header[i], header[i]);
                contentCenter.add(listitem[i]);
        }
//        for (int i=0;i<listitem.length;i++){
//            listitem[i].addMouseListener(new MouseAdapter(){
//                @Override
//                public void mousePressed(MouseEvent evt) {
//                    
//                }
//            });
//        }
        listitem[2].addMouseListener(new MouseAdapter() {
             @Override
            public void mousePressed(MouseEvent evt) {
                dlram = new DungLuongRamDialog();
                dlram.setVisible(true);
            }
        });
        
        listitem[3].addMouseListener(new MouseAdapter() {
             @Override
            public void mousePressed(MouseEvent evt) {
                dlrom = new DungLuongRomDialog();
                dlrom.setVisible(true);
            }
        });
        listitem[4].addMouseListener(new MouseAdapter() {
             @Override
            public void mousePressed(MouseEvent evt) {
                mausac = new MauSacDialog();
                mausac.setVisible(true);
            }
        });
    }
    public void Mouseopress(MouseEvent evt){
            for(int i=0;i<listitem.length;i++){
              if(evt.getSource()==listitem[i]){
                  
              }  
            }
        }

    public QuanLyThuocTinhSP() {
        initComponent();
    }

}

