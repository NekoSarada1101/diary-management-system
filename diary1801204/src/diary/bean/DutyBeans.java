
package diary.bean;

/**
 * 日誌当番の情報を格納するBeansクラス
 *
 * @author ryouta
 */
public class DutyBeans {
    private String class_code = null;
    private String insert_date = null;
    private String student_id = null;
    private String student_name = null;

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
     * @return このクラスのinsert_dateの値
     */
    public String getInsert_date() {
        return insert_date;
    }

    /**
     * @param insert_date このクラスのinsert_dateに格納する値
     */
    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

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
