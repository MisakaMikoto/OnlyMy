/**
 * Created by Misaka on 2016-04-28.
 */
var CommonRequest = (function() {
    function CommonRequest() {
    };

    var _type = '';
    var _uri = '';
    var _parameter = '';

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

        load: function(callback) {
            var xmlHttpRequest = new XMLHttpRequest();

            if(this.getType() == 'GET') {
                this.setUri(this.getUri() + '?' + this.getParameter());
            }

            xmlHttpRequest.open(this.getType(), this.getUri(), true);
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
            xmlHttpRequest.onreadystatechange = function() {
                if(xmlHttpRequest.readyState == 4) {
                    if (xmlHttpRequest.status == 200) {
                        if(callback == null || typeof callback === 'function') {
                            callback(xmlHttpRequest.responseText);

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
