package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.DutyBeans;
import diary.bean.StudentBeans;
import diary.bean.TeacherBeans;
import diary.dao.*;

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
@WebServlet("/search")
public class DiarySearchServlet extends HttpServlet {

    /**
     * 指定された単語で曖昧検索した後、画面遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiarySearchServlet"); //text

        String search_word = request.getParameter("search-word");
        String from_jsp_name = request.getParameter("from-jsp-name");

        HttpSession session = request.getSession();
        List<DiaryBeans> searched_diary_list = null;
        List<DutyBeans> searched_duty_list = null;

        //検索を実行した画面ごとに処理を分岐
        if (from_jsp_name.equals("diaryManipulationSelect")) {
            String student_id = ((StudentBeans) session.getAttribute("login-info")).getStudent_id();

            StudentDiaryDao diary_dao = new StudentDiaryDao();
            searched_diary_list = diary_dao.fetchSearchedDiaryListFromDb(student_id, search_word);

        } else if (from_jsp_name.equals("commentManipulationSelect")) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            TeacherDiaryDao diary_dao = new TeacherDiaryDao();
            searched_diary_list = diary_dao.fetchSearchedDiaryListFromDb(class_code, search_word);

        } else if (from_jsp_name.equals("dispDiaryList")) {
            CommonDiaryDao diary_dao = new CommonDiaryDao();
            searched_diary_list = diary_dao.fetchSearchedDiaryListFromDb("", search_word);

        } else if (from_jsp_name.equals("dutySelect")) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            StudentListDao duty_dao = new StudentListDao();
            searched_duty_list = duty_dao.fetchSearchedStudentListFromDb(class_code, search_word);
        } else if (from_jsp_name.equals("dutyUpdateSelect")) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            DutyDao duty_dao = new DutyDao();
            searched_duty_list = duty_dao.fetchSearchedDutyListFromDb(class_code, search_word);
        } else if (from_jsp_name.equals("dutyUpdateInsert")) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            StudentListDao duty_dao = new StudentListDao();
            searched_duty_list = duty_dao.fetchSearchedStudentListFromDb(class_code, search_word);
        }

        String url = "WEB-INF/jsp/" + from_jsp_name + ".jsp";

        session.setAttribute("diary-list", searched_diary_list);
        session.setAttribute("duty-list", searched_duty_list);
        request.setAttribute("from-jsp-name", from_jsp_name);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
