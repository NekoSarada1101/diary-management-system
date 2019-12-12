package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.DutyBeans;
import diary.bean.TeacherBeans;
import diary.dao.DutyDao;
import diary.dao.StudentListDao;
import diary.dao.TeacherDiaryDao;

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
@WebServlet("/teachersearch")
public class TeacherSearchServlet extends HttpServlet {

    /**
     * 指定された単語で曖昧検索した後、画面遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("TeacherSearchServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher_beans");
        if (teacher_beans == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String search_word = request.getParameter("search-word");
        String from_jsp_name = request.getParameter("from-jsp-name");

        List<DiaryBeans> searched_diary_list = null;
        List<DutyBeans> searched_duty_list = null;

        String class_code = ((TeacherBeans) session.getAttribute("teacher_beans")).getClass_code();

        //検索を実行した画面ごとに処理を分岐
        if (from_jsp_name.equals("commentManipulationSelect")) {
            TeacherDiaryDao diary_dao = new TeacherDiaryDao();
            searched_diary_list = diary_dao.fetchSearchedDiaryListFromDb(class_code, search_word);

        } else if (from_jsp_name.equals("dutyInsertSelect")) {
            StudentListDao duty_dao = new StudentListDao();
            searched_duty_list = duty_dao.fetchSearchedStudentListFromDb(class_code, search_word);

        } else if (from_jsp_name.equals("dutyUpdateSelect")) {
            DutyDao duty_dao = new DutyDao();
            searched_duty_list = duty_dao.fetchSearchedDutyListFromDb(class_code, search_word);

        } else if (from_jsp_name.equals("dutyUpdateInput")) {
            StudentListDao duty_dao = new StudentListDao();
            searched_duty_list = duty_dao.fetchSearchedStudentListFromDb(class_code, search_word);
        }

        //遷移先のURL生成
        String url = "WEB-INF/jsp/" + from_jsp_name + ".jsp";

        session.setAttribute("diary_list", searched_diary_list);
        session.setAttribute("student_list", searched_duty_list);
        request.setAttribute("from_jsp_name", from_jsp_name);
        request.getRequestDispatcher(url).forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiarySearchServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("teachererror");
    }
}
