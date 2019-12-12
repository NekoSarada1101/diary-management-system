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
 * 日誌削除完了画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diarydeletecomplete")
public class DiaryDeleteCompleteServlet extends HttpServlet {

    /**
     * 日誌削除完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryDeleteCompleteServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login_info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        request.getRequestDispatcher("WEB-INF/jsp/diaryDeleteComplete.jsp").forward(request, response);
    }
}
