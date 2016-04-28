/**
 * Created by Misaka on 2016-03-14.
 */
var Category = (function() {
    function Category() {
    };

    var _ul = '';
    var _liList = [];

    Category.prototype.setUl = function(ul) {
        _ul = ul
    };

    function getUl() {
        return _ul;
    };

    Category.prototype.setLiList = function(liList) {
        _liList = liList;
    };

    function getLiList() {
        return _liList;
    };

    Category.prototype.create = function(categoryListJSON, target) {
        // set and make ul
        this.setUl(document.createElement('UL'));

        var ul = getUl();
        ul.setAttribute('class', 'list-group');

        // set and make li
        for(var i in categoryListJSON) {
            this.setLiList(document.createElement('LI'));

            var li = getLiList();
            li.setAttribute('class', 'list-group-item');

            var key = Util.getJSONKeys();

            var nameSpan = document.createElement('SPAN');
            var a = document.createElement('a');
            a.setAttribute('href', '#');
            a.addEventListener('click', function() {
                drawContentsList(categoryListJSON[i].id.toLowerCase(), document.getElementById('center'));
            });
            var aTextNode = document.createTextNode(categoryListJSON[i].name);
            a.appendChild(aTextNode);

            nameSpan.appendChild(a);
            li.appendChild(nameSpan);

            var countSpan = document.createElement('SPAN');
            countSpan.setAttribute('class', 'label label-default label-pill pull-xs-right');
            var countSpanTextNode = document.createTextNode(categoryListJSON[i].count);
            countSpan.appendChild(countSpanTextNode);
            li.appendChild(countSpan);

            ul.appendChild(li);
        }
        this.setLiList(categoryListJSON);

        // target layout append
        target.appendChild(ul);
    };

    Category.prototype.load = function(target) {
        // extends
        var commonExtends = new CommonExtends();
        var layoutRequest = commonExtends.doExtends(new LayoutRequest(), CommonRequest.prototype);

        layoutRequest.prototype.setType('GET');
        layoutRequest.prototype.setUri('/category/list');
        layoutRequest.load(target, Category.prototype);
    };

    function drawContentsList(categoryCode, target) {
        var contents = new Contents();
        contents.loadList(categoryCode, target);
    };
    return Category;
}());