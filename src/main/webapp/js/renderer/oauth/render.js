/**
 * Created by Misaka on 2016-05-23.
 */
class OAuthRenderer {
    constructor() {
        this._authorization = new IAMAuthorization();
        this._client = new IAMClient();
        this._implicit = new IAMImplicit();
        this._information = new IAMInformation();
        this._refresh = new IAMRefresh();
        this._resource = new IAMResource();

    }

    get authorization() {
        return this._authorization;
    }

    get client() {
        return this._client;
    }

    get implicit() {
        return this._implicit;
    }

    get information() {
        return this._information;
    }

    get refresh() {
        return this._refresh;
    }

    get resource() {
        return this._resource;
    }

    renderAuthorization(authorizationToken) {
        let authorizationTokenJSON = JSON.parse(authorizationToken);

        let parent = window.opener;
        parent.document.getElementById('accessToken').value = authorizationTokenJSON.access_token;
        parent.document.getElementById('refreshToken').value = authorizationTokenJSON.refresh_token;

        window.opener = self;
        window.close();
    }

    renderClient(xmlHttpRequest) {
        let clientToken = xmlHttpRequest.responseText;
        let clientTokenJSON = JSON.parse(clientToken);

        document.getElementById('accessToken').value = clientTokenJSON.access_token;
        document.getElementById('refreshToken').value = clientTokenJSON.refresh_token;
    }

    renderImplicit(implicitToken) {
        let implicitTokenJSON = JSON.parse(implicitToken);

        let parent = window.opener;
        parent.document.getElementById('accessToken').value = implicitTokenJSON.access_token;
        parent.document.getElementById('refreshToken').value = implicitTokenJSON.refresh_token;

        window.opener = self;
        window.close();
    }

    renderInformation(xmlHttpRequest) {
        let responseText = xmlHttpRequest.responseText;
        document.getElementById('tokenInfo').value = responseText;
    }

    renderRefresh(xmlHttpRequest) {
        let responseText = xmlHttpRequest.responseText;
        document.getElementById('againRefreshToken').value = responseText;
    }

    renderResource(xmlHttpRequest) {
        let resourceToken = xmlHttpRequest.responseText;
        let resourceTokenJSON = JSON.parse(resourceToken);

        document.getElementById('accessToken').value = resourceTokenJSON.access_token;
        document.getElementById('refreshToken').value = resourceTokenJSON.refresh_token;
    }
}