'use strict';
var app=angular.module('app',['ngRoute','ngResource','ngCookies','ngSanitize','textAngular','ngStorage','ui.bootstrap']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'home.html',
         controller: 'LoginController',
         
     })

     .when('/blog', {
        controller: 'BlogController',
        controller: 'BlogCommentController',
        controller: 'BlogLikeController',
         templateUrl: 'blog/BlogManagement.html',
        
     })
     
      .when('/forum', {
        controller: 'ForumController',
        controller: 'ForumChatController',
        controller: 'ForumMemberController',
         templateUrl: 'forum/ForumManagement.html',
        
     })
     
     .when('/user', {
        controller: 'UserController',
         templateUrl: 'user/UserManagement.html',
        
     })
     .when('/viewUser', {
        controller: 'UserController',
        controller: 'FriendController',
         templateUrl: 'user/ViewUser.html',
        
     })
     .when('/login',{
    	 controller: 'LoginController',
         templateUrl: 'login.html', 
     })
     .when('/home',{
    	 controller: 'LoginController',
         templateUrl: 'user/UserHome.html', 
     })
     
});

app.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function(scope, element, attrs) {
            var model = $parse(attrs.fileModel);
            var modelSetter = model.assign;
            
            element.bind('change', function(){
                scope.$apply(function(){
                    modelSetter(scope, element[0].files[0]);
                });
            });
        }
    };
}]);