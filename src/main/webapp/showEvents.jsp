<!DOCTYPE html>
<html ng-app="event">
<head>
<%@ include file="imports/head.jsp"%>
<title>List - Have You Seen?</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.3/angular.min.js"></script>
<script src="js/controllers.js"></script>
</head>
<body ng-controller="eventController">
	<%@ include file="imports/menu.jsp"%>
	<div class="intro-margin"></div>
	<div class="content-section-a">
		<div class="container">
			<div class="navigation">
				<ul class="nav menu nav-pills">
					<li><button type="button" class="btn btn-primary">Filtrar</button></li>
				</ul>
			</div>
			<table class="table table-striped">
				<tr ng-repeat="e in events">
					<td class="vcenter">
						<div class="pull-left">
							<h2>{{e.title}}</h2>
							<h3><small>{{e.description}}</small></h3>
							<p><b>Date:</b> {{e.date | date}}</p>
							<p><b>State:</b> {{e.status}}</p>
						</div>
					</td>
					<td class="vcenter">
						<div class="pull-right">
							<img class="img-rounded img-responsive img-event" src="http://lorempixel.com/150/150/" ng-if="e.status != 'CANCELED'" />
							<img class="img-rounded img-responsive img-event img-canceled" src="img/cancelled-event.png" ng-if="e.status == 'CANCELED'" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="imports/footer.jsp"%>
</body>
</html>