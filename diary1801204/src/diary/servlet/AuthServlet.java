
package diary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.bean.LoginInfoBeans;
import diary.dao.UserDao;

@WebServlet("/auth")
public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String stuent_id = request.getParameter("student_id");
        String password = request.getParameter("password");

        UserDao userDao = new UserDao();

        LoginInfoBeans loginInfoBeans = userDao.getLoginInfo(stuent_id, password);

        HttpSession session = request.getSession();
        if (loginInfoBeans != null) {
            session.setAttribute("loginInfo", loginInfoBeans);
            response.sendRedirect("menu");
        } else {
            session.setAttribute("errorMessage", "学籍番号またはパスワードが間違っています");
            response.sendRedirect("login");
        }
    }
}
