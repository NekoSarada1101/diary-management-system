package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.StudentBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 日誌修正画面で入力した値を取得した後、日誌修正確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryupdatecheck")
public class DiaryUpdateCheckServlet extends HttpServlet {

    /**
     * 日誌修正画面で入力した値をBeansに格納した後、日誌修正確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryUpdateCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login_info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String good_point = request.getParameter("good-point");
        String bad_point = request.getParameter("bad-point");
        String student_comment = request.getParameter("student-comment");

        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary_beans");
        diary_beans.setGood_point(good_point);
        diary_beans.setBad_point(bad_point);
        diary_beans.setStudent_comment(student_comment);

        session.setAttribute("diary_beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/diaryUpdateCheck.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryUpdateCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("studenterror");
    }
}
