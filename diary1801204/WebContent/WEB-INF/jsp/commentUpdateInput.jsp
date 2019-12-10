<%@ page import="diary.bean.DiaryBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    DiaryBeans diary_beans = (DiaryBeans) session.getAttribute("diary-beans");
    if(diary_beans.getTeacher_comment() == null){
        diary_beans.setTeacher_comment("");
    }
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>コメント更新入力画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/insert.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">コメント更新</h1>

        <table class="table table-striped table-borderless mr-auto ml-auto mb-0 col-12 mt-5">
            <tr class="row animated bounceInLeft faster">
                <th class="col-4">日付</th>
                <td class="col-8"><%=diary_beans.getInsert_date()%></td>
            </tr>
            <tr class="row animated bounceInLeft fast">
                <th class="col-4">良い点</th>
                <td class="col-8"><%=diary_beans.getGood_point()%></td>
            </tr>
            <tr class="row animated bounceInLeft">
                <th class="col-4">悪い点</th>
                <td class="col-8"><%=diary_beans.getBad_point()%></td>
            </tr>
            <tr class="row animated bounceInLeft slow">
                <th class="col-4">学生コメント</th>
                <td class="col-8"><%=diary_beans.getStudent_comment()%></td>
            </tr>
        </table>

        <form action="commentupdatecheck" method="post" class="mt-5 col-lg-7 col-md-8 col-sm-10 col-12 mr-auto ml-auto">
            <!--教員コメント入力フォーム-->
            <div class="md-form">
                <i class="fas fa-thumbs-up prefix"></i>
                <textarea id="form1" class="md-textarea form-control" maxlength="30" name="teacher-comment" required><%=diary_beans.getTeacher_comment()%></textarea>
                <label for="form1">教員コメント</label>
            </div>

            <%--コメント登録確認画面へ--%>
            <div class="text-center">
                <button type="submit" class="btn btn-primary btn-lg">更新する</button>
            </div>
        </form>

        <%--コメント操作選択画面へ--%>
        <form action="commentselect" method="get" class="text-right back">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
