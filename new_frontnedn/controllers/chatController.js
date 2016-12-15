/**
 * Created by o_0 on 2016-12-05.
 */

var userName = 'dude';
var destName = 'bob';

app
    .controller('ChatAppController', function ($scope) {
        var chatBoard = [{text: 'welcome to chat', from: userName, to: destName}];

        // var webSocket = new WebSocket("http://localhost:3000/chat");
        var host = "ws://localhost:8085/chatserver";
        var wSocket = new WebSocket(host);


        wSocket.onopen = function () {
            alert(" Web Socket is connected, sending data");


        };


        wSocket.onerror = function () {
            alert("Fel!");
        };

        wSocket.onmessage = function (event) {
            var data = event.data;
            console.log("data: "+ data);
        };


        // fixa json som f;rutom data 'ven skickar vilken fiunktion som ;nskas
        $scope.getMessagesBetweenUsers = function () {
            // Request for all messages between users
            var req = { request:"getMessagesBetweenUsers", fromId: 1, toId: 3};
            var data = JSON.stringify(req);
            wSocket.send(data);
        };

        $scope.getMessagesByGroup = function () {
            // Request for all messages between users
            var req = { request:"getMessagesByGroup", groupId: 1};
            var data = JSON.stringify(req);
            wSocket.send(data);
        };

        $scope.getGroups = function () {
            // Request for all messages between users
            var req = { request:"getGroups"};
            var data = JSON.stringify(req);
            wSocket.send(data);
        };






        //var socket = io("http://localhost:3000");//io("http://localhost:3002");

        // webSocket.onopen = function () {
        //     webSocket.send("skickar");
        // };
        //
        // webSocket.onerror = function () {
        //     console.log("Helvete! ");
        // };
        //
        // webSocket.onmessage = function (event) {
        //     var data = event.data;
        //     console.log("data: "+ data);
        // };


        $scope.chatBoard = [];


        $scope.sendMessage = function () {
            console.log("submittin form: " + $scope.chatText);
            var msgData = {text: $scope.chatText, from: userName, to: destName};
            //socket.emit('sendmessage', msgData);
            $scope.chatText = '';
        };

        $scope.remaining = function () {
            var count = 0;
            count += $scope.chatBoard.length;
            return count;
        };

        $scope.clearBoard = function () {
            $scope.chatBoard = [];
        };

        $scope.setUser = function () {
            userName = $scope.myUser;
            $scope.myUser = '';
        };

        $scope.setDestination = function () {
            destName = $scope.theDestination;
            $scope.theDestination = '';
        };

        $scope.regChatUser = function () {
            console.log("regChatUser " + userName);


            var msgData = {userName: userName};


            //socket.emit('regUser', msgData);
        };

        // socket.on('messageEcho', function (msg) {
        //     console.log('incoming: from: ' + msg.from + ' to: ' + msg.to +' text: ' + msg.text);
        //     // $scope.chatBoard.push({text:msg, done:false});
        //     $scope.chatBoard.push(msg);
        //     $scope.$apply();
        //
        //
        // });

    });