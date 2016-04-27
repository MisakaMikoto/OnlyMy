/**
 * Created by Misaka on 2016-04-27.
 */
var Client = (function() {
    function Client() {
        IAM.call(this);
    };

    var client_id = '';
    var client_secret = '';
    var grant_type = '';
    var scope = '';

    return Client;
})();