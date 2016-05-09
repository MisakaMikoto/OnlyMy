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

        createParameter: function() {
            return 'client_id=' + this.getClientId() + '&refresh_token=' + this.getRefreshToken();
        },

        view: function(xmlHttpRequest) {
            var responseText = xmlHttpRequest.responseText;
            document.getElementById('againRefreshToken').value = responseText;
        }
    };

    return IAMRefresh;
})();