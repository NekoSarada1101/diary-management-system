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
</head>
<body class="container pt-3 pb-3">
<div class="row">
    <img src="img/cup.png" alt="カップ" class="col-3 align-top h-50 p-0">

    <div class="col-8 vh-100">
        <div class="col-8 border border-dark bg-white pt-3 pr-lg-5 pl-lg-5 pr-md-3 pl-md-3 pr-1 pl-1 form">
            <h1 class="text-center border-bottom border-dark">Register</h1>
            <form action="diaryinsertcheck" method="post" class="mt-5">
                Good point
                <textarea name="good-point" cols="10" rows="3" class="form-control mb-5"></textarea>

                Bad point
                <textarea name="bad-point" cols="10" rows="3" class="form-control mb-5"></textarea>

                Comment
                <textarea name="student-comment" cols="10" rows="3" class="form-control mb-5"></textarea>

                <div class="text-center"><input type="submit" value="Register" class="btn btn-info btn-lg"></div>
            </form>

            <form action="select" method="get">
                <div class="text-right mt-n5"><input type="submit" value="Back" class="btn btn-outline-primary btn-lg"></div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
