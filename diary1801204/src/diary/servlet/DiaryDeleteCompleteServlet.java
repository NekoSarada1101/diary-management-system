
package diary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日誌削除完了画面へ遷移するServletクラス
 *
 * @author ryo
 */
@WebServlet("/diarydeletecomplete")
public class DiaryDeleteCompleteServlet extends HttpServlet {

    /**
     * 日誌削除完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/jsp/diaryDeleteComplete.jsp").forward(request, response);
    }
}
