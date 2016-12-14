/**
 * Created by o_0 on 2016-12-14.
 */
app.controller('profileAppController', function($scope,userFactory,restFactory) {
    $scope.profileVM = {};
    var loadProfile = function (userId) {
        var promise = restFactory.getProfile(userId);
        promise.then(function (data) {
            console.log('loadFriends  data = ' +data);
            $scope.profileVM = data;
        });
    };
    init();
    function init() {
        loadProfile(userFactory.getUserId());
    }

});