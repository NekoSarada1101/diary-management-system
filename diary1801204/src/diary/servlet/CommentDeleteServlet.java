package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.TeacherBeans;
import diary.dao.TeacherDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 指定されたコメント情報をデータベースから削除するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentdelete")
public class CommentDeleteServlet extends HttpServlet {

    /**
     * コメント情報をデータベースから削除する
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

        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary_beans");

        TeacherDiaryDao diary_dao = new TeacherDiaryDao();
        diary_dao.deleteDiaryFromDb(diary_beans);

        session.removeAttribute("diary_beans");
        response.sendRedirect("commentdeletecomplete");
    }
}
