'use strict';
var app=angular.module('app',['ngRoute','ngResource','ngCookies','ngSanitize','textAngular','ngStorage']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'home.html',
         controller: 'LoginController',
         
     })

     .when('/blog', {
        controller: 'BlogController',
        controller: 'BlogCommentController',
         templateUrl: 'blog/BlogManagement.html',
        
     })
     
     .when('/user', {
        controller: 'UserController',
         templateUrl: 'user/UserManagement.html',
        
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