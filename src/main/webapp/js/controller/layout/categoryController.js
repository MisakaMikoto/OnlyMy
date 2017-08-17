/**
 * Created by Misaka on 2017-08-16.
 */

app.controller('loadCategoryController', function($scope, $http) {
    $http({
        method : "GET",
        url : '/category/list'

    }).then(function success(response) {
        $scope.categories = response.data;

    }, function fail() {
        console.log("fail load title");
    });
});