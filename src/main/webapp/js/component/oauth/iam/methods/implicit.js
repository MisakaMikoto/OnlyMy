/**
 * Created by Misaka on 2016-04-27.
 */
class IAMImplicit extends OAuthComponent{
    constructor() {
        super();
        this._client_id = '';
        this._response_type = '';
        this._redirect_uri = '';
        this._scope = '';
        this._state = '';
    }

    set clientId(client_id) {
        this._client_id = client_id;
    }

    get clientId() {
        return this._client_id;
    }

    set responseType(response_type) {
        this._response_type = response_type;
    }

    get responseType() {
        return this._response_type;
    }

    set redirectUri(redirect_url) {
        this._redirect_uri = redirect_url;
    }

    get redirectUri() {
        return this._redirect_uri;
    }

    set scope(scope) {
        this._scope = scope;
    }

    get scope() {
        return this._scope;
    }

    set state(state) {
        this._state = state;
    }

    get state() {
        return this._state;
    }

    createUri() {
        this.uri = OAuthComponent.MANAGEMENT_SERVER + OAuthComponent.AUTHORIZE + '?client_id=' + this.clientId + '&response_type=' + this.responseType +
            '&redirect_uri=' + this.redirectUri + '&scope=' + this.scope + '&state=' + this.state;
    }
}