package diary.bean;

import java.io.Serializable;

/**
 * ログインした人の情報を格納するBeansクラス
 *
 * @author ryouta
 */
public class TeacherBeans implements Serializable {
    private String teacher_code = null;
    private String teacher_name = null;
    private String class_code   = null;
    private String course_name  = null;
    private String grade        = null;
    private String class_name   = null;

    /**
     * @return このクラスのteacher_codeの値
     */
    public String getTeacher_code() {
        return teacher_code;
    }

    /**
     * @param teacher_code このクラスのteacher_codeに格納する値
     */
    public void setTeacher_code(String teacher_code) {
        this.teacher_code = teacher_code;
    }

    /**
     * @return このクラスのteacher_nameの値
     */
    public String getTeacher_name() {
        return teacher_name;
    }

    /**
     * @param teacher_name このクラスのteacher_nameに格納する値
     */
    public void setTeacher_name(String teacher_name) {
        this.teacher_name = teacher_name;
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
     * @return このクラスのcourse_nameの値
     */
    public String getCourse_name() {
        return course_name;
    }

    /**
     * @param course_name このクラスのcourse_nameに格納する値
     */
    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

    /**
     * @return このクラスのgradeの値
     */
    public String getGrade() {
        return grade;
    }

    /**
     * @param grade このクラスのgradeに格納する値
     */
    public void setGrade(String grade) {
        this.grade = grade;
    }

    /**
     * @return このクラスのclass_nameの値
     */
    public String getClass_name() {
        return class_name;
    }

    /**
     * @param class_name このクラスのclass_nameに格納する値
     */
    public void setClass_name(String class_name) {
        this.class_name = class_name;
    }

}
