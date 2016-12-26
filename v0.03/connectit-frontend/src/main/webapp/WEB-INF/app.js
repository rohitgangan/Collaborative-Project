'use strict';
var app=angular.module('app',['ngRoute','ngResource']);

app.config(function($routeProvider){
	$routeProvider
	
    .when('/', {
         
         templateUrl: 'resources/js/home.jsp',
         
         
     })

     .when('/blog', {
        controller: 'BlogController',
         templateUrl: 'resources/js/blog.jsp',
        
     })
     
});