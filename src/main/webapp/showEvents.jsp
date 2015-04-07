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
			<div ng-repeat="e in events" class="row event-info">
				<div class="col-md-6">
					<h2>{{e.title}}</h2>
					<h3><small>{{e.description}}</small></h3>
					<p>
						<b>Date:</b> {{e.date | date}}
					</p>
					<p>
						<b>State:</b> {{e.status}}
					<p>
				</div>
				<div class="col-md-6">
					<img width="150" class="img-rounded img-responsive" height="150" src="http://lorempixel.com/150/150/" alt="" style="z-index:0;position:absolute;" />
					<img ng-if="e.status == 'CANCELED'" width="200" height="150" src="img/cancelled-event.png" style="z-index:1;position:absolute;margin-left:-25px" >
				</div>
				<div class="col-md-8">
					<hr>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="imports/footer.jsp"%>
</body>
</html>