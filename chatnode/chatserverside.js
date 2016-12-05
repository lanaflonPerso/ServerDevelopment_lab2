/**
 * Created by o_0 on 2016-12-03.
 */
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);


io.on('connection', function (socket) {
    socket.on('sendmessage', function (msg) {
        console.log('sendmessage:' + msg);
        io.emit("messageEcho", msg);
    });
});

var port = 3000;
http.listen(port, function () {
    console.log('listening on port :' + port);
});