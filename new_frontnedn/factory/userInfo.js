/**
 * Created by o_0 on 2016-12-14.
 */
app.factory('userFactory', function ($q) {
    var userId = 1;
    var userName = "";
    var isLoggedin = true;
    var factory = {};
    var selectedUser = 1;
    factory.getUserId = function () {
        console.log('userFactory:getUserId');
        return userId;
    };

    factory.getSelectedUserId = function () {
        console.log('userFactory:getSelectedUserId');
        return selectedUser;
    };
    factory.setSelectedUserId = function (selected) {
        console.log('userFactory:setSelectedUserId');
        selectedUser = selected;
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

    factory.setUser = function (data) {
        userId = data.id;
        selectedUser = data.id;
        userName = data.email;
    };

    return factory;

})