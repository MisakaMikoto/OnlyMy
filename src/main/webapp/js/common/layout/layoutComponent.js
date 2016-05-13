/**
 * Created by Misaka on 2016-04-22.
 */

class LayoutComponent {
    constructor(rendererClass, rendererFunction) {
        this._uri = '';
        this._type = '';
        this._parameter = '';
        this._rendererClass = rendererClass;
        this._rendererFunction = rendererFunction;
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

    set rendererClass(rendererClass) {
        this._rendererClass = rendererClass;
    }

    get rendererClass() {
        return this._rendererClass;
    }

    set rendererFunction(rendererFunction) {
        this._rendererFunction = rendererFunction;
    }

    get rendererFunction() {
        return this._rendererFunction;
    }


    callRest(reflectObject) {
        let commonRequest = new CommonRequest();

        commonRequest.type = this.type;
        commonRequest.uri = this.uri;
        commonRequest.parameter = this.parameter;
        commonRequest.load(reflectObject);
    }

    create(data) {
        let lender = this.rendererFunction.bind(this.rendererClass);
        lender(data);
    }
}