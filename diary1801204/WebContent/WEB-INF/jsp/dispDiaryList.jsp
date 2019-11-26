<%@ page import="diary.bean.DiaryBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");
    String menu_name = (String) session.getAttribute("menu-name");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌閲覧画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/diaryList.css">
</head>
<body class="p-0">
<% if (menu_name.equals("menu")) { %>
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>
<% } else { %>
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>
<% } %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-md-10 ml-auto mr-auto mt-5 mb-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌閲覧</h1>

        <div class="table-wrapper-scroll-y my-custom-scrollbar col-12 mt-5">
            <table class="table table-hover border-top mr-auto ml-auto mb-0">
                <thead>
                <tr class="row">
                    <th class="col-3 border-right" scope="col">日付</th>
                    <th class="col-3 border-right" scope="col">学籍番号</th>
                    <th class="col-6" scope="col">日誌内容</th>
                </tr>
                </thead>

                <tbody>
                <% for (int i = 0; i < diary_list.size(); i++) { %>
                <tr class="row animated bounceInUp">
                    <td class="col-3 border-right" scope="row"><%=diary_list.get(i).getInsert_date()%></td>
                    <td class="col-3 border-right"><%=diary_list.get(i).getStudent_id()%></td>
                    <td class="col-6">
                        <div class="treeview-animated">
                            <ul class="treeview-animated-list">
                                <li class="treeview-animated-items">
                                    <a class="closed">
                                        <i class="fas fa-angle-right"></i><span>詳細</span>
                                    </a>
                                    <ul class="nested">
                                        <li>
                                            <div class="treeview-animated-element">
                                                <span>
                                                    <p><strong>良い点</strong><br><%=diary_list.get(i).getGood_point()%></p>
                                                    <p><strong>悪い点</strong><br><%=diary_list.get(i).getBad_point()%></p>
                                                    <p><strong>学生コメント</strong><br><%=diary_list.get(i).getStudent_comment()%></p>
                                                    <p><strong>教員コメント</strong><br><%=diary_list.get(i).getTeacher_comment()%></p>
                                                </span>
                                            </div>
                                        </li>
                                    </ul>
                                </li>
                            </ul>
                        </div>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <%--検索機能--%>
        <form action="search" method="post" class="mt-3">
            <div class="input-group">
                <input class="form-control mt-2" type="text" name="search-word">
                <input type="hidden" value="dispDiaryList" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">検索</button>
            </div>
        </form>

        <%--ソート機能--%>
        <form action="sort" method="post">
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
                <input type="hidden" value="dispDiaryList" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">ソート</button>
            </div>
        </form>

        <form action="<%=menu_name%>" method="get" class="text-right">
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
