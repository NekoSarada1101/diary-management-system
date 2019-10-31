package diary.servlet;

import diary.bean.LoginInfoBeans;
import diary.dao.UserDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ログイン認証を行った後画面遷移を行うServletクラス
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
        System.out.println("AuthServlet"); //test

        String student_id       = request.getParameter("student-id");
        String student_password = request.getParameter("student-password");

        UserDao userDao = new UserDao();
        LoginInfoBeans loginInfoBeans = userDao.getLoginInfo(student_id, student_password);

        HttpSession session = request.getSession();
        if (loginInfoBeans != null) {
            session.setAttribute("login-info", loginInfoBeans);
            response.sendRedirect("menu");

        } else {
            session.setAttribute("error-message", "学籍番号またはパスワードが間違っています");
            response.sendRedirect("login");
        }
    }
}
