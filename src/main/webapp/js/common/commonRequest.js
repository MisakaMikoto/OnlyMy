var CommonRequest = function() {
    var _type = '';
    var _url = '';
    var _data = '';

    this.setType = function(type) {
        _type = type;
    };

    this.setUrl = function(url) {
        _url = url;
    };

    this.setData = function(data) {
        _data = data;
    };

    this.getData = function() {
      return _data;
    };

    this.load = function(targetLayout, prototype) {
        var myself = this;
        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open(_type, _url, true);
        //xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
        xmlHttpRequest.onreadystatechange = function() {
            if(xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {
                    var component = Object.create(prototype);
                    component.create(JSON.parse(xmlHttpRequest.responseText), targetLayout);
                }
            }
        }

        if(this.getData().length > 0 && this.getData() != '') {
            xmlHttpRequest.send(this.getData());

        } else {
            xmlHttpRequest.send();
        }
    };
};
