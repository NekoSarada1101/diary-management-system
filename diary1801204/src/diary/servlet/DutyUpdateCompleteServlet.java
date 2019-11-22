package diary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * コメント更新完了画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyupdatecomplete")
public class DutyUpdateCompleteServlet extends HttpServlet {

    /**
     * コメント更新完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DutyUpdateCompleteServlet"); //test

        request.getRequestDispatcher("WEB-INF/jsp/dutyUpdateComplete.jsp").forward(request, response);
    }
}
