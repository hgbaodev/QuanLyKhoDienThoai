/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package component;

import com.toedter.calendar.JDateChooser;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class InputDate extends JPanel{

    JLabel lbltitle;
    JDateChooser date;
    DecimalFormat formatter = new DecimalFormat("###,###,###");
    SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
    private final SimpleDateFormat dateFormat;
    private final SimpleDateFormat Date_Format;
//    public DecimalFormat getFormatter() {
//        return formatter;
//    }
//
//    public SimpleDateFormat getFormatDate() {
//        return formatDate;
//    }
    public InputDate(String title) {
        this.setLayout(new GridLayout(2, 1));
        this.setBackground(Color.white);
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        lbltitle = new JLabel(title);
        date = new JDateChooser();
        date.setDateFormatString("dd/MM/yyyy");
        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date_Format = new SimpleDateFormat("yyyy-MM-dd");
        this.add(lbltitle);
        this.add(date);
    }

    public String getDate() {
        return Date_Format.format(date.getDate());
    }
    
    

    public void setDate(JDateChooser date) {
        this.date = date;
    }

   
}
