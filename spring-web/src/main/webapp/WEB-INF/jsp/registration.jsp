<!DOCTYPE html>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
<title>REGISTRATION</title>
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
	<center>
		<strong><font color="red" style="align-content: center;">${message}</font></strong>
	</center>
	<div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="/">Spring MVC Application</a>
				</div>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="/user/login"><span
							class="glyphicon glyphicon-log-in"></span> Login</a></li>
				</ul>
			</div>
		</nav>
	</div>
	<div class="container">
		<br /> <br />
		<div>
			<form:form method="post" modelAttribute="userDto">
				<fieldset class="form-group">
					<form:label path="firstName">First
						Name:</form:label>
					<form:input path="firstName" type="text" class="form-control"
						required="required" />
					<form:errors path="firstName" cssClass="text-warning" />
				</fieldset>

				<fieldset class="form-group">
					<form:label path="lastName">Last
						Name:</form:label>
					<form:input path="lastName" type="text" class="form-control"
						required="required" />
					<form:errors path="lastName" cssClass="text-warning" />
				</fieldset>

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

				<fieldset class="form-group">
					<form:label path="using2FA">Use
								Two step verification:</form:label>
					<form:checkbox path="using2FA" value="using2FA" />
					<form:errors path="using2FA" cssClass="text-warning" />
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
</body>
</html>