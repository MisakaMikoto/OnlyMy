/**
 * Created by Misaka on 2016-03-14.
 */

class Category extends LayoutComponent {
    constructor(rendererClass, renderFunction){
        super(rendererClass, renderFunction);
    }

    refresh() {
        let cleaner = new Cleaner();
        cleaner.target = document.getElementById('left');
        cleaner.clear();

        this.type = 'GET';
        this.uri = '/category/list';
        this.callRest(this);
    }
}