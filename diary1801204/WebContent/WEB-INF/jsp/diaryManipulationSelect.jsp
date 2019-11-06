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
</head>
<body class="container pt-3 pb-3">
<div class="row">
    <img src="img/cup.png" alt="カップ" class="col-3 align-top h-50 p-0">

    <div class="col-8 vh-100">
        <div class="col-8 border border-dark bg-white pt-3 pr-lg-5 pl-lg-5 pr-md-3 pl-md-3 pr-1 pl-1 form">
            <h1 class="text-center border-bottom border-dark">Register・Correct・Delete</h1>

            <p class="text-center text-danger"><%=error_message%>
            </p>

            <form action="select" method="post">
                <div class="input-group mb-3">
                    <select class="custom-select" name="sort-column">
                        <option value="insert_date">Insert date</option>
                    </select>

                    <select class="custom-select" name="sort-order">
                        <option value="ASC">Ascending order</option>
                        <option value="DESC">Descending order</option>
                    </select>
                    <input type="submit" value="Sort" class="btn btn-primary">
                </div>
            </form>

            <table class="table table-hover mr-auto ml-auto col-10 col-md-11 p-0">
                <tbody>
                <% for (int i = 0; i < diary_list.size(); i++) { %>
                <tr class="row table-light border-top">
                    <th class="col-1" scope="row">
                        <input type="radio" name="select-diary" value="<%=i%>" onclick="selectDiary(<%=i%>)">
                    </th>
                    <th class="col-10"><%=diary_list.get(i).getInsert_date()%>
                    </th>
                </tr>

                <tr class="row table-light">
                    <th class="col-4">Good point</th>
                    <td class="col-8"><%=diary_list.get(i).getGood_point()%>
                    </td>
                </tr>

                <tr class="row table-light">
                    <th class="col-4">Bad point</th>
                    <td class="col-8"><%=diary_list.get(i).getBad_point()%>
                    </td>
                </tr>

                <tr class="row table-light">
                    <th class="col-4" scope="row">Comment</th>
                    <td class="col-8"><%=diary_list.get(i).getStudent_comment()%>
                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>

            <div class="row">
                <form action="diaryinsertinput" method="post" class="col-3">
                    <div class="text-center mt-5"><input type="submit" value="Register" class="btn btn-info btn-lg">
                    </div>
                </form>

                <form action="diaryupdateinput" method="post" class="col-3">
                    <input type="hidden" name="select-diary" id="update" value="">
                    <div class="text-center mt-5"><input type="submit" value="Correct" class="btn btn-warning btn-lg">
                    </div>
                </form>

                <form action="diarydeletecheck" method="post" class="col-3">
                    <input type="hidden" name="select-diary" id="delete" value="">
                    <div class="text-center mt-5"><input type="submit" value="Delete" class="btn btn-danger btn-lg">
                    </div>
                </form>

                <form action="menu" method="get" class="col-3">
                    <div class="text-right mt-n5"><input type="submit" value="Back"
                                                         class="btn btn-outline-primary btn-lg">
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script>
    function selectDiary(position) {
        document.getElementById("update").value = position;
        document.getElementById("delete").value = position;
    }
</script>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
