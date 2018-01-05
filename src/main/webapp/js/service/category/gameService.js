/**
 * Created by misaka_mikoto on 2017. 12. 14..
 */
app.service('GameService', ['AjaxService',
    function (AjaxService) {
        let httpArray = [];

        this.getUploadList = function() {
            httpArray.push({
                method: 'get',
                url: '/contents/youtube/upload/list'
            });
            return AjaxService.run(httpArray);
        }
}]);