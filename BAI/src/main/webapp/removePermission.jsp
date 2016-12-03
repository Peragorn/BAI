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
	<%
		Message messageLabel = (Message) request.getAttribute("message");
	%>
	<label><%=messageLabel.getText()%></label><br />
	<form action="removePermission" method="get" class="form-signin"
		style="display: inline-block;">
					<select class="selectpicker" name="comboboxPermissionRemove">
					<%
						List<User> usersPermissionList= (List<User>) request.getAttribute("usersWithPermission");
						for (User user : usersPermissionList) {
					%>
						<option value="<%=user.getUser_id()%>" ><%=user.getName()%></option>
						
					<%
						}
					%>
					</select>
					<input type="hidden" name="messageID" value="<%=messageLabel.getMessage_id()%>" />
		<button class="btn btn-lg btn-danger btn-sm" type="submit" name="removePermission" value="usunUprawnienia">Usu&#324;</button>
	</form>
</body>
</body>
</html>