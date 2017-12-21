/**
 * Created by Misaka on 2017-08-16.
 */

app.controller('CategoryController', ['$scope', '$location', 'AjaxService',
    function ($scope, $location, AjaxService) {

        AjaxService.callGet('/category/list').then(function(data) {
            $scope.categories = data;
        });

        $scope.loadContentsList = function(codeId) {
            $scope.$broadcast('loadList', { codeId: codeId });
        };
}]);