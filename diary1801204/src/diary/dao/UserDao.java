package diary.dao;

import diary.bean.StudentBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースに接続して学生のログイン処理を行うDaoクラス
 *
 * @author ryouta
 */
public class UserDao extends DaoBase {

    /**
     * 学生のログイン処理とログインした学生の情報の取得
     *
     * @param student_id ログイン画面で入力された学籍番号
     * @param password   ログイン画面で入力されたパスワード
     * @return ログインに成功したらログインした学生の情報 失敗したらnull
     */
    public StudentBeans fetchLoginInfo(String student_id, String password) {
        StudentBeans login_info = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM student WHERE student_id = ? AND student_password = ?");
            stmt.setString(1, student_id);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            rs.next();

            login_info = new StudentBeans();
            login_info.setStudent_id  (rs.getString("student_id"));
            login_info.setClass_code  (rs.getString("class_code"));
            login_info.setStudent_name(rs.getString("student_name"));

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return login_info;
    }

    /**
     * ヘッダーに表示する学生の情報を取得
     *
     * @param student_beans ログインした学生の情報
     * @return ヘッダーに表示する学生の情報
     */
    public String fetchStudentInfo(StudentBeans student_beans) {
        String student_info;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(
                    "SELECT co.course_name, c.grade, c.class_name, s.student_name FROM (student s INNER JOIN class c ON s.class_code = c.class_code) INNER JOIN course co ON c.course_code = co.course_code WHERE s.student_id = ?");
            stmt.setString(1, student_beans.getStudent_id());
            rs = stmt.executeQuery();
            rs.next();

            student_info = rs.getString("course_name");
            student_info += rs.getString("grade");
            student_info += rs.getString("class_name") + " ";
            student_info += rs.getString("student_name");

        } catch (SQLException e) {
            e.printStackTrace();
            return null;

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return student_info;
    }
}
