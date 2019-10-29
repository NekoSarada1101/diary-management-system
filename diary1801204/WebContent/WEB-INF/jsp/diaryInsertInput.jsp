<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>日誌登録入力画面</title>
</head>
<body>
<h3>日誌登録</h3>
<form action="diaryinsertcheck" method="post">
    <p>良い点</p>
    <textarea name="good-point" cols="10" rows="3"></textarea>
    <p>悪い点</p>
    <textarea name="bad-point" cols="10" rows="3"></textarea>
    <p>コメント</p>
    <textarea name="student-comment" cols="10" rows="3"></textarea>

    <input type="submit" value="登録">
</form>

<form action="select" method="post">
    <input type="submit" value="戻る">
</form>
</body>
</html>
