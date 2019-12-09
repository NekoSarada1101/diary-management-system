package diary.commmon;

import diary.bean.TeacherBeans;

public class TeacherErrorCheck {

    public boolean checkLogin(TeacherBeans teacher_beans) {
        boolean is_login = false;
        try {
            if (teacher_beans.getTeacher_code() != null) is_login = true;
        } catch (Exception e) {
            is_login = false;
        }
        return is_login;
    }
}
