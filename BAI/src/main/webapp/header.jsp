<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
	<nav class="navbar navbar-inverse navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle collapsed"
					data-toggle="collapse" data-target="#navbar" aria-expanded="false"
					aria-controls="navbar">
					<span class="sr-only">Toggle navigation</span> <span
						class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="index.jsp">BAI</a>
			</div>
			<div id="navbar" class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li><a href="register.jsp">Rejestracja</a></li>
					<li><a href="login.jsp">Logowanie</a></li>
					<li><a href="userMessage">Wiadomo&#347;ci</a></li>
					<li><a href="login_ps4.jsp">Logowanie_Ps4</a></li>
				</ul>

				<ul class="nav navbar-nav navbar-right">
					<li><a href="userPanel">Witaj: <%= session.getAttribute( "user" ) %></a></li>
					<li>
					<form action="logout" method="get" class="form-signin">
						<button class="btn btn-lg btn-primary btn-block" type="submit" name="logout" value="wyloguj">Wyloguj</button>
					</form>
					</li>
				</ul>
			</div>
		</div>
	</nav>
</body>
</html>