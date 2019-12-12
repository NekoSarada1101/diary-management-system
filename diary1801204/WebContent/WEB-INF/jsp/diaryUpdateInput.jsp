<%@ page import="diary.bean.DiaryBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary_beans");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌修正入力画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/update.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌修正入力</h1>

        <form action="diaryupdatecheck" method="post" class="mt-5 col-lg-7 col-md-8 col-sm-10 col-12 mr-auto ml-auto">
            <!--良い点入力フォーム-->
            <div class="md-form">
                <i class="fas fa-thumbs-up prefix"></i>
                <textarea id="form1" class="md-textarea form-control" maxlength="30" name="good-point" required><%=diary_beans.getGood_point()%></textarea>
                <label for="form1">良い点</label>
            </div>

            <!--悪い点入力フォーム-->
            <div class="md-form mt-5">
                <i class="fas fa-thumbs-down prefix"></i>
                <textarea id="form2" class="md-textarea form-control" maxlength="30" name="bad-point" required><%=diary_beans.getBad_point()%></textarea>
                <label for="form2">悪い点</label>
            </div>

            <!--学生コメント入力フォーム-->
            <div class="md-form mt-5">
                <i class="fas fa-comment prefix"></i>
                <textarea id="form3" class="md-textarea form-control" maxlength="30" name="student-comment" required><%=diary_beans.getStudent_comment()%></textarea>
                <label for="form3">コメント</label>
            </div>

            <%--日誌修正確認画面へ--%>
            <div class="text-center">
                <button type="submit" class="btn btn-warning btn-lg">修正する</button>
            </div>
        </form>

        <%--日誌操作選択画面へ--%>
        <form action="select" method="get" class="text-right back">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
