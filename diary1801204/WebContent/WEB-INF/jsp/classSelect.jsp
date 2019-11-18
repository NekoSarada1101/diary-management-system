<%@ page import="diary.bean.TeacherBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    List<TeacherBeans> teacher_list = (List<TeacherBeans>) session.getAttribute("teacher-list");
    String teacher_name = ((TeacherBeans)((List<TeacherBeans>)session.getAttribute("teacher-list")).get(0)).getTeacher_name();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>クラス選択画面</title>

    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
</head>
<body class="p-0">
<header>
    <div class="image position-absolute animated fadeIn"></div>
    <nav class="navbar navbar-expand-lg navbar-dark scrolling-navbar z-depth-1" id="teacher-nav">
        <a class="navbar-brand" href="#"><strong class="text-dark">DiaryManagementSystem</strong></a>
        <span class="text-dark"><%=teacher_name%>先生</span>
        <form action="logout" method="get" class="ml-auto mr-0">
            <button type="submit" class="btn btn-grey pt-2 pb-2 pr-3 pl-3">ログアウト</button>
        </form>
    </nav>
</header>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">担任クラス選択</h1>


        <form action="teachermenu" method="get" class="mt-5">
            <% for (int i = 0; i < teacher_list.size(); i++) { %>
            <input type="hidden" value="<%=i%>" name="select-class">
            <div class="text-center">
                <button type="submit"
                        class="btn btn-link mt-3"><h3><%=teacher_list.get(i).getCourse_name() + teacher_list.get(i).getGrade() + teacher_list.get(i).getClass_name()%></h3>
                </button>
            </div>
            <% } %>
        </form>


    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
