/**
 * Created by Misaka on 2016-03-14.
 */
class Contents extends LayoutComponent {
    constructor(rendererClass, renderFunction){
        super(rendererClass, renderFunction);
    }

    load(contentId) {
        let cleaner = new Cleaner();
        cleaner.target = document.getElementById('center');
        cleaner.clear();

        this.type = 'GET';
        this.uri = '/contents/' + contentId;
        this.callRest(this);
    }

    loadList(categoryCode) {
        let cleaner = new Cleaner();
        cleaner.target = document.getElementById('center');
        cleaner.clear();

        this.type = 'GET';
        this.uri = '/contents/list/' + categoryCode;
        this.callRest(this);
    }
}