
package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.StudentBeans;
import diary.dao.StudentDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 学生の日誌のリストを取得した後登録修正削除選択画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/select")
public class DiaryManipulationSelectServlet extends HttpServlet {

    /**
     * ログインした学生の学籍番号から取得した日誌のリストを取得した後登録修正削除画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryManipulationSelectServlet"); //test

        HttpSession session = request.getSession();
        String student_id = ((StudentBeans) session.getAttribute("login-info")).getStudent_id();

        StudentDiaryDao diary_dao = new StudentDiaryDao();
        List<DiaryBeans> diary_list = diary_dao.fetchSortedDiaryListFromDb(student_id, "insert_date", "DESC");

        session.setAttribute("diary-list", diary_list);
        request.getRequestDispatcher("WEB-INF/jsp/diaryManipulationSelect.jsp").forward(request, response);
    }
}
