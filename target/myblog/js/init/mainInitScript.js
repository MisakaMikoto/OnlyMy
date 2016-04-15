/**
 * Created by Misaka on 2016-04-14.
 */
var MainInitScript = function() {
    this.init = function(top, left, center) {
        var title = new Title();
        title.load(top);

        var category = new Category();
        category.load(left);

        var contents = new Contents();
        contents.loadNewest(center);
    }
}