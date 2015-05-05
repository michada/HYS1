(function() {
	var app = angular.module("hys1", [ 'ngCookies' ]);

	app.config([ '$sceDelegateProvider', function($sceDelegateProvider) {
		$sceDelegateProvider.resourceUrlWhitelist([ 'self', '/**' ])
	} ]);

	app
			.controller(
					'eventController',
					[
							'$scope',
							'$http',
							function($scope, $http) {
								$scope.events = [];
								$scope.showEvents = {
									programmed : true,
									completed : false,
									cancelled : false
								};
								$scope.pagination = [];

								$scope.getEventData = function(latitude,
										longitude, categoryId, categoryName, page) {
									categoryId = typeof categoryId !== 'undefined' ? categoryId
											: 0;
									$scope.categoryName = typeof categoryName !== 'undefined' ? categoryName
											: 'All';
									page = typeof page !== 'undefined' ? page
											: 1;

									var urlEvents = "";
									if (!latitude || !longitude) {
										urlEvents = 'rest/event/' + categoryId + '?page=' + page;
									} else {
										urlEvents = 'rest/event/' + categoryId
												+ '?latitude=' + latitude
												+ '&longitude=' + longitude
												+ '&page=' + page;
										if($scope.showEvents.programmed) {
											+ '&filters=programmed';
										}
										if($scope.showEvents.completed) {
											+ '&filters=completed';
										}
										if($scope.showEvents.cancelled) {
											+ '&filters=cancelled';
										}
									}

									$http.get(urlEvents).success(
											function(data) {
												$scope.pagination = data[0];
												$scope.range = buildRange(new Array(),  Math.ceil($scope.pagination.numElemTotal / $scope.pagination.numElemPag));
												$scope.events = data[1];
											}).error(function() {
										alert("Event listing ERROR");
									});
								};

								if (navigator.geolocation) {
									var location_timeout = setTimeout(
											geolocFail, 10000);

									navigator.geolocation
											.getCurrentPosition(
													function(position) {
														clearTimeout(location_timeout);

														$scope.latitude = position.coords.latitude;
														$scope.longitude = position.coords.longitude;

														$scope
																.getEventData(
																		$scope.latitude,
																		$scope.longitude);
													},
													function(error) {
														clearTimeout(location_timeout);
														geolocFail();
													});
								} else {
									geolocFail();
								}

								function geolocFail(categoryId, categoryName) {
									$scope.getEventData('', '');
								}

								$scope.isShown = function(e) {
									return (e.status == 'COMPLETED' && $scope.showEvents.completed)
											|| (e.status == 'PROGRAMMED' && $scope.showEvents.programmed)
											|| (e.status == 'CANCELLED' && $scope.showEvents.cancelled);
								};

								$scope.getCategoryData = function() {
									$http.get('rest/category').success(
											function(data) {
												$scope.categories = data;
											}).error(function() {
										alert("Category listing ERROR");
									});
								};

								$scope.search = function(latitude, longitude) {
									text = $('#search').val();

									var urlEvents = 'rest/event/0';
									if (latitude && longitude) {
										urlEvents += '?latitude=' + latitude
												+ '&longitude=' + longitude
												+ '&text=' + text;
									} else {
										urlEvents += '?text=' + text;
									}

									$http
											.get(urlEvents)
											.success(
													function(data) {
														$scope.pagination = data[0];
														$scope.events = data[1];
													}).error(function() {
												alert("Event listing ERROR");
											});
								};

								function buildRange(input, total) {
									total = parseInt(total);
									for (var i = 0; i < total; i++)
										input.push(i + 1);
									return input;
								}

								$scope.getCategoryData();
							} ]);

	app.controller('loginController', [
			'$scope',
			function($scope) {
				$scope.getUrlVars = function getUrlVars() {
					var vars = [], hash;
					var hashes = window.location.href.slice(
							window.location.href.indexOf('?') + 1).split('&');
					for (var i = 0; i < hashes.length; i++) {
						hash = hashes[i].split('=');
						vars.push(hash[0]);
						vars[hash[0]] = hash[1];
					}
					return vars;
				};

				$scope.loginFail = false;

				if ($scope.getUrlVars()["fail"] == "true") {
					$scope.loginFail = true;
				}
			} ]);

	app.controller('cookieController', [ '$scope', '$cookieStore',
			function($scope, $cookieStore) {
				$scope.existsToken = false;
				var token = $cookieStore.get('token');
				if (!token) {
					$scope.existsToken = false;
				} else {
					$scope.existsToken = true;
				}
			} ]);
})();