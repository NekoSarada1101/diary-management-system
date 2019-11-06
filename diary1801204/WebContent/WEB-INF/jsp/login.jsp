<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<%
    String error_message = (String) session.getAttribute("error-message");
    if (error_message == null) {
        error_message = "";
    }
    session.removeAttribute("error-message");
%>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生ログイン</title>

    <%--boostwatch--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
</head>
<body class="container pt-3 pb-3">
<div class="row">
    <img src="img/cup.png" alt="カップ" class="col-3 align-top h-50 p-0">

    <div class="col-8 vh-100">
        <div class="col-8 border border-dark bg-white pt-3 pr-lg-5 pl-lg-5 pr-md-3 pl-md-3 pr-1 pl-1 form">
            <h1 class="text-center border-bottom border-dark">Login</h1>

            <form action="auth" method="post" class="mt-5">
                Student ID
                <input type="text" name="student-id" class="form-control mb-5" required>

                Password
                <input type="password" name="student-password" class="form-control mb-5" required>

                <p class="text-center text-danger"><%=error_message%></p>

                <div class="text-center mt-5"><input type="submit" value="Login" class="btn btn-info btn-lg"></div>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
