<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>LOGIN PAGE</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<body>
</body>
<div class="container">

	<div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">Spring MVC Application</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/user/registration"><span
							class="glyphicon glyphicon-log-in"></span>Sign UP</a></li>
				</ul>
			</div>
		</nav>
	</div>

	<br /> <br />

	<div>
		<form:form method="post" modelAttribute="loginDto">
			<fieldset class="form-group">
				<form:label path="email">Email:</form:label>
				<form:input path="email" type="text" class="form-control"
					required="required" />
				<form:errors path="email" cssClass="text-warning" />
			</fieldset>

			<fieldset class="form-group">
				<form:label path="password">Password:</form:label>
				<form:input path="password" type="password" class="form-control"
					required="required" />
				<form:errors path="password" cssClass="text-warning" />
			</fieldset>

			<br />
			<br />
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-10">
					<button type="submit" class="btn btn-default">Submit</button>
				</div>
			</div>
		</form:form>
	</div>

</div>
</html>