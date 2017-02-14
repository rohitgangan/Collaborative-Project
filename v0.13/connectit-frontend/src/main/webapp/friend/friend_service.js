'use strict';
 
app.factory('FriendService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/';
 
    var factory = {
        sendFriendRequest: sendFriendRequest,
        getAllFriendRequest: getAllFriendRequest,
        getAllFriends: getAllFriends,
        acceptFriendRequest:acceptFriendRequest,
        rejectFriendRequest:rejectFriendRequest,
        listFriends:listFriends
    };
 
    return factory;
    
    function sendFriendRequest(friend, friendId) {
    	 var deferred = $q.defer();
         $http.post(REST_SERVICE_URI+'sendFriendRequest/'+friendId, friend)
             .then(
             function (response) {
                 deferred.resolve(response.data);
             },
             function(errResponse){
                 console.error('Error while sending friend request');
                 deferred.reject(errResponse);
             }
         );
         return deferred.promise;     
    }
    
    function getAllFriendRequest(){
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'getAllFriendRequest')
    	.then(
    	function(response) {
			deferred.resolve(response.data);
		},
		function(errResponse){
			console.error("Error while fetching friend request");
			deferred.reject(errResponse);
		});
    	return deferred.promise;
    }
    
    function listFriends(){
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'friend')
    	.then(
    	function(response) {
			deferred.resolve(response.data);
		},
		function(errResponse){
			console.error("Error while fetching friends table");
			deferred.reject(errResponse);
		});
    	return deferred.promise;
    }
    
    function getAllFriends(){
    	var deferred = $q.defer();
    	$http.get(REST_SERVICE_URI+'friends')
    	.then(
    	function(response) {
			deferred.resolve(response.data);
		},
		function(errResponse){
			console.error("Error while fetching friends");
			deferred.reject(errResponse);
		});
    	return deferred.promise;
    }
    
    function acceptFriendRequest(userId){
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'acceptFriendRequest/'+userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while accepting friend request');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function rejectFriendRequest(userId){
    	var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+'rejectFriendRequest/'+userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while rejecting friend request');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
		
}]);