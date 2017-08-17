/**
 * Created by Misaka on 2017-08-16.
 */

app.controller('loadNewestContentsController', function($scope, $http) {
    $http({
        method : "GET",
        url : '/contents/newest'

    }).then(function success(response) {
        $scope.contents = response.data;

    }, function fail() {
        console.log("fail load title");
    });
});