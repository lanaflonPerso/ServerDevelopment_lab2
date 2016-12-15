/**
 * Created by o_0 on 2016-12-05.
 */

var userName = 'dude';
var destName = 'bob';

app
    .controller('ChatAppController', function($scope,userFactory,friendService) {
        var chatBoard = [{text:'welcome to chat',from:userName,to:destName}];
        var socket = io("http://localhost:3000");//io("http://localhost:3002");


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















        $scope.selectedChatUserId = 0;
        $scope.selectedChatUserName = 0;
        $scope.selectedType = 0;

        $scope.friendList = [];
        // test data , load from backend in init
        $scope.groupList = [{id:5,profileId:0,email:"gruppNamn"}];

        var loadFriends = function () {
            var promise = friendService.getFriendsByUserId(userFactory.getUserId());
            promise.then(function (data) {
                console.log('chat loadFriends  data = ' +data);

                for(a in data) {
                    console.log('chat  a = ' + a + ' data = ' + data[a]);
                    $scope.friendList.push(data[a]);
                }
            });
        };

        init();
        function init() {
            // only to give defult value, otherwise get selectedChatUserFrom scope
            $scope.selectedChatUser = userFactory.getSelectedUserId();
            loadFriends();
        }

        $scope.setChatTarget = function (selectedId, selectedName, isGroup) {
            $scope.selectedChatUserId = selectedId;
            $scope.selectedChatUserName = selectedName;
            $scope.selectedType = isGroup;
            console.log('setChatTarget  id = ' +selectedId + ' name = ' + selectedName + ' isgroupe = ' + isGroup);
        }


        /// to get this current user id,
        // userFactory.getUserId();

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