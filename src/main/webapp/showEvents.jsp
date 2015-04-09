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
				<div class="nav menu nav-pills btn-group">
					<label class="btn btn-primary">
						<input class="vcheck" type="checkbox" autocomplete="off" ng-model="showEvents.programmed"> Programmed events
					</label>
					<label class="btn btn-primary">
						<input class="vcheck" type="checkbox" autocomplete="off" checked ng-model="showEvents.completed"> Completed events
					</label>
					<label class="btn btn-primary">
						<input class="vcheck" type="checkbox" autocomplete="off" checked ng-model="showEvents.cancelled"> Cancelled events
					</label>
				</div>
			</div>
			<table class="table table-striped">
				<tr ng-repeat="e in events"
				ng-show="isShown(e)">
					<td class="vcenter">
						<div class="pull-left">
							<h2>{{e.title}}</h2>
							<h3 class="text-justify">
								<small>{{e.description}}</small>
							</h3>
							{{e.date | date: "MMM d, yyyy 'at' HH:mm"}}
							</p>
							<p>
								<b>State:</b> {{e.status}}
							</p>
						</div>
					</td>
					<td class="vcenter">
						<div class="pull-right">
							<img class="img-rounded img-responsive img-event"
								src="http://lorempixel.com/150/150/"
								ng-if="e.status != 'CANCELLED'" />
							<img
								class="img-rounded img-responsive img-event img-cancelled"
								src="img/cancelled-event.png" ng-if="e.status == 'CANCELLED'" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="imports/footer.jsp"%>
</body>
</html>