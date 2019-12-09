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
 * コメント削除完了画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentdeletecomplete")
public class CommentDeleteCompleteServlet extends HttpServlet {

    /**
     * コメント削除完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentDeleteCompleteServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");

        TeacherErrorCheck error_check = new TeacherErrorCheck();
        boolean is_login = error_check.checkLogin(teacher_beans);

        if (is_login) {
            request.getRequestDispatcher("WEB-INF/jsp/commentDeleteComplete.jsp").forward(request, response);
        } else {
            response.sendRedirect("teachererror");
        }
    }
}
