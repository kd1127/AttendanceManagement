<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザ登録完了</title>
		<link rel="stylesheet" href="../CSS/total.css">
	</head>
	<body>
		<h1 class="title">ユーザ登録完了</h1>
		<% String user_id = (String)request.getAttribute("user_id"); %>
		<h3 class="userid">ユーザID　　　　　　　<span class="clchange"><%= user_id %></span></h3>
		
		<p class="guidance2">上記ユーザIDで登録されました。</p>
		
		<form action="../servlet/total.ConfirmedAction" method="get">
			<table>
				<tr>
					<td><input type="submit" value="講座申し込み" name="apply" class="button4"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="ログアウト" name="logout" class="button4"/></td>
				</tr>
			</table>
		</form>
	</body>
</html>