<%@ page import="diary.bean.DiaryBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌閲覧画面</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/diaryManipulationSelect.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>
TODO
日誌をクリックすると日誌の詳細情報を見ることができるようにする

<div class="container-fluid vh-100">
    <div class="col-12 col-md-10 ml-auto mr-auto mt-5 mb-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌閲覧</h1>

        <div class="table-wrapper-scroll-y my-custom-scrollbar col-12 mt-5">

            <table class="table table-hover border-top mr-auto ml-auto mb-0">
                <thead>
                <tr class="row">
                    <th class="col-2 border-right" scope="col">日付</th>
                    <th class="col-2 border-right" scope="col">学籍番号</th>
                    <th class="col-2 border-right" scope="col">良い点</th>
                    <th class="col-2 border-right" scope="col">悪い点</th>
                    <th class="col-2 border-right" scope="col">学生コメント</th>
                    <th class="col-2" scope="col">教員コメント</th>
                </tr>

                <tbody>
                <% for (int i = 0; i < diary_list.size(); i++) { %>
                <tr class="row">
                    <a href="#">

                        <th class="col-2 border-right" scope="row"><%=diary_list.get(i).getInsert_date()%>
                        </th>
                        <th class="col-2 border-right"><%=diary_list.get(i).getStudent_id()%>
                        </th>
                        <td class="col-2 border-right"><%=diary_list.get(i).getGood_point()%>
                        </td>
                        <td class="col-2 border-right"><%=diary_list.get(i).getBad_point()%>
                        </td>
                        <td class="col-2 border-right"><%=diary_list.get(i).getStudent_comment()%>
                        </td>
                        <td class="col-2"><%=diary_list.get(i).getTeacher_comment()%>
                        </td>
                    </a>

                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <form action="search" method="post">
            <div class="input-group">
                <input class="form-control mt-2" type="text" name="search-word">
                <input type="hidden" value="dispDiaryList" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">検索</button>
            </div>
        </form>

        <form action="sort" method="post">
            <div class="input-group mb-3">
                <select class="custom-select mt-2" name="sort-column">
                    <option value="insert_date">日付</option>
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

        <form action="menu" method="get" class="text-right">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
