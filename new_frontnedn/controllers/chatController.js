/**
 * Created by o_0 on 2016-12-05.
 */

var userName = 'dude';
var destName = 'bob';

app
    .controller('ChatAppController', function ($scope) {
        var chatBoard = [{text: 'welcome to chat', from: userName, to: destName}];

        // var webSocket = new WebSocket("http://localhost:3000/chat");
        var host = "ws://localhost:8085/myapp";
        var wSocket = new WebSocket(host);


        wSocket.onopen = function () {
            alert(" Web Socket is connected, sending data");
            wSocket.send("hello you bastard");

        };


        wSocket.onerror = function () {
            alert("Fel!");
        };

        wSocket.onmessage = function (event) {
            var data = event.data;
            console.log("data: "+ data);
        };



        $scope.qwerty = function () {


            wSocket.send("qwerty");
        };

        function registerHandlerForUpdateCurrentPriceAndFeed() {

        };

        function bid() {
            var newPrice = document.getElementById('my_bid_value').value;

            var xmlhttp = (window.XMLHttpRequest) ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == 4) {
                    if (xmlhttp.status != 200) {
                        document.getElementById('error_message').innerHTML = 'Sorry, something went wrong.';
                    }
                }
            };
            xmlhttp.open("PATCH", "http://localhost:8080/api/auctions/" + auction_id);
            xmlhttp.setRequestHeader("Content-Type", "application/json");
            xmlhttp.send(JSON.stringify({price: newPrice}));
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