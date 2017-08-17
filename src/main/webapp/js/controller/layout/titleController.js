/**
 * Created by Misaka on 2017-08-16.
 */

app.controller('loadTitleController', function($scope, $http) {
    $http({
        method : "GET",
        url : '/title'

    }).then(function success(response) {
        $scope.name = response.data.name;

    }, function fail() {
        console.log("fail load title");
    });
});