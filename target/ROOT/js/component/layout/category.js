/**
 * Created by Misaka on 2016-03-14.
 */

class Category {
    constructor() {
        this._ul = '';
        this._liList = '';
    }

    set ul(ul) {
        this._url = ul;
    }

    get ul() {
        return this._url;
    }

    set liList(liList) {
        this._liList = liList;
    }

    get liList() {
        return this._liList;
    }

    draw(categoryListJSON, target) {
        // set and make ul
        this.ul = document.createElement('UL');
        var ul = this.ul;
        ul.setAttribute('class', 'list-group');

        // set and make li
        for(var i in categoryListJSON) {
            this.liList = document.createElement('LI');
            var li = this.liList;
            li.setAttribute('class', 'list-group-item');

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
        this.liList = categoryListJSON;

        // target layout append
        target.appendChild(ul);
    };

    load() {
        // extends
        var commonRequest = new CommonRequest();
        commonRequest.type = 'GET';
        commonRequest.uri = '/category/list';
        commonRequest.load(this.draw);
    };

    drawContentsList(categoryCode, target) {
        var contents = new Contents();
        contents.loadList(categoryCode, target);
    }
}