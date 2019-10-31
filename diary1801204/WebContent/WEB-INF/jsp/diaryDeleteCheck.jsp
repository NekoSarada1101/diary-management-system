<%@ page import="diary.bean.DiaryBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    DiaryBeans diaryBeans = (DiaryBeans) session.getAttribute("diary-beans");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>日誌削除確認</title>
</head>
<body>
<h3>日誌削除確認</h3>
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
        <td><%=diaryBeans.getGood_point()%></td>
        <td><%=diaryBeans.getBad_point()%></td>
        <td><%=diaryBeans.getStudent_comment()%></td>
    </tr>
    </tbody>
</table>

<form action="diarydelete" method="get">
    <input type="submit" value="削除する">
</form>

<form action="select" method="get">
    <input type="submit" value="戻る">
</form>
</body>
</html>
