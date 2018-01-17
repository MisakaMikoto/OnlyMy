/**
 * Created by misaka_mikoto on 2017. 12. 13..
 */
app.service('LayoutService', ['AjaxService',  function (AjaxService) {
    this.loadMain = function() {
        let httpArray = [];

        httpArray.push({
            method: 'get',
            url: '/title',
        });

        httpArray.push({
            method: 'get',
            async: false,
            url: '/category/list',
        });
        return AjaxService.run(httpArray);
    };
}]);