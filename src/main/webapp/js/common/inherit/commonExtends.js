/**
 * Created by Misaka on 2016-04-25.
 */
var CommonExtends = (function() {
    function CommonExtends() {
    };

    CommonExtends.prototype = {
        doExtends: function(childObject, parentPrototype) {
            childObject.prototype = parentPrototype;
            return childObject;
        }
    };

    return CommonExtends;
})();
