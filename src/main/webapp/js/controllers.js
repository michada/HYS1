(function () {
    var app = angular.module("event", []);

    app.config(['$sceDelegateProvider', function ($sceDelegateProvider) {
        $sceDelegateProvider.resourceUrlWhitelist(['self', '/**'])
    }]);

    app.controller('eventController', ['$scope', '$http', function ($scope, $http) {
        $scope.events = [];
        $scope.showEvents = {
        		programmed: true,
        		completed: true,
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
})();