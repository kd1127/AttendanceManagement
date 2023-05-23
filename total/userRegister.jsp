<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn3" scope="session" class="beans.UserBean"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>ユーザ登録</title>
		<link rel="stylesheet" href="../CSS/total.css">
	</head>
	<body>
		<h1 class="title">ユーザ登録</h1>
		<h3 class="caution">※　は入力必須です。</h3>
		
		<% List<String> eMessage3 = (List<String>)request.getAttribute("eMessage3"); %>
		 <% if(eMessage3 != null){%>
		   <div class="box">
		   			<% for(String msg : eMessage3){ %>
		   				<%= msg %><br>
		   			<% } %>
		   </div>
		<% } %>
		
		<form action="../servlet/total.UserRgsAction" method="post">
			<table class="tblayout2">
				<tr>
					<td class="headline2">ユーザID <span>※</span></td>
					<td><input type="text" name="userID" value="<%= bn3.getUser_id() %>" maxlength="8" class="text2"/></td>
				</tr>
				<tr>
					<td class="headline2">パスワード <span>※</span></td>
					<td><input type="password" name="pass" value="<%= bn3.getPasswd() %>" maxlength="8" class="text2"/></td>
				</tr>
				<tr>
					<td class="headline2">パスワード（確認用）</td>
					<td><input type="password" name="pass2" value="<%= bn3.getPasswd2() %>" maxlength="8" class="text2"/></td>
				</tr>
				<tr>
					<td><input type="submit" value="戻る" name="back" class="button2"></td>
					<td><input type="submit" value="登録" name="confirmation" class="button2"></td>
				</tr>
			</table>
		</form>
		<% session.setAttribute("bn3", bn3); %>
	</body>
</html>