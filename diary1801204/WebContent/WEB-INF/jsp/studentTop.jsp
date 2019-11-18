<%@ page import="diary.bean.LoginInfoBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String student_name = ((LoginInfoBeans) session.getAttribute("login-info")).getStudent_name();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>トップ</title>
</head>
<body>
<header>
    <div class="image position-absolute animated fadeIn"></div>
    <nav class="navbar navbar-expand-lg navbar-dark scrolling-navbar z-depth-1">
        <a class="navbar-brand" href="menu"><strong class="text-white">DiaryManagementSystem</strong></a>
        <span class="text-white"><%=student_name%>さん</span>
        <form action="logout" method="get" class="ml-auto mr-0">
            <input type="hidden" value="student" name="from">
            <button type="submit" class="btn btn-grey pt-2 pb-2 pr-3 pl-3">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
