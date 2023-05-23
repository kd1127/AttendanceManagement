<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="user.InsertApplicationDBAccesss"%>
<%@page import="admin.InputDBAccess"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn2" scope="session" class="beans.ApplicationBean"/>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>申し込み確認</title>
		<link rel="stylesheet" href="../CSS/apply.css"/>
	</head>
	<body>

		<% InputDBAccess idb = new InputDBAccess(); %>
		<% InsertApplicationDBAccesss iadb = new InsertApplicationDBAccesss(); %>
		
		<% List<String> dlist = new ArrayList<String>(); %>		<!--  開催日時・開始時刻・終了時刻 -->
		<% List<String> clist = new ArrayList<String>(); %>		<!--  講座名 -->
		<% List<String> calist = new ArrayList<String>();  %>   <!-- 定員  -->
		<% List<Integer> aplist = new ArrayList<Integer>();  %>   <!-- 講座申込者数  -->
		
		<% int capacity; %>
		
		<!--  それぞれのlistに値を入れる -->
		<% try{ %>
			<% dlist = idb.acquisition(1, null, null, null); %>
			<% clist = idb.acquisition(2, null, null, null); %>
			<% calist = idb.acquisition(4, null, null, null); %>
			
			<% for(int i=0; i<clist.size(); i++){ %>
				<% aplist = iadb.getNumApplicantsCource(clist.get(i)); %>
			<% } %>
			
		<% } catch(Exception e){ %>
			<% e.printStackTrace(); %>
		<% } %>
		
	
		<h1 class="header">申し込み確認</h1>
		<h3 class="caution">※入力内容を確認してください。</h3>
		
		<form action="../servlet/user.ConfAction" method="post">
			<table class="tbposition">
				<tr>
					<td class="confdeco">お名前</td>
					<td><jsp:getProperty property="name" name="bn2"/></td>
				</tr>
				<tr>
					<td class="confdeco">フリガナ</td>
					<td><jsp:getProperty property="furigana" name="bn2"/></td>
				</tr>
				<tr>
					<td class="confdeco">Eメールアドレス</td>
					<td><jsp:getProperty property="mail" name="bn2"/></td>
				</tr>
				<tr>
					<td class="confdeco">性別</td>
					<td>
						<% if(bn2.getGender() == 1){ %>
								<div>●男　〇女</div>
						<% } %>
						<% if(bn2.getGender() == 2){ %>
								<div>〇男　●女</div>
						<% } %>
						<% if(bn2.getGender() == 3){ %>
								<div>〇男　〇女</div>
						<% } %>
					</td>
				</tr>
				<tr>
					<td class="confdeco">生年月日</td>
					<td>
						<jsp:getProperty property="byear" name="bn2"/><span class="confdeco">年</span>
						<jsp:getProperty property="bmonth" name="bn2"/><span class="confdeco">月</span>
						<jsp:getProperty property="bday" name="bn2"/><span class="confdeco">日</span>
					</td>
				</tr>
				<tr>
					<td class="confdeco">電話番号</td>
					<td><jsp:getProperty property="tel" name="bn2"/></td>
				</tr>
				<tr>
					<td class="confdeco">希望講座</td>
					<td>
						<table border="1" style="border-collapse: collapse" class="tablestyle">
							<tr bgcolor="00bbff">
								<th>開催日時</th>
								<th>講座名</th>
								<th>空席状況</th>
								<th>チェック</th>
							</tr>
							<tr>
								<td><%= dlist.get(0) %></td>
								<td><%= clist.get(0) %></td>
								<td class="center">
									<% capacity = Integer.parseInt(calist.get(0)); %>
									<% if(capacity != 0){ %>
											<div>残<%= calist.get(0) %>席</div>
									<% } else{ %>
											<div>満席</div>
									<% } %>
								</td>
								<td>
									<% if(bn2.isApply1() == true){ %>
											<div class="center">✓</div>
									<% }  %>
								</td>
							</tr>
							<tr>
								<td><%= dlist.get(1) %></td>
								<td><%= clist.get(1) %></td>
								<td class="center">
									<% capacity = Integer.parseInt(calist.get(1)); %>
									<% if(capacity != 0){ %>
											<div>残<%= calist.get(1) %>席</div>
									<% } else{ %>
											<div>満席</div>
									<% } %>
								</td>
								<td>
									<% if(bn2.isApply2() == true){ %>
											<div class="center">✓</div>
									<% }  %>
								</td>
							</tr>
							<tr>
								<td><%= dlist.get(2) %></td>
								<td><%= clist.get(2) %></td>
								<td class="center">
									<% capacity = Integer.parseInt(calist.get(2)); %>
									<% if(capacity != 0){ %>
											<div>残<%= calist.get(2) %>席</div>
									<% } else{ %>
											<div>満席</div>
									<% } %>
								</td>
								<td>
									<% if(bn2.isApply3() == true){ %>
											<div class="center">✓</div>
									<% }  %>
								</td>
							</tr>
							<tr>
								<td><%= dlist.get(3) %></td>
								<td><%= clist.get(3) %></td>
								<td class="center">
									<% capacity = Integer.parseInt(calist.get(3)); %>
									<% if(capacity != 0){ %>
											<div>残<%= calist.get(3) %>席</div>
									<% } else{ %>
											<div>満席</div>
									<% } %>
								</td>
								<td>
									<% if(bn2.isApply4() == true){ %>
											<div class="center">✓</div>
									<% }  %>
								</td>
							</tr>
							<tr>
								<td><%= dlist.get(4) %></td>
								<td><%= clist.get(4) %></td>
								<td class="center">
									<% capacity = Integer.parseInt(calist.get(4)); %>
									<% if(capacity != 0){ %>
											<div>残<%= calist.get(4) %>席</div>
									<% } else{ %>
											<div>満席</div>
									<% } %>
								</td>
								<td>
									<% if(bn2.isApply5() == true){ %>
											<div class="center">✓</div>
									<% }  %>
								</td>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td class="confdeco">備考欄</td>
					<td><p class="newline"><jsp:getProperty property="remarks" name="bn2"/></p></td>
				</tr>
			</table>
			<input type="submit" value="戻る" name="back" class="button2"/>
			<input type="submit" value="申し込み" name="apply" class="button2"/>
		</form>
		<% session.setAttribute("bn2", bn2); %>
	</body>
</html>