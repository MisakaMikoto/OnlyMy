/**
 * Created by Misaka on 2016-05-13.
 */
class BlogLayoutRenderer {
    constructor() {
    }

    // draw category
    renderCategoryList(xmlHttpRequest) {
        let categoryListJSON = JSON.parse(xmlHttpRequest.responseText);

        // set and make ul
        let ul = document.createElement('UL');
        ul.setAttribute('class', 'list-group');

        // set and make li
        for(let i in categoryListJSON) {
            let li = document.createElement('LI');
            li.setAttribute('class', 'list-group-item');
            if(i == 0) {
                li.setAttribute('selected', '');
            }

            let nameSpan = document.createElement('SPAN');
            let a = document.createElement('a');
            a.setAttribute('id', categoryListJSON[i].id);
            a.setAttribute('href', '#');
            a.addEventListener('click', () => {
                    // find attribute select node and remove attribute
                    let liNodes = document.getElementById('left').getElementsByTagName('ul')[0].getElementsByTagName('li');

                    for(let i = 0; i < liNodes.length; i++) {
                        if(liNodes[i].getAttribute('selected') != null) {
                            liNodes[i].removeAttribute('selected');
                            break;
                        }
                    };

                    // clicked li set attribute selected
                    a.parentNode.parentNode.setAttribute('selected', '');

                    let contents = new Contents(this, this.renderContentsList);
                    contents.loadList(a.getAttribute('id'));
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
            let li = document.createElement('LI');
            li.setAttribute('class', 'list-group-item');

            let a = document.createElement('A');
            a.setAttribute('id', contentsJSON[i].id);
            a.setAttribute('href', '#');
            a.addEventListener('click', () => {
                    let contents = new Contents(this, this.renderContentViewer);
                    contents.load(contentsJSON[i].id);
                }
            );
            let aTextNode = document.createTextNode(contentsJSON[i].subject);
            a.appendChild(aTextNode);

            li.appendChild(a);
            panel.appendChild(li);
        }
        document.getElementById('center').appendChild(panel);

        // write button
        let button = document.createElement('BUTTON');
        button.setAttribute('class', 'btn btn-default');
        button.style.float = 'right';
        button.addEventListener('click', () => {
                let renderer = new BlogModeRenderer();
                renderer.renderWriteMode();
            }
        );
        let buttonTextNode = document.createTextNode("Write");
        button.appendChild(buttonTextNode);

        document.getElementById('center').appendChild(button);
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

        if(contentsJSON.videoId != null) {
            let iFrameDiv = document.createElement('DIV');
            iFrameDiv.setAttribute('class', 'embed-responsive embed-responsive-4by3');
            let iFrame = document.createElement('IFRAME');
            iFrame.setAttribute('class', 'embed-responsive-item');
            iFrame.setAttribute('src', 'http://youtube.com/v/' + contentsJSON.videoId);

            iFrameDiv.appendChild(iFrame);
            body.appendChild(iFrameDiv);
        }

        let h3 = document.createElement('H3');
        h3.setAttribute('class', 'panel-title');
        let h3TextNode = document.createTextNode(contentsJSON.subject);
        h3.appendChild(h3TextNode);

        head.appendChild(h3);

        panel.appendChild(head);
        panel.appendChild(body);
        document.getElementById('center').appendChild(panel);
    }
}