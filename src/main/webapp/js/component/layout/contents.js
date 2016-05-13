/**
 * Created by Misaka on 2016-03-14.
 */
class Contents extends LayoutComponent {
    constructor() {
        super();
    }

    empty() {
        // target div empty
        document.getElementById('center').innerHTML = '';
        document.getElementById('center').innerText = '';
    }

    create(xmlHttpRequest) {
        let contentsJSON = JSON.parse(xmlHttpRequest.responseText);

        if(Object.prototype.toString.call(contentsJSON) == '[object Array]') {
            this.drawList(contentsJSON);

        } else {
            this.drawViewer(contentsJSON);
        }
    }

    drawList(contentsJSON) {
        this.empty();
        let panel = this.createListPanel(contentsJSON);
        document.getElementById('center').appendChild(panel);
    }

    drawViewer(contentsJSON) {
        this.empty();
        let panel = this.createViewerPanel(contentsJSON);
        document.getElementById('center').appendChild(panel);
    }

    createViewerPanel(contentsJSON) {
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

        return panel;
    }

    createListPanel(contentsJSON) {
        let panel = document.createElement('DIV');
        panel.setAttribute('class', 'list-group');

        for(let i in contentsJSON) {
            let key = Util.getJSONKeys(contentsJSON[i]);

            let li = document.createElement('LI');
            li.setAttribute('class', 'list-group-item');

            let a = document.createElement('A');
            a.setAttribute('id', key.toString().toLowerCase());
            a.setAttribute('href', '#');
            a.addEventListener('click', function() {
                this.load(this.getAttribute('id'), document.getElementById('center'));
            });
            let aTextNode = document.createTextNode(contentsJSON[i][key]);
            a.appendChild(aTextNode);

            li.appendChild(a);

            panel.appendChild(li);
        }
        return panel;
    }
}