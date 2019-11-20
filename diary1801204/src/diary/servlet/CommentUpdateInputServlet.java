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
 * コメント入力画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/commentupdateinput")
public class CommentUpdateInputServlet extends HttpServlet {

    /**
     * コメント入力画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("CommentUpdateInputServlet"); //test

        HttpSession session = request.getSession();
        List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");

        int i = Integer.parseInt(request.getParameter("select-diary"));
        DiaryBeans diary_beans = diary_list.get(i);

        session.setAttribute("diary-beans", diary_beans);
        request.getRequestDispatcher("WEB-INF/jsp/commentUpdateInput.jsp").forward(request, response);
    }
}
