/**
 * Created by o_0 on 2016-12-05.
 */
var addr = "http://localhost:3500/";

app.controller('homeAppController', function($scope, userFactory,postService) {

    $scope.newsFeed = [];
    var getNewsFeed = function () {
        var promise = postService.getNewsFeed(userFactory.getUserId());
        promise.then(function (data) {
            console.log("getNewsFeed" + data);
            $scope.newsFeed = data;
        });
    }
    init();
    function init() {
            getNewsFeed();
    }

    $scope.getNewsFeed = getNewsFeed;

});