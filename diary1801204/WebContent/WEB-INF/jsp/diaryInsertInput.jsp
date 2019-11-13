<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌登録入力画面</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/diaryInsert.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌登録</h1>

        <form action="diaryinsertcheck" method="post" class="mt-5 col-lg-7 col-md-8 col-sm-10 col-12 mr-auto ml-auto">
            <!--Textarea with icon prefix-->
            <div class="md-form">
                <i class="fas fa-thumbs-up prefix"></i>
                <textarea id="form1" class="md-textarea form-control" maxlength="30" name="good-point"></textarea>
                <label for="form1">良い点</label>
            </div>

            <!--Textarea with icon prefix-->
            <div class="md-form mt-5">
                <i class="fas fa-thumbs-down prefix"></i>
                <textarea id="form2" class="md-textarea form-control" maxlength="30" name="bad-point"></textarea>
                <label for="form2">悪い点</label>
            </div>

            <!--Textarea with icon prefix-->
            <div class="md-form mt-5">
                <i class="fas fa-comment prefix"></i>
                <textarea id="form3" class="md-textarea form-control" maxlength="30" name="student-comment"></textarea>
                <label for="form3">コメント</label>
            </div>

            <div class="text-center">
                <button type="submit" class="btn btn-primary btn-lg">登録する</button>
            </div>
        </form>

        <form action="select" method="get" class="text-right back">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
