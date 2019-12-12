package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.StudentBeans;
import diary.dao.CommonDiaryDao;
import diary.dao.StudentDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 指定された単語で曖昧検索した後、画面遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/studentsearch")
public class StudentSearchServlet extends HttpServlet {

    /**
     * 指定された単語で曖昧検索した後、画面遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("StudentSearchServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login_info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String search_word = request.getParameter("search-word");
        String from_jsp_name = request.getParameter("from-jsp-name");

        List<DiaryBeans> searched_diary_list = null;

        //検索を実行した画面ごとに処理を分岐
        if (from_jsp_name.equals("diaryManipulationSelect")) {
            String student_id = ((StudentBeans) session.getAttribute("login_info")).getStudent_id();

            StudentDiaryDao diary_dao = new StudentDiaryDao();
            searched_diary_list = diary_dao.fetchSearchedDiaryListFromDb(student_id, search_word);
        } else if (from_jsp_name.equals("dispDiaryList")) {
            CommonDiaryDao diary_dao = new CommonDiaryDao();
            searched_diary_list = diary_dao.fetchSearchedDiaryListFromDb("", search_word);
        }

        //遷移先のURL生成
        String url = "WEB-INF/jsp/" + from_jsp_name + ".jsp";

        session.setAttribute("diary_list", searched_diary_list);
        request.setAttribute("from_jsp_name", from_jsp_name);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiarySearchServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("studenterror");
    }
}
