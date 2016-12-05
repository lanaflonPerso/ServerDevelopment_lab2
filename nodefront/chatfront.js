/**
 * Created by o_0 on 2016-12-03.
 */
var app = require('express')();
var http = require('http').Server(app);
var io = require('socket.io')(http);

app.get('/', function (req, res) {
    res.sendFile(__dirname + '/public/index.html');
});

app.get('/chat', function (req, res) {
    res.sendFile(__dirname + '/public/chatstuff.html');
});

app.get('/chat/clientside.js', function (req, res) {
    res.sendFile(__dirname + '/public/clientside.js');
});

io.on('connection', function (socket) {
    socket.on('sendmessage', function (msg) {
        io.emit("messageEcho", msg);
    });
});

var port = 3000;
http.listen(port, function () {
    console.log('listening on port :' + port);
});