/**
 * Created by Misaka on 2016-03-14.
 */
var Category = function() {
    var _ul = '';
    var _liList = [];

    this.setUl = function(ul) {
        _ul = ul
    };

    function getUl() {
        return _ul;
    };

    this.setLiList = function(liList) {
        _liList = liList;
    };

    function getLiList() {
        return _liList;
    };

    this.create = function(categoryListJSON, target) {
        // set and make ul
        this.setUl(document.createElement('UL'));

        var ul = getUl();
        ul.setAttribute('class', 'list-group');

        // set and make li
        for(var i in categoryListJSON) {
            this.setLiList(document.createElement('LI'));

            var li = getLiList();
            li.setAttribute('class', 'list-group-item');

            var key = Util.getJSONKeys(categoryListJSON[i]);

            var nameSpan = document.createElement('SPAN');
            var a = document.createElement('a');
            a.setAttribute('href', '#');
            a.addEventListener('click', function() {
                drawContentsList(this.innerHTML.toLowerCase(), document.getElementById('center'));
            });
            var aTextNode = document.createTextNode(key);
            a.appendChild(aTextNode);

            nameSpan.appendChild(a);
            li.appendChild(nameSpan);

            var countSpan = document.createElement('SPAN');
            countSpan.setAttribute('class', 'label label-default label-pill pull-xs-right');
            var countSpanTextNode = document.createTextNode(categoryListJSON[i][key]);
            countSpan.appendChild(countSpanTextNode);
            li.appendChild(countSpan);

            ul.appendChild(li);
        }
        this.setLiList(categoryListJSON);

        // target layout append
        target.appendChild(ul);
    };

    this.load = function(target) {
        // create commonRequest
        var commonRequest = new CommonRequest();
        commonRequest.setType('POST');
        commonRequest.setUrl('/category/list');
        commonRequest.setTargetComponentName(CommonRequest.category);
        commonRequest.load(target);
    };

    function drawContentsList(categoryCode, target) {
        var contents = new Contents();
        contents.loadList(categoryCode, target);
    };
};