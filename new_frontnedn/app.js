var app = angular.module('communityApp', ['ui.router']);

app.config(['$stateProvider', '$urlRouterProvider', function ($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state('appmain', {
            url: '/',
            views: {
                'navView': {
                    templateUrl: '/new_frontnedn/partials/navigation.html',
                    controller: 'RestAppController'
                },
                'content': {
                    templateUrl: '/new_frontnedn/partials/myrestt.html',
                    controller: 'RestAppController'
                },
                'friends': {
                    templateUrl: '/new_frontnedn/partials/friends.html',
                    controller: 'friendAppController'
                }
            }
        })
        .state('appmain.resthome', {
            url: 'rest',
            views: {
                'content@': {
                    templateUrl: '/new_frontnedn/partials/myrestt.html',
                    controller: 'RestAppController'
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