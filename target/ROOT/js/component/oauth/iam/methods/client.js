/**
 * Created by Misaka on 2016-04-27.
 */
class IAMClient extends OAuthComponent{
    constructor() {
        super();
        this._client_id = '';
        this._scope = '';
    }

    set clientId(client_id) {
        this._client_id = client_id;
    }

    get clientId() {
        return this._client_id;
    }

    set scope(scope) {
        this._scope = scope;
    }

    get scope() {
        return this._scope;
    }

    createParameter() {
        return 'client_id=' + this.clientId + '&scope=' + this.scope;
    }

    view(xmlHttpRequest) {
        var clientToken = xmlHttpRequest.responseText;
        var clientTokenJSON = JSON.parse(clientToken);

        document.getElementById('accessToken').value = clientTokenJSON.access_token;
        document.getElementById('refreshToken').value = clientTokenJSON.refresh_token;
    }
}