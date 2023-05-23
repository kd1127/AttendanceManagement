<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>申し込み完了</title>
		<link rel="stylesheet" href="../CSS/apply.css"/>
	</head>
	<body>
		<h1 class="header">申し込み完了</h1>
		<p class="thanks">講座申し込みの受付を完了しました。<br>ありがとうございました。</p>

		<form action="../servlet/user.EndAction" method="get">
			<input type="submit" value="追加申し込み" class="button"/>
		</form>
	</body>
</html>