package diary.dao;

import diary.bean.LoginInfoBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * データベースに接続して学生のログイン処理を行うクラス
 * @author ryouta
 */
public class UserDao extends DaoBase {

    /**
     * 学生のログイン処理とログインした学生の情報の取得
     * @param student_id ログイン画面で入力された学籍番号
     * @param password ログイン画面で入力されたパスワード
     * @return ログインに成功したらログインした学生の情報 失敗したらnull
     */
    public LoginInfoBeans getLoginInfo(String student_id, String password) {
        System.out.println("DiaryDao : getLoginInfo");
        System.out.println("param : student_id = " + student_id);
        System.out.println("param : password = " + password);

        LoginInfoBeans loginInfo = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM student WHERE student_id = ? AND student_password = ?");
            stmt.setString(1, student_id);
            stmt.setString(2, password);

            rs = stmt.executeQuery();
            rs.next();

            loginInfo = new LoginInfoBeans();
            loginInfo.setStudent_id(rs.getString("student_id"));
            loginInfo.setClass_code(rs.getString("class_code"));
            loginInfo.setStudent_name(rs.getString("student_name"));

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
        System.out.println("return : loginInfo = " + loginInfo);
        System.out.println("                   : student_id = " + loginInfo.getStudent_id());
        System.out.println("                   : class_code = " + loginInfo.getClass_code());
        System.out.println("                   : student_name = " + loginInfo.getStudent_name());
        return loginInfo;
    }
}
