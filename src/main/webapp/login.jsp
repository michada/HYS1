<!DOCTYPE html>
<html>
<head>
<%@ include file="imports/head.jsp"%>
<title>Login - Have You Seen?</title>
</head>
<body>
	<%@ include file="imports/menu.jsp"%>
	<div class="intro-margin"></div>
	<div class="content-section-a">
		<div class="container">
			<form action="showEvents.jsp" method="POST"
				ng-controller="loginController" ng-submit="login()">
				<fieldset>
					<legend>Login</legend>
					<div class="row">
						<div class="form-group col-lg-3">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-user fa-fw"></i></span> <input class="form-control"
									type="text" name="login" placeholder="Enter username"
									ng-model="credentials.login">
							</div>
						</div>
					</div>
					<div class="row">
						<div class="form-group col-lg-3">
							<div class="input-group">
								<span class="input-group-addon"><i
									class="fa fa-key fa-fw"></i></span> <input class="form-control"
									type="password" name="password" placeholder="Enter password" ng-model="credentials.password">
							</div>
						</div>
					</div>
					<button type="submit" class="btn btn-primary">Login</button>
				</fieldset>
			</form>
		</div>
	</div>
	<%@ include file="imports/footer.jsp"%>
</body>
</html>