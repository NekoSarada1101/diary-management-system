package diary.servlet;

import diary.bean.DiaryBeans;
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
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentDeleteServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");

        TeacherDiaryDao diary_dao = new TeacherDiaryDao();
        diary_dao.deleteDiaryFromDb(diary_beans);

        session.removeAttribute("diary-beans");
        response.sendRedirect("commentdeletecomplete");
    }
}
