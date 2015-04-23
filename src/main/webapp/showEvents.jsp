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
				<div class="nav nav-pills text-center">
					<label class="btn btn-primary btn-responsive-group"> <input
						id="showEvents.programmed" class="vcheck" type="checkbox"
						autocomplete="off" ng-model="showEvents.programmed">
						Programmed events
					</label> <label class="btn btn-primary btn-responsive-group"> <input
						id="showEvents.completed" class="vcheck" type="checkbox"
						autocomplete="off" checked ng-model="showEvents.completed">
						Completed events
					</label> <label class="btn btn-primary btn-responsive-group"> <input
						id="showEvents.cancelled" class="vcheck" type="checkbox"
						autocomplete="off" checked ng-model="showEvents.cancelled">
						Cancelled events
					</label>
				</div>
			</div>
			<div class="content-section-a">
				<div class="container">
					<div id="products" class="row list-group">
						<div class="item  col-xs-4 col-lg-4" ng-repeat="e in events"
							ng-show="isShown(e)">
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
	</div>

	<%@ include file="imports/footer.jsp"%>
</body>
</html>