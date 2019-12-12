package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.TeacherBeans;
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
@WebServlet("/teacherlist")
public class TeacherDiaryListServlet extends HttpServlet {

    /**
     * 日誌のリストを取得した後、日誌閲覧画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("TeacherDiaryListServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher_beans");
        if (teacher_beans == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String menu_name = request.getParameter("menu-name");

        CommonDiaryDao diary_dao = new CommonDiaryDao();
        List<DiaryBeans> diary_list = diary_dao.fetchSortedDiaryListFromDb("", "insert_date", "DESC");

        session.setAttribute("diary_list", diary_list);
        session.setAttribute("menu_name", menu_name);
        request.getRequestDispatcher("WEB-INF/jsp/dispDiaryList.jsp").forward(request, response);
    }
}
