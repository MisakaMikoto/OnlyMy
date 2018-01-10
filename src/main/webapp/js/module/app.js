var app = angular.module('onlyMy', ['ui.router'] )
    .config(function($stateProvider, $qProvider) {
        // $qProvider.errorOnUnhandledRejections(false);

        $stateProvider
            .state('Index', {
                url: '',
                templateUrl: '/template/category/read/picture.html'
            })

            .state('Picture', {
                url: '/picture',
                templateUrl: '/template/category/read/picture.html'
            })

            .state('WritePicture', {
                url: '/picture/write',
                templateUrl: '/template/category/write/picture.html'
            })

            .state('Game', {
                url: '/game',
                templateUrl: '/template/category/read/game.html'
            })

            .state('WriteGame', {
                url: '/game/write',
                templateUrl: '/template/category/write/game.html'
            })

            .state('Animation', {
                url: '/animation',
                templateUrl: '/template/category/read/animation.html'
            })

});
