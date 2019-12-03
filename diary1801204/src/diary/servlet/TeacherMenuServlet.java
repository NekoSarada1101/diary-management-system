package diary.servlet;

import diary.bean.TeacherBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * メニュー画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/teachermenu")
public class TeacherMenuServlet extends HttpServlet {

    /**
     * メニュー画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("TeacherMenuServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        HttpSession session = request.getSession();
        List<TeacherBeans> teacher_list = (List<TeacherBeans>) session.getAttribute("teacher-list");

        TeacherBeans teacher_beans = null;
        try {
            int i = Integer.parseInt(request.getParameter("select-class"));
            teacher_beans = teacher_list.get(i);

        } catch (Exception e) {
            teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");
        }

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        session.setAttribute("today", today);
        session.setAttribute("teacher-beans", teacher_beans);
        request.getRequestDispatcher("WEB-INF/jsp/teacherMenu.jsp").forward(request, response);
    }
}
