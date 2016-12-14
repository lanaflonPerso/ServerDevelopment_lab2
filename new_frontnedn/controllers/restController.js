/**
 * Created by o_0 on 2016-12-05.
 */
var addr = "http://localhost:3500/";

app.controller('RestAppController', function($scope,userService) {

    init();
    function init() {
        $scope.mydata;
        $scope.mydataarr = [];
        console.log('init func: ')
        var promise = userService.getUserById(1);
        promise.then(function (data) {
            $scope.mydata = data;
            $scope.mydataarr.push(data);
        });
    }


    $scope.refresh = function () {
        console.log('refresh func: ')
        var promise = userService.getUserById(1);
        promise.then(function (data) {
            $scope.mydata = data;
            $scope.mydataarr.push(data);
        });
    };

});