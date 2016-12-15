/**
 * Created by o_0 on 2016-12-15.
 */
app.controller('loginAppController', function($scope,$rootScope, userFactory,userService) {

    $scope.userLogin = {};
    $scope.status = "";
    $scope.myHomePage = "";
    $scope.login = function () {
        $scope.status = "";
        console.log("loginAppController :login" + $scope.userLogin.name + " pass: " + $scope.userLogin.pass);
        var promise = userService.loginUser($scope.userLogin.name, $scope.userLogin.pass);
        $scope.userLogin.name = "";
        $scope.userLogin.pass = "";
        promise.then(function (data) {
            console.log("user promise: data = " + data);
            if (!data || !data.id)
            {
                $scope.status = " Failed Try again";
            }else {
                userFactory.setUser(data);
                $scope.status = " you can enter now";
                $scope.myHomePage = "appmain";

                //$rootScope.$broadcast('profileUpdate',' friend selected' + userFactory.getUserId());
            }

        });
    }


});