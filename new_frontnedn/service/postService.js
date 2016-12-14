/**
 * Created by o_0 on 2016-12-14.
 */
/**
 * Created by o_0 on 2016-12-14.
 */
var pathProfileService = "services/profileservice/";
var pathPostService = "services/postservice/";
app.service('postService',function ($q, socketService) {

    this.getProfileposts = function (selectedUserId,visitorId) {
        console.log('factory:getprofileposts');

        var socket = socketService.getRestPipeSocket();
        var callbackName = "postService";
        var redirectData = {
            userId:55,
            reqType:"GET",
            path:pathPostService,
            method:"getprofileposts",
            args:{selectedUserId:selectedUserId,visitorId:visitorId},
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

    this.getNewsFeed = function (userId) {
        console.log('factory:getNewsFeed');

        var socket = socketService.getRestPipeSocket();
        var callbackName = "newsFeed";
        var redirectData = {
            userId:userId,
            reqType:"GET",
            path:pathPostService,
            method:"getFeed",
            args:{userId:userId},
            callback:callbackName
        };
        socket.emit("redirect",redirectData);

        var deferred = $q.defer();
        socket.on(callbackName, function (resData) {
            console.log('incoming before jsnon parse: ' + callbackName +' data: ' + resData);
            var res = JSON.parse(resData);
            console.log('incoming: ' + callbackName +' data: ' + resData + ' res' + res);
            // chatList.chatBoard.push({text:msg, done:false});
            deferred.resolve(res);
        });
        return deferred.promise;
    };
})