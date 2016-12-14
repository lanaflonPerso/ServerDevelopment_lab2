/**
 * Created by o_0 on 2016-12-14.
 */
var pathProfileService = "services/profileservice/";
var pathPostService = "services/postservice/";
app.service('profileService',function ($q, socketService) {

    this.getProfile = function (userId) {
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

    this.saveProfile = function (userId,username,info,age,relationshipStatus) {
        console.log('factory:saveProfile');

        var socket = socketService.getRestPipeSocket();
        var callbackName = "updateProfile";
        var redirectData = {
            userId:55,
            reqType:"POST",
            path:pathProfileService,
            method:"updateProfile",
            args:{userId:userId,username:username,info:info,relationshipStatus:relationshipStatus,age:age},
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


})