/**
 * Created by o_0 on 2016-12-14.
 */


/**
 * Created by o_0 on 2016-12-14.
 */


app.controller('makeFriendAppController', function($scope,userFactory,friendService) {
    $scope.nonFriends = [];

    var loadNonFriends = function () {
        var promise = friendService.getNonFriendsByUserId(userFactory.getUserId());
        promise.then(function (data) {
            console.log('loadFriends  data = ' +data);

            for(a in data) {
                console.log('loadFriends  a = ' + a);
                $scope.nonFriends.push(data[a]);
            }
        });
    };

    init();
    function init() {
        loadNonFriends();
    }
    $scope.remaining = function () {
        var count = 0;
        count += $scope.nonFriends.length;
        return count;
    };
    $scope.loadNonFriends = loadNonFriends;
    $scope.makeFriend = function (newFriendId) {
        console.log('makeFriend  friendId = ' + newFriendId);
    }

});