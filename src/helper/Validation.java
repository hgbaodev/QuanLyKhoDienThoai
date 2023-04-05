package helper;


import java.util.regex.Pattern;

/**
 *
 * @author Tran Nhat Sinh
 */
public class Validation {
    public static Boolean isEmpty(String input) {
        if(input == null) return false;
        return !input.equals("");
    }
    
    public static Boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";             
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    
    public static Boolean isNumber(String num) {
        Boolean valid = true;
        if(num == null) valid = false;
        try {
            Double a = Double.parseDouble(num);
        } catch (Exception e) {
            valid = false;
        }
        return valid;
    }
}