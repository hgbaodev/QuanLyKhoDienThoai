package GUI.Component.Chart.BarChart.BlankChart;

import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.geom.Rectangle2D;
import java.text.DecimalFormat;
import javax.swing.JComponent;
import javax.swing.border.EmptyBorder;

public class BlankPlotChart extends JComponent {

    public BlankPlotChatRender getBlankPlotChatRender() {
        return blankPlotChatRender;
    }

    public void setBlankPlotChatRender(BlankPlotChatRender blankPlotChatRender) {
        this.blankPlotChatRender = blankPlotChatRender;
    }

    public double getMaxValues() {
        return maxValues;
    }

    public void setMaxValues(double maxValues) {
        this.maxValues = maxValues;
        niceScale.setMax(maxValues);
        repaint();
    }

    public double getMinValues() {
        return minValues;
    }

    public int getLabelCount() {
        return labelCount;
    }

    public void setLabelCount(int labelCount) {
        this.labelCount = labelCount;
    }

    public String getValuesFormat() {
        return valuesFormat;
    }

    public void setValuesFormat(String valuesFormat) {
        this.valuesFormat = valuesFormat;
        format.applyPattern(valuesFormat);
    }

    private final DecimalFormat format = new DecimalFormat("#,##0.##");
    private NiceScale niceScale;
    private double maxValues;
    private double minValues;
    private int labelCount;
    private String valuesFormat = "#,##0.##";
    private BlankPlotChatRender blankPlotChatRender;

    public BlankPlotChart() {
        setBackground(Color.WHITE);
        setOpaque(false);
        setForeground(new Color(100, 100, 100));
        setBorder(new EmptyBorder(20, 10, 10, 10));
        init();
    }

    private void init() {
        initValues(0, 10);
    }

    public void initValues(double minValues, double maxValues) {
        this.minValues = minValues;
        this.maxValues = maxValues;
        niceScale = new NiceScale(minValues, maxValues);
        repaint();
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        if (niceScale != null) {
            Graphics2D g2 = (Graphics2D) grphcs;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            createLine(g2);
            createValues(g2);
            createLabelText(g2);
            renderSeries(g2);
        }
    }

    private void createLine(Graphics2D g2) {
        g2.setColor(new Color(220, 220, 220));
        Insets insets = getInsets();
        double textHeight = getLabelTextHeight(g2);
        double height = getHeight() - (insets.top + insets.bottom) - textHeight;
        double space = height / niceScale.getMaxTicks();
        double locationY = insets.bottom + textHeight;
        double textWidth = getMaxValuesTextWidth(g2);
        double spaceText = 5;
        for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
            int y = (int) (getHeight() - locationY);
            g2.drawLine((int) (insets.left + textWidth + spaceText), y, (int) getWidth() - insets.right, y);
            locationY += space;
        }

    }

    private void createValues(Graphics2D g2) {
        g2.setColor(getForeground());
        Insets insets = getInsets();
        double textHeight = getLabelTextHeight(g2);
        double height = getHeight() - (insets.top + insets.bottom) - textHeight;
        double space = height / niceScale.getMaxTicks();
        double valuesCount = niceScale.getNiceMin();
        double locationY = insets.bottom + textHeight;
        FontMetrics ft = g2.getFontMetrics();
        for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
            String text = format.format(valuesCount);
            Rectangle2D r2 = ft.getStringBounds(text, g2);
            double stringY = r2.getCenterY() * -1;
            double y = getHeight() - locationY + stringY;
            g2.drawString(text, insets.left, (int) y);
            locationY += space;
            valuesCount += niceScale.getTickSpacing();
        }
    }

    private void createLabelText(Graphics2D g2) {
        if (labelCount > 0) {
            Insets insets = getInsets();
            double textWidth = getMaxValuesTextWidth(g2);
            double spaceText = 5;
            double width = getWidth() - insets.left - insets.right - textWidth - spaceText;
            double space = width / labelCount;
            double locationX = insets.left + textWidth + spaceText;
            double locationText = getHeight() - insets.bottom;
            FontMetrics ft = g2.getFontMetrics();
            for (int i = 0; i < labelCount; i++) {
                double centerX = ((locationX + space / 2));
                g2.setColor(getForeground());
                String text = getChartText(i);
                Rectangle2D r2 = ft.getStringBounds(text, g2);
                double textX = centerX - r2.getWidth() / 2;
                g2.drawString(text, (int) textX, (int) locationText);
                locationX += space;
            }
        }
    }

    private void renderSeries(Graphics2D g2) {
        if (blankPlotChatRender != null) {
            Insets insets = getInsets();
            double textWidth = getMaxValuesTextWidth(g2);
            double textHeight = getLabelTextHeight(g2);
            double spaceText = 5;
            double width = getWidth() - insets.left - insets.right - textWidth - spaceText;
            double height = getHeight() - insets.top - insets.bottom - textHeight;
            double space = width / labelCount;
            double locationX = insets.left + textWidth + spaceText;
            for (int i = 0; i < labelCount; i++) {
                blankPlotChatRender.renderSeries(this, g2, getRectangle(i, height, space, locationX, insets.top), i);
            }
        }
    }

    private double getMaxValuesTextWidth(Graphics2D g2) {
        double width = 0;
        FontMetrics ft = g2.getFontMetrics();
        double valuesCount = niceScale.getNiceMin();
        for (int i = 0; i <= niceScale.getMaxTicks(); i++) {
            String text = format.format(valuesCount);
            Rectangle2D r2 = ft.getStringBounds(text, g2);
            double w = r2.getWidth();
            if (w > width) {
                width = w;
            }
            valuesCount += niceScale.getTickSpacing();
        }
        return width;
    }

    private int getLabelTextHeight(Graphics2D g2) {
        FontMetrics ft = g2.getFontMetrics();
        return ft.getHeight();
    }

    private String getChartText(int index) {
        if (blankPlotChatRender != null) {
            return blankPlotChatRender.getLabelText(index);
        } else {
            return "Label";
        }
    }

    public SeriesSize getRectangle(int index, double height, double space, double startX, double startY) {
        double x = startX + space * index;
        SeriesSize size = new SeriesSize(x, startY+1, space, height);
        return size;
    }

    public double getSeriesValuesOf(double values, double height) {
        double max = niceScale.getTickSpacing() * niceScale.getMaxTicks();
        double percentValues = values * 100d / max;
        return height * percentValues / 100d;
    }
}
