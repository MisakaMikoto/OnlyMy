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
            url: '/category/list',
        });

        httpArray.push({
            method: 'get',
            url: '/contents/list/' + 1,
        });

        return AjaxService.run(httpArray);
    };
}]);