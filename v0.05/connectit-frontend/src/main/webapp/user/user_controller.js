'use strict';
 
app.controller('UserController', ['$scope', 'UserService', '$q','$location','$http', function($scope, UserService, $q,$location,$http) {
    var self = this;
    self.user={userId:null,username:'',password:'',emailId:'',firstName:'',lastName:'',role:''};
    self.users=[];
    self.userProfile=[];
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.submitUser = submitUser;
    fetchAllUsers();
    
    fetchMyProfile();
 
    function fetchAllUsers(){
        UserService.fetchAllUsers()
            .then(
            function(d) {
                self.users = d;
            },
            function(errResponse){
                console.error('Error while fetching users');
            }
        );
    }
    
    function fetchMyProfile(){
        UserService.fetchMyProfile()
            .then(
            function(d) {
                self.userProfile = d;
            },
            function(errResponse){
                console.error('Error while fetching users');
            }
        );
    }
    function createUser(user){
        UserService.createUser(user)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while creating user');
            }
        );
    }
 
    function updateUser(user, userId){
        UserService.updateUser(user, userId)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while updating user');
            }
        );
    }
 
    function deleteUser(userId){
        BlogService.deleteUser(userId)
            .then(
            fetchAllUsers,
            function(errResponse){
                console.error('Error while deleting user');
            }
        );
    }
 
    function submit() {
        if(self.user.userId===null){
            console.log('Saving New Blog', self.user);
            createUser(self.user);
        }else{
            updateUser(self.user, self.user.userId);
            console.log('user updated with id ', self.user.userId);
        }
        reset();
    }
 
    function edit(userId){
        console.log('id to be edited', userId);
        for(var i = 0; i < self.users.length; i++){
            if(self.users[i].userId === userId) {
                self.user = angular.copy(self.users[i]);
                break;
            }
        }
    }
 
    function remove(userId){
        console.log('id to be deleted', userId);
        if(self.user.userId === userId) {//clean form if the user to be deleted is shown there.
            reset();
        }
        deleteUser(userId);
    }
 
 
    function reset(){
        self.user={id:null,username:'',password:'',email:'',firstName:'',lastName:''};
        $scope.userForm.$setPristine(); //reset Form
    }
    
    function deleteUser(userId) {
        var deferred = $q.defer();
        $http.delete(REST_SERVICE_URI+userId)
            .then(
            function (response) {
                deferred.resolve(response.data);
            },
            function(errResponse){
                console.error('Error while deleting User');
                deferred.reject(errResponse);
            }
        );
        return deferred.promise;
    }
    
   function submitUser(){
	   var file = $scope.profileImage;
	   
	   var uploadUrl = 'http://localhost:8082/connectit/user/profileUpload';
	   console.log('file is:',file);
	   var fd = new FormData();
	   fd.append('file', file);
	   $http.post(uploadUrl, fd, {
	   transformRequest : angular.identity,
	   headers : {
	   'Content-Type' : undefined
	   }
	   }).success(function() {
	   console.log('success');
	   }).error(function() {
	   console.log('error');
	   });
   }
   
  
   
}]);