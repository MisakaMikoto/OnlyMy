/**
 * Created by Misaka on 2016-04-27.
 */
class IAMResource extends OAuthComponent {
    constructor(rendererClass, renderFunction) {
        super(rendererClass, renderFunction);
        this._client_id = '';
        this._userName = '';
        this._userPassword = '';
        this._scope = '';
    }

    set clientId(client_id) {
        this._client_id = client_id;
    }

    get clientId() {
        return this._client_id;
    }

    set userName(userName) {
        this._userName = userName;
    }

    get userName() {
        return this._userName;
    }

    set userPassword(userPassword) {
        this._userPassword = userPassword
    }

    get userPassword() {
        return this._userPassword;
    }

    set scope(scope) {
        this._scope = scope;
    }

    get scope() {
        return this._scope;
    }

    createParameter() {
        return 'client_id=' + this.clientId + '&username=' + this.userName + '&password=' + this.userPassword + '&scope=' + this.scope;
    }
}