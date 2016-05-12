/**
 * Created by Misaka on 2016-05-09.
 */
var ApigatewayComponent = (function() {
    function ApigatewayComponent() {
    };

    ApigatewayComponent.prototype = {
        callRest: function (callback) {
            var commonRequest = new CommonRequest();

            commonRequest.setType(this.prototype.getType());
            commonRequest.setUri(this.prototype.getUri());
            commonRequest.setParameter(this.prototype.getParameter());
            commonRequest.load(callback);
        }
    };
    return ApigatewayComponent;
})();