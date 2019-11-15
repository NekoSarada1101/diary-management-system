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
    <title>日誌登録確認画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/diaryInsert.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌登録確認</h1>

        <table class="table table-striped table-borderless mr-auto ml-auto mb-0 col-12 mt-5">
            <tr class="row animated bounceInLeft faster">
                <th class="col-4">日付</th>
                <td class="col-8"><%=diary_beans.getInsert_date()%></td>
            </tr>
            <tr class="row animated bounceInLeft fast">
                <th class="col-4">良い点</th>
                <td class="col-8"><%=diary_beans.getGood_point()%></td>
            </tr>
            <tr class="row animated bounceInLeft">
                <th class="col-4">悪い点</th>
                <td class="col-8"><%=diary_beans.getBad_point()%></td>
            </tr>
            <tr class="row animated bounceInLeft slow">
                <th class="col-4">コメント</th>
                <td class="col-8"><%=diary_beans.getStudent_comment()%></td>
            </tr>
        </table>

        <%--日誌登録完了画面へ--%>
        <form action="diaryinsert" method="get">
            <div class="text-center mt-5">
                <button type="submit" class="btn btn-primary btn-lg">登録する</button>
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
