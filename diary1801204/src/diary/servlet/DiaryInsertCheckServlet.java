package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.LoginInfoBeans;

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
 * 日誌登録画面で入力した値を取得した後日誌登録確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryinsertcheck")
public class DiaryInsertCheckServlet extends HttpServlet {

    /**
     * 日誌登録画面で入力した値、本日の日付、ログインしている学生の学籍番号を取得しBeansに格納した後日誌登録確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryInsertCheckServlet"); //test

        String good_point      = request.getParameter("good-point");
        String bad_point       = request.getParameter("bad-point");
        String student_comment = request.getParameter("student-comment");

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        HttpSession session = request.getSession();
        String class_code = ((LoginInfoBeans) session.getAttribute("login-info")).getClass_code();
        String student_id = ((LoginInfoBeans) session.getAttribute("login-info")).getStudent_id();

        DiaryBeans diaryBeans = new DiaryBeans();
        diaryBeans.setClass_code(class_code);
        diaryBeans.setInsert_date(today);
        diaryBeans.setStudent_id(student_id);
        diaryBeans.setGood_point(good_point);
        diaryBeans.setBad_point(bad_point);
        diaryBeans.setStudent_comment(student_comment);

        session.setAttribute("diary-beans", diaryBeans);
        request.getRequestDispatcher("WEB-INF/jsp/diaryInsertCheck.jsp").forward(request, response);
    }
}
