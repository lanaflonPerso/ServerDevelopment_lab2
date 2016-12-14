/**
 * Created by o_0 on 2016-12-14.
 */


app.controller('friendAppController', function($scope,userFactory,restFactory) {
    $scope.friendlist = [];
    var loadFriends = function () {
        var promise = restFactory.getFriendsByUserId(userFactory.getUserId());
        promise.then(function (data) {
            console.log('loadFriends  data = ' +data);

            for(a in data) {
                console.log('loadFriends  a = ' + a);
                $scope.friendlist.push(data[a]);
            }
        });
    };
    init();
    function init() {
        loadFriends();
    }
    $scope.remaining = function () {
        var count = 0;
        count += $scope.friendlist.length;
        return count;
    };
    $scope.loadFriends = loadFriends;

});