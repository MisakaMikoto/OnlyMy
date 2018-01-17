/**
 * Created by MisakaMikoto on 2018. 1. 11..
 */
app.service('PictureService', ['AjaxService',
    function (AjaxService) {
        this.upload = function(formData) {

            $.ajax({
                url: '/contents/drive/upload/insert',
                data: formData,
                dataType: 'text',
                processData: false,
                contentType: false,
                type: 'post',
                cache: false,
                async: false,
                success: function(data) {
                    console.log(data);
                },
                error: function() {
                    alert('사진 업로드에 실패하였습니다.');
                }
            });
        };

        this.getUploadList = function() {
            let codeId = angular.element(document.querySelector('#Picture').getElementsByTagName('span')).attr('id');

            let httpArray = [];
            httpArray.push({
                method: 'get',
                url: '/contents/upload/list/' + codeId
            });
            return AjaxService.run(httpArray);
        };
}]);