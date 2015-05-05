<!DOCTYPE html>
<html ng-app="hys1">
<head>
<%@ include file="imports/head.jsp"%>
<title>List - Have You Seen?</title>
</head>
<body ng-controller="eventController">
	<%@ include file="imports/menu.jsp"%>
	<div class="intro-margin"></div>
	<div class="content-section-a">
		<div class="container">
			<div class="navigation">
				<div class="btn-group">
					<button type="button" class="btn btn-primary">Categories</button>
					<button type="button" id ="dropdown-toggle-categories" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#" ng-click="getEventData(latitude, longitude, 0, 'All')">All</a></li>
						<li ng-repeat="c in categories"><a href="#"
							ng-click="getEventData(latitude, longitude, c.id, c.name)">{{c.name}}</a></li>
					</ul>
				</div>
				<div class="btn-group filters">
					<button type="button" class="btn btn-primary">Filters</button>
					<button type="button" id ="dropdown-toggle-filters" class="btn btn-primary dropdown-toggle"
						data-toggle="dropdown" aria-expanded="false">
						<span class="caret"></span> <span class="sr-only">Toggle
							Dropdown</span>
					</button>
					<ul class="dropdown-menu" role="menu">
						<li><a href="#"><input id="showEvents.programmed"
								class="vcheck" type="checkbox" autocomplete="off"
								ng-model="showEvents.programmed"> Programmed events</a></li>
						<li><a href="#"><input id="showEvents.completed"
								class="vcheck" type="checkbox" autocomplete="off" checked
								ng-model="showEvents.completed"> Completed events</a></li>
						<li><a href="#"><input id="showEvents.cancelled"
								class="vcheck" type="checkbox" autocomplete="off" checked
								ng-model="showEvents.cancelled"> Cancelled events</a></li>
					</ul>
				</div>
				<form class="navbar-form navbar-right" style="margin-top:0px" role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search">
					</div>
					<button type="submit" class="btn btn-primary">Submit</button>
				</form>
			</div>
			<div class="page-header">
				<h3>Category: {{categoryName}}</h3>
			</div>
			<div class="content-section-a">
				<div id="products" class="row list-group">
					<div class="event-item item  col-xs-4 col-lg-4" ng-repeat="e in events"
						ng-if="isShown(e)">
						<div class="thumbnail">
							<img class="group list-group-image"
								src="http://lorempixel.com/400/250/" alt="" />
							<div class="caption">
								<h4 class="group inner list-group-item-heading">{{e.title}}</h4>
								<p class="group inner list-group-item-text">{{e.description}}</p>
								<div class="row">
									<div class="col-xs-12 col-md-6">
										<p class="lead">
											<small>{{e.date | date: "MMM d, yyyy 'at' HH:mm"}}</small>
										</p>
									</div>
									<div class="col-xs-12 col-md-6">
										<p class="lead">
											<small>{{e.status}}</small>
										</p>
									</div>
									<div class="col-xs-12 col-md-6">
										<p class="lead">
											<small>{{e.visibility}}</small>
										</p>
									</div>
									<div class="col-xs-12 col-md-6">
										<a class="btn btn-primary" href="#">See details</a>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="imports/footer.jsp"%>
</body>
</html>