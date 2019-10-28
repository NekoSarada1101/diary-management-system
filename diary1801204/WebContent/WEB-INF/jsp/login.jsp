<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String error_message = (String) session.getAttribute("errorMessage");
    if(error_message == null){
        error_message = "";
    }
    session.removeAttribute("errorMessage");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生ログイン</title>
</head>
<body>
<header>
    <h3>日誌管理システム</h3>
</header>
<h5>学生ログイン</h5>
<form action="auth" method="post">
    <p>学籍番号</p>
    <input type="text" name="student_id">

    <p>パスワード</p>
    <input type="password" name="password">

    <p><%=error_message%></p>

    <input type="submit" value="ログイン">
</form>
</body>
</html>
