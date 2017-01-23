'use strict';
 
app.factory('ForumMemberService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/forumMember/';
 
    var factory = {
        fetchAllForumMembers: fetchAllForumMembers,
        createForumMember: createForumMember,
        deleteForumMember:deleteForumMember,
      
    };
 
    return factory;
 
    function fetchAllForumMembers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching members');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createForumMember(forumMember) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, forumMember)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating member');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    
    
 
    function deleteForumMember(forumMemberId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+forumMemberId)
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