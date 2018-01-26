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
                success: function() {
                    let uploadFileNames = formData.get('files');
                    alert(uploadFileNames + ' 파일의 업로드가 완료 되었습니다.');

                    initProgressBar();
                    if(window.location.href.indexOf('/picture/write') > -1) {
                        initPictureInformation();
                        initPicturePreview();
                        initUploadForm();

                        setEnableUpload();
                    }
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

        function initProgressBar() {
            angular.element(document.querySelector('#progress'))[0].style.width = '0%';
            angular.element(document.querySelector('#progress')).attr('aria-valuenow', '0');
            angular.element(document.querySelector('#progress')).html('');

        };

        function initPictureInformation() {
            angular.element(document.querySelector('#uploadFileName')).html('');
            angular.element(document.querySelector('#uploadFileSize')).html('');
        };

        function initPicturePreview() {
            // angular.element(document.querySelector('#video')).removeAttr('src');
        };

        function initUploadForm() {
            let inputs = angular.element(document.querySelector('#uploadForm').querySelectorAll('input.form-control'));
            angular.forEach(inputs, function(input){
                input.value = '';
            });
        };

        function setEnableUpload() {
            // enable add files and upload button
            angular.element(document.querySelector('#uploadButton')).removeAttr('disabled');

            angular.element(document.querySelector('#file')).parent().removeAttr('disabled');
            angular.element(document.querySelector('#file')).parent()[0].style.cursor = 'pointer';

            angular.element(document.querySelector('#file')).removeAttr('disabled');
            angular.element(document.querySelector('#file'))[0].style.cursor = 'pointer';
        };
}]);