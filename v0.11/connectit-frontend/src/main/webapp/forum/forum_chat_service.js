'use strict';
 
app.factory('ForumChatService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/forumChat/';
 
    var factory = {
        fetchAllForumChats: fetchAllForumChats,
        createForumChat: createForumChat,
        deleteForumChat:deleteForumChat,
      
    };
 
    return factory;
 
    function fetchAllForumChats() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Chats');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createForumChat(chatForum) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, chatForum)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating Chat');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    
    
 
    function deleteForumChat(chatForumId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+chatForumId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting chat');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
 
}]);