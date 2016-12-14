/**
 * Created by o_0 on 2016-12-14.
 */


app.controller('friendAppController', function($scope,$rootScope,userFactory,friendService) {
    $scope.friendlist = [];
    $scope.nonFriends = [];

    var loadFriends = function () {
        var promise = friendService.getFriendsByUserId(userFactory.getUserId());
        promise.then(function (data) {
            console.log('loadFriends  data = ' +data);

            for(a in data) {
                console.log('loadFriends  a = ' + a);
                $scope.friendlist.push(data[a]);
            }
        });
    };
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
        loadFriends();
    }
    $scope.remaining = function () {
        var count = 0;
        count += $scope.friendlist.length;
        return count;
    };
    $scope.loadFriends = loadFriends;

    $scope.selectFriend = function (selected) {
        console.log('selectFriend  id = ' + selected);
        userFactory.setSelectedUserId(selected);
        $rootScope.$broadcast('profileUpdate',' friend selected' + selected);
    };

});