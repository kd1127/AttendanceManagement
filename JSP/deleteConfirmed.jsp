<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>削除完了</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
		<h1 class="title">削除完了</h1>
		
		<p class="explanation">削除を完了しました。</p>
		
		<form action="../servlet/admin.DltConfirmedAction" method="get">
			<p><input type="submit" name="admin" value="講座管理メニュー" class="button6"/></p>
			<p><input type="submit" name="search" value="講座検索" class="button6"/></p>
			<p><input type="submit" name="list" value="講座一覧" class="button6"/></p>
			<p><input type="submit" name="logout" value="ログアウト" class="button6"/></p>
		</form>
	</body>
</html>