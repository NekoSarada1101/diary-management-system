package diary.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 日誌登録画面へ遷移するServletクラス
 * @author ryouta
 */
@WebServlet("/diaryinsertinput")
public class DiaryInsertInputServlet extends HttpServlet {

    /**
     * 日誌登録画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        request.getRequestDispatcher("WEB-INF/jsp/diaryInsertInput.jsp").forward(request, response);
    }
}
