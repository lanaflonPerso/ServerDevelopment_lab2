/**
 * Created by o_0 on 2016-12-14.
 */
app.controller('profileAppController', function($scope,userFactory,profileService,postService) {
    $scope.profileVM = {};
    $scope.newProfile;
    $scope.status = "";
    $scope.postFeed = [];
    
    var loadProfile = function (userId) {
        var promise = profileService.getProfile(userId);
        promise.then(function (data) {
            console.log('loadFriends  data = ' +data);
            $scope.profileVM = data;
        });
    };

    var loadProfileFeed = function (selectedProfile) {

        var promise = postService.getProfileposts(selectedProfile,userFactory.getUserId());
        promise.then(function (data) {
            console.log('loadProfileFeed  data = ' +data);
            $scope.postFeed = data;
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
        // loadProfile(userFactory.getUserId());
        // loadProfileFeed(userFactory.getUserId());
        loadProfile(userFactory.getSelectedUserId());
        loadProfileFeed(userFactory.getSelectedUserId());
    }
    function update() {
        // loadProfile(userFactory.getUserId());
        // loadProfileFeed(userFactory.getUserId());
        loadProfile(userFactory.getSelectedUserId());
        loadProfileFeed(userFactory.getSelectedUserId());
    }
    $scope.$on('profileUpdate',function (event, arg) {
        console.log('On : profileUpdate  args = ' + arg);
        update();
    })
    
    $scope.saveProfile = saveProfile;

    $scope.remaining = function () {
        var count = 0;
        count += $scope.postFeed.length;
        return count;
    };
});