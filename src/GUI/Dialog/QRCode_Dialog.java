/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Dialog;

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
import GUI.Component.ButtonCustom;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author robot
 */
public class QRCode_Dialog extends JDialog {

    private final Dimension ds = new Dimension(700, 500);
    private final Dimension cs = WebcamResolution.VGA.getSize();
    private final Webcam wCam = Webcam.getWebcams().get(0);
    private final WebcamPanel wCamPanel = new WebcamPanel(wCam, ds, false);
    private JPanel panelCam;
    private JTextArea textArea;
    private ButtonCustom btnExit;
    private Thread t;

    public QRCode_Dialog(JFrame owner, String title, boolean modal, JTextArea textArea) {
        super(owner, title, modal);
        this.textArea = textArea;
        init();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void init() {
        
        this.setLayout(new BorderLayout());
        setSize(700, 500);
        panelCam = new JPanel(new GridLayout(1, 1));
        wCam.setViewSize(cs);
        wCamPanel.setFillArea(true);
        wCamPanel.setFPSDisplayed(true);
        panelCam.add(wCamPanel);
        JPanel panelB = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelB.setPreferredSize(new Dimension(0,40));
        panelB.setBackground(Color.WHITE);
        btnExit = new ButtonCustom("Thoát", "success", 14);
        panelB.add(btnExit);
        this.add(panelCam,BorderLayout.CENTER);
        t = new Thread() {
            @Override
            public void run() {
                wCamPanel.start();
                do {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(QRCode_Dialog.class.getName()).log(Level.SEVERE, null, ex);
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
                        Logger.getLogger(QRCode_Dialog.class.getName()).log(Level.SEVERE, null, ex);
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
                dispose();
                wCamPanel.stop();
                t.stop();
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
}
