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
	<label >Aktualnie blokuje konto po </label> <%= session.getAttribute( "userAttempt" ) %><label >  pr&#243;bach</label>
	<form action="userPanel" method="get" class="form-signin" style="width: 15%">
	<label for="n" class="sr-only">Blokuj konto po n pr&#243;bach</label>
    <input type="number" id="n" name="n" class="form-control" placeholder="Blokuj konto po n pr&#243;bach" required autofocus>
	
	<button class="btn btn-lg btn-primary btn-block" type="submit" name="lock" value="blokuj">Blokuj</button>
	</form>
	
	<table class="table">
	<thead>
		<tr>
			<th>Ostatnie nieudane logowanie</th>
			<th>Ostatnie udane logowanie</th>
			<th>Liczba nieudanych logowa&#324;</th>
		</tr>
		<tr>
		<%
			User userLogs = (User) request.getAttribute("userLogs");
		%>
			<td><%=userLogs.getLast_fail_login()%></td>
			<td><%=userLogs.getLast_login()%></td>
			<td><%=userLogs.getLoginAttemptCounterToSucces()%></td>
		</tr>
	</thead>
	</table>
</body>
</body>
</html>