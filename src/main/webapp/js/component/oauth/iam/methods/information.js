/**
 * Created by Misaka on 2016-05-02.
 */
var IAMInformation = (function() {
    function IAMInformation() {
    };

    var _access_token = '';

    IAMInformation.prototype = {
        setAccessToken: function(access_token) {
            _access_token = access_token;
        },

        getAccessToken: function() {
            return _access_token;
        },

        view: function(responseText) {
            document.getElementById('tokenInfo').value = responseText;
        },

        callRest: function() {
            var commonRequest = new CommonRequest();

            commonRequest.setType('POST');
            commonRequest.setUri('/iamOAuth/receive/tokenInfo');
            commonRequest.setParameter('access_token=' + this.getAccessToken());
            commonRequest.load(this.view);
        }
    };

    return IAMInformation;
})();