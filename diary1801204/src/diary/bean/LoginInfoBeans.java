
package diary.bean;

import java.io.Serializable;

/**
 * ログインした人の情報を格納するBeansクラス
 * @author ryo
 */
public class LoginInfoBeans implements Serializable {
    private String student_id = null;
    private String class_code = null;
    private String student_name = null;

    /**
     * @return このクラスのstudent_idの値
     */
    public String getStudent_id() {
        return student_id;
    }

    /**
     * @param student_id このクラスのstudent_idに格納する値
     */
    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    /**
     * @return このクラスのclass_codeの値
     */
    public String getClass_code() {
        return class_code;
    }

    /**
     * @param class_code このクラスのclass_codeに格納する値
     */
    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    /**
     * @return このクラスのstudent_nameの値
     */
    public String getStudent_name() {
        return student_name;
    }

    /**
     * @param student_name このクラスのstudent_nameに格納する値
     */
    public void setStudent_name(String student_name) {
        this.student_name = student_name;
    }

}
