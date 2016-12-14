/**
 * Created by o_0 on 2016-12-14.
 */
/**
 * Created by o_0 on 2016-12-14.
 */
var pathUserService = "services/userservice/";
app.service('friendService',function ($q, socketService) {

    this.getFriendsByUserId = function (userId) {
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
})