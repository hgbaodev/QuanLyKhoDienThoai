package GUI;

import DTO.TaiKhoanDTO;
import GUI.Panel.TrangChu;
import java.awt.*;
import javax.swing.*;
import GUI.Component.MenuTaskbar;
import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.FlatLightLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import javax.swing.border.EmptyBorder;

public class Main extends JFrame {

    public JPanel MainContent;
    public TaiKhoanDTO user;
    Color MainColor = new Color(250, 250, 250);

    private MenuTaskbar menuTaskbar;
    private TrangChu trangChu;

    private void initComponent() {
        this.setSize(new Dimension(1400, 800));
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0, 0));
        this.setTitle("Hệ thống quản lý kho hàng ");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        if (user != null) {
            menuTaskbar = new MenuTaskbar(this, user);
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

    public Main(TaiKhoanDTO user) throws UnsupportedLookAndFeelException {
        this.user = user;
        initComponent();
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0, 0, 0));
        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);
        UIManager.put("TabbedPane.selectedBackground", Color.white);
        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont("h4.font"));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
    }

    public void setPanel(JPanel pn) {
        MainContent.removeAll();
        MainContent.add(pn).setVisible(true);
        MainContent.repaint();
        MainContent.validate();
    }
}
