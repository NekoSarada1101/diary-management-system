
package diary.bean;

import java.io.Serializable;

/**
 * 日誌の情報を格納するBeansクラス
 * @author ryo
 */
public class DiaryBeans implements Serializable {
    private String class_code = null;
    private String insert_date = null;
    private String student_id = null;
    private String good_point = null;
    private String bad_point = null;
    private String student_comment = null;
    private String teacher_comment = null;

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
     * @return このクラスのgood_pointの値
     */
    public String getGood_point() {
        return good_point;
    }

    /**
     * @param good_point このクラスのgood_pointに格納する値
     */
    public void setGood_point(String good_point) {
        this.good_point = good_point;
    }

    /**
     * @return このクラスのbad_pointの値
     */
    public String getBad_point() {
        return bad_point;
    }

    /**
     * @param bad_point このクラスのbad_pointに格納する値
     */
    public void setBad_point(String bad_point) {
        this.bad_point = bad_point;
    }

    /**
     * @return このクラスのstudent_commentの値
     */
    public String getStudent_comment() {
        return student_comment;
    }

    /**
     * @param student_comment このクラスのstudent_commentに格納する値
     */
    public void setStudent_comment(String student_comment) {
        this.student_comment = student_comment;
    }

    /**
     * @return このクラスのteacher_commentの値
     */
    public String getTeacher_comment() {
        return teacher_comment;
    }

    /**
     * @param teacher_comment このクラスのteacher_commentに格納する値
     */
    public void setTeacher_comment(String teacher_comment) {
        this.teacher_comment = teacher_comment;
    }
}
