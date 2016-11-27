<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="com.bai.ps.model.Message" %>
<%@ page import="com.bai.ps.model.User" %>
<%@ page import="java.util.List" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
	integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp"
	crossorigin="anonymous">

<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
<title>BAI</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	<br />
	<br />
	<br />
	<br />
	<br />
	<br />

	<table class="table">
		<thead>
			<tr>
				<th>Wiadomo&#347;&#263;</th>
				<th>Akcje</th>
				<th>Uprawnienia (Nadaj)</th>
				<th>Uprawnienia (Usu&#324;)</th>
			</tr>
		</thead>
		<%
			List<Message> messageList = (List<Message>) request.getAttribute("message");
			for (Message message : messageList) {
		%>
		<tr>
		<td><%=message.getText()%></td>
		<td>
			<form action="userMessage" method="get" class="form-signin" style="display: inline-block;">
				<%
					session.setAttribute(message.toString(),message);
				%>
				<button class="btn btn-lg btn-warning btn-sm" type="submit" name="editMessage" value="Edytuj">Edytuj</button>
			</form>
			<form action="userMessage" method="get" class="form-signin" style="display: inline-block;">
				<%
					session.setAttribute(message.toString(),message);
				%>
				<input type="hidden" name="messageToRemove" value="<%=message%>" />
				<button class="btn btn-lg btn-danger btn-sm" type="submit" name="removeMessage" value="Usun">Usu&#324;</button>
			</form>	
		</td>
		<td>
			<form action="userMessage" method="get" class="form-signin" style="display: inline-block;">
				<select class="selectpicker" name="comboboxPermission">
					<%
						List<User> usersList= (List<User>) request.getAttribute("users");
						for (User user : usersList) {
					%>
						<option value="<%=user%>" ><%=user.getName()%></option>
					<%
							session.setAttribute(user.toString(),user);
						}
					%>
					</select>
				<button class="btn btn-lg btn-warning btn-sm" type="submit" name="addPermission" value="Uprawnienia">Nadaj</button>
			</form>
		</td>
		<td>
				<form action="userMessage" method="get" class="form-signin" style="display: inline-block;">
				<select class="selectpicker">
					<%
						List<User> usersPermissionList= (List<User>) request.getAttribute("users");
						for (User user : usersPermissionList) {
					%>
						<option value="<%=user%>" ><%=user.getName()%></option>
						
					<%
						}
					%>
					</select>
				<button class="btn btn-lg btn-danger btn-sm" type="submit" name="addPermission" value="Uprawnienia">Usu&#324;</button>
			</form>
		</td>
		<%
			}
		%>
		</tr>
	</table>
	<form action="userMessage" method="get" class="form-signin">
		<textarea  type="text" id="newMessage" name="newMessage" class="form-control" placeholder="Wprowad&#378; now&#261; wiadomo&#347;&#263;" style="width: 30%"></textarea>
		<button class="btn btn-lg btn-primary btn-sm" type="submit" name="addMessage" value="Dodaj">Dodaj</button>
	</form>
</body>
</body>
</html>