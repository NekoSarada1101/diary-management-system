
package diary.servlet;

import diary.bean.DutyBeans;
import diary.dao.StudentDiaryDao;

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
 * @author ryouta
 */
@WebServlet("/dutyupdatecheck")
public class DutyUpdateCheckServlet extends HttpServlet {

    /**
     * 日誌操作選択画面で指定された日誌のリスト内の位置を取得し日誌の情報を取得した後日誌削除確認画面へ遷移する
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DutyUpdateCheckServlet"); //test

        HttpSession session = request.getSession();
        List<DutyBeans> student_list = (List<DutyBeans>) session.getAttribute("student-list");

        int i = Integer.parseInt(request.getParameter("select-student"));
        DutyBeans duty_beans = student_list.get(i);

        //今日の日付を取得
        String today = (String) session.getAttribute("today");

        duty_beans.setInsert_date(today);

        StudentDiaryDao diary_dao = new StudentDiaryDao();
        boolean is_registering = diary_dao.checkTodayDiaryRegistered(duty_beans.getClass_code(), today);

        if (is_registering) {
            session.setAttribute("error-message", "今日の日誌はすでに登録されています。<br>日誌担当を変更した場合、登録済みの日誌は削除されます。");
        }

        session.setAttribute("duty-beans", duty_beans);
        request.getRequestDispatcher("WEB-INF/jsp/dutyUpdateCheck.jsp").forward(request, response);
    }
}
