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

        $scope.delete = function (id) {
            if (confirm("Are you sure to remove event with id = " + id + " ?")) {
                $http.delete('/HYS1/rest/event/' + id)
                    .success(function (data) {
                        $scope.resetForm();
                        $scope.getEventData();
                    })
                    .error(function (data) {
                        alert("Delete error");
                    });
            }
        };

        $scope.update = function () {
            if ($scope.isFormIncomplete()) {
                alert("Form incomplete");
            }
            else {
                $http.put('/HYS1/rest/event/' + $scope.eventInForm.id,
                    {
                        id: $scope.eventInForm.id,
                        date: $scope.eventInForm.name,
                        description: $scope.eventInForm.surname
                    })
                    .success(function (data) {
                    	$scope.resetForm();
                        $scope.getEventData();
                    })
                    .error(function (data) {
                        alert("Update error");
                    });
            }
        };

        $scope.insert = function () {
            if ($scope.isFormIncomplete()) {
                alert("Form incomplete");
            }
            else {
                $http.post('/HYS1/rest/event/',
                    {
                        date: $scope.eventInForm.name,
                        description: $scope.eventInForm.surname
                    })

                    .success(function (data) {
                        $scope.resetForm();
                        $scope.getEventData();
                    })
                    .error(function (data) {
                        alert("Insert error");
                    });
            }
        };
    }]);
})();