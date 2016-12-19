<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="container">
		<div class="starter-template">

			<h3>Stare haslo:</h3>
			<form action="ChangePassword" method="get" class="form-signin">
				<table class="table">
					<tr>
						<%
							char[] passes = (char[]) request.getAttribute("passwordMask");
							for (int i = 0; i < passes.length; i++) {
						%>
						<td>
							<%
								if (passes[i] == '0') {
							%> <input type="password" class="form-control"
							placeholder="<%=i + 1%>" disabled="true"> 
							<%
						 	} else {
							 %> 
							 <input type="password" name="pass[<%=i%>]" class="form-control" maxlength="1" 
							 	placeholder="<%=i + 1%>" required> 
							<%
						 	}
						 	%>
						</td>
						<%
							}
						%>
					</tr>
				</table>
				<h3>Nowe haslo:</h3>
				<input type="password" pattern=".{8,16}" id="password"
					name="password" class="form-control" placeholder="8-16 znakow"
					required>
				<br />
				<button class="btn btn-lg btn-primary btn-block" type="submit"
					name="action" value="Zmien">Zapisz</button>
			</form>
		</div>
	</div>
</body>
</body>
</html>