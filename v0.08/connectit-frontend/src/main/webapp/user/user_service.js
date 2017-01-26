'use strict';
 
app.factory('UserService', ['$http', '$q', '$rootScope',function($http, $q, $rootScope){
 
    var REST_SERVICE_URI = 'http://localhost:8082/connectit/user/';
    var MY_PROFILE_URI='http://localhost:8082/connectit/myProfile'
    	
    var factory = {
        fetchAllUsers: fetchAllUsers,
        createUser: createUser,
        updateUser:updateUser,
        deleteUser:deleteUser,
        login:login,
        logout:logout,
        fetchMyProfile:fetchMyProfile,
        getUserById:getUserById
    };
 
    return factory;
 
    function fetchAllUsers() {
        var deferred = $q.defer();
        $http.get(REST_SERVICE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching Users');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function fetchMyProfile() {
        var deferred = $q.defer();
        $http.get(MY_PROFILE_URI)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching User Profile');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
 
    function createUser(user) {
        var deferred = $q.defer();
        $http.post(REST_SERVICE_URI, user)
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
 
 
    function updateUser(user, userId) {
        var deferred = $q.defer();
        $http.put(REST_SERVICE_URI+userId, user)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while updating user');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function getUserById(userId){
    	var deferred = $q.defer();
        $http.get(REST_SERVICE_URI+userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while fetching User By Id');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
    function deleteUser(userId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+userId)
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
    
    function login(loginData){
    	
    	var deferred = $q.defer();
    	console.log(loginData);
    	var BASE_URI = 'http://localhost:8082/connectit/';
    	$http.post(BASE_URI+'perform_login', loginData,{
    		headers:{'Content-Type': undefined}
    	})
    	.then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while login user');
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
    }
    
    
    function logout(user){
    	var deferred = $q.defer();
    	var BASE_URI = 'http://localhost:8082/connectit/';
    	$http.get(BASE_URI+'perform_logout', user)
    	.then(
                function (response) {
                    deferred.resolve(response.data);
                },
                function(errResponse){
                    console.error('Error while login user');
                    deferred.reject(errResponse);
                }
            );
            return deferred.promise;
    }
 
}]);