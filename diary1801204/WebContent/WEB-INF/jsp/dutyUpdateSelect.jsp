<%@ page import="diary.bean.DutyBeans" %>
<%@ page import="java.util.List" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    List<DutyBeans> duty_list = (List<DutyBeans>) session.getAttribute("student-list");
    String today = (String) session.getAttribute("today");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌当番一覧・修正画面</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="manipulationSelect.css">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/teacherTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp faster">
    <div class="col-12 col-md-10 ml-auto mr-auto mt-5 mb-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">日誌当番一覧・修正</h1>

        <div class="table-wrapper-scroll-y my-custom-scrollbar col-12 mt-3">
            <table class="table table-hover border-top mr-auto ml-auto mb-0">
                <thead>
                <tr class="row">
                    <th class="col-3 border-right" scope="col">担当日</th>
                    <th class="col-3 border-right" scope="col">学籍番号</th>
                    <th class="col-3 border-right" scope="col">名前</th>
                    <th class="col-3" scope="col">操作</th>
                </tr>

                <tbody>
                <% for (int i = 0; i < duty_list.size(); i++) { %>
                <tr class="row animated bounceInUp">
                    <th class="col-3 border-right" scope="row"><%=duty_list.get(i).getInsert_date()%>
                    </th>
                    <th class="col-3 border-right"><%=duty_list.get(i).getStudent_id()%>
                    </th>
                    <td class="col-3 border-right"><%=duty_list.get(i).getStudent_name()%>
                    </td>
                    <td class="col-3">
                        <% if (duty_list.get(i).getInsert_date().equals(today)) { %>
                        <%--日誌当番登録確認画面へ--%>
                        <form action="dutyupdateinput" method="post" class="text-center">
                            <input type="hidden" name="select-student" value="<%=i%>">
                            <button type="submit" class="btn btn-warning">修正</button>
                        </form>
                        <% } %>

                    </td>
                </tr>
                <% } %>
                </tbody>
            </table>
        </div>

        <%--検索機能--%>
        <form action="teachersearch" method="post" class="mt-3">
            <div class="input-group">
                <input class="form-control mt-2" type="text" name="search-word">
                <input type="hidden" value="dutyUpdateSelect" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">検索</button>
            </div>
        </form>

        <%--ソート機能--%>
        <form action="teachersort" method="post">
            <div class="input-group mb-3">
                <select class="custom-select mt-2" name="sort-column">
                    <option value="insert_date">日付</option>
                    <option value="student_id">学籍番号</option>
                    <option value="student_name">名前</option>
                </select>

                <select class="custom-select mt-2" name="sort-order">
                    <option value="ASC">昇順</option>
                    <option value="DESC">降順</option>
                </select>
                <input type="hidden" value="dutyUpdateSelect" name="from-jsp-name">
                <button type="submit" class="btn btn-primary">ソート</button>
            </div>
        </form>

        <%--メニュー画面へ--%>
        <form action="teachermenu" method="get" class="text-right">
            <button type="submit" class="btn btn-outline-dark">戻る</button>
        </form>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

</body>
</html>
