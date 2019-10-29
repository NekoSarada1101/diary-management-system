
package diary.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import diary.bean.DiaryBeans;

/**
 * データベースから日誌の情報を取得、更新、削除を行うクラス
 * @author ryo
 */
public class DiaryDao extends DaoBase {

    /**
     * ログインしている人の日誌の情報をすべて取得してリストにする
     * @param student_id
     * @return 取得した日誌の情報を格納したリスト
     */
    public List<DiaryBeans> getDiaryListFromDb(String student_id) {
        DiaryBeans diaryBeans = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<DiaryBeans> list = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM diary WHERE student_id = ? ORDER BY insert_date DESC");
            stmt.setString(1, student_id);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                diaryBeans = new DiaryBeans();
                diaryBeans.setClass_code(rs.getString("class_code"));
                diaryBeans.setInsert_date(rs.getString("insert_date"));
                diaryBeans.setStudent_id(rs.getString("student_name"));
                diaryBeans.setGood_point(rs.getString("good_point"));
                diaryBeans.setBad_point(rs.getString("bad_point"));
                diaryBeans.setStudent_comment(rs.getString("student_comment"));
                diaryBeans.setTeacher_comment(rs.getString("teacher_comment"));
                list.add(diaryBeans);
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
     * データベースに生徒が入力した日誌の情報を登録する
     * @param diaryBeans データベースに登録する日誌の情報
     * @return 日誌の登録に成功したらtrue
     */
    public boolean insertDiaryToDb(DiaryBeans diaryBeans) {
        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("INSERT INTO diary (class_code, insert_date, student_id, good_point, bad_point, student_comment) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, diaryBeans.getClass_code());
            stmt.setString(2, diaryBeans.getInsert_date());
            stmt.setString(3, diaryBeans.getStudent_id());
            stmt.setString(4, diaryBeans.getGood_point());
            stmt.setString(5, diaryBeans.getBad_point());
            stmt.setString(6, diaryBeans.getStudent_comment());
            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
