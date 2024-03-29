package diary.servlet;

import diary.bean.TeacherBeans;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * クラス選択画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/class")
public class ClassSelectServlet extends HttpServlet {

    /**
     * 日誌登録完了画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //ログイン済みかチェックする///////////////////////////////////////////////////////////////////////
        HttpSession session = request.getSession();
        List<TeacherBeans> teacher_list = (List<TeacherBeans>) session.getAttribute("teacher_list");
        if (teacher_list == null) {
            response.sendRedirect("teachererror");
            return;
        }
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        if (teacher_list.size() == 1) {
            session.setAttribute("teacher_beans", teacher_list.get(0));
            response.sendRedirect("teachermenu");
        } else {
            request.getRequestDispatcher("WEB-INF/jsp/classSelect.jsp").forward(request, response);
        }
    }
}
