package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.StudentBeans;
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
 * 日誌のリストを取得した後、日誌閲覧画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/studentlist")
public class StudentDiaryListServlet extends HttpServlet {

    /**
     * 日誌のリストを取得した後、日誌閲覧画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("StudentDiaryListServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login-info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String menu_name = request.getParameter("menu-name");

        CommonDiaryDao diary_dao = new CommonDiaryDao();
        List<DiaryBeans> diary_list = diary_dao.fetchSortedDiaryListFromDb("", "insert_date", "DESC");

        session.setAttribute("diary-list", diary_list);
        session.setAttribute("menu-name", menu_name);
        request.getRequestDispatcher("WEB-INF/jsp/dispDiaryList.jsp").forward(request, response);
    }
}
