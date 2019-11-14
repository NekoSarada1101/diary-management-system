<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌修正完了画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/diaryUpdate.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌修正完了</h1>
        <p class="text-center mt-5">日誌を修正しました</p>

        <%--メニュー画面へ--%>
        <form action="menu" method="get" class="text-center mt-5">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
