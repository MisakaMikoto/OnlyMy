/**
 * Created by Misaka on 2016-05-13.
 */
class BlogRenderer {
    constructor() {
        this._category = new Category();
        this._contents = new Contents();
        this._title = new Title();
    }

    get category() {
        return this._category;
    }

    get contents() {
        return this._contents;
    }

    get title() {
        return this._title;
    }

    // draw category
    renderCategory(xmlHttpRequest) {
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
            a.addEventListener('click', () => {
                    this.contents.rendererClass = this;
                    this.contents.rendererFunction = this.renderContentsList;
                    this.contents.loadList(categoryListJSON[i].id.toLowerCase());
                }
            );

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
    }

    // draw contents
    renderContentsList(xmlHttpRequest) {
        let contentsJSON = JSON.parse(xmlHttpRequest.responseText);
        let panel = document.createElement('DIV');
        panel.setAttribute('class', 'list-group');

        for(let i in contentsJSON) {
            let key = Util.getJSONKeys(contentsJSON[i]);

            let li = document.createElement('LI');
            li.setAttribute('class', 'list-group-item');

            let a = document.createElement('A');
            a.setAttribute('id', key.toString().toLowerCase());
            a.setAttribute('href', '#');
            a.addEventListener('click', () => {
                    this.contents.rendererClass = this;
                    this.contents.rendererFunction = this.renderContentViewer;
                    this.contents.load(contentsJSON[i].id);
                }
            );
            let aTextNode = document.createTextNode(contentsJSON[i].subject);
            a.appendChild(aTextNode);

            li.appendChild(a);
            panel.appendChild(li);
        }
        document.getElementById('center').appendChild(panel);
    }

    renderContentViewer(xmlHttpRequest) {
        let contentsJSON = JSON.parse(xmlHttpRequest.responseText);
        let panel = document.createElement('DIV');
        panel.setAttribute('class', 'panel panel-default');

        let head = document.createElement('DIV');
        head.setAttribute('class', 'panel-heading');

        let body = document.createElement('DIV');
        body.setAttribute('class', 'panel-body');
        body.innerText = contentsJSON.content;

        let h3 = document.createElement('H3');
        h3.setAttribute('class', 'panel-title');
        let h3TextNode = document.createTextNode(contentsJSON.subject);
        h3.appendChild(h3TextNode);

        head.appendChild(h3);

        panel.appendChild(head);
        panel.appendChild(body);
        document.getElementById('center').appendChild(panel);
    }

    // draw title
    renderTitle(xmlHttpRequest) {
        let titleNameJSON = JSON.parse(xmlHttpRequest.responseText);

        // set and make ul
        let nav = document.createElement('NAV');
        nav.setAttribute('class', 'navbar navbar-default navbar-fixed-top');

        let containerDiv = document.createElement('DIV');
        containerDiv.setAttribute('class', 'container');
        let containerH2 = document.createElement('H2');
        let containerH2TextNode = document.createTextNode(titleNameJSON.name);
        containerH2.appendChild(containerH2TextNode);
        containerDiv.appendChild(containerH2);
        nav.appendChild(containerDiv);

        document.getElementById('top').appendChild(nav);
    }
}