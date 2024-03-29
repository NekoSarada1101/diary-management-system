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
 * コメント操作選択画面で指定した日誌の情報を取得した後、コメント削除確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentdeletecheck")
public class CommentDeleteCheckServlet extends HttpServlet {

    /**
     * コメント操作選択画面で指定された日誌のリスト内の位置を取得し日誌の情報を取得した後、コメント削除確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher_beans");
        if (teacher_beans == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary_list");

        //コメント操作選択画面で選択した日誌情報をリストから取得する
        int i = Integer.parseInt(request.getParameter("select-diary"));
        DiaryBeans diary_beans = diary_list.get(i);

        session.setAttribute("diary_beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/commentDeleteCheck.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        response.sendRedirect("teachererror");
    }
}
