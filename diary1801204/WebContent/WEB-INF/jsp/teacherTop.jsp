<%@ page import="diary.bean.TeacherBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    String teacher_name = ((TeacherBeans)((List<TeacherBeans>)session.getAttribute("teacher-list")).get(0)).getTeacher_name();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>トップ</title>
</head>
<body>
<header>
    <div class="image position-absolute"></div>
    <nav class="navbar navbar-expand-lg navbar-white scrolling-navbar z-depth-1">
        <a class="navbar-brand" href="menu"><strong class="text-dark">DiaryManagementSystem</strong></a>
        <span class="text-white"><%=teacher_name%>先生</span>
        <form action="logout" method="get" class="ml-auto mr-0">
            <button type="submit" class="btn btn-grey pt-2 pb-2 pr-3 pl-3">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
