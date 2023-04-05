package GUI.Dialog;

import DAO.DonViTinhDAO;
import DAO.KhuVucKhoDAO;
import DAO.LoaiHangDAO;
import DAO.SanPhamDAO;
import DTO.DonViTinhDTO;
import DTO.KhuVucKhoDTO;
import DTO.LoaiHangDTO;
import GUI.Panel.SanPham;
import GUI.Component.ButtonCustom;
import com.formdev.flatlaf.FlatLightLaf;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import GUI.Component.InputImage;
import GUI.Component.SelectForm;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EmptyBorder;
import javax.swing.text.View;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author Tran Nhat Sinh
 */
public class SanPhamDialog extends JDialog implements MouseListener {
    
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom, pnCenter, pnmainright;
    private ButtonCustom btnThemSanPham, btnCapNhat, btnHuyBo;
    InputForm tenSP, xuatxu, gianhap, giaban,soluong;
    SelectForm donvitinh, loaihang, khuvuc;
    InputImage hinhanh;
    JTextField idSP;
    GUI.Panel.SanPham jpSP;
    
    ArrayList<DonViTinhDTO> dvt = DonViTinhDAO.getInstance().selectAll();
    ArrayList<LoaiHangDTO> lh = LoaiHangDAO.getInstance().selectAll();
    ArrayList<KhuVucKhoDTO> kvk = KhuVucKhoDAO.getInstance().selectAll();

    public String[] getdonvitinh() {
        String tenDVT[] = new String[dvt.size()];
        for (int i = 0; i < dvt.size(); i++) {
            tenDVT[i] = dvt.get(i).getTenDVT();
        }
        return tenDVT;
    }

    public String[] getloaihang() {
        String tenLH[] = new String[lh.size()];
        for (int i = 0; i < lh.size(); i++) {
            tenLH[i] = lh.get(i).getTenloaihang();
        }
        return tenLH;
    }
    
    public String[] getkhuvuc(){
        String tenkv[] = new String[kvk.size()];
        for (int i=0;i<kvk.size();i++){
            tenkv[i]=kvk.get(i).getTenkhuvuc();
        }
        return tenkv;
    }
    
    public void initComponents(String title, String type) {
        this.setSize(new Dimension(1150, 450));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnCenter = new JPanel(new GridLayout(1, 2));
        pnmain = new JPanel(new GridLayout(3, 2, 0, 0));
        pnmain.setBackground(Color.WHITE);
        pnCenter.add(pnmain);
        
        pnmainright = new JPanel();
        pnmainright.setBackground(Color.WHITE);
        pnCenter.add(pnmainright);
        
        pnmain.add(tenSP);
        pnmain.add(xuatxu);
        pnmain.add(donvitinh);
        pnmain.add(gianhap);
        pnmain.add(giaban);
        pnmain.add(loaihang);
        pnmain.add(khuvuc);
        
        pnmainright.add(hinhanh);
        
        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThemSanPham = new ButtonCustom("Thêm Sản Phẩm", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThemSanPham.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);
        
        switch (type) {
            case "create" ->
                pnbottom.add(btnThemSanPham);
            case "update" ->
            {
                pnmain.add(soluong);
                soluong.setDisable();
                pnbottom.add(btnCapNhat);
            }
            case "view" ->
            {
                tenSP.setDisable();
                pnmain.add(soluong);
                soluong.setDisable();
            }
                
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);
        
        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnCenter, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
    }

    public SanPhamDialog(SanPham jpSP, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpSP = jpSP;
        tenSP = new InputForm("Tên sản phẩm");
        idSP = new JTextField("");
        xuatxu = new InputForm("Xuất xứ");
        gianhap = new InputForm("Giá nhập");
        giaban = new InputForm("Giá bán");
        donvitinh = new SelectForm("Đơn vị tính", getdonvitinh());
        loaihang = new SelectForm("Loại hàng", getloaihang());
        soluong = new InputForm("Số lượng");
        
        khuvuc = new SelectForm("Khu vực kho", getkhuvuc());
        hinhanh = new InputImage("Hình minh họa");
        initComponents(title, type);
    }
    
    public SanPhamDialog(GUI.Panel.SanPham jpSP, JFrame owner, String title, boolean modal, String type, DTO.SanPhamDTO sp) {
        super(owner, title, modal);
        tenSP = new InputForm("Tên sản phẩm");
        idSP = new JTextField("");
        xuatxu = new InputForm("Xuất xứ");
        gianhap = new InputForm("Giá nhập");
        giaban = new InputForm("Giá bán");
        setTenSP(sp.getTensp());
        setIdSP(Integer.toString(sp.getMasp()));
        donvitinh = new SelectForm("Đơn vị tính", getdonvitinh());
        loaihang = new SelectForm("Loại hàng", getloaihang());
        String tempKVK[] = {"khu A", "khuB", "khu C"};
        khuvuc = new SelectForm("Khu vực kho", tempKVK);
        hinhanh = new InputImage("Hình minh họa");
        this.jpSP = jpSP;
        initComponents(title, type);
    }
    
    
    
    public void setTenSP(String text) {
        tenSP.setText(text);
    }
    
    public void setIdSP(String text) {
        this.idSP.setText(text);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThemSanPham) {
            String tensp = this.tenSP.getText();
            String xuatxu = this.xuatxu.getText();
            double gianhap = Double.valueOf(this.gianhap.getText());
            double giaban = Double.valueOf(this.giaban.getText());
            String hinhanh = this.hinhanh.getUrl_img();
            int madonvi = dvt.get(donvitinh.getSelectedIndex()).getMaDVT();
            int maloaihang = lh.get(loaihang.getSelectedIndex()).getMaloaihang();
            int makhuvuc = kvk.get(khuvuc.getSelectedIndex()).getMakhuvuckho();
            int sluong = 0;
            int id = SanPhamDAO.getInstance().getAutoIncrement();
            JOptionPane.showMessageDialog(rootPane, tensp+"\n"+xuatxu+"\n"+gianhap+"\n"+giaban+"\n"+hinhanh+"\n"+madonvi+"\n"
                    + maloaihang+"\n"+makhuvuc);
            DTO.SanPhamDTO sp = new DTO.SanPhamDTO(id, tensp, xuatxu, gianhap, giaban, hinhanh, madonvi, maloaihang, makhuvuc,sluong);
            jpSP.spBUS.add(sp);
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {  
            String tensp = this.tenSP.getText();
            String xuatxu = this.xuatxu.getText();
            double gianhap = Double.valueOf(this.gianhap.getText());
            double giaban = Double.valueOf(this.giaban.getText());
            String hinhanh = this.hinhanh.getUrl_img();
            int madonvi = dvt.get(donvitinh.getSelectedIndex()).getMaDVT();
            int maloaihang = lh.get(loaihang.getSelectedIndex()).getMaloaihang();
            int makhuvuc = kvk.get(khuvuc.getSelectedIndex()).getMakhuvuckho();
            int sluong = Integer.valueOf(this.soluong.getText());
            int id = SanPhamDAO.getInstance().getAutoIncrement();
            DTO.SanPhamDTO sp = new DTO.SanPhamDTO(id, tensp, xuatxu, gianhap, giaban, hinhanh, madonvi, maloaihang, makhuvuc,sluong);
            jpSP.spBUS.update(sp);
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        }
    }
    
    @Override
    public void mouseReleased(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void mouseEntered(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    @Override
    public void mouseExited(MouseEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
