<%@ page import="diary.bean.DiaryBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    List<DiaryBeans> diary_list = (List<DiaryBeans>) session.getAttribute("diary-list");
    String error_message = (String) session.getAttribute("error-message");
    if (error_message == null) {
        error_message = "";
    }
    session.removeAttribute("error-message");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録・修正・削除・選択画面</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/diaryManipulationSelect.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100">
    <div class="col-12 col-md-10 ml-auto mr-auto mt-5 mb-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">登録・修正・削除選択</h1>

        <p class="text-center text-danger"><%=error_message%>
        </p>
        <form action="diaryinsertinput" method="post" class="text-right">
                <button type="submit" class="btn btn-info btn-lg register">新規登録</button>
        </form>

        <div class="table-wrapper-scroll-y my-custom-scrollbar col-12 mt-3">

            <table class="table table-hover border-top mr-auto ml-auto mb-0">
                <thead>
                <tr class="row">
                    <th class="col-2 border-right" scope="col">日付</th>
                    <th class="col-2 border-right" scope="col">良い点</th>
                    <th class="col-2 border-right" scope="col">悪い点</th>
                    <th class="col-3 border-right" scope="col">コメント</th>
                    <th class="col-3" scope="col">操作</th>
                </tr>

                <tbody>
                <% for (int i = 0; i < diary_list.size(); i++) { %>
                <tr class="row">
                    <th class="col-2 border-right" scope="row"><%=diary_list.get(i).getInsert_date()%>
                    </th>
                    <td class="col-2 border-right"><%=diary_list.get(i).getGood_point()%>
                    </td>
                    <td class="col-2 border-right"><%=diary_list.get(i).getBad_point()%>
                    </td>
                    <td class="col-3 border-right"><%=diary_list.get(i).getStudent_comment()%>
                    </td>
                    <td class="col-3">
                        <form action="diaryupdateinput" method="post" class="text-center">
                            <input type="hidden" name="select-diary" id="update" value="<%=i%>">
                            <button type="submit" class="btn btn-warning">修正</button>
                        </form>
                        <form action="diarydeletecheck" method="post" class=text-center>
                            <input type="hidden" name="select-diary" id="delete" value="<%=i%>">
                            <button type="submit" class="btn btn-danger">削除</button>
                        </form>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <form action="search" method="post">
            <div class="input-group">
                <input class="form-control mt-2" type="text" name="search-word">
                <input type="hidden" value="diaryManipulationSelect" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">検索</button>
            </div>
        </form>

        <form action="sort" method="post">
            <div class="input-group mb-3">
                <select class="custom-select mt-2" name="sort-column">
                    <option value="insert_date">日付</option>
                    <option value="good_point">良い点</option>
                    <option value="bad_point">悪い点</option>
                    <option value="student_comment">コメント</option>
                </select>

                <select class="custom-select mt-2" name="sort-order">
                    <option value="ASC">昇順</option>
                    <option value="DESC">降順</option>
                </select>
                <input type="hidden" value="diaryManipulationSelect" name="from-jsp-name">
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
