/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI.Component;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.filechooser.FileSystemView;
import org.apache.commons.compress.harmony.unpack200.bytecode.forms.ThisFieldRefForm;

/**
 *
 * @author 84907
 */
public class InputImage extends JPanel implements ActionListener {

    private JButton btnChooseImg;
    private JLabel img;
    private  String url_img;

    public InputImage() {

    }

    public InputImage(String title) {
        this.setBackground(Color.white);
        btnChooseImg = new JButton(title);
        img = new JLabel();
        img.setPreferredSize(new Dimension(400,300));
        btnChooseImg.addActionListener(this);
        this.add(btnChooseImg);
    }

    public  String getUrl_img() {
        return url_img;
    }
    public  void setUrl_img(String url_img) {
        btnChooseImg.setIcon(new ImageIcon(url_img));
        btnChooseImg.setText("");
    }
    public void setUnable(){
        this.btnChooseImg.setEnabled(false);
    }

 
    
    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser jfc;
        jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setAcceptAllFileFilterUsed(false);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("PNG and  GIF images", "png", "gif", "jpg", "jpeg");
        jfc.addChoosableFileFilter(filter);
        int returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            System.out.println(jfc.getSelectedFile().getPath());
            //this.setUrl_img((String)jfc.getSelectedFile().getPath());
            this.url_img = (String)jfc.getSelectedFile().getPath();
            System.out.println(url_img);
            File file = jfc.getSelectedFile();
            ImageIcon imgicon = new ImageIcon(String.valueOf(jfc.getSelectedFile()));
            BufferedImage b;
            try {
                b = ImageIO.read(file);
                int WIDTH = 350;
                int HEIGHT = 250;
                Image scaledImage = imgicon.getImage().getScaledInstance(WIDTH,HEIGHT ,Image.SCALE_SMOOTH);
                imgicon = new ImageIcon(scaledImage);
                System.out.println(imgicon.getIconWidth()+":"+imgicon.getIconHeight());
                btnChooseImg.setText("");
                btnChooseImg.setIcon(imgicon);
            } catch (IOException ex) {
                Logger.getLogger(InputImage.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }


}
