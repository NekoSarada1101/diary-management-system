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
    <link rel="stylesheet" href="css/menu.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100">
    <div class="col-12 col-md-9 col-lg-6 ml-auto mr-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">メニュー</h1>

        <div class="row mt-5">
            <form action="select" method="get" class="col-6 p-0 ml-auto mr-1">
                <button type="submit" class="image1 w-100"><div class="link text-white">登録・修正・削除</div></button>
            </form>

            <form action="select" method="get" class="col-5 p-0 mr-auto">
                <button type="submit" class="image2 w-100"><div class="link text-white">日誌閲覧</div></button>
            </form>

        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
