/**
 * Created by o_0 on 2016-12-05.
 */

var userName = 'dude';
var destName = 'bob';

angular.module('chatApp', [])
    .controller('ChatAppController', function($scope) {
        var chatList = this;
        chatList.chatBoard = [{text:'welcome to chat',from:userName,to:destName}];
        var socket = io("http://localhost:3000");


        chatList.sendMessage = function () {
            console.log("submittin form: " + chatList.chatText);
            var msgData = {text:chatList.chatText,from:userName,to:destName};
            socket.emit('sendmessage', msgData);
            chatList.chatText = '';
        };

        chatList.remaining = function () {
            var count = 0;
            count += chatList.chatBoard.length;
            // angular.forEach(chatList.chatBoard,function (chat) {
            //     count += chat.done ? 0 : 1;
            // });
            return count;
        };

        chatList.clearBoard = function () {
            // var oldChatBoard = chatList.chatBoard;
            chatList.chatBoard = [];
            // angular.forEach(oldChatBoard, function (chat) {
            //     if (chat.done == false) {
            //         chatList.chatBoard.push(chat);
            //     }
            // });
        };

        chatList.setUser = function () {
            userName = chatList.myUser;
            chatList.myUser = '';
        };

        chatList.setDestination = function () {
            destName = chatList.theDestination;
            chatList.theDestination = '';
        };

        chatList.regChatUser = function () {
            console.log("regChatUser " + userName);
            var msgData = {userName:userName};
            socket.emit('regUser', msgData);
        };

        socket.on('messageEcho', function (msg) {
            console.log('incoming: from: ' + msg.from + ' to: ' + msg.to +' text: ' + msg.text);
            // chatList.chatBoard.push({text:msg, done:false});
            chatList.chatBoard.push(msg);
            $scope.$apply();


        });

    });