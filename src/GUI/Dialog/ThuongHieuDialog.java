package GUI.Dialog;

import DTO.ThuongHieuDTO;
import GUI.Panel.ThuongHieu;
import GUI.Component.ButtonCustom;
import GUI.Component.HeaderTitle;
import GUI.Component.InputForm;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author 84907
 */
public class ThuongHieuDialog extends JDialog implements MouseListener {

    private ThuongHieu jpLH;
    private HeaderTitle titlePage;
    private JPanel pnmain, pnbottom;
    private ButtonCustom btnThem, btnCapNhat, btnHuyBo;
    private InputForm tenLH;
    private ThuongHieuDTO loaihangDto;

    public ThuongHieuDialog(ThuongHieu jpLH, JFrame owner, String title, boolean modal, String type) {
        super(owner, title, modal);
        this.jpLH = jpLH;
        initComponents(title, type);
    }

    public ThuongHieuDialog(ThuongHieu jpLH, JFrame owner, String title, boolean modal, String type, DTO.ThuongHieuDTO lh) {
        super(owner, title, modal);
        this.jpLH = jpLH;
        this.loaihangDto = lh;
        initComponents(title, type);
    }

    public void initComponents(String title, String type) {
        this.setSize(new Dimension(500, 270));
        this.setLayout(new BorderLayout(0, 0));
        titlePage = new HeaderTitle(title.toUpperCase());
        pnmain = new JPanel(new GridLayout(1, 1, 20, 0));
        pnmain.setBackground(Color.white);
        tenLH = new InputForm("Tên thương hiệu hóa");
        pnmain.add(tenLH);

        pnbottom = new JPanel(new FlowLayout());
        pnbottom.setBorder(new EmptyBorder(10, 0, 10, 0));
        pnbottom.setBackground(Color.white);
        btnThem = new ButtonCustom("Thêm thương hiệu", "success", 14);
        btnCapNhat = new ButtonCustom("Lưu thông tin", "success", 14);
        btnHuyBo = new ButtonCustom("Huỷ bỏ", "danger", 14);

        //Add MouseListener btn
        btnThem.addMouseListener(this);
        btnCapNhat.addMouseListener(this);
        btnHuyBo.addMouseListener(this);

        switch (type) {
            case "create" -> {
                pnbottom.add(btnThem);
            }
            case "update" -> {
                pnbottom.add(btnCapNhat);
                tenLH.setText(loaihangDto.getTenthuonghieu());
            }
            case "view" -> {
                tenLH.setText(loaihangDto.getTenthuonghieu());
            }
            default ->
                throw new AssertionError();
        }
        pnbottom.add(btnHuyBo);

        this.add(titlePage, BorderLayout.NORTH);
        this.add(pnmain, BorderLayout.CENTER);
        this.add(pnbottom, BorderLayout.SOUTH);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getSource() == btnThem) {
            String name = tenLH.getText();
            jpLH.lhBUS.add(name);
            jpLH.loadDataTalbe(jpLH.listLH);
            dispose();
        } else if (e.getSource() == btnHuyBo) {
            dispose();
        } else if (e.getSource() == btnCapNhat) {
            String name = tenLH.getText();
            int id = loaihangDto.getMathuonghieu();
            DTO.ThuongHieuDTO lh = new DTO.ThuongHieuDTO(id, name);
            jpLH.lhBUS.update(lh);
            tenLH.setText(name);
            jpLH.loadDataTalbe(jpLH.listLH);
            dispose();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
