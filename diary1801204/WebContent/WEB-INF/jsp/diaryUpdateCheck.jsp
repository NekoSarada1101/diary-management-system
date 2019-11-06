<%@ page import="diary.bean.DiaryBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌修正確認</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
</head>
<body>
<h3>日誌修正確認</h3>
<table>
    <thead>
    <tr>
        <th>良い点</th>
        <th>悪い点</th>
        <th>コメント</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td><%=diary_beans.getGood_point()%>
        </td>
        <td><%=diary_beans.getBad_point()%>
        </td>
        <td><%=diary_beans.getStudent_comment()%>
        </td>
    </tr>
    </tbody>
</table>

<form action="diaryupdate" method="get">
    <input type="submit" value="修正する">
</form>

<form action="select" method="get">
    <input type="submit" value="戻る">
</form>

<%@include file="/WEB-INF/jsp/script.jsp" %>
</body>
</html>
