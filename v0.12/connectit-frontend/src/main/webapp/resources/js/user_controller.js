'use strict';
 
app.controller('UserController', ['$scope', 'UserService', '$q','$location', function($scope, UserService, $q,$location) {
    var self = this;
    self.user={userId:null,username:'',password:'',emailId:'',firstName:'',lastName:'',role:''};
    self.users=[];
 
    self.submit = submit;
    self.edit = edit;
    self.remove = remove;
    self.reset = reset;
    self.login = login;
    self.logout = logout;
 
    fetchAllUsers();
 
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
    
   function login(loginData){
	   var deferred = $q.defer();
 	   var loginData = "username="+self.user.username+"&password="+self.user.password;
	   console.log(loginData);
 	   UserService.login(loginData)
	   .then(
			   
			   function(d) {
	            	
	            	self.users = d;
	            	/*reset();*/
	                /*$location.path('/user');*/
	            	/*console.log('errorcode' + self.user.errorCode)
	                if(self.user.errorCode == "404"){
	                	alert("Invalid username or password")
	                	self.user.username = "";
	                	self.user.password = "";
	                }
	                else{
	                	$location.path('/');
	                }*/
	            },
	            function(errResponse){
	                console.error('Error while login User');
	                deferred.reject(errResponse);
	            }
	            );
	   return deferred.promise;
   }
   
   function logout(user){
       UserService.logout(user)
           .then(
           function(d) {
               self.users = d;
           },
           function(errResponse){
               console.error('Error while logout');
           }
       );
   }
   
  /* var loginCredientials = this;
   loginCredientials.details = {};
   loginCredientials.login = function(){
	   var config = loginCredientials.user = "username" +loginCredientials.details.uername + "&password"+loginCredientials.details.password;
	   console.log(config);
	   userService.login(config);
		   }*/
   
}]);