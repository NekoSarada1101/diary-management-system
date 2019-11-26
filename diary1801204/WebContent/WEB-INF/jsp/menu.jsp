<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    boolean is_registering = (boolean) session.getAttribute("is-registering");
    String[] today  = ((String) session.getAttribute("today")).split("-");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生トップメニュー</title>
    <%--MDB--%>
    <%@include file="/WEB-INF/jsp/bootstrap.jsp" %>
    <%--Original--%>
    <link rel="stylesheet" href="css/menu.css">
    <link href="https://fonts.googleapis.com/css?family=Orbitron" rel="stylesheet">
</head>
<body class="p-0">
<%@include file="/WEB-INF/jsp/studentTop.jsp" %>

<div class="container-fluid vh-100 animated bounceInUp">
    <div class="col-12 col-sm-10 col-md-8 col-lg-6 m-auto p-5 bg-white z-depth-1">
        <h1 class="text-center border-bottom border-dark">メニュー</h1>

        <% if (is_registering){%>
            <h4 class="text-center">今日の日誌当番は<%=student_name%>さんです</h4>
        <h4 class="text-center">締め切りまで残り</h4>
        <h2 class="text-center" id="deadline"></h2>
        <%}%>

        <div class="row mt-5">
            <%--日誌操作選択画面へ--%>
            <form action="select" method="get" class="col-6 p-0 ml-auto mr-1">
                <button type="submit" class="image1 w-100 animated fadeInLeft delay-1s"><div class="link text-white">登録・修正・削除</div></button>
            </form>

            <%--日誌閲覧画面へ--%>
            <form action="list" method="get" class="col-5 p-0 mr-auto">
                <input type="hidden" value="menu" name="menu-name">
                <button type="submit" class="image2 w-100 animated fadeInRight delay-1s"><div class="link text-white">日誌閲覧</div></button>
            </form>
        </div>
    </div>
</div>

<%@include file="/WEB-INF/jsp/script.jsp" %>

<script>
    var recalc = function () {

        var nenmatsu = new Date(<%=today[0]%>, <%=Integer.parseInt(today[1]) - 1%>, <%=Integer.parseInt(today[2] + 1)%>);

        // カウントダウンタイマーファンクション呼び出し(年末の日付を渡す)
        var counter = countdown(nenmatsu);
        document.getElementById("deadline").textContent = counter;

        refresh();
    }

    var countdown = function (endDate) {
        var today = new Date();

        // 調べたい日 - 今の日付
        var rest = endDate.getTime() - today.getTime();

        // 日付の差分を時・分・秒を計算
        var hours = Math.floor(rest / 1000 / 60 / 60) % 24;
        var minutes = Math.floor(rest / 1000 / 60) % 60;
        var seconds = Math.floor(rest / 1000) % 60;

        // HTMLに表示する文字列
        var msg = hours + "：" + minutes + "：" + seconds;

        return msg;
    }

    var refresh = function () {
        setTimeout(recalc, 1000);
    }

    recalc();

</script>

</body>
</html>
