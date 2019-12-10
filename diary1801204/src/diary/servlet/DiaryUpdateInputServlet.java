package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.StudentBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 日誌操作選択画面で指定した日誌の情報を取得した後、日誌修正画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryupdateinput")
public class DiaryUpdateInputServlet extends HttpServlet {

    /**
     * 日誌操作選択画面で指定された日誌のリスト内の位置を取得し日誌の情報を取得した後、日誌修正画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryUpdateInputServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        StudentBeans student_beans = (StudentBeans) session.getAttribute("login-info");
        if (student_beans == null) {
            response.sendRedirect("studenterror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");

        //日誌操作選択画面で選択した日誌情報をリストから取得する
        DiaryBeans diary_beans = null;
        try {
            int i = Integer.parseInt(request.getParameter("select-diary"));
            diary_beans = diary_list.get(i);
        } catch (NumberFormatException e) {
            diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
        }

        session.setAttribute("diary-beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/diaryUpdateInput.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryUpdateInputServlet");
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        response.sendRedirect("studenterror");
    }
}
