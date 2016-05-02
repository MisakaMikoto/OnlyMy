/**
 * Created by Misaka on 2016-04-18.
 */
var OAuthComponent = (function() {
    function OAuthComponent() {
    };

    var _uri = '';
    var _type = '';
    var _parameter = '';

    OAuthComponent.prototype = {
        setUri: function(uri) {
            _uri = uri;
        },

        getUri: function() {
            return _uri;
        },

        setType: function(type) {
            _type = type;
        },

        getType: function() {
            return _type;
        },

        setParameter: function(parameter) {
            _parameter = parameter;
        },

        getParameter: function() {
            return _parameter;
        },

        verify: function() {
            window.open(this.getUri(), '_self');
        },

        callRest: function(callback) {
            var commonRequest = new CommonRequest();

            commonRequest.setType(this.getType());
            commonRequest.setUri(this.getUri());
            commonRequest.setParameter(this.getParameter());
            commonRequest.load(callback);
        }
    };

    return OAuthComponent;
})();

OAuthComponent.FACEBOOK_SERVER = 'https://graph.facebook.com/';

OAuthComponent.MANAGEMENT_SERVER = 'http://52.79.164.208:8080';
OAuthComponent.ACCESS_TOKEN = '/oauth/access_token';
OAuthComponent.AUTHORIZE = '/oauth/authorize';

// ECMA Script
//class OAuthComponent {
//    set url(url) {
//        this._url = url;
//    };
//
//    get url() {
//        return this._url;
//    };
//
//    set clientId(clientId) {
//        this._client_id = clientId;
//    };
//
//    get clientId() {
//        return this._client_id;
//    };
//
//    set scope(scope) {
//        this._scope = scope;
//    };
//
//    get scope() {
//        return this._scope;
//    };
//
//    set redirectUrl(redirectUrl) {
//        this._redirect_url = redirectUrl;
//    };
//
//    get redirectUrl() {
//        return this._redirect_url;
//    };
//
//    verify() {
//        window.open(this.url, '_self');
//    };
//
//    view(tokenJSON, target) {
//        target.value = tokenJSON.access_token;
//    };
//
//};