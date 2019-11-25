package diary.dao;

import diary.bean.DutyBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * データベースから学生の情報の操作を行うクラス
 *
 * @author ryouta
 */
public class StudentListDao extends DaoBase {

    /**
     * データベースに今日の日付と同じ日誌当番の情報が登録されているか確認する
     *
     * @param class_code 教員の担任クラスのクラスコード
     * @param today      今日の日付
     * @return 登録済みならtrue
     */
    public boolean checkTodayDuty(String class_code, String today) {
        //test
        System.out.println("StudentListDao : checkTodayDiaryDuty");
        System.out.println("param : class_code = " + class_code);
        System.out.println("param : today = "      + today);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary_duty WHERE class_code = ? AND insert_date = ?");
            stmt.setString(1, class_code);
            stmt.setString(2, today);
            rs = stmt.executeQuery();

            //もし登録されてなければ
            if (!rs.next()) {
                System.out.println("return : is_registering = false"); //test

                return /* is_registering = */ false;
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return : is_registering = true"); //test

        return /* is_registering = */ true;
    }


    /**
     * 指定されたカラムを指定された順番にソートした学生の情報をすべて取得(SELECT)する
     *
     * @param condition   SQL文のWHERE句に指定する条件
     * @param sort_column ソート対象のカラム名
     * @param sort_order  ソートの順番
     * @return 取得した日誌の情報を格納したリスト
     */
    public List<DutyBeans> fetchSortedStudentListFromDb(String condition, String sort_column, String sort_order) {
        //test
        System.out.println("StudentListDao : fetchSortedStudentListFromDb");
        System.out.println("param : condition = "   + condition);
        System.out.println("param : sort_column = " + sort_column);
        System.out.println("param : sort_order = "  + sort_order);

        DutyBeans duty_beans = null;
        List<DutyBeans> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createSortSqlSentence(sort_column, sort_order));
            stmt.setString(1, condition);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                duty_beans = new DutyBeans();
                duty_beans.setClass_code  (rs.getString("class_code"));
                duty_beans.setStudent_id  (rs.getString("student_id"));
                duty_beans.setStudent_name(rs.getString("student_name"));
                list.add(duty_beans);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return : list = " + list); //test

        return list;
    }

    protected String createSortSqlSentence(String sort_column, String sort_order) {
        String sql = "SELECT * FROM student WHERE class_code = ? ORDER BY ";

        boolean is_sort_allowed = false;

        switch (sort_column) {
            case "student_id":
                is_sort_allowed = true;
                break;
            case "student_name":
                is_sort_allowed = true;
                break;
            default:
                is_sort_allowed = false;
                break;
        }

        if (!(sort_order.equals("ASC") || sort_order.equals("DESC"))) {
            is_sort_allowed = false;
        }

        //許可されたカラムかつ予約語が妥当なもの(ASC,DESC)なら
        if (is_sort_allowed) {
            sql += sort_column + " " + sort_order;
        }
        return sql;
    }


    /**
     * 指定された単語をで曖昧検索を行って抽出された学生の情報をすべて取得(SELECT)する
     *
     * @param condition   SQL文のWHERE句に指定する条件
     * @param search_word 曖昧検索を行う単語
     * @return 取得した日誌の情報を格納したリスト
     */
    public List<DutyBeans> fetchSearchedStudentListFromDb(String condition, String search_word) {
        //test
        System.out.println("StudentListDao : fetchSearchedStudentListFromDb");
        System.out.println("param : condition = "   + condition);
        System.out.println("param : search_word = " + search_word);

        DutyBeans duty_beans = null;
        List<DutyBeans> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createSearchSqlSentence());
            stmt.setString(1, "%" + search_word + "%");
            stmt.setString(2, "%" + search_word + "%");
            stmt.setString(3, condition);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                duty_beans = new DutyBeans();
                duty_beans.setClass_code  (rs.getString("class_code"));
                duty_beans.setStudent_id  (rs.getString("student_id"));
                duty_beans.setStudent_name(rs.getString("student_name"));
                list.add(duty_beans);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("return : list = " + list); //test

        return list;
    }

    protected String createSearchSqlSentence() {
        String sql = "SELECT * FROM student WHERE (student_id LIKE ? OR student_name LIKE ?) AND class_code = ?";
        return sql;
    }
}
