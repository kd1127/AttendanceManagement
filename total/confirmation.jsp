<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn3" scope="session" class="beans.UserBean"/>
   
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザ登録確認</title>
		<link rel="stylesheet" href="../CSS/total.css">
	</head>
	<body>
		<h1 class="title">ユーザ登録確認</h1>
		
		<p class="userid">ユーザID　　　　　<span class="clchange"><%= bn3.getUser_id() %></span></p>
		
		<form action="../servlet/total.ConfirmAction" method="post">
			<input type="submit" value="戻る" name="back" class="button3">
			<input type="submit" value="登録" name="register" class="button3">
		</form>
		<% session.setAttribute("bn3", bn3); %>
	</body>
</html>