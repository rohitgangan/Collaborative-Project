'use strict';
var app=angular.module('app',['ngRoute','ngResource']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'home.jsp',
         
         
     })

     .when('/blog', {
        controller: 'BlogController',
         templateUrl: 'blog.jsp',
        
     })
     
});