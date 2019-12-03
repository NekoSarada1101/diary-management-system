package diary.servlet;

import diary.bean.StudentBeans;
import diary.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ログイン認証を行った後、画面遷移を行うServletクラス
 *
 * @author ryouta
 */
@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    /**
     * ログイン認証を行った後ログイン認証に成功すればメニュー画面へ遷移する
     * 失敗したらログイン画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("AuthServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String student_id       = request.getParameter("student-id");
        String student_password = request.getParameter("student-password");

        UserDao user_dao = new UserDao();
        StudentBeans login_info_beans = user_dao.getLoginInfo(student_id, student_password);

        HttpSession session = request.getSession();
        //成功したら
        if (login_info_beans != null) {
            session.setAttribute("login-info", login_info_beans);
            response.sendRedirect("menu");

        //失敗したら
        } else {
            session.setAttribute("error-message", "ログインに失敗しました。学籍番号又はパスワードを確認してください。");
            response.sendRedirect("login");
        }
    }
}
