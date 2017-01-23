'use strict';
 
app.factory('FriendService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/';
 
    var factory = {
        sendFriendRequest: sendFriendRequest
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
		
}]);