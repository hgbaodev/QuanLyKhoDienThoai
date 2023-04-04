
package GUI.Component;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PanelShadow extends JPanel{
    
    int shadowSize = 3;
    float shadowOpacity = 0.3f;
    private Color shadowColor = Color.BLACK;
    Color HowerBackgroundColor = new Color(187, 222, 251);

    public PanelShadow () {
        setOpaque(false);
    }
    
    @Override
    protected void paintComponent(Graphics grphcs) {
        createShadow(grphcs);
    }

    private void createShadow(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs;
        int size = shadowSize *2;
        int x = 0;
        int y=0;
        int width = getWidth() -size;
        int height = getHeight() - size;
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = img.createGraphics();
        g2.setBackground(HowerBackgroundColor);

        g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g.fillRoundRect(0, 0, width,  height, 30, 30);
        
        //Create Shadow
        ShadowRenderer render = new ShadowRenderer(size, shadowOpacity, shadowColor);
        g2.drawImage(render.createShadow(img), 0,0,null);
        g2.setPaint(shadowColor);
        
        g2.drawImage(img, x, y, null);
    }
}
