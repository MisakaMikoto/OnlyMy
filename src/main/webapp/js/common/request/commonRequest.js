/**
 * Created by Misaka on 2016-04-28.
 */
class CommonRequest {
    constructor() {
        this._type = '';
        this._uri = '';
        this._parameter = '';
        this._header = [];
    }

    set type(type) {
        this._type = type;
    }

    get type() {
        return this._type;
    }

    set uri(uri) {
        this._uri = uri;
    }

    get uri() {
        return this._uri;
    }

    set parameter(parameter) {
        this._parameter = parameter;
    }

    get parameter() {
        return this._parameter;
    }

    addHeader(key, value) {
        let headerJSON = JSON.parse('{\"' + key + '\" :\"' + value + '\"}');
        this._header.push(headerJSON);
    }

    get header() {
        return this._header;
    }

    load(reflectObject) {
        let xmlHttpRequest = new XMLHttpRequest();

        // type and uri setting
        if(this.type == 'GET') {
            this.uri = (this.parameter != '' && this.parameter.length > 0) ? this.uri + '?' + this.parameter : this.uri;
        }

        xmlHttpRequest.open(this.type, this.uri, true);
        xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
        // header setting
        if(this.header != '' && this.header.length > 0) {
            for(let i in this.header()) {
                let key = Util.getJSONKeys(this.header[i]);
                xmlHttpRequest.setRequestHeader(key, this.header[i][key]);
            }
        }
        xmlHttpRequest.onreadystatechange = function() {
            if(xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {
                    if(reflectObject != null) {
                        if(Reflect.has(reflectObject, 'create')) {
                            Reflect.apply(reflectObject.create, reflectObject, [xmlHttpRequest]);

                        } else {
                            console.log('this object is not create function!! please make the create function!!');
                        }

                    } else {
                        console.log('callback is not function!!');
                    }
                }
            }
        }

        if(this.type == 'POST' && this.parameter != null && this.parameter.length > 0) {
            xmlHttpRequest.send(this.parameter);

        } else {
            xmlHttpRequest.send();
        }
    }
}
