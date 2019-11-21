package diary.servlet;

import diary.bean.DutyBeans;
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
 * 入力された日誌情報をデータベースに登録するServletクラス
 *
 * @author ryouta
 */
@WebServlet("/dutyinsert")
public class DutyInsertServlet extends HttpServlet {

    /**
     * 日誌情報をデータベースに登録する
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("DutyInsertServlet"); //test

        HttpSession session = request.getSession();
        DutyBeans duty_beans = (DutyBeans) session.getAttribute("duty-beans");

        //今日の日付を取得
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String today = sdf.format(cal.getTime());

        duty_beans.setInsert_date(today);

        DutyDao duty_dao = new DutyDao();
        duty_dao.insertDutyToDb(duty_beans);

        session.removeAttribute("diary-beans");
        response.sendRedirect("dutyinsertcomplete");

    }
}
