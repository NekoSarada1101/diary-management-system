<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String error_message = (String) session.getAttribute("error-message");
    if (error_message == null) error_message = "";
    session.removeAttribute("error-message");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生ログイン</title>
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
        <h1 class="text-center border-bottom border-dark">学生ログイン</h1>

        <form action="auth" method="post" class="mt-5 col-lg-6 col-md-8 col-sm-10 col-12 mr-auto ml-auto">
            <!-- 学籍番号入力フォーム -->
            <div class="md-form">
                <i class="fas fa-user prefix"></i>
                <input type="text" id="inputIconEx1" class="form-control" name="student-id" required>
                <label for="inputIconEx1">学籍番号</label>
            </div>

            <!-- パスワード入力フォーム -->
            <div class="md-form mt-5">
                <i class="fas fa-lock prefix"></i>
                <input type="password" id="inputIconEx2" class="form-control" name="student-password" required>
                <label for="inputIconEx2">パスワード</label>
            </div>

            <p class="text-center text-danger"><%=error_message%></p>

            <div class="text-center mt-5">
                <button type="submit" class="btn btn-primary">ログイン</button>
            </div>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
