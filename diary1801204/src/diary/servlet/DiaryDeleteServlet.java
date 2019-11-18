
package diary.servlet;

import diary.bean.DiaryBeans;
import diary.dao.StudentDiaryDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * 指定された日誌情報をデータベースから削除するServletクラス
 *
 * @author ryo
 */
@WebServlet("/diarydelete")
public class DiaryDeleteServlet extends HttpServlet {

    /**
     * 日誌情報をデータベースから削除する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DiaryDeleteServlet"); //test

        HttpSession session = request.getSession();
        DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");

        StudentDiaryDao diary_dao = new StudentDiaryDao();
        diary_dao.deleteDiaryFromDb(diary_beans);

        session.removeAttribute("diary-beans");
        response.sendRedirect("diarydeletecomplete");
    }
}
