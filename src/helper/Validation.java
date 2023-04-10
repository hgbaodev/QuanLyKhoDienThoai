package helper;

import java.util.regex.Pattern;

/**
 *
 * @author Tran Nhat Sinh
 */
public class Validation {

    public static Boolean isEmpty(String input) {
        if (input == null) {
            return true;
        }
        return input.equals("");
    }

    public static Boolean isEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null) {
            return false;
        }
        return pat.matcher(email).matches();
    }

    public static int isNumber(String num) {
        int result = -1;
        if (num == null) {
            result = -1;
        }
        try {
            result = Integer.parseInt(num);
        } catch (NumberFormatException e) {

        }
        return result;
    }
}
