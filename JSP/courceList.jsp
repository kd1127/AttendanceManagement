<%@page import="result.RegistCourseEntity"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.time.LocalDateTime"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%
	List<RegistCourseEntity> courseList = (List<RegistCourseEntity>) session.getAttribute("courseList");
%>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>講座一覧</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
		<% int i = 0; %>
		
		<h1 class="title">講座一覧</h1>
		
		<form action="../servlet/admin.ListAction" method="post">
			<% if(courseList.isEmpty()){ %>
					<tr><td><h3 class="explanation"><span>※</span> 検索条件に一致する講座がありませんでした</h3></td></tr>
			<% }else{ %>
				<table border="1" style="border-collapse: collapse" class="courceList">
					<tr bgcolor="00bbff">
						<th style="text-align: center">講座番号</th>
						<th style="text-align: center">講座名</th>
						<th style="text-align: center">開催日時</th>
						<th style="text-align: center">定員</th>
						<th style="text-align: center">状態</th>
						<th style="text-align: center"></th>
					</tr>					
					<% for(RegistCourseEntity course : courseList){ %>
						<tr>
							<td><%= course.getCourse_no() %></td>
							<td><%= course.getCourse_name() %></td>
							<td><%= course.getThe_Date() %></td>
							<td style="text-align: center"><%= course.getCapacity() %></td>
							
							<% LocalDate ld = LocalDate.now();	//	現在時刻
							   String event_date = course.getThe_Date().substring(0, 10); %>
							<% LocalDate ed = LocalDate.parse(event_date); %>			<!-- DBから抽出した時刻 -->
							
							<% if(ld.isEqual(ed)){ %>
									<td style="text-align: center">開催中</td>
							<% }else if(ld.isBefore(ed)){ %>
								<td style="text-align: center">開催予定</td>
							<% } else{ %>
								<td style="text-align: center">終了</td>
							<% } %>
							<td style="text-align: center">
								<input type="submit" name="fix<%= i %>" value="修正" class="button3"/>
								<input type="submit" name="remove<%= i %>" value="削除" class="button3"/>
							</td>
							<% i++; %>
						</tr>
					<% } %>
					<% session.setAttribute("courseList", courseList); %>
					<% session.setAttribute("i", i); %>
				</table>
			<% } %>
			<input type="submit" name="search" value="講座検索" class="button4"/>
		</form>
	</body>
</html>