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
							function($scope, $http, $routeParams) {
								$scope.events = [];
								$scope.showEvents = {
									programmed : true,
									completed : false,
									cancelled : true
								};

								$scope.getEventData = function() {
									$http.get('rest/event').success(
											function(data) {
												$scope.events = data;
											}).error(function() {
										alert("Event listing ERROR");
									});
								};

								$scope.getEventData();

								$scope.isShown = function(e) {
									return (e.status == 'COMPLETED' && $scope.showEvents.completed)
											|| (e.status == 'PROGRAMMED' && $scope.showEvents.programmed)
											|| (e.status == 'CANCELLED' && $scope.showEvents.cancelled);
								};

								$scope.getUrlVars = function getUrlVars() {
									var vars = [], hash;
									var hashes = window.location.href
											.slice(
													window.location.href
															.indexOf('?') + 1)
											.split('&');
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