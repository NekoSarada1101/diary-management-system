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
 * 入力されたコメント情報をデータベースに登録するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentupdate")
public class CommentUpdateServlet extends HttpServlet {

    /**
     * コメント情報をデータベースに登録する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentUpdateServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");

        TeacherDiaryDao diary_dao = new TeacherDiaryDao();
        diary_dao.updateDiaryToDb(diary_beans);

        session.removeAttribute("diary-beans");
        response.sendRedirect("commentupdatecomplete");
    }
}
