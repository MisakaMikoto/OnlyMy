/**
 * Created by Misaka on 2016-04-22.
 */

class LayoutComponent {
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

    callRest(callback) {
        let commonRequest = new CommonRequest();

        commonRequest.type = this.type;
        commonRequest.uri = this.uri;
        commonRequest.parameter = this.parameter;
        commonRequest.load(callback);
    }
}