var messageDAO = require('./dbRequestHandler');
var async = require('async');


// //var messagesGrp1 = messageDAO.getMessagesByGroup(1);
// Promise.[
//     messageDAO.getMessagesBetweenUsers(1,3)
// ])
//     .then((data) => console.log(data))
// .catch((err) => console.log(err))

//
// async.parallel(messageDAO.getMessagesBetweenUsers(1,3), function (err, results) {
//     // results is now an array of stats for each file
//     str = JSON.stringify(results,null, 4);
//     console.log(`Super result: ${str}`);
// });


var messagesUsers1 = messageDAO.getMessagesBetweenUsers(1,3);



//var messagesUsers3 = messageDAO.getMessagesBetweenUsers(3,1);

