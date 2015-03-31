(function () {
    var app = angular.module("event", []);

    app.config(['$sceDelegateProvider', function ($sceDelegateProvider) {
        $sceDelegateProvider.resourceUrlWhitelist(['self', '/**'])
    }]);

    app.controller('eventController', ['$scope', '$http', function ($scope, $http) {
        $scope.event = [];
        $scope.isEditing = false;
        $scope.eventInForm = {};

        $scope.getEventData = function () {
            $http.get('/HYS1/rest/event')
                .success(function (data) {
                    $scope.event = data;
                })
                .error(function () {
                    alert("Event listing ERROR");
                });
        };

        $scope.getEventData();

        $scope.resetForm = function () {
            $scope.eventInForm = {};
            $scope.isEditing = false;
        }

        $scope.edit = function (id) {
            for (var i = 0; i < $scope.event.length; i++) {
                elem = $scope.event[i];
                if (elem.id == id) {
                    $scope.eventInForm = Object.assign({}, elem);
                    $scope.isEditing = true;
                    break;
                }
            }
        };

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

        $scope.isFormIncomplete = function () {
            return $scope.eventInForm.name == "" || $scope.eventInForm.surname == ""
                || $scope.eventInForm.name == null || $scope.eventInForm.surname == null;
        };
    }]);
})();