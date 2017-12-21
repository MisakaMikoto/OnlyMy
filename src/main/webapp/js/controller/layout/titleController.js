/**
 * Created by Misaka on 2017-08-16.
 */

app.controller('TitleController', ['$scope', '$location', 'AjaxService', function ($scope, $location, AjaxService) {
    AjaxService.callGet('/title').then(function(data) {
        $scope.name = data.name;
    });
}]);