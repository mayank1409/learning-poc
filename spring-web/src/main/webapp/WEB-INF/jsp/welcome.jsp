<html>

<head>
<title>REGISTRATION SUCCESS</title>
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

	<div>
		<nav class="navbar navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<a class="navbar-brand" href="#">Spring MVC Application</a>
				</div>
			</div>
		</nav>
	</div>
	<br />
	<br />

	<div class="container">

		<h1 class="alert alert-success">Welcome ${email}</h1>
		<h2>
			<a href="/todos">Click Here</a> to Manage Your Todos
		</h2>
	</div>
</body>
</html>