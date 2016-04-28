/**
 * Created by Misaka on 2016-04-27.
 */
var CommonOAuthRequest = (function() {
    function CommonOAuthRequest() {
    };

    var _type = '';
    var _uri = '';
    var _parameter = '';

    CommonOAuthRequest.prototype = {
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

        load: function(prototype, target) {
            var xmlHttpRequest = new XMLHttpRequest();

            if(this.getType() == 'GET') {
                this.setUri(this.getUri() + '?' + this.getParameter());
            }

            xmlHttpRequest.open(this.getType(), this.getUri(), true);
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
            xmlHttpRequest.onreadystatechange = function() {
                if(xmlHttpRequest.readyState == 4) {
                    if (xmlHttpRequest.status == 200) {
                        var component = Object.create(prototype);

                        if(target != null || typeof target !== 'undefined') {
                            component.view(xmlHttpRequest.responseText, target);

                        } else {
                            var component = Object.create(prototype);
                            component.view(xmlHttpRequest.responseText);
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
    return CommonOAuthRequest;
}());
