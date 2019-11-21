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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@WebServlet("/dutyselect")
public class DutySelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DutySelectServlet");

        HttpSession session = request.getSession();
        String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        StudentListDao duty_dao = new StudentListDao();
        boolean is_registering = duty_dao.checkTodayDutyRegistered(class_code, today);

        if (is_registering) {
            session.setAttribute("error-message", "今日の日誌当番はすでに登録済みです");
            response.sendRedirect("teachermenu");
        } else {
            List<DutyBeans> student_list = duty_dao.fetchSortedStudentListFromDb(class_code, "student_id", "ASC");

            session.setAttribute("duty-list", student_list);
            request.getRequestDispatcher("WEB-INF/jsp/dutySelect.jsp").forward(request, response);
        }
    }
}
