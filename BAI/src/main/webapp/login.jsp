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
			<form action="login" method="get" class="form-signin">
			<label for="login" class="sr-only">Login</label>
        	<input type="text" id="login" name="login" class="form-control" placeholder="Wprowad&#378; login" required autofocus>
			<label for="password" class="sr-only">Has&#322;o</label>
        	<input type="password" id="password" name="password" class="form-control" placeholder="Wprowad&#378; has&#322;o" required>
			<button class="btn btn-lg btn-primary btn-block" type="submit" name="login" value="zaloguj">Zaloguj</button>
			</form>
		</div>

	</div>

</body>
</body>
</html>