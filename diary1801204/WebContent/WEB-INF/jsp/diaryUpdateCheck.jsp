<%@ page import="diary.bean.DiaryBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌修正確認画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/diaryUpdate.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌修正確認</h1>

        <table class="table mr-auto ml-auto mb-0 col-12 mt-5">
            <tr class="row">
                <th class="col-4 border-right">日付</th>
                <td class="col-8"><%=diary_beans.getInsert_date()%></td>
            </tr>
            <tr class="row">
                <th class="col-4 border-right">良い点</th>
                <td class="col-8"><%=diary_beans.getGood_point()%></td>
            </tr>
            <tr class="row">
                <th class="col-4 border-right">悪い点</th>
                <td class="col-8"><%=diary_beans.getBad_point()%></td>
            </tr>
            <tr class="row">
                <th class="col-4 border-right">コメント</th>
                <td class="col-8"><%=diary_beans.getStudent_comment()%></td>
            </tr>
        </table>

        <%--日誌修正完了画面へ--%>
        <form action="diaryupdate" method="get">
            <div class="text-center mt-5">
                <button type="submit" class="btn btn-primary btn-lg">修正する</button>
            </div>
        </form>

        <%--日誌操作選択画面へ--%>
        <form action="select" method="get" class="text-right back">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
