package diary.servlet;

import diary.bean.DiaryBeans;
import diary.dao.DiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 入力された日誌情報をデータベースに登録するServletクラス
 * @author ryouta
 */
@WebServlet("/diaryinsert")
public class DiaryInsertServlet extends HttpServlet {

    /**
     * 日誌情報をデータベースに登録する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        DiaryBeans diaryBeans = (DiaryBeans) session.getAttribute("diary-beans");

        DiaryDao diaryDao = new DiaryDao();
        boolean is_success = diaryDao.insertDiaryToDb(diaryBeans);

        session.removeAttribute("diary-beans");

        if (is_success) {
            response.sendRedirect("diaryinsertcomplete");
        } else {
            session.setAttribute("error-message", "今日はすでに日誌を登録しています。");
            response.sendRedirect("select");
        }

    }
}
