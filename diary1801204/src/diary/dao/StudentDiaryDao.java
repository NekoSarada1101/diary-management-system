
package diary.dao;

import diary.bean.DiaryBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースから日誌の情報を取得、更新、削除を行うクラス
 *
 * @author ryouta
 */
public class StudentDiaryDao extends DiaryDao {

    /**
     * データベースに今日の日付と同じ日誌の情報が登録されているか確認する
     *
     * @param class_code ログインしている学生のクラスコード
     * @param today      今日の日付
     * @return 登録済みならtrue
     */
    public boolean checkTodayDiaryRegistered(String class_code, String today) {
        //test
        System.out.println("StudentDiaryDao : checkTodayDiaryRegistered");
        System.out.println("param : class_code = " + class_code);
        System.out.println("param : today = " + today);

        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary WHERE class_code = ? AND insert_date = ?");
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


    //INSERT
    @Override
    protected String createInsertSqlSentence() {
        String sql = "INSERT INTO diary (class_code, insert_date, student_id, good_point, bad_point, student_comment) VALUES (?, ?, ?, ?, ?, ?)";
        return sql;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfInsertSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans) {
        try {
            stmt.setString(1, diary_beans.getClass_code());
            stmt.setString(2, diary_beans.getInsert_date());
            stmt.setString(3, diary_beans.getStudent_id());
            stmt.setString(4, diary_beans.getGood_point());
            stmt.setString(5, diary_beans.getBad_point());
            stmt.setString(6, diary_beans.getStudent_comment());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    //DELETE
    @Override
    protected String createDeleteSqlSentence() {
        String sql = "DELETE FROM diary WHERE class_code = ? AND insert_date = ?";
        return sql;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfDeleteSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans) {
        try {
            stmt.setString(1, diary_beans.getClass_code());
            stmt.setString(2, diary_beans.getInsert_date());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    //UPDATE
    @Override
    protected String createUpdateSqlSentence() {
        String sql = "UPDATE diary SET good_point = ?, bad_point = ?, student_comment = ? WHERE class_code = ? AND insert_date = ?";
        return sql;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfUpdateSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans) {
        try {
            stmt.setString(1, diary_beans.getGood_point());
            stmt.setString(2, diary_beans.getBad_point());
            stmt.setString(3, diary_beans.getStudent_comment());
            stmt.setString(4, diary_beans.getClass_code());
            stmt.setString(5, diary_beans.getInsert_date());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    //SORT
    @Override
    protected String createSortSqlSentence(String sort_column, String sort_order) {
        String sql = "SELECT * FROM diary WHERE student_id = ? ORDER BY ";

        boolean is_sort_allowed = false;

        switch (sort_column) {
            case "insert_date":
                is_sort_allowed = true;
                break;
            case "good_point":
                is_sort_allowed = true;
                break;
            case "bad_point":
                is_sort_allowed = true;
                break;
            case "student_comment":
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

    @Override
    protected PreparedStatement configureValueInPlaceholderOfSortSqlSentence(PreparedStatement stmt, String condition) {
        try {
            stmt.setString(1, condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    //SEARCH
    @Override
    protected String createSearchSqlSentence() {
        String sql = "SELECT * FROM diary WHERE (insert_date LIKE ? OR good_point LIKE ? OR bad_point LIKE ? OR student_comment LIKE ?) AND student_id = ?";
        return sql;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfSearchSqlSentence(PreparedStatement stmt, String condition, String search_word) {
        try {
            stmt.setString(1, "%" + search_word + "%");
            stmt.setString(2, "%" + search_word + "%");
            stmt.setString(3, "%" + search_word + "%");
            stmt.setString(4, "%" + search_word + "%");
            stmt.setString(5, condition);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }
}
