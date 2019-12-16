
package diary.commmon;

public class StudentErrorCheck {
    public String inputEscape(String str) {
        str = str.replace("&", "&amp");
        str = str.replace("<", "&lt");
        str = str.replace(">", "&gt");
        str = str.replace("\"", "&quot");
        str = str.replace("\'", "&#39");
        str = str.replace(" ", "&nbsp");
        return str;
    }

    public boolean checkMaxLength_30(String input_text) {
        boolean is_30_length_or_less = false;
        if (input_text.length() <= 30) is_30_length_or_less = true;
        return is_30_length_or_less;
    }
}
