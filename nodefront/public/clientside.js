/**
 * Created by o_0 on 2016-12-03.
 */

var socket = io();
document.getElementById("chatForm").onsubmit = function () {

    var input = document.getElementById('message');
    console.log("submittin form: " + input.value);
    socket.emit('sendmessage', input.value);
    input.value = '';
};

socket.on('messageEcho', function (msg) {
    console.log('incoming msg: ' + msg);
    var output = document.getElementById('dispmsg');
    var disp = document.createElement('disp');
    disp.innerText = "\n" + msg;
    output.appendChild(disp);

});