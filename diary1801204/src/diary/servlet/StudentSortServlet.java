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
 * 指定したカラムを指定した順番にソートした後、画面遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/studentsort")
public class StudentSortServlet extends HttpServlet {

    /**
     * 指定したカラムを指定した順番にソートした後、画面遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("StudentSortServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login-info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String sort_column = request.getParameter("sort-column");
        String sort_order = request.getParameter("sort-order");
        String from_jsp_name = request.getParameter("from-jsp-name");

        List<DiaryBeans> sorted_diary_list = null;

        //ソートを実行した画面ごとに処理を分岐
        if (from_jsp_name.equals("diaryManipulationSelect")) {
            String student_id = ((StudentBeans) session.getAttribute("login-info")).getStudent_id();

            StudentDiaryDao diary_dao = new StudentDiaryDao();
            sorted_diary_list = diary_dao.fetchSortedDiaryListFromDb(student_id, sort_column, sort_order);

        } else if (from_jsp_name.equals("dispDiaryList")) {
            CommonDiaryDao diary_dao = new CommonDiaryDao();
            sorted_diary_list = diary_dao.fetchSortedDiaryListFromDb("", sort_column, sort_order);
        }

        //遷移先のURL生成
        String url = "WEB-INF/jsp/" + from_jsp_name + ".jsp";

        session.setAttribute("diary-list", sorted_diary_list);
        request.setAttribute("from-jsp-name", from_jsp_name);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiarySortServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("studenterror");
    }
}
