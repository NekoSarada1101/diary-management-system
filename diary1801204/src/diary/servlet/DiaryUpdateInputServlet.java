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
 * 日誌操作選択画面で指定した日誌の情報を取得した後日誌修正画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/diaryupdateinput")
public class DiaryUpdateInputServlet extends HttpServlet {

    /**
     * 日誌操作選択画面で指定された日誌のリスト内の位置を取得し日誌の情報を取得した後日誌修正画面へ遷移する
     * 日誌が指定されていなかったら日誌操作選択画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryUpdateInputServlet"); //test

        HttpSession session = request.getSession();
        List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");

        String position_in_diaryList = request.getParameter("select-diary");

        if (position_in_diaryList.equals("")) {
            session.setAttribute("error-message", "Please select a diary to correct or delete");
            response.sendRedirect("select");

        } else {
            session.setAttribute("diary-beans", diary_list.get(Integer.parseInt(position_in_diaryList)));
            request.getRequestDispatcher("WEB-INF/jsp/diaryUpdateInput.jsp").forward(request, response);
        }
    }
}
