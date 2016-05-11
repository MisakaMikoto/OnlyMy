/**
 * Created by Misaka on 2016-04-18.
 */
class OAuthComponent {
    constructor() {
        this._uri = '';
        this._type = '';
        this._parameter = '';
    }

    set uri(uri) {
        this._uri = uri;
    }

    get uri() {
        return this._uri;
    }

    set type(type) {
        this._type = type;
    }

    get type() {
        return this._type;
    }

    set parameter(parameter) {
        this._parameter = parameter;
    }

    get parameter() {
        return this._parameter;
    }

    verify() {
        var form = document.form;
        var width = '600';
        var height = '600';

        var wTop = window.screenTop ? window.screenTop : window.screenY;
        var wLeft = window.screenLeft ? window.screenLeft : window.screenX;

        var top = wTop + (window.innerHeight / 2) - (height / 2);
        var left = wLeft + (window.innerWidth / 2) - (width / 2);

        var popUri = '/oauth/popup/authorization';
        var popOption = 'width=' + width + ',height=' + height + ',top=' + top + ',left=' + left;

        window.open('','Authorize',popOption);

        form.target = 'Authorize';
        form.action = popUri;
        form.method = "post";
        form.uri.value = this.uri;
        form.submit();
    }

    callRest(callback) {
        var commonRequest = new CommonRequest();

        commonRequest.type = this.type;
        commonRequest.uri = this.uri;
        commonRequest.parameter = this.parameter;
        commonRequest.load(callback);
    }
}

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