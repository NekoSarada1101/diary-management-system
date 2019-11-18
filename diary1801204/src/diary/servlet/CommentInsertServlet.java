package diary.servlet;

import diary.bean.DiaryBeans;
import diary.dao.DiaryDao;
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
@WebServlet("/commentinsert")
public class CommentInsertServlet extends HttpServlet {

    /**
     * コメント情報をデータベースに登録する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommentInsertServlet"); //test

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");

        DiaryDao diary_dao = new TeacherDiaryDao();
        diary_dao.insertDiaryToDb(diary_beans);

        session.removeAttribute("diary-beans");

        response.sendRedirect("commentinsertcomplete");
    }
}
