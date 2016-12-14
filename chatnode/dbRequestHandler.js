/**
 * Created by cj on 2016-12-13.
 */

var mysql = require("mysql");

var connection = mysql.createConnection({

    host: "localhost",
    user: "serverutv",
    password: "!Qaz2wsx",
    database: "communityDB"
});

connection.connect(function (error) {
    if (!!error) {
        console.log("Error when connecting to DB");
    } else {
        console.log("Connected to DB");
    }

});


module.exports = {

    getGroups: function (id) {
        var groups = []
        var query = "SELECT * FROM Group";

        connection.query(query, function (error, rows, fields) {
            if (!!error) {
                console.log("Error in query, %j", error.message);

            } else {

                for (var i = rows.length - 1; i >= 0; i--) {
                    var rId =  rows[i].id;
                    var rName =  rows[i].name;

                    var group = {id:rId, name: rName};

                    groups.push(group);
                }
                ;
                return groups;
            }

        });
    },


    getMessagesByGroup: function (id) {
        var messages = [];
        var query = "SELECT * FROM MESSAGE WHERE groupId=" + id;

        connection.query(query, function (error, rows, fields) {
            if (!!error) {
                console.log("Error in query, %j", error.message);
            } else {
                for (var i = rows.length - 1; i >= 0; i--) {
                    var rId =  rows[i].id;
                    var rFromId =  rows[i].fromId;
                    var rGroupId = rows[i].groupId;
                    var rText = rows[i].text;
                    var rTimestamp = rows[i].timestamp;

                    var msg = { id: rId, fromId: rFromId, groupId: rGroupId, text: rText, timestamp: rTimestamp};

                    messages.push(msg);
                }
                ;
                return messages;
            }

        });
    },

    insertMessageToUser: function (fromId, toId, text) {
        var query = "INSERT INTO Message (fromId,toId,text) VALUES (" + fromId + "," + toId + "," + text + ")";

        connection.query(query, function (error, rows, fields) {
            if (!!error) {
                console.log("Error in query, %j", error.message);

            } else {
                console.log(`success`);
            }
        });
    },

    insertMessageToGroup: function (fromId, groupId, text) {
        var query = "INSERT INTO Message (fromId,groupId,text) VALUES (" + fromId + "," + groupId + "," + text + ")";
        connection.query(query, function (error, rows, fields) {
            if (!!error) {
                console.log("Error in query, %j", error.message);

            } else {
                console.log(`success`);
            }
        });
    },


    getMessagesBetweenUsers: function (fromId,toId) {

        var messages = [];
        var query = "SELECT * FROM MESSAGE WHERE (fromId =" + fromId + " and toId ="+toId+") or (fromId =" + toId + " and toId ="+fromId+")";

        connection.query(query, function (error, rows, fields) {
            if (!!error) {
                console.log("Error in query, %j", error.message);

            } else {
                for (var i = rows.length - 1; i >= 0; i--) {

                    var rId =  rows[i].id;
                    var rFromId =  rows[i].fromId;
                    var rToId = rows[i].toId;
                    var rText = rows[i].text;
                    var rTimestamp = rows[i].timestamp;

                    var msg = { id: rId, fromId: rFromId, toId: rToId, text: rText, timestamp: rTimestamp};

                    messages.push(msg);
                };
            }

             messages.sort(function (a, b) {
                if (a.timestamp > b.timestamp) {
                    return -1;
                }
                if (a.timestamp < b.timestamp) {
                    return 1;
                }
                // a must be equal to b
                return 0;
            });

             return messages

        });
    },
};
















