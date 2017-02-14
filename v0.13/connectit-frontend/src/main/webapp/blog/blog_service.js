'use strict';
 
app.factory('BlogService', ['$http', '$q' ,function($http, $q){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/blog/';
    var BLOG_SERVICE_URI = 'http://localhost:8082/connectit/blogs/';
 
    var factory = {
        fetchAllBlogs: fetchAllBlogs,
        fetchBlog:fetchBlog,
        createBlog: createBlog,
        updateBlog:updateBlog,
        deleteBlog:deleteBlog,
        fetchUserBlogs:fetchUserBlogs
    };
 
    return factory;
 
    function fetchAllBlogs() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching blogs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchUserBlogs() {
        var deferred = $q.defer();
        $http.get(BLOG_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching user blogs');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchBlog(blogId) {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+blogId)
            .then(
            function (response) {
                
            	deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching particular blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createBlog(blog) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, blog)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while creating User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    	
 
    function updateBlog(blog, blogId) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+blogId, blog)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating Blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function deleteBlog(blogId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+blogId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting Blog');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    
 
}]);