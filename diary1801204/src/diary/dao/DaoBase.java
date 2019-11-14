
package diary.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * データベースへの接続と切断をするクラス
 *
 * @author ryouta
 */
public class DaoBase {
    protected Connection con = null;

    /**
     * データベースdiary1801204に接続する
     */
    public void dbConnect() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/diary1801204?characterEncoding=UTF-8&serverTimezone=JST&useSSL=false", "root", "cVnvEfW84P9gi2HG");

        } catch (ClassNotFoundException e) {
            e.printStackTrace();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * データベースから切断する
     */
    public void dbClose() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
