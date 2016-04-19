/**
 * Created by Misaka on 2016-03-16.
 */
var Title = function() {
    var _navigation = '';

    this.setNavigation = function(navigation) {
        _navigation = navigation;
    };

    function getNavigation() {
        return _navigation;
    };

    this.create = function(titleNameJSON, target) {
        // set and make ul
        this.setNavigation(document.createElement('NAV'));

        var nav = getNavigation();
        nav.setAttribute('class', 'navbar navbar-default navbar-fixed-top');

        var containerDiv = document.createElement('DIV');
        containerDiv.setAttribute('class', 'container');
        var containerH2 = document.createElement('H2');
        var containerH2TextNode = document.createTextNode(titleNameJSON.title);
        containerH2.appendChild(containerH2TextNode);
        containerDiv.appendChild(containerH2);
        nav.appendChild(containerDiv);
        this.setNavigation(nav);

        target.insertBefore(nav, window.document.body.firstChild);
    };

    this.load = function(target) {
        // create commonRequest
        var commonRequest = new CommonRequest();
        commonRequest.setType('GET');
        commonRequest.setUrl('/title');
        commonRequest.setTargetComponentName(CommonRequest.title);
        commonRequest.load(target);
    };
}