/**
 * Created by Misaka on 2016-03-16.
 */
class Title extends LayoutComponent{
    constructor(){
        super();
    }

    create(xmlHttpRequest) {
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
    };
}