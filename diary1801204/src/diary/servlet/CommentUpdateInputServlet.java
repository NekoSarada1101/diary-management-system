package diary.servlet;

import diary.bean.DiaryBeans;
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
 * コメント操作選択画面で指定した日誌の情報を取得した後、コメント入力画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentupdateinput")
public class CommentUpdateInputServlet extends HttpServlet {

    /**
     * コメント操作選択画面で指定した日誌の情報を取得した後、コメント入力画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentUpdateInputServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");
        if (teacher_beans == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");

        //コメント操作選択画面で選択した日誌情報をリストから取得する
        DiaryBeans diary_beans = null;
        try {
            int i = Integer.parseInt(request.getParameter("select-diary"));
            diary_beans = diary_list.get(i);
        } catch (NumberFormatException e) {
            diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
        }

        session.setAttribute("diary-beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/commentUpdateInput.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentUpdateInputServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("teachererror");
    }
}
