'use strict';
var app=angular.module('app',['ngRoute','ngResource','ngCookies','ngSanitize','textAngular','ngStorage','ui.bootstrap','chatApp.controllers','chatApp.services']);
angular.module('chatApp.controllers', []);
angular.module('chatApp.services', []);

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
      .when('/job',{
    	 controller: 'JobController',
         templateUrl: 'job/JobManagement.html', 
     })
      .when('/jobs',{
    	 controller: 'JobController',
         templateUrl: 'job/AllJobs.html', 
     })
     .when('/jobApplications',{
    	 controller: 'JobController',
         templateUrl: 'job/JobApplications.html', 
     })
     .when('/event',{
    	 controller: 'EventController',
         templateUrl: 'event/EventManagement.html', 
     })
      .when('/chat',{
    	 controller: 'ChatController',
         templateUrl: 'chat/ChatManagement.html', 
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