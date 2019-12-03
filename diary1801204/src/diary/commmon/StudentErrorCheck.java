package diary.commmon;

import diary.bean.StudentBeans;

public class StudentErrorCheck {

    public boolean checkLogin(StudentBeans student_beans) {
        boolean is_login = false;
        try {
            if (student_beans.getStudent_id() == null) ;
            is_login = true;
        } catch (Exception e) {
            is_login = false;
        }
        return is_login;
    }
}
