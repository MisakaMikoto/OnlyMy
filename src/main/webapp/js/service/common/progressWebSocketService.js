/**
 * Created by Misaka_Mikoto on 2017. 12. 26..
 */
app.service('ProgressWebSocketService', ['$http', '$q', '$log',
    function ($http, $q, $log) {
        this.connect = function() {
            let webSocket = new WebSocket("ws://127.0.0.1:8080/serverWebSocket");
            //웹 소켓이 연결되었을 때 호출되는 이벤트
            webSocket.onopen = function(message){
                console.log(message);
            };
            //웹 소켓이 닫혔을 때 호출되는 이벤트
            webSocket.onclose = function(message){
                console.log(message);
            };
            //웹 소켓이 에러가 났을 때 호출되는 이벤트
            webSocket.onerror = function(message){
                console.log(message);
            };
            //웹 소켓에서 메시지가 날라왔을 때 호출되는 이벤트
            webSocket.onmessage = function(message){
                drawProgressBar(message);
            };
            return webSocket;
        };

        function drawProgressBar(message) {
            console.log(message);

            // draw progressbar
            let percentage = Number(message.data);
            angular.element(document.querySelector('#progress'))[0].style.width = percentage + '%';
            angular.element(document.querySelector('#progress')).attr('aria-valuenow', percentage.toString());
            angular.element(document.querySelector('#progress')).html(percentage.toString() + ' %');
        };
}]);