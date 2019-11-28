package diary.servlet;

import diary.bean.DutyBeans;
import diary.bean.TeacherBeans;
import diary.dao.StudentListDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 学生のリストを取得した後、日誌当番入力画面に遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyupdateinput")
public class DutyUpdateInputServlet extends HttpServlet {

    /**
     * 学生のリストを取得した後、日誌当番入力画面に遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DutyUpdateInsertServlet");

        HttpSession session = request.getSession();
        String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

        StudentListDao student_list_dao = new StudentListDao();
        List<DutyBeans> student_list = student_list_dao.fetchSortedStudentListFromDb(class_code, "student_id", "ASC");

        session.setAttribute("student-list", student_list);
        request.getRequestDispatcher("WEB-INF/jsp/dutyUpdateInput.jsp").forward(request, response);
    }
}
