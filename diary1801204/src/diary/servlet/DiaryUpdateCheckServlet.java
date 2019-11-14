package diary.servlet;

import diary.bean.DiaryBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 日誌修正画面で入力した値を取得した後日誌修正確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryupdatecheck")
public class DiaryUpdateCheckServlet extends HttpServlet {

    /**
     * 日誌修正画面で入力した値をBeansに格納した後日誌修正確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryUpdateCheckServlet"); //test

        String good_point      = request.getParameter("good-point");
        String bad_point       = request.getParameter("bad-point");
        String student_comment = request.getParameter("student-comment");

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
        diary_beans.setGood_point     (good_point);
        diary_beans.setBad_point      (bad_point);
        diary_beans.setStudent_comment(student_comment);

        session.setAttribute("diary-beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/diaryUpdateCheck.jsp").forward(request, response);
    }
}
