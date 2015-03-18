(function () {
    var app = angular.module("people", []);

    app.config(['$sceDelegateProvider', function ($sceDelegateProvider) {
        $sceDelegateProvider.resourceUrlWhitelist(['self', '/**'])
    }]);

    app.controller('peopleController', ['$scope', '$http', function ($scope, $http) {
        $scope.people = [];
        $scope.isEditing = false;
        $scope.personInForm = {};

        $scope.getPeopleData = function () {
            $http.get('/DAAExample/rest/people')
                .success(function (data) {
                    $scope.people = data;
                })
                .error(function () {
                    alert("People listing ERROR");
                });
        };

        $scope.getPeopleData();

        $scope.resetForm = function () {
            $scope.personInForm = {};
            $scope.isEditing = false;
        }

        $scope.edit = function (id) {
            for (var i = 0; i < $scope.people.length; i++) {
                elem = $scope.people[i];
                if (elem.id == id) {
                    $scope.personInForm = Object.assign({}, elem);
                    $scope.isEditing = true;
                    break;
                }
            }
        };

        $scope.delete = function (id) {
            if (confirm("Are you sure to remove person with id = " + id + " ?")) {
                $http.delete('/DAAExample/rest/people/' + id)
                    .success(function (data) {
                        $scope.resetForm();
                        $scope.getPeopleData();
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
                $http.put('/DAAExample/rest/people/' + $scope.personInForm.id,
                    {
                        id: $scope.personInForm.id,
                        name: $scope.personInForm.name,
                        surname: $scope.personInForm.surname
                    })
                    .success(function (data) {
                        $scope.getPeopleData();
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
                $http.post('/DAAExample/rest/people/',
                    {
                        name: $scope.personInForm.name,
                        surname: $scope.personInForm.surname
                    })

                    .success(function (data) {
                        $scope.resetForm();
                        $scope.getPeopleData();
                    })
                    .error(function (data) {
                        alert("Insert error");
                    });
            }
        };

        $scope.isFormIncomplete = function () {
            return $scope.personInForm.name == "" || $scope.personInForm.surname == ""
                || $scope.personInForm.name == null || $scope.personInForm.surname == null;
        };
    }]);
})();