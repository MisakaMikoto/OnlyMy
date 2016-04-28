var CommonLayoutRequest = (function() {
    function CommonLayoutRequest() {
    };

    var _type = '';
    var _uri = '';

    CommonLayoutRequest.prototype = {
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

        load: function(targetLayout, prototype) {
            var xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.open(this.getType(), this.getUri(), true);
            xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
            xmlHttpRequest.onreadystatechange = function() {
                if(xmlHttpRequest.readyState == 4) {
                    if (xmlHttpRequest.status == 200) {
                        var component = Object.create(prototype);
                        component.create(JSON.parse(xmlHttpRequest.responseText), targetLayout);
                    }
                }
            }
            xmlHttpRequest.send();
        }
    };

    return CommonLayoutRequest;
}());
