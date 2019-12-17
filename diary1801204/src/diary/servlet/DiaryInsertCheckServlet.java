package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.StudentBeans;
import diary.commmon.StudentErrorCheck;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 日誌登録画面で入力した値を取得した後、日誌登録確認画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryinsertcheck")
public class DiaryInsertCheckServlet extends HttpServlet {

    /**
     * 日誌登録画面で入力した値、本日の日付、ログインしている学生の学籍番号を取得しBeansに格納した後、日誌登録確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryInsertCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login_info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String good_point = request.getParameter("good-point");
        String bad_point = request.getParameter("bad-point");
        String student_comment = request.getParameter("student-comment");

        //入力が30字以下かチェックする/////////////////////////////////////////////////////////////////////
        boolean is_30_length_or_less = checkLength(good_point, bad_point, student_comment);
        if (!is_30_length_or_less) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        String today = (String) session.getAttribute("today");

        String class_code = ((StudentBeans) session.getAttribute("login_info")).getClass_code();
        String student_id = ((StudentBeans) session.getAttribute("login_info")).getStudent_id();

        DiaryBeans diary_beans = new DiaryBeans();
        diary_beans.setClass_code(class_code);
        diary_beans.setInsert_date(today);
        diary_beans.setStudent_id(student_id);
        diary_beans.setGood_point(good_point);
        diary_beans.setBad_point(bad_point);
        diary_beans.setStudent_comment(student_comment);

        session.setAttribute("diary_beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/diaryInsertCheck.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryInsertCheckServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("studenterror");
    }

    private boolean checkLength(String good_point, String bad_point, String student_comment) {
        StudentErrorCheck error_check = new StudentErrorCheck();
        if (!error_check.checkMaxLength_30(good_point)) {
            return false;
        } else if (!error_check.checkMaxLength_30(bad_point)) {
            return false;
        } else if (!error_check.checkMaxLength_30(student_comment)) {
            return false;
        }
        return true;
    }
}
