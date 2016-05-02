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

        createParameter: function() {
            return 'access_token=' + this.getAccessToken();
        }
    };

    return IAMInformation;
})();