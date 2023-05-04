/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

import DTO.ChiTietSanPhamDTO;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.ThreadFactory;
import GUI.Component.ButtonCustom;
import GUI.Component.CheckListItem;
import GUI.Component.CheckListRenderer;
import GUI.Component.InputForm;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author robot
 */
public class PhieuKiemKeDialog extends JDialog {

    private final Dimension ds = new Dimension(700, 500);
    private final Dimension cs = WebcamResolution.VGA.getSize();
    private final Webcam wCam = Webcam.getWebcams().get(0);
    private final WebcamPanel wCamPanel = new WebcamPanel(wCam, ds, false);
    private JPanel panelCam;
    private JTextArea textArea;
    private ButtonCustom btnExit;
    private Thread t;
     private JList list;
    private JTextField findImei;
    private DefaultListModel<Object> listMode;
    
    public PhieuKiemKeDialog(JFrame owner, String title, boolean modal) {
        super(owner, title, modal);
        init();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        this.setLayout(new BorderLayout());
        this.setBackground(Color.WHITE);
        JPanel jpanelLeft = new JPanel(new BorderLayout());
        jpanelLeft.setBackground(Color.WHITE);
        JPanel jpanelCenter = new JPanel(new GridLayout(1,2));
        JPanel jpanelBottom = new JPanel(new GridLayout(2,1));
        JPanel jpanelBottomTop = new JPanel(new BorderLayout());
        jpanelBottomTop.setBorder(new EmptyBorder(5,5,5,5));
        JLabel jLabelGhiChu = new JLabel("Ghi chú");
        jLabelGhiChu.setPreferredSize(new Dimension(70,0));
        JTextArea jTextAreaGhiChu = new JTextArea();
        jTextAreaGhiChu.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        jpanelBottomTop.add(jLabelGhiChu,BorderLayout.WEST);
        jpanelBottomTop.add(jTextAreaGhiChu,BorderLayout.CENTER);
        JPanel jpanelBottomBottom = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jpanelBottom.setPreferredSize(new Dimension(0,100));
        jpanelBottom.setBackground(Color.WHITE);
        ButtonCustom btnAdd = new ButtonCustom("Thêm", "success", 14);
        jpanelBottomBottom.add(btnAdd);
        jpanelBottomTop.setBackground(Color.WHITE);
        jpanelBottomBottom.setBackground(Color.WHITE);
        jpanelBottom.add(jpanelBottomTop);
        jpanelBottom.add(jpanelBottomBottom);
        listMode = new DefaultListModel<>();
        findImei = new JTextField("");
        loadImei();
        findImei.setPreferredSize(new Dimension(0,40));
        findImei.putClientProperty("JTextField.placeholderText", "Tìm kiếm mã IMEI ...");
        findImei.putClientProperty("JTextField.showClearButton", true);
        findImei.setBackground(Color.WHITE);
        findImei.addKeyListener(new KeyAdapter(){
        @Override
            public void keyReleased(KeyEvent e) {
                loadImei();
            }
        });
        list.setBackground(Color.WHITE);
        jpanelLeft.add(findImei,BorderLayout.NORTH);
        jpanelLeft.add(new JScrollPane(list),BorderLayout.CENTER);
        setSize(800, 600);
        panelCam = new JPanel(new GridLayout(1, 1));
        wCam.setViewSize(cs);
        wCamPanel.setFillArea(true);
        wCamPanel.setFPSDisplayed(true);
        panelCam.add(wCamPanel);
        panelCam.setPreferredSize(new Dimension(0,200));
        JPanel panelB = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelB.setPreferredSize(new Dimension(0,40));
        panelB.setBackground(Color.WHITE);
        btnExit = new ButtonCustom("Thoát", "success", 14);
        panelB.add(btnExit);
        JPanel jPanelRight = new JPanel(new BorderLayout(5,5));
        jPanelRight.setBackground(Color.WHITE);
        jPanelRight.add(panelCam,BorderLayout.NORTH);
        JPanel panelImei = new JPanel(new BorderLayout());
        JLabel jLabelSpdu = new JLabel("Sản phẩm dư");
        jLabelSpdu.setPreferredSize(new Dimension(0,40));
        JTextArea jTextAreaImei = new JTextArea();
        jTextAreaImei.setBorder(BorderFactory.createLineBorder(new Color(153, 153, 153)));
        panelImei.add(jLabelSpdu,BorderLayout.NORTH);
        panelImei.add(jTextAreaImei,BorderLayout.CENTER);
        panelImei.setBackground(Color.WHITE);
        jPanelRight.add(panelImei,BorderLayout.CENTER);
        jPanelRight.setBackground(Color.WHITE);
        jPanelRight.setBorder(new EmptyBorder(0,5,0,0));
        jpanelCenter.setBorder(new EmptyBorder(5,5,5,5));
        jpanelCenter.add(jpanelLeft);
        jpanelCenter.add(jPanelRight);
        JPanel jpanelTop = new JPanel(new GridLayout(1, 4));
        jpanelTop.setPreferredSize(new Dimension(0,80));
        InputForm tensp = new InputForm("Tên SP");
        InputForm tencauhinh = new InputForm("Cấu hình");
        InputForm slTonKho = new InputForm("Tồn kho");
        InputForm slHienTai = new InputForm("Số lượng hiện tại");
        jpanelTop.add(tensp);
        jpanelTop.add(tencauhinh);
        jpanelTop.add(slTonKho);
        jpanelTop.add(slHienTai);
        this.add(jpanelTop,BorderLayout.NORTH);
        this.add(jpanelCenter,BorderLayout.CENTER);
        this.add(jpanelBottom,BorderLayout.SOUTH);
        t = new Thread() {
            @Override
            public void run() {
                wCamPanel.start();
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(PhieuKiemKeDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    Result result = null;
                    BufferedImage image = null;

                    if (wCam.isOpen()) {
                        if ((image = wCam.getImage()) == null) {
                            continue;
                        }
                    }

                    LuminanceSource source = new BufferedImageLuminanceSource(image);
                    BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                    try {
                        result = new MultiFormatReader().decode(bitmap);
                    } catch (NotFoundException ex) {
//                        Logger.getLogger(PhieuKiemKeDialog.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (result != null) {
                        if(!textArea.getText().contains(result.getText())){
                            soundScan();
                            textArea.append(result.getText() + "\n");
                        } else {
                            System.out.println("Mã đã được quét!");
                        }
                    }

                } while (true);
            }
        };
        t.setDaemon(true);
        t.start();
        setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                wCam.close();
                wCamPanel.stop();
                t.stop();
                dispose();
            }
        });
    }

    public void soundScan() {
        try {
            File soundFile = new File("./sound_scan/Barcode-scanner-beep-sound.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    
    public void loadImei(){
        String search = findImei.getText();
        listMode.setSize(0);
        for (int i = 0; i< 10;i++) {
            CheckListItem check = new CheckListItem("123456"+i);
            listMode.addElement(check);
        }
        list = new JList(listMode);
        list.setCellRenderer(new CheckListRenderer());
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent event) {
                JList list = (JList) event.getSource();
                int index = list.locationToIndex(event.getPoint());// Get index of item
                CheckListItem item = (CheckListItem) list.getModel().getElementAt(index);
                item.setSelected(!item.isSelected()); // Toggle selected state
                list.repaint(list.getCellBounds(index, index));// Repaint cell
            }
        });
    }
    
}
