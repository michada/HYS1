<!DOCTYPE html>
<html ng-app="event">
<head>
<%@ include file="imports/head.jsp"%>
<title>List - Have You Seen?</title>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.4.0-beta.3/angular.min.js"></script>
<script src="js/controllers.js"></script>
</head>
<body ng-controller="eventController">
	<%@ include file="imports/menu.jsp"%>
	<div class="intro-margin"></div>
	<div class="content-section-a">
		<div class="container">
			<table class="table table-hover text-center" id="event-list">
				<tr class="first-row">
					<td>Id</td>
					<td>Date</td>
					<td>Title</td>
				</tr>
				<tr ng-repeat="e in events">
					<td>{{e.id}}</th>
					<td>{{e.date}}</th>
					<td>{{e.title}}</th>
				</tr>
			</table>
		</div>
	</div>
	<%@ include file="imports/footer.jsp" %>
</body>
</html>