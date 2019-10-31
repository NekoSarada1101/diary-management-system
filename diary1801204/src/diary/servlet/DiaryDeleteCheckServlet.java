
package diary.servlet;

import diary.bean.DiaryBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * 日誌操作選択画面で指定した日誌の情報を取得した後日誌削除確認画面へ遷移するServletクラス
 *
 * @author ryo
 */
@WebServlet("/diarydeletecheck")
public class DiaryDeleteCheckServlet extends HttpServlet {

    /**
     * 日誌操作選択画面で指定された日誌のリスト内の位置を取得し日誌の情報を取得した後日誌削除確認画面へ遷移する
     * 日誌が指定されていなかったら日誌操作選択画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryDeleteCheckServlet"); //test

        HttpSession session = request.getSession();
        List<DiaryBeans> diaryList = (List<DiaryBeans>) session.getAttribute("diary-list");

        String position_in_diaryList = request.getParameter("select-diary");

        if (position_in_diaryList.equals("")) {
            session.setAttribute("error-message", "修正または削除する日誌を選択してください");
            response.sendRedirect("select");

        } else {
            session.setAttribute("diary-beans", diaryList.get(Integer.parseInt(position_in_diaryList)));
            request.getRequestDispatcher("WEB-INF/jsp/diaryDeleteCheck.jsp").forward(request, response);
        }
    }
}
