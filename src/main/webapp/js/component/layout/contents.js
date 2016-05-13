/**
 * Created by Misaka on 2016-03-14.
 */
class Contents extends LayoutComponent {
    constructor(rendererClass, renderFunction){
        super(rendererClass, renderFunction);
    }

    clear() {
        document.getElementById('center').innerHTML = '';
        document.getElementById('center').innerText = '';
    }

    load(contentId) {
        this.clear();
        this.type = 'GET';
        this.uri = '/contents/' + contentId;
        this.callRest(this);
    }

    loadList(categoryCode) {
        this.clear();
        this.type = 'GET';
        this.uri = '/contents/list/' + categoryCode;
        this.callRest(this);
    }
}