<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn5" scope="session" class="beans.InputBean"/>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>修正確認</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
		<% 
			String cource_no = (String)session.getAttribute("cource_no");
			String cource_name= (String)session.getAttribute("cource_name");
			String ayear = (String)session.getAttribute("ayear");
			String amonth = (String)session.getAttribute("amonth");
			String aday = (String)session.getAttribute("aday");
			String sthour = (String)session.getAttribute("sthour");
			String stminute = (String)session.getAttribute("stminute");
			String endhour = (String)session.getAttribute("endhour");
			String endminute = (String)session.getAttribute("endminute");
			String capacity = (String)session.getAttribute("capacity");
		%>
		
		<h1 class="title">修正確認</h1>
		
		<p class="explanation">講座情報の修正内容を確認してください。</p>
		
		<form action="../servlet/admin.ConfirmAction" method="post">
			<table class="confirmtbl">	<!-- cssを変更 -->
				<tr>
					<td>講座番号　</td>
					<td><span class="position"><%= cource_no %></span></td>
				</tr>
				<tr>
					<td>講座名　　</td>
					<td><div class="fontdeco"><%= cource_name %></div></td>
				</tr>
				<tr>
					<td>講座開催日</td>
					<td>
						　　　<span class="fontcolor"><%= ayear %></span>年
						<span class="fontcolor"><%= amonth %></span>月
						<span class="fontcolor"><%= aday %></span>日
					</td>
				</tr>
				<tr>
					<td>開始時刻　</td>
					<td>
						　　 <span class="position5"><%= sthour %></span>時
						<span class="position6"><%= stminute %></span>分
					</td>
				</tr>
				<tr>
					<td>終了時刻　</td>
					<td>
						　　 <span class="position3"><%= endhour %></span>時
						<span class="position4"><%= endminute %></span>分
					</td>
				</tr>
				<tr>
					<td>定員　　　</td>
					<td><span class="position2"><%= capacity %></span>人</td>
				</tr>
			</table>
			<input type="submit" name="back" value="戻る" class="button8"/>
			<input type="submit" name="fix" value="修正" class="button8"/>
		</form>
	</body>
</html>