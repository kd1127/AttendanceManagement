<%@page import="beans.UserBean"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn3" scope="session" class="beans.UserBean"/>

<!-- エラー対応処理 -->
<% 
	System.out.println(bn3.toString());
	if(bn3 != null || bn3.equals(null)){
		bn3 = new UserBean();
	}
%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ログイン</title>
		<link rel="stylesheet" href="../CSS/total.css"/>
	</head>
	<body>
		<h1 class="title">ログイン</h1>
		
		<% List<String> eMessage2 = (List<String>)request.getAttribute("eMessage2"); %>
		 <% if(eMessage2 != null){%>
		   <div class="box">
		   			<% for(String msg : eMessage2){ %>
		   				<%= msg %><br>
		   			<% } %>
		   </div>
		<% } %>
		
		<form action="../servlet/total.LoginAction" method="post">
			<table class="tblayout">
				<tr>
					<td class="headline">ユーザID <span>※</span></td>
					<td><input type="text" name="userID" value="<%= bn3.getUser_id() %>" maxlength="8" class="text"/></td>
				</tr>
				<tr>
					<td class="headline">パスワード <span>※</span></td>
					<td><input type="password" name="pass" value="<%= bn3.getPasswd() %>" maxlength="8" class="text"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="ログイン" name="login" class="button"/></td>
				</tr>
				<tr>
					<td class="guidance">初めての方はユーザ登録してください。</td>
				</tr>
				<tr>
					<td><input type="submit" value="ユーザ登録" name="register" class="button"/></td>
			</table>
		</form>
	</body>
</html>