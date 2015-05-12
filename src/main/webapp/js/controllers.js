(function() {
	var app = angular.module("hys1", [ 'ngCookies' ]);

	app.config([ '$sceDelegateProvider', function($sceDelegateProvider) {
		$sceDelegateProvider.resourceUrlWhitelist([ 'self', '/**' ])
	} ]);
	
    app.directive('onFinishRender', function ($timeout) {
	    return {
	        restrict: 'A',
	        link: function (scope, element, attr) {
	            if (scope.$last === true) {
	                $timeout(function () {
	                    scope.$emit('ngRepeatFinished');
	                });
	            }
	        }
	    }
    });

	app
			.controller(
					'eventController',
					[
							'$scope',
							'$http',
							function($scope, $http) {
								$scope.loading = true;
								$scope.events = [];
								$scope.showEvents = {
									programmed : true,
									completed : false,
									cancelled : false
								};
								$scope.DEFAULT_PAGE = 1;
								$scope.DEFAULT_CATEGORY_ID = 0;
								$scope.DEFAULT_CATEGORY_NAME = 'All';
								$scope.pagination = [];

								$scope.getEventData = function(categoryId,
										categoryName, page) {
									$scope.categoryName = typeof categoryName !== 'undefined' ? categoryName
											: 'All';
									page = typeof page !== 'undefined' ? page
											: 1;
									categoryId = typeof categoryId !== 'undefined' ? categoryId
											: 0;
									$scope.idCategorySelected = categoryId;
									$scope.categorySelected = categoryName;


									var urlEvents = 'rest/event/' + categoryId
											+ '?1=1';
									if ($scope.latitude && $scope.longitude) {

										urlEvents += '&latitude='
												+ $scope.latitude
												+ '&longitude='
												+ $scope.longitude;
									}

									if ($scope.showEvents.programmed) {
										urlEvents += '&filters=programmed';
									}

									if ($scope.showEvents.completed) {
										urlEvents += '&filters=completed';
									}

									if ($scope.showEvents.cancelled) {
										urlEvents += '&filters=cancelled';

									}
									urlEvents += '&page=' + page;
									if ($scope.text) {
										urlEvents += '&text=' + $scope.text;
									}

									$scope.loading = true;
									$http.get(urlEvents).success(
											function(data) {
												$scope.pagination = data.pageBean;
												$scope.range = buildRange(new Array(),  Math.ceil($scope.pagination.numElemTotal / $scope.pagination.numElemPag));
												$scope.events = data.listEvents;
												$scope.loading = false;
											});
								};


								$scope.search = function() {
									$scope.text = $('#search').val();									$scope.getEventData(
											$scope.idCategorySelected,
											$scope.categorySelected,
											$scope.DEFAULT_PAGE);
								}

								$scope.paging = function(pag) {
									$scope.getEventData(
											$scope.idCategorySelected,
											$scope.categorySelected, pag);
								}

								function geolocFail(categoryId, categoryName) {
									$scope.getEventData(
											$scope.DEFAULT_CATEGORY_ID,
											$scope.DEFAULT_CATEGORY_NAME,
											$scope.DEFAULT_PAGE);
								}

								if (navigator.geolocation) {
									navigator.geolocation
											.getCurrentPosition(
													function(position) {
														$scope.latitude = position.coords.latitude;
														$scope.longitude = position.coords.longitude;

														$scope
																.getEventData(
																		$scope.DEFAULT_CATEGORY_ID,
																		$scope.DEFAULT_CATEGORY_NAME,
																		$scope.DEFAULT_PAGE);
													}, function(error) {
														geolocFail();
													});
								} else {
									geolocFail();
								}

								$scope.getCategoryData = function() {
									$http.get('rest/category').success(
											function(data) {
												$scope.categories = data;
											}).error(function() {
										alert("Category listing ERROR");
									});
								};

								function buildRange(input, total) {
									total = parseInt(total);
									for (var i = 0; i < total; i++)
										input.push(i + 1);
									return input;
								}

								$scope.getCategoryData();
								
								$scope.$on('ngRepeatFinished', function(ngRepeatFinishedEvent) {
									setSameHeight();
								});
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

	app.controller('cookieController', [ '$scope', '$cookies',
			function($scope, $cookies) {
				$scope.existsToken = false;
				var token = $cookies.token;
				if (!token) {
					$scope.existsToken = false;
				} else {
					$scope.existsToken = true;
				}
			} ]);
})();