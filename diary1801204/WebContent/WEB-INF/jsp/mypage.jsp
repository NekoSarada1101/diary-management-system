<%@ page import="sun.rmi.runtime.Log" %>
<%@ page import="diary.bean.LoginInfoBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    String student_name = ((LoginInfoBeans)session.getAttribute("loginInfo")).getStudent_name();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>トップ</title>
</head>
<body>
<h3>マイページ</h3>
<p><%=student_name%>さん</p>
<form action="logout" method="get">
    <input type="submit" value="ログアウト">
</form>
</body>
</html>
