<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="bn6" scope="session" class="beans.InputBean"/>
    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>削除確認</title>
		<link rel="stylesheet" href="../CSS/search.css">
	</head>
	<body>
		<h1 class="title">削除確認</h1>
		
		<p class="explanation">削除する講座情報を確認してください</p>
		
		<form action="../servlet/admin.DeleteAction" method="post">
			<table class="cdtable">
				<tr>
					<td>講座番号　</td>
					<td><span class="position"><jsp:getProperty property="anumber" name="bn6"/></span></td>
				</tr>
				<tr>
					<td>講座名　　</td>
					<td><div class="fontdeco"><jsp:getProperty property="aname" name="bn6"/></div></td>
				</tr>
				<tr>
					<td>講座開催日</td>
					<td>
						　　　<span class="fontcolor"><jsp:getProperty property="ayear" name="bn6"/></span>年
						<span class="fontcolor"><jsp:getProperty property="amonth" name="bn6"/></span>月
						<span class="fontcolor"><jsp:getProperty property="aday" name="bn6"/></span>日
					</td>
				</tr>
				<tr>
					<td>開始時刻　</td>
					<td>
						　　 <span class="position7"><jsp:getProperty property="sthour" name="bn6"/></span>時
						<span class="position8"><jsp:getProperty property="stminute" name="bn6"/></span>分
					</td>
				</tr>
				<tr>
					<td>終了時刻　</td>
					<td>
						　　 <span class="position3"><jsp:getProperty property="endhour" name="bn6"/></span>時
						<span class="position4"><jsp:getProperty property="endminute" name="bn6"/></span>分
					</td>
				</tr>
				<tr>
					<td>定員　　　</td>
					<td><span class="position2"><jsp:getProperty property="capacity" name="bn6"/></span>人</td>
				</tr>
			</table>
			<input type="submit" name="back" value="戻る" class="button5"/>
			<input type="submit" name="remove" value="削除" class="button5"/>
		</form>
	</body>
</html>