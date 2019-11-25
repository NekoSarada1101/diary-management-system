package diary.servlet;

import diary.bean.StudentBeans;
import diary.dao.DutyDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * メニュー画面へ遷移するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/menu")
public class MenuServlet extends HttpServlet {

    /**
     * メニュー画面へ遷移する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("MenuServlet"); //test

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        HttpSession session = request.getSession();
        session.setAttribute("today", today);

        DutyDao duty_dao = new DutyDao();
        boolean is_registering = duty_dao.checkTodayDutyRegistered(((StudentBeans) session.getAttribute("login-info")).getClass_code(), today);

        session.setAttribute("is-registering", is_registering);
        request.getRequestDispatcher("WEB-INF/jsp/menu.jsp").forward(request, response);
    }
}
