 /**
 * Created by o_0 on 2016-12-03.
 */
var io = require('socket.io')(http);
var app = require('express')();
var httpBack = require("http");
var http = require('http').Server(app);

var connectedUsers = {};

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

io.on('connection', function (socket) {
    // var address = socket.handshake.address;
    // console.log('New connection: socket: ' + address.address + " port: " + address.port);
    var address = socket.handshake.address;
    var addr = socket.conn.remoteAddress;
    console.log('New connection from ' + addr);
    socket.on('login',function (regUser) {
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
    socket.on('redirect', function (redirectData) {
        // var redirectData = {
        //     path:"services/userservice/",
        //     method:"userById",
        //     args:{id:1}
        // };
        console.log('redirect: userId: ' + redirectData.userId +' path: ' + redirectData.path + " method: " + redirectData.method + " args" + redirectData.args);
        var args = redirectData.args;
        var urlString = redirectData.path + redirectData.method;
        if (args) {

            var first = true;
            for(var key in args) {
                if(first) {
                    urlString += '?';
                    first = false;
                }else {
                    urlString += '&';
                }
                urlString += key + '=' + encodeURI(args[key]);
                console.log('test' + key + ' args:' + args[key]);

            }
        }

        console.log('urlString = ' +urlString);
        var callback = redirectData.callback;

        var options = {
            hostname: "localhost",
            port: 8090,
            path: '/' + urlString,
            method: redirectData.reqType
        };
        var req = httpBack.request(options, function(res) {

            var responseBode = ""

            console.log("Response from server started. ");
            console.log('Server Status:' + res.statusCode);
            console.log("Response Headers: %j", res.headers);

            res.setEncoding("UTF-8");

            res.once("data", function(chunk){
                console.log(chunk);
            });

            res.on("data", function(chunk){
                console.log('--chunk--' + chunk.length);
                responseBode += chunk;
            });

            res.on("end", function() {
                console.log("Response completed: " + responseBode);
                // socket.emit('redirectResp',responseBode);
                socket.emit(callback,responseBode);


            });

        });
        req.on("error", function(err) {
            console.log('problem with request:' + err.message);

        });

        req.end();
    });

    socket.on('disconnect', function() {
        console.log('disconnect: ' +connectedUsers );
        var userKey  = getKeyFromSocket(socket);
        if(userKey) {
            console.log('disconnect: user was = ' + userKey);
            connectedUsers[userKey] = null;
        }else {
            console.log('disconnect: no user found');
        }

    });
});

var port = 3500;
http.listen(port, function () {
    console.log('listening on port :' + port);
});