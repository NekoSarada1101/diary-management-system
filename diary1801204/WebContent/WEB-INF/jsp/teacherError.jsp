<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>エラー画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body class="p-0">
<header>
    <div class="image position-absolute animated fadeIn"></div>
    <nav class="navbar navbar-expand-lg navbar-dark scrolling-navbar z-depth-1">
        <a class="navbar-brand" href="#"><strong class="text-white">DiaryManagementSystem</strong></a>
    </nav>
</header>

<div class="container-fluid vh-100 animated bounceInUp">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <p class="text-center mt-5">エラーが発生しました</p>

        <%--ログアウト処理へ--%>
        <form action="logout" method="get" class="text-center mt-5">
            <input type="hidden" value="teacher" name="from">
            <button type="submit" class="btn btn-link">ログイン画面へ戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
