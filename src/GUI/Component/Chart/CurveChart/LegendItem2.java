package GUI.Component.Chart.CurveChart;

public class LegendItem2 extends javax.swing.JPanel {

    public LegendItem2(ModelLegend2 data) {
        initComponents();
        setOpaque(false);
        lbColor.setBackground(data.getColor());
        lbColor.setForeground(data.getColorLight());
        lbName.setText(data.getName());
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        lbColor = new GUI.Component.Chart.CurveChart.LabelColor2();
        lbName = new javax.swing.JLabel();

        lbColor.setText("labelColor1");

        lbName.setForeground(new java.awt.Color(180, 180, 180));
        lbName.setText("Name");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbColor, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbName)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbName)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(lbColor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>                        


    // Variables declaration - do not modify                     
    private GUI.Component.Chart.CurveChart.LabelColor2 lbColor;
    private javax.swing.JLabel lbName;
    // End of variables declaration                   
}
