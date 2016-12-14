/**
 * Created by o_0 on 2016-12-14.
 */
app.factory('userFactory', function ($q) {
    var userId = 1;
    var userName = "";
    var isLoggedin = true;
    var factory = {};
    factory.getUserId = function () {
        console.log('userFactory:getUserId');
        return userId;
    };

    factory.isLoggedin = function () {
        console.log('userFactory:getUserId');
        return isLoggedin;
    };
    factory.logout = function () {
        console.log('userFactory:getUserId');
        isLoggedin = false;
    };
    factory.getUserName = function () {
        console.log('userFactory:getUserName');
        return userName;
    };
    return factory;

})