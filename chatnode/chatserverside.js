/**
 * Created by o_0 on 2016-12-03.
 */
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

var connectedUsers = {};
io.on('connection', function (socket) {
    // var address = socket.handshake.address;
    // console.log('New connection: socket: ' + address.address + " port: " + address.port);
    var address = socket.handshake.address;
    var addr = socket.conn.remoteAddress;
    console.log('New connection from ' + addr);
    socket.on('regUser',function (regUser) {
        if(regUser) {
            var name = regUser.userName;

            if(connectedUsers[name]) {
                // already connected
                console.log('regUser: ' + name + ' already registrated');
            }else {
                connectedUsers[name] = socket;
                console.log('regUser: ' + name + ' registrated with socket: ' + socket);
            }
        } else {
            console.log('regUser:  bad message format' + regUser );
        }
    })
    socket.on('sendmessage', function (msg) {
        console.log('sendmessage: from: ' + msg.from + ' to: ' + msg.to +' text: ' + msg.text);
        // io.emit("messageEcho", msg);
        var sockFrom = connectedUsers[msg.from];
        var sockTo = connectedUsers[msg.to];
        if(sockFrom) {
            console.log('sendmessage: msg.from =  ' +sockFrom);
            sockFrom.emit("messageEcho", msg);
        }
        if(sockTo) {
            sockTo.emit("messageEcho", msg);
        }
    });

    socket.on('disconnect', function() {
        console.log('disconnect: ' +connectedUsers );
        var userKey = '';
        for(var a in connectedUsers) {
            console.log('disconnect: key = ' + a + ' value =  ' + connectedUsers[a]);
            if(connectedUsers[a] == socket) {
                userKey = a;
                break;
            }
        }
        if(userKey.length > 0) {
            console.log('disconnect: user was = ' + userKey);
            connectedUsers[userKey] = null;
        }else {
            console.log('disconnect: no user found');
        }

    });
});

var port = 3000;
http.listen(port, function () {
    console.log('listening on port :' + port);
});