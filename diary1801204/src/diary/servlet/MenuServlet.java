package diary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * メニュー画面へ遷移するServletクラス
 * @author ryouta
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    /**
     * メニュー画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("MenuServlet"); //test

        request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
    }
}
