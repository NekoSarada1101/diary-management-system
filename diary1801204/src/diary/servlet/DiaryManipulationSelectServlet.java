
package diary.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import diary.bean.DiaryBeans;
import diary.bean.LoginInfoBeans;
import diary.dao.DiaryDao;

@WebServlet("/select")
public class DiaryManipulationSelectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String student_id = ((LoginInfoBeans) session.getAttribute("login-info")).getStudent_id();

        DiaryDao diaryDao = new DiaryDao();
        List<DiaryBeans> diaryList = diaryDao.getDiaryListFromDb(student_id);

        session.setAttribute("diary-list", diaryList);
        request.getRequestDispatcher("WEB-INF/jsp/diaryManipulationSelect.jsp").forward(request, response);
    }
}
