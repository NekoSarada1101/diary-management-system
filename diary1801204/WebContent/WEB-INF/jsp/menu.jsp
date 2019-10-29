<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生トップメニュー</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<form action="select" method="get">
    <input type="submit" value="登録・修正・削除">
</form>

<form action="list" method="get">
    <input type="submit" value="日誌閲覧">
</form>

</body>
</html>
