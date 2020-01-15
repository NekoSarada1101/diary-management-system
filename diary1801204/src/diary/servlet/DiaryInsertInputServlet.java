package diary.servlet;

import diary.bean.StudentBeans;
import diary.dao.StudentDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 今日の日誌が登録済みかを確認してから日誌登録画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryinsertinput")
public class DiaryInsertInputServlet extends HttpServlet {

    /**
     * 今日の日誌が登録済みかを確認してから日誌登録画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login_info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String today = (String) session.getAttribute("today");
        String class_code = ((StudentBeans) session.getAttribute("login_info")).getClass_code();

        StudentDiaryDao diary_dao = new StudentDiaryDao();
        boolean is_registering = diary_dao.checkTodayDiaryRegistered(class_code, today);

        //登録済みなら
        if (is_registering) {
            session.setAttribute("error_message", "今日はすでに日誌を登録しています。");
            response.sendRedirect("select");
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/diaryInsertInput.jsp").forward(request, response);
        }
    }
}
