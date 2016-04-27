/**
 * Created by Misaka on 2016-04-27.
 */
var CommonOAuthRequest = (function() {
    function CommonOAuthRequest() {
    };

    var _type = '';
    var _url = '';
    var _parameter = '';

    CommonOAuthRequest.prototype = {
        setType: function(type) {
            _type = type;
        },

        getType: function() {
            return _type;
        },

        setUrl: function(url) {
            _url = url;
        },

        getUrl: function() {
            return _url;
        },

        setParameter: function(parameter) {
            _parameter = parameter;
        },

        getParameter: function() {
            return _parameter;
        },

        load: function(targetLayout, prototype) {
            var xmlHttpRequest = new XMLHttpRequest();

            if(this.getType() == 'GET') {
                this.setUrl(this.getUrl() + '?' + this.getParameter());
            }
            xmlHttpRequest.open(this.getType(), this.getUrl(), true);
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
            xmlHttpRequest.onreadystatechange = function() {
                if(xmlHttpRequest.readyState == 4) {
                    if (xmlHttpRequest.status == 200) {
                        var component = Object.create(prototype);
                        component.view(xmlHttpRequest.responseText, targetLayout);
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
