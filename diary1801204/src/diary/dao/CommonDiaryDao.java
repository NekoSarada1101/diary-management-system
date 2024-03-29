package diary.dao;

import diary.bean.DiaryBeans;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * データベースから日誌の情報の取得を行うクラス
 *
 * @author ryouta
 */
public class CommonDiaryDao extends DiaryDao {

    //SORT
    @Override
    protected String createSortSqlSentence(String sort_column, String sort_order) {
        String sql = "SELECT * FROM diary ORDER BY ";

        boolean is_sort_allowed = false;

        //ソートを許可されたカラムであるかチェックする
        switch (sort_column) {
            case "insert_date":
                is_sort_allowed = true;
                break;
            case "student_id":
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
            case "teacher_comment":
                is_sort_allowed = true;
                break;
            default:
                is_sort_allowed = false;
                break;
        }

        //予約後がASCまたはDESCであるかチェックする
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
        return stmt;
    }


    //SEARCH
    @Override
    protected String createSearchSqlSentence() {
        String sql = "SELECT * FROM diary WHERE insert_date LIKE ? OR student_id LIKE ? OR good_point LIKE ? OR bad_point LIKE ? OR student_comment LIKE ? OR teacher_comment LIKE ?";
        return sql;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfSearchSqlSentence(PreparedStatement stmt, String condition, String search_word) {
        try {
            stmt.setString(1, "%" + search_word + "%");
            stmt.setString(2, "%" + search_word + "%");
            stmt.setString(3, "%" + search_word + "%");
            stmt.setString(4, "%" + search_word + "%");
            stmt.setString(5, "%" + search_word + "%");
            stmt.setString(6, "%" + search_word + "%");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stmt;
    }


    @Override
    protected String createInsertSqlSentence() {
        return null;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfInsertSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans) {
        return null;
    }

    @Override
    protected String createDeleteSqlSentence() {
        return null;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfDeleteSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans) {
        return null;
    }

    @Override
    protected String createUpdateSqlSentence() {
        return null;
    }

    @Override
    protected PreparedStatement configureValueInPlaceholderOfUpdateSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans) {
        return null;
    }
}
