'use strict';
 
app.factory('BlogLikeService', ['$http', '$q', function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/blogLike/';
 
    var factory = {
        fetchAllBlogLikes: fetchAllBlogLikes,
        createBlogLike: createBlogLike,
        deleteBlogLike:deleteBlogLike,
      
    };
 
    return factory;
 
    function fetchAllBlogLikes() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Likes');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createBlogLike(blogLike) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, blogLike)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating like');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    
    
 
    function deleteBlogLike(blogLikeId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+blogLikeId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting like');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
 
}]);