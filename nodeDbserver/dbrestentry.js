var http = require('http');
var WebSocket = require('websocket');

var webSocketPort = 8085;

var webSocketServer = WebSocket.server;

var httpServer = http.createServer( function (requset, response) {

});

httpServer.listen(webSocketPort, function () {
    console.log("httpServer listening: " + webSocketPort);
});

var wsServer = new webSocketServer({httpServer: httpServer});

var connectedUsers = {};
var groupMembers = {};

function getKeyFromSocket(socket) {
    var userKey;
    for(var a in connectedUsers) {
        console.log('disconnect: key = ' + a + ' value =  ' + connectedUsers[a]);
        if(connectedUsers[a] == socket) {
            userKey = a;
            break;
        }
    }
    return userKey;
}

var regUser = function (userId, userConn) {


        if (connectedUsers[userId]) {
            // already connected
            console.log('regUser: ' + userId + ' already registrated');
        } else {
            connectedUsers[userId] = userConn;
            console.log('regUser: ' + userId + ' registrated with socket: ' + userConn);
        }

};

var simpleSend = function (userId, data) {
    if (userId) {
        var sockTo = connectedUsers[userId];
        if (sockTo) {
            sockTo.sendUTF(JSON.stringify(data));
        }else {
            console.log("simpleSend failed sockTo == null: ");
        }
    }else {
        console.log("simpleSend no iser userId:" + userId);
    }

};

var sendMessageP2P = function (data) {
    console.log("sendMessageToUser: ");
    console.log('sendmessage: from: ' + data);

    simpleSend(data.fromId,data);
    simpleSend(data.toId,data);
};

var sendMessageGroup = function (data) {
    console.log('sendMessageGroup: ' + data);
    var groupId = data.groupId;
    if(!groupId) {
        console.log('sendMessageGroup: missing groupId');
        return false;
    }
    var memberList = groupMembers[groupId];
    if(memberList) {
        for( idx in memberList) {
            simpleSend(idx,data);
        }
    }else {
        console.log("sendMessageGroup no group:" + groupId );
    }


};

var disconnectUser = function (conn) {
    console.log('disconected = ' );
    console.log('disconnect: ' +connectedUsers );
    var userKey  = getKeyFromSocket(conn);
    if(userKey) {
        console.log('disconnect: user was = ' + userKey);
        connectedUsers[userKey] = null;
    }else {
        console.log('disconnect: no user found');
    }
};

wsServer.on('request',function (request) {
    console.log('user connected');

    var userConn = request.accept(null,request.origin);


    userConn.on('message',function (indata) {
        var datatest = JSON.stringify(indata);
        console.log('message = ' + indata + ' dataTest = ' + datatest);
        var data = JSON.parse(indata.utf8Data);

        console.log("onmessage: " + data);
        if (data && data.request) {
            console.log("onmessage req ok: ");
            var req = data.request;
            if (req == "register") {
                console.log("register: ");
                if (data.userId) {
                    regUser(data.userId, userConn);
                } else {
                    console.log('regUser:  bad message format' + regUser);
                }

            }
            if (req == "sendMessageToUser") {
                sendMessageP2P(data);
            }
            if (req == "sendMessageToGroup") {
                console.log("sendMessageToGroup: " );

                sendMessageGroup(data);
            }
            if (req == "getMessagesBetweenUsers") {
                console.log("getMessagesBetweenUsers: " );
            }
            if (req == "getMessagesByGroup") {
                console.log("getMessagesByGroup: " );
            }
        }
    });

    userConn.on('close',function (conn) {
        disconnectUser(conn);
    })

});



//
// io.on('connection', function (socket) {
//     // var address = socket.handshake.address;
//     // console.log('New connection: socket: ' + address.address + " port: " + address.port);
//     var address = socket.handshake.address;
//     var addr = socket.conn.remoteAddress;
//     console.log('New connection from ' + addr);
//     socket.on('regUser',function (regUser) {
//         if(regUser) {
//             var name = regUser.userName;
//
//             if(connectedUsers[name]) {
//                 // already connected
//                 console.log('regUser: ' + name + ' already registrated');
//             }else {
//                 connectedUsers[name] = socket;
//                 console.log('regUser: ' + name + ' registrated with socket: ' + socket);
//             }
//         } else {
//             console.log('regUser:  bad message format' + regUser );
//         }
//     })
//     socket.on('sendmessage', function (msg) {
//         console.log('sendmessage: from: ' + msg.from + ' to: ' + msg.to +' text: ' + msg.text);
//         // io.emit("messageEcho", msg);
//         var sockFrom = connectedUsers[msg.from];
//         var sockTo = connectedUsers[msg.to];
//
//
//         if(sockFrom) {
//             console.log('sendmessage: msg.from =  ' +sockFrom);
//             sockFrom.emit("messageEcho", msg);
//         }
//         if(sockTo) {
//             sockTo.emit("messageEcho", msg);
//         }
//     });
//
//     socket.on('disconnect', function() {
//         console.log('disconnect: ' +connectedUsers );
//         var userKey  = getKeyFromSocket(socket);
//         if(userKey) {
//             console.log('disconnect: user was = ' + userKey);
//             connectedUsers[userKey] = null;
//         }else {
//             console.log('disconnect: no user found');
//         }
//
//     });
// });

// var express = require('express');
// var messageDAO = require('./dbRequestHandler');
//
// var app = express();
// var fs = require("fs");
//
// app.get('/insertMessageToUser/:firstid/:secondid/:text', function (req, res) {
//    // First read existing users.
//    var user1 = req.params.firstid;
//    var user2 = req.params.secondid;
//    var textParm = req.params.text;
//
//    console.log( 'user1 = ' + user1 + " user2 = " + user2 +" text: " + textParm);
//    var data = messageDAO.insertMessageToUser(user1,user2,textParm, function (data) {
//        var result = JSON.stringify(data);
//        console.log( result );
//        res.end(result);
//    });
//
// })
// app.get('/getMessagesBetweenUsers/:firstid/:secondid', function (req, res) {
//    // First read existing users.
//    var user1 = req.params.firstid;
//    var user2 =req.params.secondid;
//    console.log( 'user1 = ' + user1 + " user2 = " + user2 );
//    var data = messageDAO.getMessagesBetweenUsers(user1,user2,function (data) {
//         var result = JSON.stringify(data);
//         console.log( result );
//         res.end(result);
//     });
//
// })
// var server = app.listen(8081, function () {
//
//   var host = server.address().address
//   var port = server.address().port
//   console.log("Example app listening at http://%s:%s", host, port)
//
// })