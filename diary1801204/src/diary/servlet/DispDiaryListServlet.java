
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
import java.util.List;

/**
 * 学生の日誌のリストを取得した後登録修正削除選択画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/list")
public class DispDiaryListServlet extends HttpServlet {

    /**
     * ログインした学生の学籍番号から取得した日誌のリストを取得した後登録修正削除画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DispDiaryListServlet"); //test

        HttpSession session = request.getSession();

        DiaryDao diary_dao = new DiaryDao();
        List<DiaryBeans> diary_list = diary_dao.fetchAllDiaryListFromDb();

        session.setAttribute("diary-list", diary_list);
        request.getRequestDispatcher("WEB-INF/jsp/dispDiaryList.jsp").forward(request, response);
    }
}
