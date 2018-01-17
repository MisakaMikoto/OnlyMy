/**
 * Created by MisakaMikoto on 2018. 1. 11..
 */
app.controller('PictureController', ['$scope', '$location', '$log', 'PictureService',
    function ($scope, $location, $log, PictureService) {
        $scope.$on('$viewContentLoaded', function() {
            alert(1);
        });

        angular.element(document).ready(function() {
            let gallery = angular.element(document.querySelector('#gallery'));
            if(gallery.length > 0) {
                let getUploadListPromise = PictureService.getUploadList();
                getUploadListPromise.then(function success(response) {
                    let group = 0;
                    for (let i = 0; i < response[0].data.length; i++) {
                        let left = 0;
                        let top = 0;

                        if (i % 3 == 0) {
                            ++group;

                        }

                        if (group > 0) {
                            left = ((i % 3) * 30);
                            top = ((group - 1) * 400);
                        }

                        response[0].data[i].group = group;
                        response[0].data[i].style = {
                            'position': 'absolute',
                            'left': left + '%',
                            'top': top + 'px',
                        };
                    }
                    $scope.pictures = response[0].data;
                    angular.element(document.querySelector('#gallery'))[0].style.height = (((response[0].data.length * 400) / 3) + 400) + 'px';

                }).catch(function (error) {
                    $log.error('ERROR: ', error);
                    throw error;
                });

            } else {
                $('#file').on("change", function(){
                    setImageInformation($(this));
                    setImagePreview($(this))
                });
            }

            let myModal = $('#myModal');
            if(myModal.length > 0) {
                myModal.on('hidden.bs.modal', function () {
                    // angular.element(document.querySelector('#myModal').querySelector('.modal-title')).html(subject);
                    let iframe = angular.element(document.querySelector('#myModal').querySelector('.modal-body').getElementsByTagName('iframe'));
                    iframe.removeAttr('src');
                });
            }
        });

        $scope.upload = function() {
            let codeId = angular.element(document.querySelector('#Picture').getElementsByTagName('span')).attr('id');

            let formData = new FormData(angular.element(document.querySelector('#uploadForm'))[0]);
            formData.append('codeId', codeId);

            PictureService.upload(formData);
        };

        $scope.popupOpen = function(subject, viewUrl) {
            // angular.element(document.querySelector('#myModal').querySelector('.modal-title')).html(subject);
            let img = angular.element(document.querySelector('#myModal').querySelector('.modal-body').getElementsByTagName('img'));
            img.attr('src', viewUrl);
        };

        function setImageInformation(file) {
            angular.element(document.querySelector('#uploadInformation').getElementsByTagName('span')).html("");
            let information = angular.element(document.querySelector('#uploadInformation').getElementsByTagName('span')).html();

            for(let i = 0; i < file[0].files.length; i++) {
                let nameInformation = '파일 이름 : ' + file[0].files[i].name;
                information += nameInformation + '<br>';

                let fileInformation = '';
                let fileSize = file[0].files[i].size;
                let fileSizeMb = fileSize / (1000 * 1000).toFixed(2);
                let fileSizeGb = (fileSizeMb / 1000).toFixed(2);
                fileSizeMb < 1000 ? fileInformation += '파일 크기 : ' + fileSizeMb + ' (MB)':
                                    fileInformation += '파일 크기 : ' + fileSizeGb + ' (GB)';
                information += fileInformation + '</br></br>';

            }
            angular.element(document.querySelector('#uploadInformation').getElementsByTagName('span')).html(information);
        };

        function setImagePreview(file) {
            // var fileURL = URL.createObjectURL(file[0].files[0]);
            // angular.element(document.querySelector('#video'))[0].src = fileURL;
        };
    }
]);