/**
 * Created by MisakaMikoto on 2018. 1. 11..
 */
app.service('PictureService', ['$stateParams', 'AjaxService',
    function ($stateParams, AjaxService) {
        this.upload = function(formData) {
            alert(formData.get('files') + ' 파일의 업로드를 시작합니다.');
            this.setDisableUpload();

            $.ajax({
                url: '/contents/drive/upload/insert',
                data: formData,
                dataType: 'text',
                processData: false,
                contentType: false,
                type: 'post',
                cache: false,
                success: function() {
                    alert(formData.get('files') + ' 파일의 업로드가 완료 되었습니다.');

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

                    initPictureInformation();
                    initPicturePreview();
                    initUploadForm();

                    setEnableUpload();
                }
            });
        };

        this.getUploadList = function() {
            let codeId = $stateParams.codeId;

            let httpArray = [];
            httpArray.push({
                method: 'get',
                url: '/contents/upload/list/' + codeId
            });
            return AjaxService.run(httpArray);
        };

        this.setDisableUpload = function() {
            // disable add files and upload button
            angular.element(document.querySelector('#uploadButton')).attr('disabled', 'disabled');

            angular.element(document.querySelector('#file')).parent().attr('disabled', 'disabled');
            angular.element(document.querySelector('#file')).parent()[0].style.cursor = 'not-allowed';

            angular.element(document.querySelector('#file')).attr('disabled', 'disabled');
            angular.element(document.querySelector('#file'))[0].style.cursor = 'not-allowed';
        };

        function initProgressBar() {
            angular.element(document.querySelector('#pictureProgress'))[0].style.width = '0%';
            angular.element(document.querySelector('#pictureProgress')).attr('aria-valuenow', '0');
            angular.element(document.querySelector('#pictureProgress')).html('');

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