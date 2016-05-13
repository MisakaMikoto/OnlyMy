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

    get parameter() {
        return this._parameter;
    }

    verify() {
        let form = document.form;
        let width = '600';
        let height = '600';

        let wTop = window.screenTop ? window.screenTop : window.screenY;
        let wLeft = window.screenLeft ? window.screenLeft : window.screenX;

        let top = wTop + (window.innerHeight / 2) - (height / 2);
        let left = wLeft + (window.innerWidth / 2) - (width / 2);

        let popUri = '/oauth/popup/authorization';
        let popOption = 'width=' + width + ',height=' + height + ',top=' + top + ',left=' + left;

        window.open('','Authorize',popOption);

        form.target = 'Authorize';
        form.action = popUri;
        form.method = "post";
        form.uri.value = this.uri;
        form.submit();
    }

    callRest(callback) {
        let commonRequest = new CommonRequest();

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