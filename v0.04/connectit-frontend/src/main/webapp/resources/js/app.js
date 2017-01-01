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
     
});