/**
 * Created by Misaka on 2016-03-15.
 */
// private method
var Util = function() {
};
Util.getJSONKeys = function(json) {
    var keys = [], name;
    for (name in json) {
        if (json.hasOwnProperty(name)) {
            keys.push(name);
        }
    }
    return keys;
};

Util.parseYoutubeUrl = function(url) {
    var replacedUrl = url.replace('watch?v=', 'v/');
    return replacedUrl;
};

