<%@page import="admin.InputDBAccess"%>
<%@page import="user.InsertApplicationDBAccesss" %>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="java.time.LocalDate"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn2" scope="session" class="beans.ApplicationBean"/>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>講座申し込み</title>
		<link rel="stylesheet" href="../CSS/layout.css">
		<link rel="stylesheet" href="../CSS/apply.css">
	</head>
	<body>
	
		<% String byear = (String)session.getAttribute("byear");
		   String bmonth = (String)session.getAttribute("bmonth");
		   String bday = (String)session.getAttribute("bday");
		   int capacity; %>
	
		<% InputDBAccess idb = new InputDBAccess(); %>
		<% InsertApplicationDBAccesss iadb = new InsertApplicationDBAccesss(); %>
		
		<% List<String> dlist = new ArrayList<String>(); %>		<!--  開催日時・開始時刻・終了時刻 -->
		<% List<String> clist = new ArrayList<String>(); %>		<!--  講座名 -->
		<% List<String> calist = new ArrayList<String>();  %>   <!-- 定員  -->
		<% List<Integer> aplist = new ArrayList<Integer>();  %>   <!-- 講座申込者数  -->
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
		
		<% String name = (String)request.getAttribute("name"); %>
		
		<h1 class="header">講座申し込み</h1>
		<h3 class="caution">※　は必須項目です。</h3>
		
		<% List<String> eMessage = (List<String>)request.getAttribute("eMessage");
		   if(eMessage != null){%>
		   <div class="box">
		   			<% for(String msg : eMessage){ %>
		   				<%= msg %><br>
		   			<% } %>
		   </div>
		<% } %>
		
		<form action="../servlet/user.InputAction" method="post">
			<table class="tbposition">
				<tr>
					<td><span class="fontdeco">お名前</span> <span class="asterisk">※</span></td>
					<td><input type="text" name="name" value="<%= bn2.getName() %>" maxlength="100"/></td>
				</tr>
				<tr>
					<td><span class="fontdeco">フリガナ</span> <span class="asterisk">※</span></td>
					<td><input type="text" name="furigana" value="<%= bn2.getFurigana() %>" maxlength="100"/></td>
				</tr>
				<tr>
					<td><span class="fontdeco">Eメールアドレス</span> <span class="asterisk">※</span></td>
					<td><input type="text" name="mail" value="<%= bn2.getMail() %>" maxlength="255"/></td>
				</tr>
				<tr>
					<td><span class="fontdeco">性別</span></td>
					<td>
						<% if(bn2.getGender() == 0 || bn2.getGender() == 3){ %>
							<input type="radio" name="gender" value="man"/>男
							<input type="radio" name="gender" value="woman"/>女
						<% } else if(bn2.getGender() == 1){ %>
							<input type="radio" name="gender" value="man" checked="checked"/>男
							<input type="radio" name="gender" value="woman"/>女
						<% } else{ %>
							<input type="radio" name="gender" value="man"/>男
							<input type="radio" name="gender" value="woman" checked="checked"/>女
						<% } %>
					</td>
				</tr>
				<tr>
					<td><span class="fontdeco">生年月日</span> <span class="asterisk">※</span></td>
					<td>
						<select name="byear">
							<% int year = LocalDate.now().getYear(); %>
							<% for(int i=year-60; i<=year-18; i++){ %>
									<option value=<%= i %> selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(byear != null){ %>
								<option value="<%= bn2.getByear() %>" selected="selected"><%= bn2.getByear() %></option>
							<% } %>
						</select><span class="fontdeco">　年</span>
						
						<select name="bmonth" class="timefield">
							<% for(int i=1; i<=12; i++){ %>
									<option value="<%= i %>" selected="selected"><%= i %></option>
							<% } %>
							<option value="" selected="selected"></option>
							<% if(bmonth != null){ %>
								<option value="<%= bn2.getBmonth() %>" selected="selected"><%= bn2.getBmonth() %></option>
							<% }  %>
						</select><span class="fontdeco">　月</span>
						
						<select name="bday" class="timefield">
							<% for(int i=1; i<=31; i++){ %>
									<option value="<%= i %>" selected="selected"><%= i %></option>
							<% }  %>
							<option value="" selected="selected"></option>
							<% if(bday != null){ %>
								<option value="<%= bn2.getBday() %>" selected="selected"><%= bn2.getBday() %></option>
							<% } %>
						</select><span class="fontdeco">　日</span>
					</td>
				</tr>
				<tr>
					<td><span class="fontdeco">電話番号</span></td>
					<td><input type="tel" name="tel" value="<%= bn2.getTel() %>" maxlength="15"/></td>
				</tr>
				<tr>
					<td><span class="applyfont">希望講座</span> <span class="asterisk">※</span></td>
					<td>
						<table border="1" style="border-collapse: collapse" class="applytable">
							<tr bgcolor="00bbff">
								<th width="340">開催日時</th>
								<th width="140">講座名</th>
								<th width="85">空席状況</th>
								<th width="85">チェック</th>
							</tr>
							<tr>
								<td nowrap><%= dlist.get(0) %></td>
								<td nowrap><%= clist.get(0) %></td>
								<th>
									<% capacity = Integer.parseInt(calist.get(0)); %>
									<% if(capacity != 0){ %>
											<div class="fontstyle">残<%= calist.get(0) %>席</div>
									<% } else{ %>
											<div class="fontstyle">満席</div>
									<% } %>
								</th>
								<th>
									<% if(capacity >= 1){ %>
										<% if(bn2.isApply1() == true){ %>
											<input type="checkbox" name="apply1" value="apply1" checked="checked"/>
										<% } else{ %>
											<input type="checkbox" name="apply1" value="apply1">
										<% } %>
									<% } %>
								</th>
							</tr>
							<tr>
								<td nowrap><%= dlist.get(1) %></td>
								<td nowrap><%= clist.get(1) %></td>
								<th>
									<% capacity = Integer.parseInt(calist.get(1)); %>
									<% if(capacity != 0){ %>
											<div class="fontstyle">残<%= calist.get(1) %>席</div>
									<% } else{ %>
											<div class="fontstyle">満席</div>
									<% } %>
								</th>
								<th>
									<% if(capacity >= 1){ %>
										<% if(bn2.isApply2() == true){ %>
											<input type="checkbox" name="apply2" value="apply2" checked="checked"/>
										<% } else{ %>
											<input type="checkbox" name="apply2" value="apply2">
										<% } %>
									<% } %>
								</th>
							</tr>
							<tr>
								<td nowrap><%= dlist.get(2) %></td>
								<td nowrap><%= clist.get(2) %></td>
								<th>
									<% capacity = Integer.parseInt(calist.get(2)); %>
									<% if(capacity != 0){ %>
											<div class="fontstyle">残<%= calist.get(2) %>席</div>
									<% } else{ %>
											<div class="fontstyle">満席</div>
									<% } %>
								</th>
								<th>
									<% if(capacity >= 1){ %>
										<% if(bn2.isApply3() == true){ %>
											<input type="checkbox" name="apply3" value="apply3" checked="checked"/>
										<% } else{ %>
											<input type="checkbox" name="apply3" value="apply3">
										<% } %>
									<% } %>
								</th>
							</tr>
							<tr>
								<td nowrap><%= dlist.get(3) %></td>
								<td nowrap><%= clist.get(3) %></td>
								<th>
									<% capacity = Integer.parseInt(calist.get(3)); %>
									<% if(capacity != 0){ %>
											<div class="fontstyle">残<%= calist.get(3) %>席</div>
									<% } else{ %>
											<div class="fontstyle">満席</div>
									<% } %>
								</th>
								<th>
									<% if(capacity >= 1){ %>
											<% if(bn2.isApply4() == true){ %>
											<input type="checkbox" name="apply4" value="apply4" checked="checked"/>
										<% } else{ %>
											<input type="checkbox" name="apply4" value="apply4">
										<% } %>
									<% } %>
								</th>
							</tr>
							<tr>
								<td nowrap><%= dlist.get(4) %></td>
								<td nowrap><%= clist.get(4) %></td>
								<th>
									<% capacity = Integer.parseInt(calist.get(4)); %>
									<% if(capacity != 0){ %>
											<div class="fontstyle">残<%= calist.get(4) %>席</div>
									<% } else{ %>
											<div class="fontstyle">満席</div>
									<% } %>
								</th>
								<th>
									<% if(capacity >= 1){ %>
											<% if(bn2.isApply5() == true){ %>
											<input type="checkbox" name="apply5" value="apply5" checked="checked"/>
										<% } else{ %>
											<input type="checkbox" name="apply5" value="apply5">
										<% } %>
									<% } %>
								</th>
							</tr>
						</table>
					</td>
				</tr>
				<tr>
					<td><span class="fontdeco">備考欄</span></td>
					<td>
						<textarea rows="7" cols="25"name="remarks" ><%= bn2.getRemarks() %></textarea><br>
					</td>
				</tr>
			</table>
			<input type="submit" value="確認" class="button"/>
		</form>
		<% session.setAttribute("bn2", bn2); %>
	</body>
</html>