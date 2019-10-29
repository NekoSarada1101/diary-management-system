
package diary.bean;

import java.io.Serializable;

public class DiaryBeans implements Serializable {
    private String class_code = null;
    private String insert_date = null;
    private String student_id = null;
    private String good_point = null;
    private String bad_point = null;
    private String student_comment = null;
    private String teacher_comment = null;

    public String getClass_code() {
        return class_code;
    }

    public void setClass_code(String class_code) {
        this.class_code = class_code;
    }

    public String getInsert_date() {
        return insert_date;
    }

    public void setInsert_date(String insert_date) {
        this.insert_date = insert_date;
    }

    public String getStudent_id() {
        return student_id;
    }

    public void setStudent_id(String student_id) {
        this.student_id = student_id;
    }

    public String getGood_point() {
        return good_point;
    }

    public void setGood_point(String good_point) {
        this.good_point = good_point;
    }

    public String getBad_point() {
        return bad_point;
    }

    public void setBad_point(String bad_point) {
        this.bad_point = bad_point;
    }

    public String getStudent_comment() {
        return student_comment;
    }

    public void setStudent_comment(String student_comment) {
        this.student_comment = student_comment;
    }

    public String getTeacher_comment() {
        return teacher_comment;
    }

    public void setTeacher_comment(String teacher_comment) {
        this.teacher_comment = teacher_comment;
    }
}
