var express = require('express');
var messageDAO = require('./dbRequestHandler');

var app = express();
var fs = require("fs");

app.get('/insertMessageToUser/:firstid/:secondid/:text', function (req, res) {
   // First read existing users.
   var user1 = req.params.firstid;
   var user2 = req.params.secondid;
   var textParm = req.params.text;
   
   console.log( 'user1 = ' + user1 + " user2 = " + user2 +" text: " + textParm);
   var data = messageDAO.insertMessageToUser(user1,user2,textParm, function (data) {
       var result = JSON.stringify(data);
       console.log( result );
       res.end(result);
   });

})
app.get('/getMessagesBetweenUsers/:firstid/:secondid', function (req, res) {
   // First read existing users.
   var user1 = req.params.firstid;
   var user2 =req.params.secondid;
   console.log( 'user1 = ' + user1 + " user2 = " + user2 );
   var data = messageDAO.getMessagesBetweenUsers(user1,user2,function (data) {
        var result = JSON.stringify(data);
        console.log( result );
        res.end(result);
    });

})
var server = app.listen(8081, function () {

  var host = server.address().address
  var port = server.address().port
  console.log("Example app listening at http://%s:%s", host, port)

})