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
        }
    };

    return CommonRequest;
}());
