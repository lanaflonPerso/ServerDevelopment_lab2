/**
 * Created by o_0 on 2016-12-05.
 */

var userName = 'dude';
var destName = 'bob';

app
    .controller('ChatAppController', function($scope) {
        var chatBoard = [{text:'welcome to chat',from:userName,to:destName}];
        var socket = io("http://localhost:3000");//io("http://localhost:3002");
        $scope.chatBoard = [];


        $scope.sendMessage = function () {
            console.log("submittin form: " + $scope.chatText);
            var msgData = {text:$scope.chatText,from:userName,to:destName};
            socket.emit('sendmessage', msgData);
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
            var msgData = {userName:userName};
            socket.emit('regUser', msgData);
        };

        socket.on('messageEcho', function (msg) {
            console.log('incoming: from: ' + msg.from + ' to: ' + msg.to +' text: ' + msg.text);
            // $scope.chatBoard.push({text:msg, done:false});
            $scope.chatBoard.push(msg);
            $scope.$apply();


        });

    });