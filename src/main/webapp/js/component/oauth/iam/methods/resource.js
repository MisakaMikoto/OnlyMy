/**
 * Created by Misaka on 2016-04-27.
 */
var Resource = (function() {
    function Resource() {
    };

    var _client_id = '';
    var _userName = '';
    var _userPassword = '';
    var _scope = '';

    Resource.prototype = {
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
            // create commonOAuthRequest
            var commonOAuthRequest = new CommonOAuthRequest();
            commonOAuthRequest.setType('POST');
            commonOAuthRequest.setUri('/iamOAuth/receive/resource/token');
            commonOAuthRequest.setParameter('client_id=' + this.getClientId() + '&username=' + this.getUserName() +
                                            '&password=' + this.getUserPassword() + '&scope=' + this.getScope());
            commonOAuthRequest.load(Resource.prototype);
        }
    };

    return Resource;
})();