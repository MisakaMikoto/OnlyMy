/**
 * Created by misaka_mikoto on 2017. 12. 13..
 */

app.service('AjaxService', ['$http', '$q', '$log',
    function ($http, $q, $log) {

        this.run = function(httpInfoArray) {
            var httpArray = [];

            for(let i = 0; i < httpInfoArray.length; i++) {
                if('get' == httpInfoArray[i].method.toLowerCase()) {
                    httpArray.push($http.get(httpInfoArray[i].url));

                } else if('post' == httpInfoArray[i].method.toLowerCase()) {
                    httpArray.push($http.post(httpInfoArray[i].url));

                } else if('put' == httpInfoArray[i].method.toLowerCase()) {
                    httpArray.push($http.put(httpInfoArray[i].url));

                } else if('delete' == httpInfoArray[i].method.toLowerCase()) {
                    httpArray.push($http.delete(httpInfoArray[i].url));

                } else {
                    ;
                    // another conditions..
                }
            }

            return $q.all(httpArray).then(function success(responses){
               return responses;

            }).catch(function (error) {
                $log.error('ERROR: ', error);
                throw error;
            });
        };
    }
]);