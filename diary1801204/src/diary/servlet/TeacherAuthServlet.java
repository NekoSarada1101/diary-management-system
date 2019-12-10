package diary.servlet;

import diary.bean.TeacherBeans;
import diary.dao.TeacherDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * ログイン認証を行った後画面遷移を行うServletクラス
 *
 * @author ryouta
 */
@WebServlet("/teacherauth")
public class TeacherAuthServlet extends HttpServlet {

    /**
     * ログイン認証を行った後ログイン認証に成功すればクラス選択画面へ遷移する
     * 失敗したらログイン画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("TeacherAuthServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String teacher_code = null;
        String teacher_password = null;
        try {
            teacher_code = request.getParameter("teacher-code");
            teacher_password = request.getParameter("teacher-password");
        } catch (Exception e) {
            response.sendRedirect("studenterror");
            return;
        }

        TeacherDao teacher_dao = new TeacherDao();
        List<TeacherBeans> teacher_list = teacher_dao.getLoginInfo(teacher_code, teacher_password);

        HttpSession session = request.getSession();
        //成功したら
        if (teacher_list != null) {
            session.setAttribute("teacher-list", teacher_list);
            response.sendRedirect("class");

            //失敗したら
        } else {
            session.setAttribute("error-message", "ログインに失敗しました。教員番号又はパスワードを確認してください。");
            response.sendRedirect("teacherlogin");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentUpdateCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("teachererror");
    }
}
