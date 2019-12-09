package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.TeacherBeans;
import diary.commmon.TeacherErrorCheck;
import diary.dao.TeacherDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 学生の日誌のリストを取得した後、登録修正削除選択画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentselect")
public class CommentManipulationSelectServlet extends HttpServlet {

    /**
     * ログインした教員のクラスコードから取得した日誌のリストを取得した後、登録修正削除画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("CommentManipulationSelectServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする
        HttpSession session = request.getSession();
        TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");

        TeacherErrorCheck error_check = new TeacherErrorCheck();
        boolean is_login = error_check.checkLogin(teacher_beans);

        if (is_login) {
            String class_code = ((TeacherBeans) session.getAttribute("teacher-beans")).getClass_code();

            TeacherDiaryDao diary_dao = new TeacherDiaryDao();
            List<DiaryBeans> diary_list = diary_dao.fetchSortedDiaryListFromDb(class_code, "insert_date", "DESC");

            session.setAttribute("diary-list", diary_list);
            request.getRequestDispatcher("WEB-INF/jsp/commentManipulationSelect.jsp").forward(request, response);
        } else {
            response.sendRedirect("tachererror");
        }
    }
}
