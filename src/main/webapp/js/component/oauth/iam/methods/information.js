/**
 * Created by Misaka on 2016-05-02.
 */
class IAMInformation extends OAuthComponent{
    constructor(rendererClass, renderFunction) {
        super(rendererClass, renderFunction);
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
}