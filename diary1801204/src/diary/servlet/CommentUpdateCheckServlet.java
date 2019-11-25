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
 * コメント入力画面で入力した値を取得した後、コメント更新確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentupdatecheck")
public class CommentUpdateCheckServlet extends HttpServlet {

    /**
     * コメント入力画面で入力した値を取得しBeansに格納した後、コメント更新確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommentUpdateCheckServlet"); //test

        String teacher_comment = request.getParameter("teacher-comment");

        if (teacher_comment.equals("")) teacher_comment = null;

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
        diary_beans.setTeacher_comment(teacher_comment);

        session.setAttribute("diary-beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/commentUpdateCheck.jsp").forward(request, response);
    }
}
