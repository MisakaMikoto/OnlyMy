/**
 * Created by Misaka on 2016-03-16.
 */
var Title = (function() {
    function Title() {
    };

    var _navigation = '';

    Title.prototype.setNavigation = function(navigation) {
        _navigation = navigation;
    };

    function getNavigation() {
        return _navigation;
    };

    Title.prototype.create = function(titleNameJSON, target) {
        // set and make ul
        this.setNavigation(document.createElement('NAV'));

        var nav = getNavigation();
        nav.setAttribute('class', 'navbar navbar-default navbar-fixed-top');

        var containerDiv = document.createElement('DIV');
        containerDiv.setAttribute('class', 'container');
        var containerH2 = document.createElement('H2');
        var containerH2TextNode = document.createTextNode(titleNameJSON.name);
        containerH2.appendChild(containerH2TextNode);
        containerDiv.appendChild(containerH2);
        nav.appendChild(containerDiv);
        this.setNavigation(nav);

        target.insertBefore(nav, window.document.body.firstChild);
    };

    Title.prototype.load = function(target) {
        // create commonLayoutRequest
        var commonLayoutRequest = new CommonLayoutRequest();
        commonLayoutRequest.setType('GET');
        commonLayoutRequest.setUrl('/title');
        commonLayoutRequest.load(target, Title.prototype);
    };
    return Title;
}());