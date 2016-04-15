var CommonRequest = function() {
    var _type = '';
    var _url = '';
    var _targetComponentName = '';
    var _data = '';

    this.setType = function(type) {
        _type = type;
    };

    this.setUrl = function(url) {
        _url = url;
    };

    this.setTargetComponentName = function(setTargetComponentName) {
        _targetComponentName = setTargetComponentName;
    };

    this.getTargetComponentName = function() {
        return _targetComponentName;
    };

    this.setData = function(data) {
        _data = data;
    };

    this.getData = function() {
      return _data;
    };

    this.load = function(targetLayout) {
        var myself = this;
        var xmlHttpRequest = new XMLHttpRequest();
        xmlHttpRequest.open(_type, _url, true);
        xmlHttpRequest.setRequestHeader('Content-type', 'application/x-www-form-urlencoded; charset=utf-8');
        xmlHttpRequest.onreadystatechange = function() {
            if(xmlHttpRequest.readyState == 4) {
                if (xmlHttpRequest.status == 200) {
                    var component = null;

                    if(CommonRequest.title == myself.getTargetComponentName()) {
                        component = new Title();
                    }

                    if(CommonRequest.category == myself.getTargetComponentName()) {
                        component = new Category();
                    }

                    if(CommonRequest.contentList == myself.getTargetComponentName()) {
                        component = new Contents();
                    }

                    if(CommonRequest.contentsViewer == myself.getTargetComponentName()) {
                        component = new Contents();
                    }
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

CommonRequest.title = 'title';
CommonRequest.category = 'category';
CommonRequest.contentList = 'contentList';
CommonRequest.contentsViewer = 'contentsViewer';
