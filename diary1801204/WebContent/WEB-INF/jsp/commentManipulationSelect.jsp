<%@ page import="diary.bean.DiaryBeans" %>
<%@ page import="diary.commmon.StudentErrorCheck" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");
    String error_message = (String) session.getAttribute("error-message");
    if (error_message == null) error_message = "";
    session.removeAttribute("error-message");

    StudentErrorCheck error_check = new StudentErrorCheck();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>更新・削除・選択画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/manipulationSelect.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-md-10 ml-auto mr-auto mt-5 mb-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">更新・削除選択</h1>

        <p class="text-center text-danger"><%=error_message%></p>

        <div class="table-wrapper-scroll-y my-custom-scrollbar col-12 mt-3">
            <table class="table table-hover border-top mr-auto ml-auto mb-0">
                <thead>
                <tr class="row">
                    <th class="col-2 border-right" scope="col">日付</th>
                    <th class="col-3 border-right" scope="col">学籍番号</th>
                    <th class="col-4 border-right" scope="col">日誌詳細</th>
                    <th class="col-3" scope="col">操作</th>
                </tr>

                <tbody>
                <% for (int i = 0; i < diary_list.size(); i++) {
                    String good_point = error_check.inputEscape(diary_list.get(i).getGood_point());
                    String bad_point = error_check.inputEscape(diary_list.get(i).getBad_point());
                    String student_comment = error_check.inputEscape(diary_list.get(i).getStudent_comment());
                    if (diary_list.get(i).getTeacher_comment() == null)diary_list.get(i).setTeacher_comment("");
                    String teacher_comment = error_check.inputEscape(diary_list.get(i).getTeacher_comment());
                %>
                <tr class="row animated bounceInUp">
                    <th class="col-2 border-right" scope="row"><%=diary_list.get(i).getInsert_date()%></th>
                    <th class="col-3 border-right" scope="row"><%=diary_list.get(i).getStudent_id()%></th>
                    <td class="col-4 border-right">
                        <div class="treeview-animated">
                            <ul class="treeview-animated-list">
                                <li class="treeview-animated-items">
                                    <a class="closed"><i class="fas fa-angle-right"></i><span>詳細</span></a>
                                    <ul class="nested">
                                        <li>
                                            <div class="treeview-animated-element">
                                                <span>
                                                    <p><strong>良い点</strong><br><%=good_point%></p>
                                                    <p><strong>悪い点</strong><br><%=bad_point%></p>
                                                    <p><strong>学生コメント</strong><br><%=student_comment%></p>
                                                    <p><strong>教員コメント</strong><br><%=teacher_comment%></p>
                                                </span>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </td>
                    <td class="col-3">
                        <%--コメント登録・修正入力画面へ--%>
                        <form action="commentupdateinput" method="post" class="text-center">
                            <input type="hidden" name="select-diary" id="insert" value="<%=i%>">
                            <button type="submit" class="btn btn-info">更新</button>
                        </form>

                        <%--コメント削除確認画面へ--%>
                        <form action="commentdeletecheck" method="post" class=text-center>
                            <input type="hidden" name="select-diary" id="delete" value="<%=i%>">
                            <button type="submit" class="btn btn-danger">削除</button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <%--検索機能--%>
        <form action="teachersearch" method="post" class="mt-3">
            <div class="input-group">
                <input class="form-control mt-2" type="text" name="search-word">
                <input type="hidden" value="commentManipulationSelect" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">検索</button>
            </div>
        </form>

        <%--ソート機能--%>
        <form action="teachersort" method="post">
            <div class="input-group mb-3">
                <select class="custom-select mt-2" name="sort-column">
                    <option value="insert_date">日付</option>
                    <option value="student_id">学籍番号</option>
                    <option value="good_point">良い点</option>
                    <option value="bad_point">悪い点</option>
                    <option value="student_comment">学生コメント</option>
                    <option value="teacher_comment">教員コメント</option>
                </select>

                <select class="custom-select mt-2" name="sort-order">
                    <option value="ASC">昇順</option>
                    <option value="DESC">降順</option>
                </select>
                <input type="hidden" value="commentManipulationSelect" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">ソート</button>
            </div>
        </form>

        <%--メニュー画面へ--%>
        <form action="teachermenu" method="get" class="text-right">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

<script>
    // Treeview Initialization
    $(document).ready(function () {
        $('.treeview-animated').mdbTreeview();
    });
</script>

</body>
</html>
