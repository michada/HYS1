(function () {
    var app = angular.module("event", []);

    app.config(['$sceDelegateProvider', function ($sceDelegateProvider) {
        $sceDelegateProvider.resourceUrlWhitelist(['self', '/**'])
    }]);

    app.controller('eventController', ['$scope', '$http', function ($scope, $http) {
        $scope.events = [];
        $scope.showEvents = {
        		programmed: true,
        		completed: false,
        		cancelled: true
        };

        $scope.getEventData = function () {
            $http.get('rest/event')
                .success(function (data) {
                    $scope.events = data;
                })
                .error(function () {
                    alert("Event listing ERROR");
                });
        };

        $scope.getEventData();
        
        $scope.isShown = function(e){
        	return (e.status == 'COMPLETED' && $scope.showEvents.completed) || 
			(e.status == 'PROGRAMMED' && $scope.showEvents.programmed) || 
			(e.status == 'CANCELLED' && $scope.showEvents.cancelled);
        };
    }]);
    
    
    app.controller('loginController', function ($scope, $location, $cookieStore, authorization, api) {
    	  $scope.title = 'Likeastore. Analytics';
    	  $scope.login = "";
    	  $scope.password = "";
    	  $scope.login = function () {
    	      var credentials = {
    	          login: $scope.login,
    	          password: $scope.password
    	      };
    	      var success = function (data) {
    	          var token = data.token;
    	          api.init(token);
    	          $cookieStore.put('token', token);
    	          $location.path('/');
    	          alert("login correcto");
    	      };
    	      var error = function () {
    	    	  alert("login incorrecto");
    	          // TODO: apply user notification here..
    	      };
    	      authorization.login(credentials).success(success).error(error);
    	  };
    	});
    
    app.factory('authorization', function ($http, config) {
    	  var url = config.analytics.url;

    	  return {
    	      login: function (credentials) {
    	          return $http.post(url + '/login', credentials);
    	      }
    	  };
    	});
    
    
    
})();