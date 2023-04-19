package helper;

import java.sql.Timestamp;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;

/**
 *
 * @author Tran Nhat Sinh
 */
public class Formater {

    public static String FormatVND(double vnd) {
        DecimalFormat formatter = new DecimalFormat("###,###,###");
        return formatter.format(vnd) + "đ";
    }
    
    public static String FormatTime(Timestamp thoigian) {
        SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/YYYY HH:mm");
        return formatDate.format(thoigian);
    }
}
