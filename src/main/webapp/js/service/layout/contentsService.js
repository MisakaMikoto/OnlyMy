/**
 * Created by misaka_mikoto on 2017. 12. 13..
 */
app.service('ContentsService', ['$log', 'AjaxService',  function ($log, AjaxService) {
    this.loadList = function(codeId) {
        return AjaxService.callGet('/contents/list/' + codeId).then(function (data) {
            return data;
        });
    };
}]);