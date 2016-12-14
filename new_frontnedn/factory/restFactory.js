/**
 * Created by o_0 on 2016-12-14.
 */

var pathUserService = "services/userservice/";
var pathProfileService = "services/profileservice/";
var pathPostService = "services/postservice/";
app.factory('restFactory', function ($q,socketService) {
    var factory = {};
    factory.getUserById = function (userId) {
        console.log('factory:getUserById');

        var socket = socketService.getRestPipeSocket();
        var callbackName = "userById";
        var redirectData = {
            userId:55,
            reqType:"POST",
            path:"services/userservice/",
            method:"userById",
            args:{id:userId},
            callback:callbackName
        };
        socket.emit("redirect",redirectData);

        var deferred = $q.defer();
        socket.on(callbackName, function (msg) {
            var res = JSON.parse(msg);
            console.log('incoming: from: id ' + res.id + ' profileId: ' + res.profileId +'  email: ' + res.email + " " );
            // chatList.chatBoard.push({text:msg, done:false});
            deferred.resolve(res);
        });
        return deferred.promise;
    };

    factory.getFriendsByUserId = function (userId) {
        console.log('factory:getUserById');

        var socket = socketService.getRestPipeSocket();
        var callbackName = "resultFriendsByUserId";
        var redirectData = {
            userId:55,
            reqType:"GET",
            path:pathUserService,
            method:"getFriendsByUserId",
            args:{userId:userId},
            callback:callbackName
        };
        socket.emit("redirect",redirectData);

        var deferred = $q.defer();
        socket.on(callbackName, function (resData) {
            var res = JSON.parse(resData);
            console.log('incoming: ' + callbackName +' data: ' + resData + ' res' + res);
            // chatList.chatBoard.push({text:msg, done:false});
            deferred.resolve(res);
        });
        return deferred.promise;
    };

    factory.getProfile = function (userId) {
        console.log('factory:getProfile');

        var socket = socketService.getRestPipeSocket();
        var callbackName = "resultGetProfile";
        var redirectData = {
            userId:55,
            reqType:"GET",
            path:pathProfileService,
            method:"getProfile",
            args:{userId:userId},
            callback:callbackName
        };
        socket.emit("redirect",redirectData);

        var deferred = $q.defer();
        socket.on(callbackName, function (resData) {
            var res = JSON.parse(resData);
            console.log('incoming: ' + callbackName +' data: ' + resData + ' res' + res);
            // chatList.chatBoard.push({text:msg, done:false});
            deferred.resolve(res);
        });
        return deferred.promise;
    };
    return factory;

})