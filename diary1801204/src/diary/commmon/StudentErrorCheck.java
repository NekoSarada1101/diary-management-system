
package diary.commmon;

public class StudentErrorCheck {
    public String inputEscape(String str) {
        str = str.replace("&", "&amp");
        str = str.replace("<", "&lt");
        str = str.replace(">", "&gt");
        str = str.replace("\"", "&quot");
        str = str.replace("\'", "&#39");
        str = str.replace(" ", "&nbsp");
        System.out.println(str);
        return str;
    }
}
