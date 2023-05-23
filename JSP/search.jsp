<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn4" scope="session" class="beans.SearchBean"/>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>講座検索</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
		
		<%
			String syear = (String)session.getAttribute("syear");
			String smonth = (String)session.getAttribute("smonth");
			String sday = (String)session.getAttribute("sday");
			String sshour = (String)session.getAttribute("sshour");
			String ssminute = (String)session.getAttribute("ssminute");
			String sehour = (String)session.getAttribute("sehour");
			String seminute = (String)session.getAttribute("seminute");
		%>
		
		<h1 class="title">講座検索</h1>
		
		<% List<String> eMessage4 = (List<String>)request.getAttribute("eMessage4");
		   if(eMessage4 != null){%>
		   <div class="box">
		   			<% for(String msg : eMessage4){ %>
		   				<%= msg %><br>
		   			<% } %>
		   </div>
		<% } %>
		
		<form action="../servlet/admin.SearchAction" method="post">
			<table class="tbesearch">
				<tr>
					<td>講座番号</td>
					<td><input type="text" name="cource_no" value="<%= bn4.getCource_no() %>" size="5" class="parts"/></td>
				</tr>
				<tr>
					<td>講座名</td>
					<td><input type="text" name="cource_name" value="<%= bn4.getCource_name() %>" size="10" class="parts"/></td>
				</tr>
				<tr>
					<td>講座開催日</td>
					<td>
						<select name="syear" class="parts2">
							<% int year =  LocalDate.now().getYear(); %>
							<% for(int i=year-5; i<=year+5; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(syear != null && syear != "0"){ %>
								<option value="<%= syear %>" selected="selected"><%= syear %></option>
							<% }  %>
						</select>年
						
						<select name="smonth" class="parts4">
							<% for(int i=1; i<=12; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(smonth != null && smonth != "0"){ %>
								<option value="<%= smonth %>" selected="selected"><%= smonth %></option>
							<% } %>
						</select>月
						
						<select name="sday" class="parts4">
							<% for(int i=1; i<=31; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(sday != null && sday != "0"){ %>
								<option value="<%= sday %>" selected="selected"><%= sday %></option>
							<% }  %>
						</select>日
					</td>
				</tr>
				<tr>
					<td>開始時刻</td>
					<td>
						<select name="sshour" class="parts">
							<% for(int i=10; i<=18; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(sshour != null && sshour != "0"){ %>
								<option value="<%= sshour %>" selected="selected"><%= sshour %></option>
							<% }  %>
						</select>時
						
						<select name="ssminute">
							<% for(int i=0; i<=30; i+=30){ %>
								<% if(i == 0){ %>
									<option value="00" selected="selected">00</option>
								<% }else { %>
									<option value="<%= i %>" selected="selected"><%= i %></option>
								<% } %>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(ssminute != null && ssminute != "0"){ %>
								<option value="<%= ssminute %>" selected="selected"><%= ssminute %></option>
							<% }  %>
						</select>分
					</td>
				</tr>
				<tr>
					<td>終了時刻</td>
					<td>
						<select name="sehour" class="parts">
							<% for(int i=10; i<=18; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(sehour != null && sehour != "0"){ %>
								<option value="<%= sehour %>" selected="selected"><%= sehour %></option>
							<% }  %>
						</select>時
						
						<select name="seminute">
							<% for(int i=0; i<=30; i+=30){ %>
								<% if(i == 0){ %>
									<option value="00" selected="selected">00</option>
								<% }else { %>
									<option value="<%= i %>" selected="selected"><%= i %></option>
								<% } %>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(seminute != null && seminute != "0"){ %>
								<option value="<%= seminute %>" selected="selected"><%= seminute %></option>
							<% } %>
						</select>分
					</td>
				</tr>
				<tr>
					<td>状態</td>
					<td>
						<input type="radio" name="end_condition" class="parts"/><span>終了</span>
						<input type="radio" name="in_session" /><span>開催中</span>
						<input type="radio" name="date_held" /><span>開催予定</span>
						<input type="radio" name="no_condition" /><span>条件無し</span>
					</td>
				</tr>
			</table>
			<p><td><input type="submit" name="search" value="検索" class="button"/></td>
			<p><td><input type="submit" name="admin" value="講座管理メニュー" class="button2"/></td>
		</form>
		<% String userId = (String)request.getAttribute("userId");
		   request.setAttribute("userId", userId); %>
	</body>
</html>