<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生トップメニュー</title>
    <%--Flat UI--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
</head>
<body class="container pt-3 pb-3">
<div class="row">
    <div class="h-25 col-3">
        <a href="menu"><img src="img/cup.png" alt="カップ" class="h-50 p-0"></a>
    </div>

    <div class="col-8 vh-100">
        <div class="col-8 border border-dark bg-white pt-3 pr-lg-5 pl-lg-5 pr-md-3 pl-md-3 pr-1 pl-1 form">
            <h1 class="text-center border-bottom border-dark">Menu</h1>

            <form action="select" method="get">
                <div class="text-center mt-5"><input type="submit" value="Register・Correct・Delete"
                                                     class="btn btn-info btn-lg"></div>
            </form>

            <form action="list" method="get">
                <div class="text-center mt-5"><input type="submit" value="Diary Browsing"
                                                     class="btn btn-success btn-lg"></div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
