/**
 * Created by Misaka on 2017-08-16.
 */

app.controller('ContentsController', ['$scope', '$location', 'ContentsService', function ($scope, $location, ContentsService) {
    $scope.$on('loadList', function (event, args) {
        var vv = ContentsService.loadList(args.codeId);
        console.log(vv);
    });

    // init 1
    ContentsService.loadList(1).then(function(data) {
        $scope.contents = data;
    });
}]);