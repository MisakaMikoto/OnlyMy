/**
 * Created by Misaka on 2016-04-14.
 */
var MainInitScript = function() {
    this.init = function(top, left, center) {
        var title = new Title();
        title.load(top, title.constructor.prototype);

        var category = new Category();
        category.load(left, category.constructor.prototype);

        var contents = new Contents();
        contents.loadNewest(center, contents.constructor.prototype);
    }
}