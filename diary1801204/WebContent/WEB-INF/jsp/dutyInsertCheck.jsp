<%@ page import="diary.bean.DutyBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    DutyBeans duty_beans = (DutyBeans) session.getAttribute("duty-beans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌当番登録確認画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/insert.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌当番登録確認</h1>

        <p class="text-center">以下の情報で登録しますか？</p>

        <table class="table table-striped table-borderless mr-auto ml-auto mb-0 col-12 mt-5">
            <tr class="row animated bounceInLeft faster">
                <th class="col-4">学籍番号</th>
                <td class="col-8"><%=duty_beans.getStudent_id()%></td>
            </tr>
            <tr class="row animated bounceInLeft fast">
                <th class="col-4">名前</th>
                <td class="col-8"><%=duty_beans.getStudent_name()%></td>
            </tr>
        </table>

        <%--日誌当番登録完了画面へ--%>
        <form action="dutyinsert" method="get">
            <div class="text-center mt-5">
                <button type="submit" class="btn btn-info btn-lg">登録する</button>
            </div>
        </form>

        <%--日誌当番選択画面へ--%>
        <form action="dutyinsertselect" method="get" class="text-right back">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
