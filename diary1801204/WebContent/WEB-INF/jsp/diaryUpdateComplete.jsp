<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌修正完了</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
</head>
<body>
<h3>修正完了</h3>
<p>日誌を修正しました</p>

<form action="menu" method="get">
    <input type="submit" value="戻る">
</form>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
