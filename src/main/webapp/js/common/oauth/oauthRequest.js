/**
 * Created by Misaka on 2016-04-27.
 */
var OAuthRequest = (function() {
    function OAuthRequest() {
    };

    OAuthRequest.prototype = {
        load: function(prototype, target) {
            var xmlHttpRequest = new XMLHttpRequest();

            if(this.prototype.getType() == 'GET') {
                this.setUri(this.prototype.getUri() + '?' + this.prototype.getParameter());
            }

            xmlHttpRequest.open(this.prototype.getType(), this.prototype.getUri(), true);
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

            if(this.prototype.getType() == 'POST' && this.prototype.getParameter() != null && this.prototype.getParameter().length > 0) {
                xmlHttpRequest.send(this.prototype.getParameter());

            } else {
                xmlHttpRequest.send();
            }
        }
    };
    return OAuthRequest;
}());
