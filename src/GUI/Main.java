package GUI;

import DTO.TaiKhoanDTO;
import GUI.Panel.TrangChu;
import java.awt.*;
import javax.swing.*;
import GUI.Component.MenuTaskbar;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;

public class Main extends JFrame {

    public JPanel MainContent;
    TaiKhoanDTO user;
    Color MainColor = new Color(250, 250, 250);
    

    private MenuTaskbar menuTaskbar;
    private TrangChu trangChu;

    private void initComponent() {
        this.setSize(new Dimension(1400, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Hệ thống quản lý kho hàng ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if(user!=null){
            menuTaskbar = new MenuTaskbar(this,user);
        } else {
            menuTaskbar = new MenuTaskbar(this);
        }
        
        menuTaskbar.setPreferredSize(new Dimension(250, 1400));

        this.add(menuTaskbar, BorderLayout.WEST);

        MainContent = new JPanel();
        MainContent.setBackground(MainColor);
        MainContent.setLayout(new BorderLayout(0, 0));

        this.add(MainContent, BorderLayout.CENTER);

        trangChu = new TrangChu();
        MainContent.add(trangChu).setVisible(true);
    }

    public Main() {
        initComponent();
    }
    
    public Main(TaiKhoanDTO user) {
        this.user = user;
        initComponent();
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put( "PasswordField.showRevealButton", true );
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.selectionBackground", new Color(55, 71, 79));
    }

    public void setPanel(JPanel pn) {
        MainContent.removeAll();
        MainContent.add(pn).setVisible(true);
        MainContent.repaint();
        MainContent.validate();
    }

    public static void main(String[] args) throws UnsupportedLookAndFeelException {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        UIManager.put("Table.showVerticalLines", true);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put( "PasswordField.showRevealButton", true );
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.selectionBackground", new Color(187, 222, 251));
        Main main = new Main();
        main.setVisible(true);
    }
}
