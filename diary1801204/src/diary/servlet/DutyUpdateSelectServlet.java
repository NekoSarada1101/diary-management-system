package diary.servlet;

import diary.bean.DutyBeans;
import diary.bean.TeacherBeans;
import diary.dao.DutyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 日誌当番のリストを取得した後、日誌当番更新選択画面に遷移するServletクラス
 */
@WebServlet("/dutyupdateselect")
public class DutyUpdateSelectServlet extends HttpServlet {

    /**
     * 日誌当番のリストを取得した後、日誌当番更新選択画面に遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher_beans");
        if (teacher_beans == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String class_code = ((TeacherBeans) session.getAttribute("teacher_beans")).getClass_code();

        DutyDao duty_dao = new DutyDao();
        List<DutyBeans> duty_list = duty_dao.fetchSortedDutyListFromDb(class_code, "insert_date", "DESC");

        session.setAttribute("student_list", duty_list);
        request.getRequestDispatcher("WEB-INF/jsp/dutyUpdateSelect.jsp").forward(request, response);
    }
}
