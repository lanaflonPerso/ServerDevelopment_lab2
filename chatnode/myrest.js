/**
 * Created by o_0 on 2016-12-05.
 */
var addr = "http://localhost:3500/";

var userName = 'dude';
var destName = 'bob';

angular.module('restApp', [])
    .controller('restAppController', function($scope,$http) {
        var socket = io(addr);

        var redirectData = {
            userId:55,
            path:"services/userservice/",
            method:"userById",
            args:{id:1}
        };
        socket.emit("redirect",redirectData);
        var back = "services/userservice/userById?id=1";
        socket.on('redirectResp', function (msg) {
            console.log('incoming: from: ' + msg.from + ' to: ' + msg.to +' text: ' + msg.text);
            // chatList.chatBoard.push({text:msg, done:false});
            chatList.chatBoard.push(msg);
            //$scope.$apply();


        });



    });