package diary.servlet;

import diary.bean.TeacherBeans;
import diary.commmon.TeacherErrorCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 日誌当番更新完了画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyupdatecomplete")
public class DutyUpdateCompleteServlet extends HttpServlet {

    /**
     * 日誌当番更新完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DutyUpdateCompleteServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////
//ログイン済みかチェックする
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");

        TeacherErrorCheck error_check = new TeacherErrorCheck();
        boolean is_login = error_check.checkLogin(teacher_beans);

        if (is_login) {

            request.getRequestDispatcher("WEB-INF/jsp/dutyUpdateComplete.jsp").forward(request, response);
        } else {
            response.sendRedirect("tachererror");
        }
    }

}
