app.factory('NewUser', ['$resource', function ($resource) {
    return $resource('http://localhost:8082/connectit/user/:uId', {uId: '@userId'},
	{
		updateUser: {method: 'PUT'}
	}
    );
}]);
app.controller('UserController', ['$scope', 'NewUser', function($scope, NewUser) {
    var ob = this;
    ob.users=[];
    ob.user = new NewUser(); 
    ob.fetchAllUsers = function(){
        ob.users = NewUser.query();
    };
    ob.fetchAllUsers();
    ob.addUser = function(){
	console.log('Inside save');
	if($scope.userForm.$valid) {
	  ob.user.$save(function(user){
	     console.log(user); 
	     ob.flag= 'created';	
	     ob.reset();	
	     ob.fetchAllUsers();
	  },
	  function(err){
	     console.log(err.status);
	     ob.flag='failed';
	  }
          );
        }
    }; 
    ob.editUser = function(uid){
	    console.log('Inside edit');
            ob.user = NewUser.get({ uId: uid}, function() {
	       ob.flag = 'edit'; 
	    });
    };    
    ob.updateUserDetail = function(){
	console.log('Inside update');
	if($scope.userForm.$valid) {
    	   ob.user.$updateUser(function(user){
    		console.log(user); 
		ob.updatedId = user.userId;
		ob.reset();
		ob.flag = 'updated'; 
    		ob.fetchAllUsers();
           });
	}
    };	
    ob.deleteUser = function(uid){
	    console.log('Inside delete');
	    ob.user = NewUser.delete({ uId: uid}, function() {
		ob.reset();  
		ob.flag = 'deleted';
    		ob.fetchAllUsers(); 
	    });
    };		
    ob.reset = function(){
    	ob.user = new NewUser();
        $scope.userForm.$setPristine();
    };	
    ob.cancelUpdate = function(uid){
	    ob.user = new NewUser();
	    ob.flag= '';	
   	    ob.fetchAllUsers();
    };    
}]);