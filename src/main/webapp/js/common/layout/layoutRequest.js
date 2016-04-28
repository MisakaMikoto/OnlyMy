var LayoutRequest = (function() {
    function LayoutRequest() {
    };

    LayoutRequest.prototype = {
        load: function(targetLayout, prototype) {
            var xmlHttpRequest = new XMLHttpRequest();
            xmlHttpRequest.open(this.prototype.getType(), this.prototype.getUri(), true);
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

    return LayoutRequest;
}());
