package diary.dao;

import diary.bean.DutyBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * データベースから日誌当番の情報の操作を行うクラス
 *
 * @author ryouta
 */
public class DutyDao extends DaoBase {

    /**
     * データベースに今日の日誌当番の情報が登録されているか確認する
     *
     * @param student_id ログインしている学生の学籍番号
     * @param today      今日の日付
     * @return 登録済みならtrue
     */
    public boolean checkTodayDutyRegistered(String student_id, String today) {
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary_duty WHERE student_id = ? AND insert_date = ?");
            stmt.setString(1, student_id);
            stmt.setString(2, today);
            rs = stmt.executeQuery();

            //もし登録されてなければ
            if (!rs.next()) {
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
        return /* is_registering = */ true;
    }

    /**
     * データベースに日誌当番の情報を登録(INSERT)する
     *
     * @param duty_beans データベースに登録する日誌当番の情報
     */
    public void insertDutyToDb(DutyBeans duty_beans) {
        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("INSERT INTO diary_duty (class_code, insert_date, student_id) VALUES (?, ?, ?)");
            stmt.setString(1, duty_beans.getClass_code());
            stmt.setString(2, duty_beans.getInsert_date());
            stmt.setString(3, duty_beans.getStudent_id());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * データベースの日誌当番の情報を入力した情報に更新(UPDATE)する
     *
     * @param duty_beans 更新する日誌当番の情報
     */
    public void updateDutyToDb(DutyBeans duty_beans) {
        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("UPDATE diary_duty SET class_code = ?, insert_date = ?, student_id = ? WHERE class_code = ? AND insert_date = ?");
            stmt.setString(1, duty_beans.getClass_code());
            stmt.setString(2, duty_beans.getInsert_date());
            stmt.setString(3, duty_beans.getStudent_id());
            stmt.setString(4, duty_beans.getClass_code());
            stmt.setString(5, duty_beans.getInsert_date());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 指定されたカラムを指定された順番にソートした日誌当番の情報をすべて取得(SELECT)する
     *
     * @param condition   SQL文のWERHE句に指定する条件
     * @param sort_column ソート対象のカラム名
     * @param sort_order  ソートの順番
     * @return 取得した日誌当番の情報を格納したリスト
     */
    public List<DutyBeans> fetchSortedDutyListFromDb(String condition, String sort_column, String sort_order) {
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
                duty_beans.setInsert_date (rs.getString("insert_date"));
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
        return list;
    }

    /**
     * 日誌当番の情報をソートするSELECT文を作成する
     *
     * @param sort_column ソート対象のカラム名
     * @param sort_order  ソートの順番
     * @return 作成したSELECT文
     */
    protected String createSortSqlSentence(String sort_column, String sort_order) {
        String sql = "SELECT * FROM diary_duty d INNER JOIN student s ON d.student_id = s.student_id WHERE d.class_code = ? ORDER BY ";

        boolean is_sort_allowed = false;

        switch (sort_column) {
            case "insert_date":
                sort_column = "d.insert_date";
                is_sort_allowed = true;
                break;
            case "student_id":
                sort_column = "d.student_id";
                is_sort_allowed = true;
                break;
            case "student_name":
                sort_column = "s.student_name";
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
     * 指定された単語をで曖昧検索を行って抽出された日誌当番の情報をすべて取得(SELECT)する
     *
     * @param condition   SQL文のWHERE句に指定する条件
     * @param search_word 曖昧検索を行う単語
     * @return 取得した日誌当番の情報を格納したリスト
     */
    public List<DutyBeans> fetchSearchedDutyListFromDb(String condition, String search_word) {
        DutyBeans duty_beans = null;
        List<DutyBeans> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary_duty d INNER JOIN student s ON d.student_id = s.student_id WHERE (d.insert_date LIKE ? OR d.student_id LIKE ? OR s.student_name LIKE ?) AND d.class_code = ?");
            stmt.setString(1, "%" + search_word + "%");
            stmt.setString(2, "%" + search_word + "%");
            stmt.setString(3, "%" + search_word + "%");
            stmt.setString(4, condition);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                duty_beans = new DutyBeans();
                duty_beans.setClass_code  (rs.getString("class_code"));
                duty_beans.setInsert_date (rs.getString("insert_date"));
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
        return list;
    }
}
