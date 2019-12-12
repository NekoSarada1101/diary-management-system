package diary.servlet;

import diary.bean.StudentBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 日誌修正完了画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryupdatecomplete")
public class DiaryUpdateCompleteServlet extends HttpServlet {

    /**
     * 日誌修正完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryUpdateCompleteServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login_info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        request.getRequestDispatcher("WEB-INF/jsp/diaryUpdateComplete.jsp").forward(request, response);
    }
}
