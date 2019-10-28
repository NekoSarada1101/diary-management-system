package diary.dao;


import diary.bean.LoginInfoBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao extends DaoBase {
    //ログイン処理
    public LoginInfoBeans getLoginInfo(String student_id, String password) {
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
        return loginInfo;
    }
}
