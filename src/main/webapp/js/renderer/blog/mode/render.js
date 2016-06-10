/**
 * Created by Misaka on 2016-05-13.
 */
class BlogModeRenderer {
    constructor() {
    }

    renderWriteMode() {
        let cleaner = new Cleaner();
        cleaner.target = document.getElementById('center');
        cleaner.clear();

        // load write.html
        document.getElementById('center').setAttribute('w3-include-html', '/template/mode/write.html');
        w3IncludeHTML();
    }

    renderUploadCallback(xmlHttpRequest) {
        let categoryCode = '';
        // find attribute select node and remove attribute
        let liNodes = document.getElementById('left').getElementsByTagName('ul')[0].getElementsByTagName('li');
        for (let i = 0; i < liNodes.length; i++) {
            if (liNodes[i].getAttribute('selected') != null) {
                categoryCode = liNodes[i].getElementsByTagName('a')[0].getAttribute('id');
                break;
            }
        };
        let title = document.getElementById('videoTitle').value;
        let description = document.getElementById('videoDescription').value;
        let videoId = xmlHttpRequest.responseText

        let renderer = new BlogLayoutRenderer();
        let contents = new Contents(renderer, renderer.renderContentViewer);

        contents.type = 'POST';
        contents.uri = '/contents/add';
        contents.parameter = 'categoryCode=' + categoryCode + '&title=' + title + '&description=' + description + '&videoId=' + videoId;
        contents.callRest(contents);
    }
}