var app = angular.module('communityApp', ['ui.router']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('appmain', {
            url: '/',
            views: {
                'navView': {
                    templateUrl: '/new_frontnedn/partials/navigation.html',
                    controller: 'homeAppController'
                },
                'content': {
                    templateUrl: '/new_frontnedn/partials/homePage.html',
                    controller: 'homeAppController'
                },
                'friends': {
                    templateUrl: '/new_frontnedn/partials/friends.html',
                    controller: 'friendAppController'
                }
            }
        })
        .state('appmain.homeNewsfeed', {
            url: 'home',
            views: {
                'content@': {
                    templateUrl: '/new_frontnedn/partials/homePage.html',
                    controller: 'homeAppController'
                }
            }
        }).state('appmain.makeFriends', {
        url: 'makeFriends',
        views: {
            'content@': {
                templateUrl: '/new_frontnedn/partials/makeFriends.html',
                controller: 'makeFriendAppController'
            }
        }
    }).state('appmain.profile', {
        url: 'profile',
        views: {
            'content@': {
                templateUrl: '/new_frontnedn/partials/profile.html',
                controller: 'profileAppController'
            }
        }
    }).state('appmain.profileEdit', {
        url: 'profileEdit',
        views: {
            'content@': {
                templateUrl: '/new_frontnedn/partials/profileEdit.html',
                controller: 'profileAppController'
            }
        }
    }).state('appmain.chat', {
        url: 'chat',
        views: {
            'content@': {
                templateUrl: '/new_frontnedn/partials/mychat.html',
                controller: 'ChatAppController'
            }
        }
    })

}]);