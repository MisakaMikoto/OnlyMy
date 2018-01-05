/**
 * Created by MisakaMikoto on 2017. 12. 21..
 */
app.controller('GameController', ['$scope', '$location', '$log', 'GameService', 'ProgressWebSocketService',
    function ($scope, $location, $log, GameService, ProgressWebSocketService) {
        angular.element(document).ready(function() {
            $('#myModal').on('hidden.bs.modal', function () {
                // angular.element(document.querySelector('#myModal').querySelector('.modal-title')).html(subject);
                let iframe = angular.element(document.querySelector('#myModal').querySelector('.modal-body').getElementsByTagName('iframe'));
                iframe.removeAttr('src');
            });
        });
        let videosPromise = GameService.getUploadList();
        videosPromise.then(function success(response) {
            let group = 0;
            for(let i = 0; i < response[0].data.length; i++) {
                let left = 0;
                let top = 0;

                if(i % 3 == 0) {
                    ++group;

                }

                if(group > 0) {
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
            $scope.videos = response[0].data;
            angular.element(document.querySelector('#gallery'))[0].style.height = (((response[0].data.length * 400) / 3) + 400) + 'px';

            // create web socket
            ProgressWebSocketService.connect();

        }).catch(function (error) {
            $log.error('ERROR: ', error);
            throw error;
        });

        let formData = new FormData();
        $scope.getTheFiles = function ($files) {
            angular.forEach($files, function (value, key) {
                formData.append('file' + key, value);
            });
        };

        $scope.popupOpen = function(subject, videoId) {
            // angular.element(document.querySelector('#myModal').querySelector('.modal-title')).html(subject);
            let iframe = angular.element(document.querySelector('#myModal').querySelector('.modal-body').getElementsByTagName('iframe'));
            iframe.attr('src', 'https://www.youtube.com/embed/' + videoId);
        };
    }
]);


app.controller('GamePopupController', ['$scope', '$modalInstance', 'title',
    function ($scope, $modalInstance, title) {
        $scope.title = title;
        $scope.close = function () {
            $modalInstance.dismiss('cancel');
        };
    }
]);