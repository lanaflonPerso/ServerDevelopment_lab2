/**
 * Created by o_0 on 2016-12-14.
 */
app.controller('profileAppController', function($scope,userFactory,profileService) {
    $scope.profileVM = {};
    $scope.newProfile;
    $scope.status = "";
    var loadProfile = function (userId) {
        var promise = profileService.getProfile(userId);
        promise.then(function (data) {
            console.log('loadFriends  data = ' +data);
            $scope.profileVM = data;
        });
    };

    var saveProfile = function () {
        if (userFactory.isLoggedin()) {
            var promise = profileService.saveProfile(userFactory.getUserId(),$scope.newProfile.name,$scope.newProfile.info,$scope.newProfile.age,$scope.newProfile.relationshipStatus);
            promise.then(function (data) {
                console.log('loadFriends  data = ' +data);
                $scope.status = "success";
            });
        }else {
            console.log('saveProfile  not loggedin');
        }

    };
    init();
    function init() {
        loadProfile(userFactory.getUserId());
    }
    $scope.saveProfile = saveProfile;
});