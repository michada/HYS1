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
						<li><a href="#"
							ng-click="getEventData(DEFAULT_CATEGORY_ID, DEFAULT_CATEGORY_NAME, DEFAULT_PAGE)">All</a></li>
						<li ng-repeat="c in categories">
						   <a href="#"
							ng-click="getEventData(c.id, c.name, DEFAULT_PAGE)">{{c.name}}</a></li>
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
								ng-model="showEvents.programmed"
								ng-click="getEventData(idCategorySelected, categorySelected, DEFAULT_PAGE)"> Programmed events</a></li>
						<li><a href="#"><input id="showEvents.completed"
								class="vcheck" type="checkbox" autocomplete="off" checked
								ng-model="showEvents.completed"
								ng-click="getEventData(idCategorySelected, categorySelected, DEFAULT_PAGE)"> Completed events</a></li>
						<li><a href="#"><input id="showEvents.cancelled"
								class="vcheck" type="checkbox" autocomplete="off" checked
								ng-model="showEvents.cancelled"
								ng-click="getEventData(idCategorySelected, categorySelected, DEFAULT_PAGE)"> Cancelled events</a></li>
					</ul>
				</div>
				<form class="navbar-form navbar-right" style="margin-top: 0px"
					role="search">
					<div class="form-group">
						<input type="text" class="form-control" placeholder="Search"
							id="search">
					</div>

					<button type="submit" class="btn btn-primary" id="submit-search"
						ng-click="search()">Submit</button>
				</form>
			</div>
			<div class="page-header">
				<h3>Category: {{categoryName}}</h3>
				
				<div ng-show="text">
					<h3>Search by: {{ text | limitTo: 30 }}{{text.length > 30 ? '...' : ''}}</h3>
				</div>
			</div>
			<div class="content-section-a">
					<div ng-show="loading">
						<div class="event-item item  col-xs-4 col-lg-4" >
							<img class="img-responsive" src="img/loading.gif" alt="">
						</div>
					</div>
					<div ng-show="!loading">
						<div id="products" class="row list-group">
								<div class="event-item item  col-xs-4 col-lg-4" ng-repeat="e in events">
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
					
							<div ng-show="events.length > 0">
								<nav>
									<ul class="pagination">
										<!-- <li ng-class="{disabled: pagination.numPag != 1}"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li> -->
										<li ng-repeat="n in range" ng-class="{active: pagination.numPag + 1 == n}" ng-click="paging(n)"><a href="#">{{n}}</a></li>
										<!-- <li ng-class="{disabled: pagination.numPag != pagination.numElemPag * pagination.numElemTotal}"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li> -->
									</ul>
								</nav>
							</div>
							
							<div ng-show="events.length == 0">
								<div class="event-item item  col-xs-4 col-lg-4" >
									<div class="thumbnail">
										<h5>&nbsp;&nbsp;&nbsp;Oh! Unfortunately, no results were found.</h5>
									</div>
								</div>
							</div>
						</div>
				</div>
				<nav>
					<ul class="pagination">
						<li ng-class="{disabled: pagination.numPag != 1}"><a href="#" aria-label="Previous"><span aria-hidden="true">&laquo;</span></a></li>
						<li ng-repeat="n in range" id="page{{n}}" ng-class="{active: pagination.numPag + 1 == n}" ng-click="getEventData(latitude, longitude, 0, 'All', n)"><a href="#">{{n}}</a></li>
						<li ng-class="{disabled: pagination.numPag != pagination.numElemPag * pagination.numElemTotal}"><a href="#" aria-label="Next"><span aria-hidden="true">&raquo;</span></a></li>
					</ul>
				</nav>
			</div>
		</div>
	</div>
	<%@ include file="imports/footer.jsp"%>
</body>
</html>