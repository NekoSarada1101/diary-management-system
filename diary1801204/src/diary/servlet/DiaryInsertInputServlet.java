package diary.servlet;

import diary.bean.LoginInfoBeans;
import diary.dao.StudentDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * 日誌登録画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryinsertinput")
public class DiaryInsertInputServlet extends HttpServlet {

    /**
     * 日誌登録画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryInsertInputServlet"); //test

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        HttpSession session = request.getSession();
        String class_code = ((LoginInfoBeans) session.getAttribute("login-info")).getClass_code();

        StudentDiaryDao diary_dao = new StudentDiaryDao();
        boolean is_registering = diary_dao.checkTodayDiaryRegistered(class_code, today);

        if (is_registering) {
            session.setAttribute("error-message", "今日はすでに日誌を登録しています。");
            response.sendRedirect("select");

        } else {
            request.getRequestDispatcher("WEB-INF/jsp/diaryInsertInput.jsp").forward(request, response);
        }
    }
}
