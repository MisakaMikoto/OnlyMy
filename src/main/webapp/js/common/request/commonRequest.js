/**
 * Created by Misaka on 2016-04-28.
 */
var CommonRequest = (function() {
    function CommonRequest() {
    };

    var _type = '';
    var _uri = '';
    var _parameter = '';
    var _header = [];

    CommonRequest.prototype = {
        setType: function(type) {
            _type = type;
        },

        getType: function() {
            return _type;
        },

        setUri: function(uri) {
            _uri = uri;
        },

        getUri: function() {
            return _uri;
        },

        setParameter: function(parameter) {
            _parameter = parameter;
        },

        getParameter: function() {
            return _parameter;
        },

        addHeader: function(key, value) {
            var headerJSON = JSON.parse('{\"' + key + '\" :\"' + value + '\"}');
            _header.push(headerJSON);
        },

        getHeader: function() {
            return _header;
        },

        load: function(callback) {
            var xmlHttpRequest = new XMLHttpRequest();

            // type and uri setting
            if(this.getType() == 'GET') {
                this.setUri((this.getParameter() != '' && this.getParameter().length > 0) ? this.getUri() + '?' + this.getParameter() : this.getUri());
            }

            xmlHttpRequest.open(this.getType(), this.getUri(), true);
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
            // header setting
            if(this.getHeader() != '' && this.getHeader().length > 0) {
                for(var i in this.getHeader()) {
                    var key = Util.getJSONKeys(this.getHeader()[i]);
                    xmlHttpRequest.setRequestHeader(key, this.getHeader()[i][key]);
                }
            }
            xmlHttpRequest.onreadystatechange = function() {
                if(xmlHttpRequest.readyState == 4) {
                    if (xmlHttpRequest.status == 200) {
                        if(callback == null || typeof callback === 'function') {
                            callback(xmlHttpRequest);

                        } else {
                            console.log('callback is not function!!');
                        }
                    }
                }
            }

            if(this.getType() == 'POST' && this.getParameter() != null && this.getParameter().length > 0) {
                xmlHttpRequest.send(this.getParameter());

            } else {
                xmlHttpRequest.send();
            }
        }
    };

    return CommonRequest;
}());
