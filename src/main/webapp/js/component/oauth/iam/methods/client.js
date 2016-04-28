/**
 * Created by Misaka on 2016-04-27.
 */
var IAMClient = (function() {
    function IAMClient() {
    };

    var _client_id = '';
    var _scope = '';

    IAMClient.prototype = {
        setClientId: function(client_id) {
            _client_id = client_id;
        },

        getClientId: function() {
            return _client_id;
        },

        setScope: function(scope) {
            _scope = scope;
        },

        getScope: function() {
            return _scope;
        },

        view: function(clientToken) {
            var clientTokenJSON = JSON.parse(clientToken);
            document.getElementById('accessToken').value = clientTokenJSON.access_token;
            document.getElementById('refreshToken').value = clientTokenJSON.refresh_token;
        },

        callRest: function() {
            // extends
            var commonExtends = new CommonExtends();
            var oauthRequest = commonExtends.doExtends(new OAuthRequest(), CommonRequest.prototype);

            oauthRequest.prototype.setType('POST');
            oauthRequest.prototype.setUri('/iamOAuth/receive/client/token');
            oauthRequest.prototype.setParameter('client_id=' + this.getClientId() + '&scope=' + this.getScope());
            oauthRequest.load(IAMClient.prototype);
        }
    };

    return IAMClient;
})();