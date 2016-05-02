/**
 * Created by Misaka on 2016-04-27.
 */
var IAMResource = (function() {
    function IAMResource() {
    };

    var _client_id = '';
    var _userName = '';
    var _userPassword = '';
    var _scope = '';

    IAMResource.prototype = {
        setClientId: function(client_id) {
            _client_id = client_id;
        },

        getClientId: function() {
            return _client_id;
        },

        setUserName: function(userName) {
            _userName = userName;
        },

        getUserName: function() {
            return _userName;
        },

        setUserPassword: function(userPassword) {
            _userPassword = userPassword
        },

        getUserPassword: function() {
            return _userPassword;
        },

        setScope: function(scope) {
            _scope = scope;
        },

        getScope: function() {
            return _scope;
        },

        view: function(resourceToken) {
            var resourceTokenJSON = JSON.parse(resourceToken);
            document.getElementById('accessToken').value = resourceTokenJSON.access_token;
            document.getElementById('refreshToken').value = resourceTokenJSON.refresh_token;
        },

        callRest: function() {
            var commonRequest = new CommonRequest();

            commonRequest.setType('POST');
            commonRequest.setUri('/iamOAuth/receive/resource/token');
            commonRequest.setParameter('client_id=' + this.getClientId() + '&username=' + this.getUserName() +
                                            '&password=' + this.getUserPassword() + '&scope=' + this.getScope());
            commonRequest.load(this.view);
        }
    };

    return IAMResource;
})();