
package diary.servlet;

import diary.bean.DiaryBeans;
import diary.dao.CommonDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 日誌のリストを取得した後日誌閲覧画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/list")
public class DispDiaryListServlet extends HttpServlet {

    /**
     * 日誌のリストを取得した後日誌閲覧画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DispDiaryListServlet"); //test

        String menu_name = request.getParameter("menu-name");

        CommonDiaryDao diary_dao = new CommonDiaryDao();
        List<DiaryBeans> diary_list = diary_dao.fetchSortedDiaryListFromDb("", "insert_date", "DESC");

        HttpSession session = request.getSession();
        session.setAttribute("diary-list", diary_list);
        session.setAttribute("menu-name", menu_name);
        request.getRequestDispatcher("WEB-INF/jsp/dispDiaryList.jsp").forward(request, response);
    }
}
