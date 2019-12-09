package diary.servlet;

import diary.bean.DutyBeans;
import diary.bean.TeacherBeans;
import diary.commmon.TeacherErrorCheck;
import diary.dao.StudentListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 今日の日誌当番が登録済みか確認してから日誌当番を選択する画面に遷移するServletクラス
 */
@WebServlet("/dutyinsertselect")
public class DutyInsertSelectServlet extends HttpServlet {

    /**
     * 今日の日誌当番が登録済みか確認してから日誌当番を選択する画面に遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DutySelectServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");

        TeacherErrorCheck error_check = new TeacherErrorCheck();
        boolean is_login = error_check.checkLogin(teacher_beans);

        if (is_login) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            String today = (String) session.getAttribute("today");

            StudentListDao duty_dao = new StudentListDao();
            boolean is_registering = duty_dao.checkTodayDuty(class_code, today);

            //登録済みなら
            if (is_registering) {
                session.setAttribute("error-message", "今日の日誌当番はすでに登録済みです");
                response.sendRedirect("teachermenu");
            } else {
                List<DutyBeans> student_list = duty_dao.fetchSortedStudentListFromDb(class_code, "student_id", "ASC");

                session.setAttribute("student-list", student_list);
                request.getRequestDispatcher("WEB-INF/jsp/dutyInsertSelect.jsp").forward(request, response);
            }
        } else {
            response.sendRedirect("teachererror");
        }
    }
}
