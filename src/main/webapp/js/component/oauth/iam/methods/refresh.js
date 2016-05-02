/**
 * Created by Misaka on 2016-05-02.
 */
var IAMRefresh = (function() {
    function IAMRefresh() {
    };

    var _client_id = '';
    var _refresh_token = '';

    IAMRefresh.prototype = {
        setClientId: function(client_id) {
            _client_id = client_id;
        },

        getClientId: function() {
            return _client_id;
        },

        setRefreshToken: function(refresh_token) {
            _refresh_token = refresh_token;
        },

        getRefreshToken: function() {
            return _refresh_token;
        },

        view: function(responseText) {
            document.getElementById('againRefreshToken').value = responseText;
        },

        callRest: function() {
            var commonRequest = new CommonRequest();

            commonRequest.setType('POST');
            commonRequest.setUri('/iamOAuth/receive/refreshToken');
            commonRequest.setParameter('client_id=' + this.getClientId() + '&refresh_token=' + this.getRefreshToken());
            commonRequest.load(this.view);
        }
    };

    return IAMRefresh;
})();