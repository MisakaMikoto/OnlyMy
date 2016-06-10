/**
 * Created by Misaka on 2016-06-09.
 */
class CommonWebSocket {
    constructor() {
        this._endPoint = '';
    }

    set endPoint(endPoint) {
        this._endPoint = endPoint;
    }

    get endPoint() {
        return this._endPoint;
    }

    connect() {
        let socket = new SockJS(this.endPoint);
        let stompClient = Stomp.over(socket);

        stompClient.connect({}, function() {
            stompClient.subscribe('/push', function(message){
                let receiveMessage = message.body.toString();
                let renderer = new BlogUploadProgressRenderer();

                if(receiveMessage == 'iCompleted') {
                    renderer.renderInitCompleted();

                } else if(receiveMessage == 'uCompleted') {
                    renderer.renderUploadCompleted();

                } else {
                    renderer.renderProgress(receiveMessage);
                }
            });
        });
    }
}