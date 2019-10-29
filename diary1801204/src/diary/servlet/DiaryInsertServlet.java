
package diary.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.bean.DiaryBeans;
import diary.dao.DiaryDao;

@WebServlet("/diaryinsert")
public class DiaryInsertServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        DiaryBeans diaryBeans = (DiaryBeans) session.getAttribute("diary-beans");

        DiaryDao diaryDao = new DiaryDao();
        boolean is_success = diaryDao.insertDiaryToDb(diaryBeans);

        if (is_success) {
            response.sendRedirect("diaryinsertcomplete");
        } else {
            session.setAttribute("error-message", "今日はすでに日誌を登録しています。");
            response.sendRedirect("select");
        }

    }
}
