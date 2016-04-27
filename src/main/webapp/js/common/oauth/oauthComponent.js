/**
 * Created by Misaka on 2016-04-18.
 */
var OAuthComponent = (function() {
    function OAuthComponent() {
    };

    var _url = '';

    OAuthComponent.prototype = {
        setUrl: function(url) {
            _url = url;
        },

        getUrl: function() {
            return _url;
        },

        verify: function() {
            window.open(this.getUrl(), '_self');
        },

        view: function(tokenInfo, target) {
            target.value = tokenInfo;
        }
    };

    return OAuthComponent;
})();

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