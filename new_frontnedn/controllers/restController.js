/**
 * Created by o_0 on 2016-12-05.
 */
var addr = "http://localhost:3500/";

app.controller('RestAppController', function($scope,restFactory) {

    init();
    function init() {
        $scope.mydata;
        $scope.mydataarr = [];
        console.log('init func: ')
        var promise = restFactory.getUserById(1);
        promise.then(function (data) {
            $scope.mydata = data;
            $scope.mydataarr.push(data);
        });
    }


    $scope.refresh = function () {
        console.log('refresh func: ')
        var promise = restFactory.getUserById(1);
        promise.then(function (data) {
            $scope.mydata = data;
            $scope.mydataarr.push(data);
        });
    };

        // var socket = io(addr);
        // $scope.mydata;
        // $scope.mydataarr = [];
        // $scope.remaining = function () {
        //     var count = 0;
        //     count += $scope.mydataarr.length;
        //     return count;
        // };
        //
        // var redirectData = {
        //     userId:55,
        //     reqType:"POST",
        //     path:"services/userservice/",
        //     method:"userById",
        //     args:{id:1},
        //     callback:"redirectResp"
        // };
        // socket.emit("redirect",redirectData);
        // var back = "services/userservice/userById?id=1";
        // socket.on('redirectResp', function (msg) {
        //     var res = JSON.parse(msg);
        //     rom: id ' + res.id + ' profileId: ' + res.profileId +'  email: ' + res.email + " " );
        //     // chatList.chatBoard.push({text:msg, done:false});
        //
        //     $scope.mydata = res;
        //     $scope.mydata  = res;
        //     $scope.mydataarr.push(res);
        //     $scope.$apply();
        //
        //
        // });
    });