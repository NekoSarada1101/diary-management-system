<%@ page import="diary.bean.DiaryBeans" %>
<%@ page import="diary.bean.TeacherBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%
    List<TeacherBeans> teacher_list = (List<TeacherBeans>) session.getAttribute("teacher-list");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>クラス選択画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>

    <%--Original--%>
    <link rel="stylesheet" href="css/dispDiaryList.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100">
    <div class="col-12 col-md-10 ml-auto mr-auto mt-5 mb-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">担任クラス選択</h1>


        <form action="teachermenu" method="post" class="mt-5">
            <% for (int i = 0; i < teacher_list.size(); i++) { %>
            <input type="hidden" value="<%=i%>">
            <button type="submit"
                    class="btn btn-link mt-3"><%=teacher_list.get(i).getCourse_name() + teacher_list.get(i).getGrade() + teacher_list.get(i).getClass_name()%>
            </button>
            <% } %>
        </form>



    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
