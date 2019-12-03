package diary.dao;

import diary.bean.DiaryBeans;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * データベース操作のメソッドを記述する抽象クラス
 *
 * @author ryouta
 */
public abstract class DiaryDao extends DaoBase {

    /**
     * データベースに日誌の情報を登録(INSERT)する
     *
     * @param diary_beans データベースに登録する日誌の情報
     */
    public void insertDiaryToDb(DiaryBeans diary_beans) {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryDao : insertDiaryToDb");
        System.out.println("param : diary_beans = " + diary_beans);
        System.out.println("                    : class_code = "      + diary_beans.getClass_code());
        System.out.println("                    : insert_date = "     + diary_beans.getInsert_date());
        System.out.println("                    : student_id = "      + diary_beans.getStudent_id());
        System.out.println("                    : good_point = "      + diary_beans.getGood_point());
        System.out.println("                    : bad_point = "       + diary_beans.getBad_point());
        System.out.println("                    : student_comment = " + diary_beans.getStudent_comment());
        System.out.println("                    : teacher_comment = " + diary_beans.getTeacher_comment());
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createInsertSqlSentence());
            stmt = configureValueInPlaceholderOfInsertSqlSentence(stmt, diary_beans);
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
     * INSERT文を作成する
     *
     * @return 作成したINSERT文
     */
    abstract String createInsertSqlSentence();

    /**
     * INSERT文のプレースホルダーに値を埋め込む
     *
     * @param stmt        プレースホルダーに値を埋め込む前のINSERT文
     * @param diary_beans プレースホルダーに埋め込む値
     * @return プレースホルダーに値を埋め込んだINSERT文
     */
    abstract PreparedStatement configureValueInPlaceholderOfInsertSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans);


    /**
     * データベースから指定した日誌の情報を削除(DELETE)する
     *
     * @param diary_beans データベースから削除する日誌の情報
     */
    public void deleteDiaryFromDb(DiaryBeans diary_beans) {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryDao : deleteDiaryFromDb");
        System.out.println("param : diary_beans = " + diary_beans);
        System.out.println("                    : class_code = "      + diary_beans.getClass_code());
        System.out.println("                    : insert_date = "     + diary_beans.getInsert_date());
        System.out.println("                    : student_id = "      + diary_beans.getStudent_id());
        System.out.println("                    : good_point = "      + diary_beans.getGood_point());
        System.out.println("                    : bad_point = "       + diary_beans.getBad_point());
        System.out.println("                    : student_comment = " + diary_beans.getStudent_comment());
        System.out.println("                    : teacher_comment = " + diary_beans.getTeacher_comment());
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createDeleteSqlSentence());
            stmt = configureValueInPlaceholderOfDeleteSqlSentence(stmt, diary_beans);
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
     * DELETE文を作成する
     *
     * @return 作成したDELETE文
     */
    abstract String createDeleteSqlSentence();

    /**
     * DELETE文のプレースホルダーに値を埋め込む
     *
     * @param stmt        プレースホルダーに値を埋め込む前のDELETE文
     * @param diary_beans プレースホルダーに埋め込む値
     * @return プレースホルダーに値を埋め込んだDELETE文
     */
    abstract PreparedStatement configureValueInPlaceholderOfDeleteSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans);


    /**
     * データベースの日誌の情報を入力した情報に更新(UPDATE)する
     *
     * @param diary_beans 更新する日誌の情報
     */
    public void updateDiaryToDb(DiaryBeans diary_beans) {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryDao : updateDiaryToDb");
        System.out.println("param : diary_beans = " + diary_beans);
        System.out.println("                    : class_code = "      + diary_beans.getClass_code());
        System.out.println("                    : insert_date = "     + diary_beans.getInsert_date());
        System.out.println("                    : student_id = "      + diary_beans.getStudent_id());
        System.out.println("                    : good_point = "      + diary_beans.getGood_point());
        System.out.println("                    : bad_point = "       + diary_beans.getBad_point());
        System.out.println("                    : student_comment = " + diary_beans.getStudent_comment());
        System.out.println("                    : teacher_comment = " + diary_beans.getTeacher_comment());
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        PreparedStatement stmt = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createUpdateSqlSentence());
            stmt = configureValueInPlaceholderOfUpdateSqlSentence(stmt, diary_beans);
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
     * UPDATE文を作成する
     *
     * @return 作成したUPDATE文
     */
    abstract String createUpdateSqlSentence();

    /**
     * UPDATE文のプレースホルダーに値を埋め込む
     *
     * @param stmt        プレースホルダーに値を埋め込む前のUPDATE文
     * @param diary_beans プレースホルダーに埋め込む値
     * @return プレースホルダーに値を埋め込んだUPDATE文
     */
    abstract PreparedStatement configureValueInPlaceholderOfUpdateSqlSentence(PreparedStatement stmt, DiaryBeans diary_beans);


    /**
     * 指定されたカラムを指定された順番にソートした日誌の情報をすべて取得(SELECT)する
     *
     * @param condition   SQL文のWHERE句に指定する条件
     * @param sort_column ソート対象のカラム名
     * @param sort_order  ソートの順番
     * @return 取得した日誌の情報を格納したリスト
     */
    public List<DiaryBeans> fetchSortedDiaryListFromDb(String condition, String sort_column, String sort_order) {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryDao : fetchSortedDiaryListFromDb");
        System.out.println("param : condition = "   + condition);
        System.out.println("param : sort_column = " + sort_column);
        System.out.println("param : sort_order = "  + sort_order);
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        DiaryBeans diary_beans = null;
        List<DiaryBeans> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createSortSqlSentence(sort_column, sort_order));
            stmt = configureValueInPlaceholderOfSortSqlSentence(stmt, condition);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                diary_beans = new DiaryBeans();
                diary_beans.setClass_code     (rs.getString("class_code"));
                diary_beans.setInsert_date    (rs.getString("insert_date"));
                diary_beans.setStudent_id     (rs.getString("student_id"));
                diary_beans.setGood_point     (rs.getString("good_point"));
                diary_beans.setBad_point      (rs.getString("bad_point"));
                diary_beans.setStudent_comment(rs.getString("student_comment"));
                diary_beans.setTeacher_comment(rs.getString("teacher_comment"));
                list.add(diary_beans);
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
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("return : list = " + list); //test
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        return list;
    }

    /**
     * 日誌の情報をソートするSELECT文を作成する
     *
     * @param sort_column ソート対象のカラム名
     * @param sort_order  ソートの順番
     * @return 作成したSELECT文
     */
    abstract String createSortSqlSentence(String sort_column, String sort_order);

    /**
     * ソートするSELECT文のプレースホルダーに値を埋め込む
     *
     * @param stmt      プレースホルダーに値を埋め込む前のSELECT文
     * @param condition プレースホルダーに埋め込む値
     * @return プレースホルダーに値を埋め込んだSELECT文
     */
    abstract PreparedStatement configureValueInPlaceholderOfSortSqlSentence(PreparedStatement stmt, String condition);


    /**
     * 指定された単語をで曖昧検索を行って抽出された日誌の情報をすべて取得(SELECT)する
     *
     * @param condition   SQL文のWHERE句に指定する条件
     * @param search_word 曖昧検索を行う単語
     * @return 取得した日誌の情報を格納したリスト
     */
    public List<DiaryBeans> fetchSearchedDiaryListFromDb(String condition, String search_word) {
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("DiaryDao : fetchSearchedDiaryListFromDb");
        System.out.println("param : condition = "   + condition);
        System.out.println("param : search_word = " + search_word);
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        DiaryBeans diary_beans = null;
        List<DiaryBeans> list = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            this.dbConnect();
            stmt = con.prepareStatement(createSearchSqlSentence());
            stmt = configureValueInPlaceholderOfSearchSqlSentence(stmt, condition, search_word);
            rs = stmt.executeQuery();

            list = new ArrayList<>();

            while (rs.next()) {
                diary_beans = new DiaryBeans();
                diary_beans.setClass_code     (rs.getString("class_code"));
                diary_beans.setInsert_date    (rs.getString("insert_date"));
                diary_beans.setStudent_id     (rs.getString("student_id"));
                diary_beans.setGood_point     (rs.getString("good_point"));
                diary_beans.setBad_point      (rs.getString("bad_point"));
                diary_beans.setStudent_comment(rs.getString("student_comment"));
                diary_beans.setTeacher_comment(rs.getString("teacher_comment"));
                list.add(diary_beans);
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
        //  TEST   /////////////////////////////////////////////////////////////////////////////////////////
        System.out.println("return : list = " + list);
        ///////////////////////////////////////////////////////////////////////////////////////////////////

        return list;
    }

    /**
     * 日誌の情報を曖昧検索するSELECT文を作成する
     *
     * @return 作成したSELECT文
     */
    abstract String createSearchSqlSentence();

    /**
     * 曖昧検索するSELECT文のプレースホルダーに値を埋め込む
     *
     * @param stmt        プレースホルダーに値を埋め込む前のSELECT文
     * @param search_word プレースホルダーに埋め込む曖昧検索のための単語
     * @param condition   プレースホルダーに埋め込む値
     * @return プレースホルダーに値を埋め込んだSELECT文
     */
    abstract PreparedStatement configureValueInPlaceholderOfSearchSqlSentence(PreparedStatement stmt, String condition, String search_word);
}
