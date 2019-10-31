package diary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * ログアウト処理を行った後ログイン画面へ遷移するServletクラス
 * @author ryouta
 */
@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

    /**
     * セッションスコープを全て削除した後ログイン画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("LogoutServlet");

        HttpSession session = request.getSession();
        session.invalidate();
        request.getRequestDispatcher("WEB-INF/jsp/login.jsp").forward(request, response);
    }
}
