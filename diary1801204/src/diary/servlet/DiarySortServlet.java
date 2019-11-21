package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.DutyBeans;
import diary.bean.StudentBeans;
import diary.bean.TeacherBeans;
import diary.dao.CommonDiaryDao;
import diary.dao.StudentDiaryDao;
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
 * 指定したカラムを指定した順番にソートした後登録修正削除選択画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/sort")
public class DiarySortServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiarySortServlet"); //text

        String sort_column = request.getParameter("sort-column");
        String sort_order = request.getParameter("sort-order");
        String from_jsp_name = request.getParameter("from-jsp-name");

        HttpSession session = request.getSession();
        List<DiaryBeans> sorted_diary_list = null;
        List<DutyBeans> searched_duty_list = null;

        //ソートを実行した画面ごとに処理を分岐
        if (from_jsp_name.equals("diaryManipulationSelect")) {
            String student_id = ((StudentBeans) session.getAttribute("login-info")).getStudent_id();

            StudentDiaryDao diary_dao = new StudentDiaryDao();
            sorted_diary_list = diary_dao.fetchSortedDiaryListFromDb(student_id, sort_column, sort_order);

        } else if (from_jsp_name.equals("commentManipulationSelect")) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            TeacherDiaryDao diary_dao = new TeacherDiaryDao();
            sorted_diary_list = diary_dao.fetchSortedDiaryListFromDb(class_code, sort_column, sort_order);

        } else if (from_jsp_name.equals("dispDiaryList")) {
            CommonDiaryDao diary_dao = new CommonDiaryDao();
            sorted_diary_list = diary_dao.fetchSortedDiaryListFromDb("", sort_column, sort_order);

        } else if (from_jsp_name.equals("dutySelect")) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            StudentListDao duty_dao = new StudentListDao();
            searched_duty_list = duty_dao.fetchSortedStudentListFromDb(class_code, sort_column, sort_order);
        }

        String url = "WEB-INF/jsp/" + from_jsp_name + ".jsp";

        session.setAttribute("diary-list", sorted_diary_list);
        session.setAttribute("duty-list", searched_duty_list);
        request.setAttribute("from-jsp-name", from_jsp_name);
        request.getRequestDispatcher(url).forward(request, response);
    }
}
