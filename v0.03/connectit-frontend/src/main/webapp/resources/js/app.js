'use strict';
var app=angular.module('app',['ngRoute','ngResource']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'home.html',
         
         
     })

     .when('/blog', {
        controller: 'BlogController',
         templateUrl: 'BlogManagement.html',
        
     })
     
     .when('/user', {
        controller: 'UserController',
         templateUrl: 'user.html',
        
     })
     
});