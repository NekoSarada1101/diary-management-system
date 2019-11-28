<%@ page import="diary.bean.TeacherBeans" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    TeacherBeans teacher_beans = (TeacherBeans) session.getAttribute("teacher-beans");
    String error_message = (String) session.getAttribute("error-message");
    if (error_message == null) error_message = "";
    session.removeAttribute("error-message");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>教員トップメニュー</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/menu.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark"><%=teacher_beans.getCourse_name() + teacher_beans.getGrade() + teacher_beans.getClass_name()%>
        </h1>

        <p class="text-center text-danger"><%=error_message%></p>

        <div class="row mt-3">
            <%--コメント操作選択画面へ--%>
            <form action="commentselect" method="get" class="col-6 p-0 ml-auto mr-1">
                <button type="submit" class="image1 w-100 animated fadeInLeft delay-1s">
                    <div class="link text-white">登録・修正・削除</div>
                </button>
            </form>

            <%--日誌閲覧画面へ--%>
            <form action="list" method="get" class="col-5 p-0 mr-auto">
                <input type="hidden" value="teachermenu" name="menu-name">
                <button type="submit" class="image2 w-100 animated fadeInDown delay-1s">
                    <div class="link text-white">日誌閲覧</div>
                </button>
            </form>
        </div>

        <div class="row mt-1">
            <%--日誌当番指名画面へ--%>
            <form action="dutyinsertselect" method="get" class="col-5 p-0 ml-auto mr-1">
                <button type="submit" class="image3 w-100 animated fadeInUp delay-1s">
                    <div class="link text-white">日誌当番登録</div>
                </button>
            </form>

            <%--日誌当番変更画面へ--%>
            <form action="dutyupdateselect" method="get" class="col-6 p-0 mr-auto">
                <button type="submit" class="image4 w-100 animated fadeInRight delay-1s">
                    <div class="link text-white">日誌当番一覧・修正</div>
                </button>
            </form>
        </div>

        <form action="classselect" method="get" class="mt-1">
            <button type="submit" class="btn btn-outline-dark btn-block">クラス選択画面へ戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
