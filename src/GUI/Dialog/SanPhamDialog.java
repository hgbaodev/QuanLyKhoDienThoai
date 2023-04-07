package GUI.Dialog;

import DAO.DonViTinhDAO;
import DAO.KhuVucKhoDAO;
import DAO.LoaiHangDAO;
import DAO.SanPhamDAO;
import DTO.DonViTinhDTO;
import DTO.KhuVucKhoDTO;
import DTO.LoaiHangDTO;
import DTO.SanPhamDTO;
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
    InputForm tenSP, xuatxu, gianhap, giaxuat,soluong,idSP;
    SelectForm donvitinh, loaihang, khuvuc;
    InputImage hinhanh;
    GUI.Panel.SanPham jpSP;
    SanPhamDTO spDTO;
    String urlImage;
    
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
    public int setdonvitinh(int n){
        int x=0;
        for (int i = 0; i < dvt.size(); i++) {
            if(n==dvt.get(i).getMaDVT())
                x=i;
        }
        return x;
    }
    public String[] getloaihang() {
        String tenLH[] = new String[lh.size()];
        for (int i = 0; i < lh.size(); i++) {
            tenLH[i] = lh.get(i).getTenloaihang();
        }
        return tenLH;
    }
    public int setloaihang(int n){
        int x=0;
        for (int i = 0; i < lh.size(); i++) {
            if(n==lh.get(i).getMaloaihang())
                x=i;
        }
        return x;
    }
    
    public String[] getkhuvuc(){
        String tenkv[] = new String[kvk.size()];
        for (int i=0;i<kvk.size();i++){
            tenkv[i]=kvk.get(i).getTenkhuvuc();
        }
        return tenkv;
    }
    public int setkhuvuc(int n){
        int x=0;
        for (int i = 0; i < kvk.size(); i++) {
            if(n==kvk.get(i).getMakhuvuckho()){
                x=i;
            }
        }
        return x;
    }
    
    public void initComponents(String title, String type) {
        this.setSize(new Dimension(1150, 500));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnCenter = new JPanel(new GridLayout(1, 2));
        pnmain = new JPanel(new GridLayout(3, 2, 0, 0));
        pnmain.setBackground(Color.WHITE);
        pnCenter.add(pnmain);
        
        pnmainright = new JPanel();
        pnmainright.setBackground(Color.WHITE);
        pnCenter.add(pnmainright);
        
        
        tenSP = new InputForm("Tên sản phẩm");
        idSP = new InputForm("Mã sản phẩm");
        xuatxu = new InputForm("Xuất xứ");
        gianhap = new InputForm("Giá nhập");
        giaxuat = new InputForm("Giá bán");
        donvitinh = new SelectForm("Đơn vị tính", getdonvitinh());
        loaihang = new SelectForm("Loại hàng", getloaihang());
        soluong = new InputForm("Số lượng");
        khuvuc = new SelectForm("Khu vực kho", getkhuvuc());
        hinhanh = new InputImage("Hình minh họa");

        
        pnmain.add(tenSP);
        pnmain.add(xuatxu);
        pnmain.add(donvitinh);
        pnmain.add(gianhap);
        pnmain.add(giaxuat);
        pnmain.add(loaihang);
        pnmain.add(khuvuc);
        pnmain.add(soluong);
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
            {
                soluong.setVisible(false);
                pnbottom.add(btnThemSanPham);
            }
            case "update" ->
            {
                soluong.setDisable();
                initInfomation(this.spDTO);
                pnbottom.add(btnCapNhat);
            }
            case "view" ->
            {
                pnmain.add(idSP);
                pnmain.add(soluong);
                initInfomation(this.spDTO);
                idSP.setDisable();
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
        initComponents(title, type);
    }
    
    public SanPhamDialog(GUI.Panel.SanPham jpSP, JFrame owner, String title, boolean modal, String type, SanPhamDTO sp) {
        super(owner, title, modal);
        this.jpSP = jpSP;
        this.spDTO=sp;
        initComponents(title, type);
    }
    
    public void initInfomation(SanPhamDTO sp) {
        setNoidung(tenSP,sp.getTensp());
        setNoidung(xuatxu,sp.getXuatxu());
        setNoidung(gianhap, String.valueOf(sp.getGianhap()));
        setNoidung(giaxuat, String.valueOf(sp.getGiaxuat()));
        setIdSP(Integer.toString(sp.getMasp()));
        setNoidung(soluong,String.valueOf(sp.getSoluong()));
        donvitinh.setSelectedIndex(setdonvitinh(sp.getMaDVT()));
        loaihang.setSelectedIndex(setloaihang(sp.getMaloaihang()));
        khuvuc.setSelectedIndex(setkhuvuc(sp.getMakhuvuc()));
        hinhanh.setUrl_img(sp.getHinhanh());
        urlImage=sp.getHinhanh();
    }
    
    public void setNoidung(InputForm inputForm, String text) {
        inputForm.setText(text);
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
            double giaxuat = Double.valueOf(this.giaxuat.getText());
            String hinhanh = this.hinhanh.getUrl_img();
            int madonvi = dvt.get(donvitinh.getSelectedIndex()).getMaDVT();
            int maloaihang = lh.get(loaihang.getSelectedIndex()).getMaloaihang();
            int makhuvuc = kvk.get(khuvuc.getSelectedIndex()).getMakhuvuckho();
            int sluong = 0;
            int id = SanPhamDAO.getInstance().getAutoIncrement();
            JOptionPane.showMessageDialog(rootPane, tensp+"\n"+xuatxu+"\n"+gianhap+"\n"+giaxuat+"\n"+hinhanh+"\n"+madonvi+"\n"
                    + maloaihang+"\n"+makhuvuc);
            SanPhamDTO sp = new SanPhamDTO(id, tensp, xuatxu, gianhap, giaxuat, hinhanh, madonvi, maloaihang, makhuvuc,sluong);
            jpSP.spBUS.add(sp);
            jpSP.loadDataTalbe(jpSP.listSP);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {  
            String tensp = this.tenSP.getText();
            String xuatxu = this.xuatxu.getText();
            double gianhap = Double.valueOf(this.gianhap.getText());
            double giaxuat = Double.valueOf(this.giaxuat.getText());
            String hinhanh=urlImage;
            if(this.hinhanh.getUrl_img()!=null){
            hinhanh = this.hinhanh.getUrl_img();
            }
            //System.out.println(hinhanh);
            int madonvi = dvt.get(donvitinh.getSelectedIndex()).getMaDVT();
            int maloaihang = lh.get(loaihang.getSelectedIndex()).getMaloaihang();
            int makhuvuc = kvk.get(khuvuc.getSelectedIndex()).getMakhuvuckho();
            int sluong = Integer.valueOf(this.soluong.getText());
            int id= spDTO.getMasp();
            DTO.SanPhamDTO sp = new DTO.SanPhamDTO(id, tensp, xuatxu, gianhap, giaxuat, hinhanh, madonvi, maloaihang, makhuvuc,sluong);
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
