<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>講座修正</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
	
		<% String cource_no = (String)session.getAttribute("cource_no"); %>
		<% String cource_name = (String)session.getAttribute("cource_name"); %>
		<% String ayear = (String)session.getAttribute("ayear"); %>
		<% String amonth = (String)session.getAttribute("amonth"); %>
		<% String aday = (String)session.getAttribute("aday"); %>
		<% String sthour = (String)session.getAttribute("sthour"); %>
		<% String stminute = (String)session.getAttribute("stminute"); %>
		<% String endhour = (String)session.getAttribute("endhour"); %>
		<% String endminute = (String)session.getAttribute("endminute"); %>
		<% String capacity = (String)session.getAttribute("capacity"); %>
		
		<h1 class="title">講座修正</h1>
		
		<% List<String> eMessage4 = (List<String>)request.getAttribute("eMessage4");
		   if(eMessage4 != null){%>
		   <div class="box">
		   			<% for(String msg : eMessage4){ %>
		   				<%= msg %><br>
		   			<% } %>
		   </div>
		<% } %>
		
		<form action="../servlet/admin.FixAction" method="post">
			<table class="fixtable">
				<tr>
					<td>講座番号</td>
					<td><span><div class="parts"><%= cource_no %></div></span></td>
				</tr>
				<tr>
					<td>講座名</td>
					<td><input type="text" name="cource_name" value="<%= cource_name %>" maxlength="40" class="parts"/></td>
				</tr>
				<tr>
					<td>講座開催日</td>
					<td>
						<select name="ayear" class="parts2">
							<% int year =  LocalDate.now().getYear(); %>
							<% for(int i=year; i<=year+5; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(ayear != null){ %>
								<option value="<%= ayear %>" selected="selected"><%= ayear %></option>
							<% }  %>
						</select>年
						
						<select name="amonth"  class="parts3">
							<% for(int i=1; i<=12; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(amonth != null){ %>
								<option value="<%= amonth %>" selected="selected"><%= amonth %></option>
							<% } %>
						</select>月
						
						<select name="aday" class="parts3">
							<% for(int i=1; i<=31; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(aday != null){ %>
								<option value="<%= aday %>" selected="selected"><%= aday %></option>
							<% } %>
						</select>日
					</td>
				</tr>
				<tr>
					<td>開始時刻</td>
					<td>
						<select name="sthour" class="parts2">
							<% for(int i=10; i<=18; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(sthour != null){ %>
								<option value="<%= sthour %>" selected="selected"><%= sthour %></option>
							<% } %>
						</select>時
						
						<select name="stminute" class="parts3">
							<% for(int i=0; i<=30; i+=30){ %>
								<% if(i == 0){ %>
									<option value="00" selected="selected">00</option>
								<% }else { %>
									<option value="<%= i %>" selected="selected"><%= i %></option>
								<% } %>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(stminute != null){ %>
								<option value="<%= stminute %>" selected="selected"><%= stminute %></option>
							<% } %>
						</select>分
					</td>
				</tr>
				<tr>
					<td>終了時刻</td>
					<td>
						<select name="endhour" class="parts2">
							<% for(int i=10; i<=18; i++){ %>
								<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(endhour != null){ %>
								<option value="<%= endhour %>" selected="selected"><%= endhour %></option>
							<% } %>
						</select>時
						
						<select name="endminute" class="parts3">
							<% for(int i=0; i<=30; i+=30){ %>
								<% if(i == 0){ %>
									<option value="00" selected="selected">00</option>
								<% }else { %>
									<option value="<%= i %>" selected="selected"><%= i %></option>
								<% } %>
							<% } %>
							<option value="" selected="selected"></option>
							
							<% if(endminute != null){ %>
								<option value="<%= endminute %>" selected="selected"><%= endminute %></option>
							<% }  %>
						</select>分
					</td>
				</tr>
				<tr>
					<td>定員</td>
					<td><input type="text" name="capacity" value="<%= capacity %>" size="4" class="parts"/>人</td>
				</tr>
			</table>
			<input type="submit" name="back" value="戻る" class="button7"/>
			<input type="submit" name="check" value="確認" class="button7"/>
		</form>
	</body>
</html>