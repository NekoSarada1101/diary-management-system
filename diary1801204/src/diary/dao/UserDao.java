package diary.dao;

import diary.bean.StudentBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースに接続して学生のログイン処理を行うクラス
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
    public StudentBeans getLoginInfo(String student_id, String password) {
        //test
        System.out.println("UserDao : getLoginInfo");
        System.out.println("param : student_id = " + student_id);
        System.out.println("param : password = "   + password);

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
            System.out.println("return : null"); //test
            return null;

        } finally {
            try {
                this.dbClose();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //test
        System.out.println("return : loginInfo = " + login_info);
        System.out.println("                   : student_id = " + login_info.getStudent_id());
        System.out.println("                   : class_code = " + login_info.getClass_code());
        System.out.println("                   : student_name = " + login_info.getStudent_name());

        return login_info;
    }
}
