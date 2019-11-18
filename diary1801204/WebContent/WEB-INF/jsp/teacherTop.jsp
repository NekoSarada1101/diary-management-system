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
    <div class="image position-absolute animated fadeIn"></div>
    <nav class="navbar navbar-expand-lg navbar-dark scrolling-navbar z-depth-1" id="teacher-nav">
        <a class="navbar-brand" href="teachermenu"><strong class="text-dark">DiaryManagementSystem</strong></a>
        <span class="text-dark"><%=teacher_name%>先生</span>
        <form action="logout" method="get" class="ml-auto mr-0">
            <input type="hidden" value="teacher" name="from">
            <button type="submit" class="btn btn-grey pt-2 pb-2 pr-3 pl-3">ログアウト</button>
        </form>
    </nav>
</header>
</body>
</html>
