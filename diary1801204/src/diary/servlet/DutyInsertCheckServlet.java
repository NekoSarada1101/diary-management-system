package diary.servlet;

import diary.bean.DutyBeans;
import diary.bean.TeacherBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 日誌当番選択画面で指定した学生の情報を取得した後、日誌当番確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyinsertcheck")
public class DutyInsertCheckServlet extends HttpServlet {

    /**
     * 日誌当番選択画面で指定された学生のリスト内の位置を取得し学生の情報を取得した後、日誌当番確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DutyInsertCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");
        if (teacher_beans == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        List<DutyBeans> student_list = (List<DutyBeans>) session.getAttribute("student-list");

        //日誌当番登録選択画面で選択した学生情報をリストから取得する
        int i = Integer.parseInt(request.getParameter("select-student"));
        DutyBeans duty_beans = student_list.get(i);

        session.setAttribute("duty-beans", duty_beans);
        request.getRequestDispatcher("WEB-INF/jsp/dutyInsertCheck.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DutyInsertCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("studenterror");
    }
}
