/**
 * Created by Misaka on 2016-03-14.
 */
var Contents = (function() {
    function Contents() {
    };

    var myself = this;

    Contents.prototype.create = function(contentsJSON, target) {
        if(Object.prototype.toString.call(contentsJSON) == '[object Array]') {
            myself.drawList(contentsJSON, target);

        } else {
            drawViewer(contentsJSON, target);
        }
    };

    Contents.prototype.drawList = function(contentsJSON, target) {
        empty(target);
        var panel = createListPanel(contentsJSON);
        target.appendChild(panel);
    };

    function drawViewer(contentsJSON, target) {
        empty(target);
        var panel = createViewerPanel(contentsJSON);
        target.appendChild(panel);
    };

    function empty(target) {
        // target div empty
        target.innerHTML = '';
        target.innerText = '';
    };

    function createViewerPanel(contentsJSON) {
        var panel = document.createElement('DIV');
        panel.setAttribute('class', 'panel panel-default');

        var head = document.createElement('DIV');
        head.setAttribute('class', 'panel-heading');

        var body = document.createElement('DIV');
        body.setAttribute('class', 'panel-body');
        body.innerText = contentsJSON.content;

        var h3 = document.createElement('H3');
        h3.setAttribute('class', 'panel-title');
        var h3TextNode = document.createTextNode(contentsJSON.subject);
        h3.appendChild(h3TextNode);

        head.appendChild(h3);

        panel.appendChild(head);
        panel.appendChild(body);

        return panel;
    };

    function createListPanel(contentsJSON) {
        var panel = document.createElement('DIV');
        panel.setAttribute('class', 'list-group');

        for(var i in contentsJSON) {
            var key = Util.getJSONKeys(contentsJSON[i]);

            var li = document.createElement('LI');
            li.setAttribute('class', 'list-group-item');

            var a = document.createElement('A');
            a.setAttribute('id', key.toString().toLowerCase());
            a.setAttribute('href', '#');
            a.addEventListener('click', function() {
                myself.load(this.getAttribute('id'), document.getElementById('center'));
            });
            var aTextNode = document.createTextNode(contentsJSON[i][key]);
            a.appendChild(aTextNode);

            li.appendChild(a);

            panel.appendChild(li);
        }
        return panel;
    };

    Contents.prototype.load = function(contentId, target) {
        // create commonLayoutRequest
        var commonLayoutRequest = new CommonLayoutRequest();
        commonLayoutRequest.setType('GET');
        commonLayoutRequest.setUri('/contents/' + contentId);
        commonLayoutRequest.load(target, Contents.prototype);
    };

    Contents.prototype.loadNewest = function(target) {
        // create commonLayoutRequest
        var commonLayoutRequest = new CommonLayoutRequest();
        commonLayoutRequest.setType('GET');
        commonLayoutRequest.setUri('/contents/newest');
        commonLayoutRequest.load(target, Contents.prototype);
    };

    Contents.prototype.loadList = function(categoryCode, target) {
        // create commonLayoutRequest
        var commonLayoutRequest = new CommonLayoutRequest();
        commonLayoutRequest.setType('POST');
        commonLayoutRequest.setUri('/contents/' + categoryCode);
        commonLayoutRequest.load(target, Contents.prototype);
    };
    return Contents;
}());