/**
 * Created by o_0 on 2016-12-05.
 */

angular.module('chatApp', [])
    .controller('ChatAppController', function($scope) {
        var chatList = this;
        chatList.chatBoard = [{text:'welcome to chat',done:true}];
        var socket = io("http://localhost:3000");


        chatList.sendMessage = function () {
            console.log("submittin form: " + chatList.chatText);
            socket.emit('sendmessage', chatList.chatText);
            chatList.chatText = '';
        };

        chatList.remaining = function () {
            var count = 0;
            angular.forEach(chatList.chatBoard,function (chat) {
                count += chat.done ? 0 : 1;
            });
            return count;
        };

        chatList.archive = function () {
            var oldChatBoard = chatList.chatBoard;
            chatList.chatBoard = [];
            angular.forEach(oldChatBoard, function (chat) {
                if (chat.done == false) {
                    chatList.chatBoard.push(chat);
                }
            });
        };

        socket.on('messageEcho', function (msg) {
            console.log('incoming msg: ' + msg);
            chatList.chatBoard.push({text:msg, done:true});
            $scope.$apply();


        });

    });