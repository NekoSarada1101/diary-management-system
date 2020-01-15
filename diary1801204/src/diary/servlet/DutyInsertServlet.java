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

/**
 * 入力された日誌当番情報をデータベースに登録するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyinsert")
public class DutyInsertServlet extends HttpServlet {

    /**
     * 日誌当番情報をデータベースに登録する
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

        String today = (String) session.getAttribute("today");

        duty_beans.setInsert_date(today);

        DutyDao duty_dao = new DutyDao();
        duty_dao.insertDutyToDb(duty_beans);

        session.removeAttribute("duty_beans");
        response.sendRedirect("dutyinsertcomplete");
    }
}
