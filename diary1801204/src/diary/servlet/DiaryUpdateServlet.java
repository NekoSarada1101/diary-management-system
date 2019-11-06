
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
 * 入力された日誌情報でデータベースを更新するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryupdate")
public class DiaryUpdateServlet extends HttpServlet {

    /**
     * 日誌情報でデータベースを更新する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryUpdateServlet"); //test

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");

        DiaryDao diary_dao = new DiaryDao();
        diary_dao.updateDiaryToDb(diary_beans);

        session.removeAttribute("diary-beans");
        response.sendRedirect("diaryupdatecomplete");
    }
}
