/**
 * Created by Misaka on 2016-05-02.
 */
class IAMInformation extends OAuthComponent{
    constructor() {
        super();
        this._access_token = '';
    }

    set accessToken(access_token) {
        this._access_token = access_token;
    }

    get accessToken() {
        return this._access_token;
    }

    createParameter(){
        return 'access_token=' + this.accessToken;
    }

    view(xmlHttpRequest) {
        var responseText = xmlHttpRequest.responseText;
        document.getElementById('tokenInfo').value = responseText;
    }
}