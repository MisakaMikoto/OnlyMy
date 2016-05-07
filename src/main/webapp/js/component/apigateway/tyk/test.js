/**
 * Created by Misaka on 2016-05-02.
 */
var Test = (function() {
    function Test() {
    };

    Test.prototype = {
        view: function(responseText) {
            console.log(responseText);
        }
    };

    return Test;
})();