/**
 * Created by Misaka on 2016-04-28.
 */
class CommonRequest {
    constructor() {
        this._type = '';
        this._uri = '';
        this._parameter = '';
        this._header = [];
        this._file = '';
        this._data = '';
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

    set file(file) {
        this._file = file;
    }

    get file() {
        return this._file;
    }

    set data(data) {
        this._data = data;
    }

    get data() {
        return this._data;
    }

    load(reflectObject) {
        let xmlHttpRequest = new XMLHttpRequest();

        // progress
        xmlHttpRequest.upload.onprogress = (e) => {
            if (e.lengthComputable) {
                document.getElementById('progressBar').max = e.total;
                document.getElementById('progressBar').value = e.loaded;
                document.getElementById('display').innerText = Math.floor((e.loaded / e.total) * 100) + '%';
            }
        }
        xmlHttpRequest.upload.onloadstart = () => {
            document.getElementById('progress').style.display = '';
            document.getElementById('progressBar').value = 0;
        }
        xmlHttpRequest.upload.onloadend = (e) => {
            document.getElementById('progress').style.display = 'none';
            document.getElementById('progressBar').value = e.loaded;
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

        // header setting
        if(this.header != '' && this.header.length > 0) {
            for(let i in this.header()) {
                let key = Util.getJSONKeys(this.header[i]);
                xmlHttpRequest.setRequestHeader(key, this.header[i][key]);
            }
        }

        // type and uri and parameter setting
        if(this.type == 'GET') {
            this.uri = (this.parameter != '' && this.parameter.length > 0) ? this.uri + '?' + this.parameter : this.uri;

        } else if(this.type == 'POST' && this.parameter != null && this.parameter.length > 0) {
            // create data
            this.data = new FormData();

            let parseParameter = this.parameter.split('&');
            for(let i in parseParameter) {
                let innerParseParameter = parseParameter[i].split('=');
                this.data.append(innerParseParameter[0], innerParseParameter[1]);
            }
            // file upload?
            if(this.file != '' && this.file != 'undefined') {
                this.data.append('file', this.file);

            } else {
                ;
            }
        }

        xmlHttpRequest.open(this.type, this.uri);

        if(this.type == 'GET') {
            xmlHttpRequest.send();

        // POST
        } else {
            xmlHttpRequest.send(this.data);
        }
    }
}
