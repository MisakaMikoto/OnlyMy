/**
 * Created by Misaka on 2016-05-02.
 */
class IAMRefresh extends OAuthComponent{
    constructor() {
        super();
        this._client_id = '';
        this._refresh_token = '';
    }

    set clientId(client_id) {
        this._client_id = client_id;
    }

    get clientId() {
        return this._client_id;
    }

    set refreshToken(refresh_token) {
        this._refresh_token = refresh_token;
    }

    get refreshToken() {
        return this._refresh_token;
    }

    createParameter() {
        return 'client_id=' + this.clientId + '&refresh_token=' + this.refreshToken;
    }

    view(xmlHttpRequest) {
        let responseText = xmlHttpRequest.responseText;
        document.getElementById('againRefreshToken').value = responseText;
    }
}