<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn5" scope="session" class="beans.InputBean"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修正完了</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
		<h1 class="title">修正完了</h1>
		
		<p class="explanation">講座情報の修正を完了しました。</p>
		
		<form action="../servlet/admin.ConfirmedAction" method="get">
			<input type="submit" name="admin" value="講座管理メニュー" class="button9"/>
			<input type="submit" name="search" value="講座検索" class="button9"/>
			<input type="submit" name="list" value="講座一覧" class="button9"/>
			<input type="submit" name="logout" value="ログアウト" class="button9"/>
		</form>
		<% session.setAttribute("bn5", bn5); %>
	</body>
</html>