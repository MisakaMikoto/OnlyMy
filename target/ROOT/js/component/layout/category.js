/**
 * Created by Misaka on 2016-03-14.
 */

class Category extends LayoutComponent {
    constructor(){
        super();
    }

    draw(data) {
        angular.forEach(data, function(value){
            let li = angular.element(value);
            li.addClass("list-group-item");
        });
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