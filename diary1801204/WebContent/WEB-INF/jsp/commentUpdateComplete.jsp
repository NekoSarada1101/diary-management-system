<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>コメント更新完了画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/insert.css">
</head>

<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">コメント更新完了</h1>
        <h1 class="animated jackInTheBox delay-1s text-center mt-5 text-success"><i class="far fa-check-circle"></i></h1>
        <p class="text-center mt-5">コメントを更新しました</p>

        <%--メニュー画面へ--%>
        <form action="teachermenu" method="get" class="text-center mt-5">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
