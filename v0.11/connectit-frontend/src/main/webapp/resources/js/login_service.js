app.service('LoginService',['$http','$q','$rootScope','$location', function($http, $q, $rootScope,$location){
	

	this.login = function (config) {
		

        var res = $http({
            url: 'http://localhost:8082/connectit/perform_login',
            method: 'POST',
            data: config,
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
                'X-Requested-With': 'XMLHttpRequest'
              }
        
        
        });

        res.success(function(data, status, headers, config) {
        	
        	console.log(config);
        	$location.path('/home');
        	location.reload();
        	$rootScope.isLoggedIn='true';

        });
        res.error(function(data, status, headers, config) {
        	console.log("Error")

        });
        return res;
    };
    
    this.logout = function(){
    	 var res = $http({
             url: 'http://localhost:8082/connectit/perform_logout',
             method: 'GET',
         
         });
    	 res.success(function() {
         	
         	console.log("Succesfully logged out")
         	$location.path('/');
         	location.reload();

         });
         res.error(function() {
         	console.log("Error")

         });
         return res;
    	
    }
    
}]);