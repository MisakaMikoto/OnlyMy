/**
 * Created by Misaka on 2016-03-14.
 */

class Category extends LayoutComponent {
    constructor() {
        super();
    }

    create(xmlHttpRequest) {
        let categoryListJSON = JSON.parse(xmlHttpRequest.responseText);

        // set and make ul
        let ul = document.createElement('UL');
        ul.setAttribute('class', 'list-group');

        // set and make li
        for(let i in categoryListJSON) {
            let li = document.createElement('LI');
            li.setAttribute('class', 'list-group-item');

            let nameSpan = document.createElement('SPAN');
            let a = document.createElement('a');
            a.setAttribute('href', '#');
            a.addEventListener('click', function() {
                drawContentsList(categoryListJSON[i].id.toLowerCase(), document.getElementById('center'));
            });

            let aTextNode = document.createTextNode(categoryListJSON[i].name);
            a.appendChild(aTextNode);

            nameSpan.appendChild(a);
            li.appendChild(nameSpan);

            let countSpan = document.createElement('SPAN');
            countSpan.setAttribute('class', 'label label-default label-pill pull-xs-right');
            let countSpanTextNode = document.createTextNode(categoryListJSON[i].count);
            countSpan.appendChild(countSpanTextNode);
            li.appendChild(countSpan);

            ul.appendChild(li);
        }

        // target layout append
        document.getElementById('left').appendChild(ul);
    };

    drawContentsList(categoryCode, target) {
        let contents = new Contents();
        contents.loadList(categoryCode, target);
    }
}