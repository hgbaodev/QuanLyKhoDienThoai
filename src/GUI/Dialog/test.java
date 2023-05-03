package GUI.Dialog;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Color;
import java.awt.Insets;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author robot
 */
public class test {
    public static void main(String[] args) {
        FlatRobotoFont.install();
        FlatLaf.setPreferredFontFamily(FlatRobotoFont.FAMILY);
        FlatLaf.setPreferredLightFontFamily(FlatRobotoFont.FAMILY_LIGHT);
        FlatLaf.setPreferredSemiboldFontFamily(FlatRobotoFont.FAMILY_SEMIBOLD);
        UIManager.put("Table.showVerticalLines", false);
        UIManager.put("Table.showHorizontalLines", true);
        UIManager.put("TextComponent.arc", 5);
        UIManager.put("ScrollBar.thumbArc", 999);
        UIManager.put("ScrollBar.thumbInsets", new Insets(2, 2, 2, 2));
        UIManager.put("Button.iconTextGap", 10);
        UIManager.put("PasswordField.showRevealButton", true);
        FlatIntelliJLaf.registerCustomDefaultsSource("style");
        FlatIntelliJLaf.setup();
        UIManager.put("Table.selectionBackground", new Color(240, 247, 250));
        UIManager.put("Table.selectionForeground", new Color(0,0,0));

        UIManager.put("Table.scrollPaneBorder", new EmptyBorder(0, 0, 0, 0));
        UIManager.put("Table.rowHeight", 40);

        UIManager.put("TableHeader.height", 40);
        UIManager.put("TableHeader.font", UIManager.getFont( "h4.font" ));
        UIManager.put("TableHeader.background", new Color(242, 242, 242));
        UIManager.put("TableHeader.separatorColor", new Color(242, 242, 242));
        UIManager.put("TableHeader.bottomSeparatorColor", new Color(242, 242, 242));
        JFrame test = new JFrame();
        PhieuKiemKeDialog pk = new PhieuKiemKeDialog(test, "oke", true);
    }
}
