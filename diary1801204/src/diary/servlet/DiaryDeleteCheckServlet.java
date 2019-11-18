
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
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryDeleteCheckServlet"); //test

        HttpSession session = request.getSession();
        List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");

        int i = Integer.parseInt(request.getParameter("select-diary"));
        DiaryBeans diary_beans = diary_list.get(i);

        session.setAttribute("diary-beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/diaryDeleteCheck.jsp").forward(request, response);
    }
}
