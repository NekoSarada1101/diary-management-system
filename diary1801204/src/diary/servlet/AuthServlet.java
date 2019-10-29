
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

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String stuent_id = request.getParameter("student-id");
        String student_password = request.getParameter("student-password");

        UserDao userDao = new UserDao();

        LoginInfoBeans loginInfoBeans = userDao.getLoginInfo(stuent_id, student_password);

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
