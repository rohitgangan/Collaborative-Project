app.controller('LoginController', ['$http','$scope','$q','LoginService','$rootScope','$localStorage',function($http,$scope, $q, LoginService,$rootScope,$localStorage) {
$scope.form={};
$scope.currentUser = $localStorage.isLoggedIn
	var vm = this;
    vm.credentials = {};
   
    
   
    
    vm.login = function(){
       

    	var config = 
    			vm.user = "username="+vm.credentials.username+"&password="+vm.credentials.password;
    	        console.log(config)       
               $localStorage.isLoggedIn = vm.credentials.username
               console.log($localStorage.isLoggedIn);
        		LoginService.login(config);
    	        $rootScope.c_user = vm.credentials.username;
    	        
    	        
    	        
    };
    
    vm.logout = function(){
    	LoginService.logout();
    	$localStorage.$reset();
    	console.log($scope.currentUSer);
    }
    /*$scope.reset = function() {
        $scope.form = {};
        $scope.form.loginForm.$setPristine();
    };*/
}]);