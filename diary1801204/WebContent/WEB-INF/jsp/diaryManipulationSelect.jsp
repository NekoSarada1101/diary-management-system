<%@ page import="diary.bean.DiaryBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    List<DiaryBeans> diaryList = (List<DiaryBeans>) session.getAttribute("diary-list");
String error_message = (String) session.getAttribute("error-message");
if(error_message == null){
    error_message = "";
}
session.removeAttribute("error-message");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登録・修正・削除・選択画面</title>
</head>
<body>
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<h3>登録・修正・削除</h3>
<p><%=error_message %></p>
<table>
    <thead>
    <tr>
        <th>選択</th>
        <th>登録日</th>
        <th>良い点</th>
        <th>悪い点</th>
        <th>コメント</th>
    </tr>
    </thead>

    <tbody>
    <% for (int i = 0; i < diaryList.size(); i++) { %>
    <tr>
        <td><%--@declare id="fix delete"--%><input type="radio" name="selectDiary" value="<%=i%>" form="fix delete">
        </td>
        <td><%=diaryList.get(i).getInsert_date()%>
        </td>
        <td><%=diaryList.get(i).getGood_point()%>
        </td>
        <td><%=diaryList.get(i).getBad_point()%>
        </td>
        <td><%=diaryList.get(i).getStudent_comment()%>
        </td>
    </tr>
    <% } %>
    </tbody>
</table>

<form action="diaryinsertinput" method="post">
    <input type="submit" value="登録">
</form>
<form action="diaryfixinput" method="post">
    <input type="submit" value="修正" id="fix">
</form>
<form action="diarydeletecheck" method="post">
    <input type="submit" value="削除" id="delete">
</form>

<form action="menu" method="get">
    <input type="submit" value="戻る">
</form>
</body>
</html>
