/**
 * Created by o_0 on 2016-12-05.
 */

var userName = 'dude';
var destName = 'bob';

app
    .controller('ChatAppController', function ($scope, userFactory, friendService) {
        var chatBoard = [];
        $scope.selectedChatUserId = 0;
        $scope.selectedChatUserName = "";
        $scope.selectedType = false;
        $scope.newGroupName = "";
        $scope.friendList = [];
        // test data , load from backend in init
        $scope.groupList = [];
        //var socket = io("http://localhost:3000");//io("http://localhost:3002");


        var host = "ws://localhost:8085/chatserver";
        var wSocket = new WebSocket(host);

        var loadFriends = function () {
            var promise = friendService.getFriendsByUserId(userFactory.getUserId());
            promise.then(function (data) {
                console.log('chat loadFriends  data = ' + data);

                for (a in data) {
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

        wSocket.onopen = function () {
            console.log(" Web Socket is connected, sending data");
            var req = {request: "register", userId: userFactory.getUserId()};
            var data = JSON.stringify(req);
            wSocket.send(data);
            getGroups();


        };

        wSocket.onerror = function () {
            alert("Fel!");
        };

        wSocket.onmessage = function (event) {
            var data = JSON.parse(event.data);

            console.log("onmessage: " + data);
            if(data && data.request) {
                console.log("onmessage req ok: = " + data.request);
                var req = data.request;
                if (req == "sendMessageToUser") {
                    $scope.chatBoard.push(data);
                    $scope.$apply();
                }
                if (req == "sendMessageToGroup") {
                    $scope.chatBoard.push(data);
                    $scope.$apply();
                }
                if (req == "getMessagesBetweenUsers") {
                    $scope.chatBoard = [];
                    $scope.chatBoard = data.response;
                    $scope.$apply();
                }
                if (req == "getMessagesByGroup") {
                    $scope.chatBoard = [];
                    $scope.chatBoard = data.response;
                    $scope.$apply();
                }
                if (req == "getGroups") {
                    $scope.groupList = [];
                    $scope.groupList = data.response;
                    $scope.$apply();
                }
            }else {
                console.log("onmessage: failed to find handler " + data);
            }


        };


        // fixa json som f;rutom data 'ven skickar vilken fiunktion som ;nskas
        var getMessagesBetweenUsers = function () {
            // Request for all messages between users
            var req = {request: "getMessagesBetweenUsers", fromId: userFactory.getUserId(), toId: $scope.selectedChatUserId};
            var data = JSON.stringify(req);
            wSocket.send(data);
        };


        var joinGroup = function () {
            // Request for all messages between users
            var req = {request: "joinGroup", groupId: $scope.selectedChatUserId, groupName: $scope.selectedChatUserName, userId: userFactory.getUserId()};
            var data = JSON.stringify(req);
            wSocket.send(data);
        };

        var getMessagesByGroup = function () {
            // Request for all messages between users
            var req = {request: "getMessagesByGroup",fromId:userFactory.getUserId(), groupId: $scope.selectedChatUserId };
            var data = JSON.stringify(req);
            wSocket.send(data);
        };

        var getGroups = function () {
            // Request for all messages between users
            var req = {request: "getGroups",fromId:userFactory.getUserId()};
            var data = JSON.stringify(req);
            wSocket.send(data);
        };








        $scope.setChatTarget = function (selectedId, selectedName, isGroup) {
            $scope.selectedChatUserId = selectedId;
            $scope.selectedChatUserName = selectedName;
            $scope.selectedType = isGroup;
            console.log('setChatTarget  id = ' + selectedId + ' name = ' + selectedName + ' isgroupe = ' + isGroup);
            if (isGroup) {
                joinGroup();
                getMessagesByGroup();
            }else {
                getMessagesBetweenUsers();
            }
        }


        /// to get this current user id,
        // userFactory.getUserId();

        $scope.chatBoard = [];


        $scope.sendMessage = function () {
            console.log("submitting form: " + $scope.chatText);
            var req = {};

            if (!$scope.selectedType) {
                console.log("sendMessageToUser msg: " + $scope.chatText);
                req = {
                    request: "sendMessageToUser",
                    fromId: userFactory.getUserId(),
                    fromName: userFactory.getUserName(),
                    toId: $scope.selectedChatUserId,
                    toName: $scope.selectedChatUserName,
                    text: $scope.chatText
                };
            } else {
                console.log("sendMessageToGroup msg: " + $scope.chatText);
                req = {
                    request: "sendMessageToGroup",
                    fromId: userFactory.getUserId(),
                    fromName: userFactory.getUserName(),
                    groupId: $scope.selectedChatUserId,
                    toName: $scope.selectedChatUserName,
                    text: $scope.chatText
                };
            }

            var data = JSON.stringify(req);
            wSocket.send(data);

            $scope.chatText = '';
        };


        $scope.createGroup = function () {
            var req = {request:"joinGroup", groupName: $scope.newGroupName, userId: userFactory.getUserId()}
            console.log("submitting form groupName: " + $scope.newGroupName);
            var data = JSON.stringify(req);
            wSocket.send(data);

            $scope.newGroupName = '';
        };

        $scope.remaining = function () {
            var count = 0;
            count += $scope.chatBoard.length;
            return count;
        };

        $scope.clearBoard = function () {
            $scope.chatBoard = [];
        };


        //////// remove later
        $scope.setUser = function () {
            userName = $scope.myUser;
            $scope.myUser = '';
        };

        $scope.setDestination = function () {
            destName = $scope.theDestination;
            $scope.theDestination = '';
        };




    });