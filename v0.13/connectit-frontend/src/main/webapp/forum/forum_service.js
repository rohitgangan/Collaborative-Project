'use strict';
 
app.factory('ForumService', ['$http', '$q' ,function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/forum/';
    var FORUM_SERVICE_URI = 'http://localhost:8082/connectit/forums/';
 
    var factory = {
        fetchAllForums: fetchAllForums,
        fetchForum:fetchForum,
        createForum: createForum,
        updateForum:updateForum,
        deleteForum:deleteForum,
        fetchUserForums:fetchUserForums
    };
 
    return factory;
 
    function fetchAllForums() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Forums');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchUserForums() {
        var deferred = $q.defer();
        $http.get(FORUM_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Forums');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchForum(forumId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+forumId)
            .then(
            function (response) {
                
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching particular Forum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createForum(forum) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, forum)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Forum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    	
 
    function updateForum(forum, forumId) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+forumId, forum)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Forum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteForum(forumId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+forumId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Forum');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
 
}]);