package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.DutyBeans;
import diary.bean.TeacherBeans;
import diary.dao.DutyDao;
import diary.dao.StudentDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 入力された日誌当番情報でデータベースを更新するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyupdate")
public class DutyUpdateServlet extends HttpServlet {

    /**
     * 日誌当番情報でデータベースを更新する
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

        DutyBeans duty_beans = (DutyBeans) session.getAttribute("duty_beans");

        DutyDao duty_dao = new DutyDao();
        duty_dao.updateDutyToDb(duty_beans);

        DiaryBeans diary_beans = new DiaryBeans();
        diary_beans.setClass_code(duty_beans.getClass_code());
        diary_beans.setInsert_date(duty_beans.getInsert_date());

        StudentDiaryDao diary_dao = new StudentDiaryDao();
        diary_dao.deleteDiaryFromDb(diary_beans);

        session.removeAttribute("duty_beans");
        response.sendRedirect("dutyupdatecomplete");
    }
}
