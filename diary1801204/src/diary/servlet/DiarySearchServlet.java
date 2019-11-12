package diary.servlet;

import diary.bean.DiaryBeans;
import diary.bean.LoginInfoBeans;
import diary.dao.DiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class DiarySearchServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiarySearchServlet"); //text

        String search_word = request.getParameter("search-word");
        String from_jsp_name = request.getParameter("from-jsp-name");

        HttpSession session = request.getSession();
        String student_id = ((LoginInfoBeans) session.getAttribute("login-info")).getStudent_id();

        DiaryDao diary_dao = new DiaryDao();
        List<DiaryBeans> sorted_diary_list = diary_dao.fetchSearchedDiaryListFromDb(student_id, search_word, from_jsp_name);

        session.setAttribute("diary-list", sorted_diary_list);
        request.getRequestDispatcher("WEB-INF/jsp/diaryManipulationSelect.jsp").forward(request, response);
    }
}
