package diary.dao;

import diary.bean.TeacherBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * データベースに接続して教員のログイン処理を行うクラス
 *
 * @author ryouta
 */
public class TeacherDao extends DaoBase {

    /**
     * 教員のログイン処理とログインした教員の情報の取得
     *
     * @param teacher_code ログイン画面で入力された教員番号
     * @param password     ログイン画面で入力されたパスワード
     * @return ログインに成功したらログインした教員の情報 失敗したらnull
     */
    public List<TeacherBeans> getLoginInfo(String teacher_code, String password) {
        //test
        System.out.println("DiaryDao : getLoginInfo");
        System.out.println("param : teacher_code = " + teacher_code);
        System.out.println("param : password = " + password);

        TeacherBeans teacher_beans = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<TeacherBeans> list = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement("SELECT * FROM (teacher t INNER JOIN class c ON t.teacher_code = c.teacher_code) INNER JOIN course co ON c.course_code = co.course_code WHERE t.teacher_code = ? AND t.teacher_password = ?;");
            stmt.setString(1, teacher_code);
            stmt.setString(2, password);

            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while(rs.next()){
                teacher_beans = new TeacherBeans();
                teacher_beans.setTeacher_code(rs.getString("teacher_code"));
                teacher_beans.setTeacher_name(rs.getString("teacher_name"));
                teacher_beans.setClass_code(rs.getString("class_code"));
                teacher_beans.setCourse_name(rs.getString("course_name"));
                teacher_beans.setGrade(rs.getString("grade"));
                teacher_beans.setClass_name(rs.getString("class_name"));
                list.add(teacher_beans);
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
        //test
        if(list.size() != 0){
            System.out.println("return : list = " + list);
            System.out.println("                   : teacher_code = " + teacher_beans.getTeacher_code());
            System.out.println("                   : teacher_name = " + teacher_beans.getTeacher_name());
            System.out.println("                   : class_code = " + teacher_beans.getClass_code());
            System.out.println("                   : course_name = " + teacher_beans.getCourse_name());
            System.out.println("                   : grade = " + teacher_beans.getGrade());
            System.out.println("                   : class_name = " + teacher_beans.getClass_name());

            return list;
        }else {
            return null;
        }
    }
}
