(function () {
    var app = angular.module("event", []);

    app.config(['$sceDelegateProvider', function ($sceDelegateProvider) {
        $sceDelegateProvider.resourceUrlWhitelist(['self', '/**'])
    }]);

    app.controller('eventController', ['$scope', '$http', function ($scope, $http) {
        $scope.events = [];

        $scope.getEventData = function () {
            $http.get('/HYS1/rest/event')
                .success(function (data) {
                    $scope.events = data;
                })
                .error(function () {
                    alert("Event listing ERROR");
                });
        };

        $scope.getEventData();

    }]);
})();